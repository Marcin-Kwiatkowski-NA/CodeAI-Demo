package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MongoUserPersistentRepositoryGeneratedAiTests {

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
        when(mongoDatabase.getCollection(any(), any())).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewId_ShouldReturnValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull();
        assertThatCode(() -> new ObjectId(id)).doesNotThrowAnyException();
    }

    @Test
    void testFindByEmail_ShouldReturnUser_WhenFound() {
        // GIVEN
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("1", "user", "email@test.com", "pass");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("user");
    }

    @Test
    void testFindByEmail_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findByEmail("email@test.com"))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testFindById_ShouldReturnUser_WhenFound() {
        // GIVEN
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("1", "user", "email@test.com", "pass");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(collection.find(any(Bson.class))).thenReturn(iterable);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("email@test.com");
    }

    @Test
    void testFindById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testInsert_ShouldInsertUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testInsert_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testReplace_ShouldReplaceUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenReturn(updateResult);

        // WHEN
        User result = repository.replace(user.getId(), user);

        // THEN
        assertThat(result).isEqualTo(user);
        verify(collection, times(1)).replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class));
    }

    @Test
    void testReplace_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace failed"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testRemoveById_ShouldReturnTrue_WhenAcknowledged() {
        // GIVEN
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(new ObjectId().toString());

        // THEN
        assertThat(result).isTrue();
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class);
    }
}