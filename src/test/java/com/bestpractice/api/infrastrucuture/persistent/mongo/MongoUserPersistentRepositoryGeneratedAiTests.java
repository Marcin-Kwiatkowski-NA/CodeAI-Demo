package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

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
        MockitoAnnotations.openMocks(this);
        when(mongoDatabase.getCollection(eq("users"), eq(MongoUserEntity.class))).thenReturn(collection);
    }

    @Test
    void newId_shouldGenerateNewObjectId() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThat(newId).hasSize(24); // ObjectId length
    }

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        // GIVEN
        String email = "test@example.com";
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("123", "testUser", email, "password");
        FindIterable<MongoUserEntity> findIterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User user = repository.findByEmail(email);

        // THEN
        assertThat(user).isEqualTo(expectedUser);
    }

    @Test
    void findByEmail_shouldThrowInternalServerError_whenEmailNotFound() {
        // GIVEN
        String email = "nonexistent@example.com";
        FindIterable<MongoUserEntity> findIterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_shouldReturnUser_whenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User(id, "testUser", "test@example.com", "password");
        FindIterable<MongoUserEntity> findIterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User user = repository.findById(id);

        // THEN
        assertThat(user).isEqualTo(expectedUser);
    }

    @Test
    void findById_shouldThrowInternalServerError_whenIdNotFound() {
        // GIVEN
        String id = new ObjectId().toString();
        FindIterable<MongoUserEntity> findIterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(null);

        // WHEN & THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_shouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("123", "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
       // THEN
        verify(collection).insertOne(eq(mongoUserEntity));
        assertThat(insertedUser).isEqualTo(user);
    }

    @Test
    void insert_shouldThrowInternalServerError_whenInsertFails() {
        // GIVEN
        User user = new User("123", "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_shouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(updateResult);

        // WHEN
        User replacedUser = repository.replace(id, user);

        // THEN
        verify(collection).replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions));
        assertThat(replacedUser).isEqualTo(user);
    }

    @Test
    void replace_shouldThrowInternalServerError_whenReplaceFails() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "testUser", "test@example.com", "password");
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        doThrow(new RuntimeException("Replace failed")).when(collection).replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_shouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean isRemoved = repository.removeById(id);

        // THEN
        verify(collection).deleteOne(any(Bson.class));
        assertThat(isRemoved).isTrue();
    }

    @Test
    void removeById_shouldThrowInternalServerError_whenRemoveFails() {
        // GIVEN
        String id = new ObjectId().toString();
        doThrow(new RuntimeException("Delete failed")).when(collection).deleteOne(any(Bson.class));

        // WHEN & THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
