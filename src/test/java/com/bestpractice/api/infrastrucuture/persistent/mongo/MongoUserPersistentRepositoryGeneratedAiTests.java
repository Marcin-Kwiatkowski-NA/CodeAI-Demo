package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.bson.conversions.Bson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    @InjectMocks
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection("users", MongoUserEntity.class)).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void newId_ShouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).hasSize(24); // ObjectId length
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "testUser", email, "password");
        when(collection.find(any(Bson.class))).thenReturn(Mockito.mock(Iterable.class));
        when(collection.find(any(Bson.class)).first()).thenReturn(mongoUserEntity);

        // WHEN
        User user = repository.findByEmail(email);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void findByEmail_ShouldThrowInternalServerError_WhenEmailNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        when(collection.find(any(Bson.class)).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(id), "testUser", "test@example.com", "password");
        when(collection.find(any(Bson.class))).thenReturn(Mockito.mock(Iterable.class));
        when(collection.find(any(Bson.class)).first()).thenReturn(mongoUserEntity);

        // WHEN
        User user = repository.findById(id);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void findById_ShouldThrowInternalServerError_WhenIdNotFound() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class)).first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        doNothing().when(collection).insertOne(mongoUserEntity);

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void insert_ShouldThrowInternalServerError_WhenInsertionFails() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "testUser", "test@example.com", "password");
        doThrow(new RuntimeException("Insertion failed")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(updateResult);

        // WHEN
        User replacedUser = repository.replace(id, user);

        // THEN
        assertThat(replacedUser).isNotNull();
        assertThat(replacedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void replace_ShouldThrowInternalServerError_WhenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword");
        doThrow(new RuntimeException("Replace failed")).when(collection).replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean isRemoved = repository.removeById(id);

        // THEN
        assertThat(isRemoved).isTrue();
    }

    @Test
    void removeById_ShouldThrowInternalServerError_WhenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        doThrow(new RuntimeException("Delete failed")).when(collection).deleteOne(any(Bson.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}