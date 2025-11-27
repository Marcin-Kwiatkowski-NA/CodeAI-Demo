package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoUserPersistentRepository repository;

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        FindIterable<MongoUserEntity> findIterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(mongoUserEntity);
        User expectedUser = new User("1", "username", email, "password");
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail(email);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void findByEmail_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String email = "test@example.com";
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        FindIterable<MongoUserEntity> findIterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(mongoUserEntity);
        User expectedUser = new User(id, "username", "email", "password");
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User result = repository.findById(id);

        // THEN
        assertThat(result).isEqualTo(expectedUser);
    }

    @Test
    void findById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("Database error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User("1", "username", "email", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // WHEN
        User result = repository.insert(user);

        // THEN
        verify(collection).insertOne(mongoUserEntity);
        assertThat(result).isEqualTo(user);
    }

    @Test
    void insert_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        User user = new User("1", "username", "email", "password");
        doThrow(new RuntimeException("Insert error")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void replace_ShouldReplaceUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "username", "email", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        UpdateResult updateResult = mock(UpdateResult.class);
        when(collection.replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions))).thenReturn(updateResult);
        when(updateResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        User result = repository.replace(id, user);

        // THEN
        verify(collection).replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions));
        assertThat(result).isEqualTo(user);
    }

    @Test
    void replace_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        User user = new User(id, "username", "email", "password");
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        ReplaceOptions replaceOptions = new ReplaceOptions().upsert(true);
        doThrow(new RuntimeException("Replace error")).when(collection).replaceOne(any(Bson.class), eq(mongoUserEntity), eq(replaceOptions));

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(id, user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldRemoveUserSuccessfully() {
        // GIVEN
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);
        when(deleteResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById(id);

        // THEN
        verify(collection).deleteOne(any(Bson.class));
        assertThat(result).isTrue();
    }

    @Test
    void removeById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        doThrow(new RuntimeException("Delete error")).when(collection).deleteOne(any(Bson.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
