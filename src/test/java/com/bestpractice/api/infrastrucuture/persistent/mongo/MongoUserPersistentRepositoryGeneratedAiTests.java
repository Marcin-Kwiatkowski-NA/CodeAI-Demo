package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.ReplaceOptions;
import org.assertj.core.api.Assertions;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    @Mock
    private FindIterable<MongoUserEntity> findIterable;

    @Mock
    private UpdateResult updateResult;

    @Mock
    private DeleteResult deleteResult;

    @InjectMocks
    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(any(String.class), any())).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewId_ShouldReturnNonNullString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        Assertions.assertThat(id).isNotNull();
        Assertions.assertThat(ObjectId.isValid(id)).isTrue();
    }

    @Test
    void testFindByEmail_ShouldReturnUser_WhenFound() {
        // GIVEN
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "user1", "email@test.com", "pass");
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(mongoUserEntity);

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getUsername()).isEqualTo("user1");
    }

    @Test
    void testFindByEmail_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        Assertions.assertThatThrownBy(() -> repository.findByEmail("email@test.com"))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testFindById_ShouldReturnUser_WhenFound() {
        // GIVEN
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "user2", "email2@test.com", "pass2");
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(mongoUserEntity);

        // WHEN
        User result = repository.findById(mongoUserEntity.getId().toString());

        // THEN
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getUsername()).isEqualTo("user2");
    }

    @Test
    void testFindById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        Assertions.assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testInsert_ShouldReturnUser_WhenSuccess() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user3", "email3@test.com", "pass3");

        // WHEN
        User result = repository.insert(user);

        // THEN
        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    void testInsert_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user4", "email4@test.com", "pass4");
        doThrow(new RuntimeException("Insert error")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN THEN
        Assertions.assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testReplace_ShouldReturnUser_WhenAcknowledged() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user5", "email5@test.com", "pass5");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenReturn(updateResult);
        when(updateResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        User result = repository.replace(user.getId(), user);

        // THEN
        Assertions.assertThat(result).isEqualTo(user);
    }

    @Test
    void testReplace_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user6", "email6@test.com", "pass6");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace error"));

        // WHEN THEN
        Assertions.assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(InternalServerError.class);
    }

    @Test
    void testRemoveById_ShouldReturnTrue_WhenAcknowledged() {
        // GIVEN
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);
        when(deleteResult.wasAcknowledged()).thenReturn(true);

        // WHEN
        boolean result = repository.removeById(new ObjectId().toString());

        // THEN
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void testRemoveById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete error"));

        // WHEN THEN
        Assertions.assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class);
    }
}
