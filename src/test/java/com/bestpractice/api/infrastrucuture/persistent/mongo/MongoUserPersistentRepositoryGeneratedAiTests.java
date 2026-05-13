package com.bestpractice.api.infrastrucuture.persistent.mongo;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.FindIterable;
import org.bson.types.ObjectId;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
public class MongoUserPersistentRepositoryGeneratedAiTests {

    @Mock
    private MongoClient mongoClient;

    @Mock
    private MongoDatabase mongoDatabase;

    @Mock
    private MongoCollection<MongoUserEntity> collection;

    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String newId = repository.newId();

        // THEN
        assertThat(newId).isNotNull();
        assertThatCode(() -> new ObjectId(newId)).doesNotThrowAnyException();
    }

    @Test
    void testFindByEmailReturnsUserWhenFound() {
        // GIVEN
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "user1", "email@test.com", "pass");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("user1");
    }

    @Test
    void testFindByEmailThrowsInternalServerErrorWhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findByEmail("email@test.com"))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testFindByIdReturnsUserWhenFound() {
        // GIVEN
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "user2", "email2@test.com", "pass2");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        // WHEN
        User result = repository.findById(mongoUserEntity.getId().toString());

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("user2");
    }

    @Test
    void testFindByIdThrowsInternalServerErrorWhenExceptionOccurs() {
        // GIVEN
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.findById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testInsertInsertsUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user3", "email3@test.com", "pass3");

        // WHEN
        User result = repository.insert(user);

        // THEN
        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testInsertThrowsInternalServerErrorOnException() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user4", "email4@test.com", "pass4");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoUserEntity.class));

        // WHEN THEN
        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplaceUpdatesUserSuccessfully() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user5", "email5@test.com", "pass5");
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
    void testReplaceThrowsInternalServerErrorOnException() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user6", "email6@test.com", "pass6");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace failed"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById(new ObjectId().toString());

        // THEN
        assertThat(result).isTrue();
        verify(collection, times(1)).deleteOne(any(Bson.class));
    }

    @Test
    void testRemoveByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));

        // WHEN THEN
        assertThatThrownBy(() -> repository.removeById(new ObjectId().toString()))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}