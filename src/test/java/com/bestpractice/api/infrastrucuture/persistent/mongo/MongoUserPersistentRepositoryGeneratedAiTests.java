package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

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
import static org.mockito.ArgumentMatchers.any;

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
        when(mongoDatabase.getCollection("users", MongoUserEntity.class)).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewIdGeneratesValidObjectIdString() {
        String newId = repository.newId();
        assertThat(newId).isNotNull();
        assertThat(ObjectId.isValid(newId)).isTrue();
    }

    @Test
    void testFindByEmailReturnsUserWhenFound() {
        String email = "test@example.com";
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "username", email, "password");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        User result = repository.findByEmail(email);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username");
    }

    @Test
    void testFindByEmailThrowsInternalServerErrorWhenExceptionOccurs() {
        when(collection.find(any(Bson.class))).thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() -> repository.findByEmail("error@example.com"))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void testFindByIdReturnsUserWhenFound() {
        String id = new ObjectId().toString();
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(id), "username", "email@example.com", "password");
        FindIterable<MongoUserEntity> iterable = mock(FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any(Bson.class))).thenReturn(iterable);

        User result = repository.findById(id);

        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("username");
    }

    @Test
    void testInsertInsertsUserSuccessfully() {
        User user = new User(new ObjectId().toString(), "username", "email@example.com", "password");

        User result = repository.insert(user);

        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
        assertThat(result).isEqualTo(user);
    }

    @Test
    void testInsertThrowsInternalServerErrorOnException() {
        User user = new User(new ObjectId().toString(), "username", "email@example.com", "password");
        doThrow(new RuntimeException("Insert failed")).when(collection).insertOne(any(MongoUserEntity.class));

        assertThatThrownBy(() -> repository.insert(user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testReplaceUpdatesUserSuccessfully() {
        User user = new User(new ObjectId().toString(), "username", "email@example.com", "password");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class))).thenReturn(updateResult);

        User result = repository.replace(user.getId(), user);

        assertThat(result).isEqualTo(user);
        verify(collection, times(1)).replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class));
    }

    @Test
    void testReplaceThrowsInternalServerErrorOnException() {
        User user = new User(new ObjectId().toString(), "username", "email@example.com", "password");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("Replace failed"));

        assertThatThrownBy(() -> repository.replace(user.getId(), user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void testRemoveByIdReturnsTrueWhenAcknowledged() {
        String id = new ObjectId().toString();
        DeleteResult deleteResult = mock(DeleteResult.class);
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);

        boolean result = repository.removeById(id);

        assertThat(result).isTrue();
        verify(collection, times(1)).deleteOne(any(Bson.class));
    }

    @Test
    void testRemoveByIdThrowsInternalServerErrorOnException() {
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("Delete failed"));

        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
