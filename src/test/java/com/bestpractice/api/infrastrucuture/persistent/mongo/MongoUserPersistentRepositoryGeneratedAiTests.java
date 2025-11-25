package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        Mockito.when(mongoDatabase.getCollection(eq("users"), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void newId_shouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).matches("^[a-f0-9]{24}$");
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mockEntity = new MongoUserEntity(new ObjectId(), "testUser", email, "password123");
        FindIterable<MongoUserEntity> mockFindIterable = Mockito.mock(FindIterable.class);
        Mockito.when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(mockFindIterable);
        Mockito.when(mockFindIterable.first()).thenReturn(mockEntity);

        // WHEN
        User user = repository.findByEmail(email);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void findByEmail_shouldThrowInternalServerError_whenEmailDoesNotExist() {
        // GIVEN
        String email = "nonexistent@example.com";
        FindIterable<MongoUserEntity> mockFindIterable = Mockito.mock(FindIterable.class);
        Mockito.when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(mockFindIterable);
        Mockito.when(mockFindIterable.first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mockEntity = new MongoUserEntity(new ObjectId(id), "testUser", "test@example.com", "password123");
        FindIterable<MongoUserEntity> mockFindIterable = Mockito.mock(FindIterable.class);
        Mockito.when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(mockFindIterable);
        Mockito.when(mockFindIterable.first()).thenReturn(mockEntity);

        // WHEN
        User user = repository.findById(id);

        // THEN
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void findById_shouldThrowInternalServerError_whenIdDoesNotExist() {
        // GIVEN
        String id = new ObjectId().toString();
        FindIterable<MongoUserEntity> mockFindIterable = Mockito.mock(FindIterable.class);
        Mockito.when(collection.find(any(org.bson.conversions.Bson.class))).thenReturn(mockFindIterable);
        Mockito.when(mockFindIterable.first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "testUser", "test@example.com", "password123");
        Mockito.doNothing().when(collection).insertOne(any());

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertThat(insertedUser).isNotNull();
        assertThat(insertedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void insert_shouldThrowInternalServerError_whenInsertionFails() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "testUser", "test@example.com", "password123");
        Mockito.doThrow(new RuntimeException("Insertion failed")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult mockUpdateResult = Mockito.mock(UpdateResult.class);
        Mockito.when(mockUpdateResult.wasAcknowledged()).thenReturn(true);
        Mockito.when(collection.replaceOne(any(org.bson.conversions.Bson.class), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(mockUpdateResult);

        // WHEN
        User replacedUser = repository.replace(id, user);

        // THEN
        assertThat(replacedUser).isNotNull();
        assertThat(replacedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void replace_shouldThrowInternalServerError_whenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "updatedUser", "updated@example.com", "updatedPassword");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        Mockito.doThrow(new RuntimeException("Replace failed")).when(collection).replaceOne(any(org.bson.conversions.Bson.class), eq(mongoUserEntity), eq(replaceOptions));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_shouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult mockDeleteResult = Mockito.mock(DeleteResult.class);
        Mockito.when(mockDeleteResult.wasAcknowledged()).thenReturn(true);
        Mockito.when(collection.deleteOne(any(org.bson.conversions.Bson.class))).thenReturn(mockDeleteResult);

        // WHEN
        boolean isRemoved = repository.removeById(id);

        // THEN
        assertThat(isRemoved).isTrue();
    }

    @Test
    void removeById_shouldThrowInternalServerError_whenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        Mockito.doThrow(new RuntimeException("Delete failed")).when(collection).deleteOne(any(org.bson.conversions.Bson.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
