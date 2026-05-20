package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.ReplaceOptions;
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
        when(mongoDatabase.getCollection(any(String.class), any(Class.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void givenNothing_whenNewId_thenReturnNonNullId() {
        String id = repository.newId();
        assertThat(id).isNotNull();
        assertThat(ObjectId.isValid(id)).isTrue();
    }

    @Test
    void givenValidEmail_whenFindByEmail_thenReturnUser() {
        MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "user", "email@test.com", "pass");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        User result = repository.findByEmail("email@test.com");

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("user");
    }

    @Test
    void givenException_whenFindByEmail_thenThrowInternalServerError() {
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));
        assertThatThrownBy(() -> repository.findByEmail("email@test.com"))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void givenValidId_whenFindById_thenReturnUser() {
        MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "user", "email@test.com", "pass");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(entity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        User result = repository.findById(entity.getId().toString());

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("user");
    }

    @Test
    void givenException_whenFindById_thenThrowInternalServerError() {
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));
        assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void givenValidUser_whenInsert_thenReturnSameUser() {
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        User result = repository.insert(user);
        verify(collection, atLeastOnce()).insertOne(any(MongoUserEntity.class));
        assertThat(result).isEqualTo(user);
    }

    @Test
    void givenInsertThrowsException_whenInsert_thenThrowInternalServerError() {
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoUserEntity.class));
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void givenValidUser_whenReplace_thenReturnUser() {
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);

        User result = repository.replace(user.getId(), user);
        assertThat(result).isEqualTo(user);
    }

    @Test
    void givenReplaceNotAcknowledged_whenReplace_thenThrowInternalServerError() {
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(false);
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);

        assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void givenException_whenReplace_thenThrowInternalServerError() {
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class))).thenThrow(new RuntimeException("Replace failed"));
        assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void givenValidId_whenRemoveById_thenReturnTrue() {
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        boolean result = repository.removeById(new ObjectId().toString());
        assertThat(result).isTrue();
    }

    @Test
    void givenException_whenRemoveById_thenThrowInternalServerError() {
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));
        assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class);
    }
}