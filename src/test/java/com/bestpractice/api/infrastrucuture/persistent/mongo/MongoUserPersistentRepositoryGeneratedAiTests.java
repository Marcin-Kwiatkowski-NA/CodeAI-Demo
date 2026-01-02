package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;


@ExtendWith(MockitoExtension.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

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

    @Captor
    private ArgumentCaptor<Bson> bsonCaptor;

    @Captor
    private ArgumentCaptor<ReplaceOptions> replaceOptionsCaptor;

    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(eq("users"), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void newId_ShouldReturnValidObjectIdString() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertThat(id).isNotNull();
        assertThat(ObjectId.isValid(id)).isTrue();
    }

    @Test
    void findByEmail_ShouldReturnUser_WhenEmailExists() {
        // GIVEN
        String email = "user@example.com";
        MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "user", email, "password");
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);
        // WHEN
        User result = repository.findByEmail(email);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getUsername()).isEqualTo("user");
        verify(collection).find(bsonCaptor.capture());
        assertThat(bsonCaptor.getValue()).isInstanceOf(Filters.class);
    }

    @Test
    void findByEmail_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String email = "user@example.com";
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenThrow(new RuntimeException("db error"));
        // WHEN / THEN
        assertThatThrownBy(() -> repository.findByEmail(email))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void findById_ShouldReturnUser_WhenIdExists() {
        // GIVEN
        String id = new ObjectId().toString();
        MongoUserEntity entity = new MongoUserEntity(new ObjectId(id), "user", "user@example.com", "password");
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);
        // WHEN
        User result = repository.findById(id);
        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        verify(collection).find(bsonCaptor.capture());
        assertThat(bsonCaptor.getValue()).isInstanceOf(Filters.class);
    }

    @Test
    void findById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.find(any(Bson.class))).thenReturn(findIterable);
        when(findIterable.first()).thenThrow(new RuntimeException("db error"));
        // WHEN / THEN
        assertThatThrownBy(() -> repository.findById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to get detail from database");
    }

    @Test
    void insert_ShouldPersistAndReturnUser() {
        // GIVEN
        User user = new User("1", "user", "user@example.com", "password");
        doNothing().when(collection).insertOne(any(MongoUserEntity.class));
        // WHEN
        User result = repository.insert(user);
        // THEN
        assertThat(result).isSameAs(user);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void replace_ShouldUpsertAndReturnUser() {
        // GIVEN
        User user = new User("1", "user", "user@example.com", "password");
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenReturn(updateResult);
        // WHEN
        User result = repository.replace("1", user);
        // THEN
        assertThat(result).isSameAs(user);
        verify(collection).replaceOne(bsonCaptor.capture(), any(MongoUserEntity.class), replaceOptionsCaptor.capture());
        assertThat(replaceOptionsCaptor.getValue().isUpsert()).isTrue();
        assertThat(bsonCaptor.getValue()).isInstanceOf(Filters.class);
    }

    @Test
    void replace_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        User user = new User("1", "user", "user@example.com", "password");
        when(collection.replaceOne(any(Bson.class), any(MongoUserEntity.class), any(ReplaceOptions.class)))
                .thenThrow(new RuntimeException("db error"));
        // WHEN / THEN
        assertThatThrownBy(() -> repository.replace("1", user))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to insert");
    }

    @Test
    void removeById_ShouldReturnTrue_WhenAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);
        // WHEN
        boolean result = repository.removeById(id);
        // THEN
        assertThat(result).isTrue();
        verify(collection).deleteOne(bsonCaptor.capture());
        assertThat(bsonCaptor.getValue()).isInstanceOf(Filters.class);
    }

    @Test
    void removeById_ShouldReturnFalse_WhenNotAcknowledged() {
        // GIVEN
        String id = new ObjectId().toString();
        when(deleteResult.wasAcknowledged()).thenReturn(false);
        when(collection.deleteOne(any(Bson.class))).thenReturn(deleteResult);
        // WHEN
        boolean result = repository.removeById(id);
        // THEN
        assertThat(result).isFalse();
    }

    @Test
    void removeById_ShouldThrowInternalServerError_WhenExceptionOccurs() {
        // GIVEN
        String id = new ObjectId().toString();
        when(collection.deleteOne(any(Bson.class))).thenThrow(new RuntimeException("db error"));
        // WHEN / THEN
        assertThatThrownBy(() -> repository.removeById(id))
                .isInstanceOf(InternalServerError.class)
                .hasMessageContaining("Failed to delete");
    }
}
