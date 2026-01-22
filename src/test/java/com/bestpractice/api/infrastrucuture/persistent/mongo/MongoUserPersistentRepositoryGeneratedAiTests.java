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
import com.mongodb.client.FindIterable;
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
import org.mockito.ArgumentCaptor;
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
    private DeleteResult deleteResult;

    @Mock
    private UpdateResult updateResult;

    private MongoUserPersistentRepository repository;

    @BeforeEach
    void setUp() {
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void testNewIdReturnsValidObjectIdString() {
        // GIVEN
        // no preconditions

        // WHEN
        String id = repository.newId();

        // THEN
        assertThat(id).isNotNull().isNotEmpty();
        assertThat(new ObjectId(id)).isNotNull();
    }

    @Test
    void testFindByEmailReturnsUserWhenEntityFound() {
        // GIVEN
        ObjectId objId = new ObjectId();
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(objId);
        entity.setUsername("john");
        entity.setEmail("john@example.com");
        entity.setPassword("secret");
        when(collection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);

        // WHEN
        User result = repository.findByEmail("john@example.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(objId.toString());
        assertThat(result.getUsername()).isEqualTo("john");
        assertThat(result.getEmail()).isEqualTo("john@example.com");
        assertThat(result.getPassword()).isEqualTo("secret");
        verify(collection).find(any());
    }

    @Test
    void testFindByEmailThrowsInternalServerErrorWhenEntityNotFound() {
        // GIVEN
        when(collection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(null);

        // WHEN
        Throwable thrown = catchThrowable(() -> repository.findByEmail("missing@example.com"));

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
    }

    @Test
    void testFindByIdReturnsUserWhenEntityFound() {
        // GIVEN
        ObjectId objId = new ObjectId();
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(objId);
        entity.setUsername("alice");
        entity.setEmail("alice@example.com");
        entity.setPassword("pass");
        when(collection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(entity);

        // WHEN
        User result = repository.findById("alice@example.com");

        // THEN
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(objId.toString());
        assertThat(result.getUsername()).isEqualTo("alice");
        assertThat(result.getEmail()).isEqualTo("alice@example.com");
        assertThat(result.getPassword()).isEqualTo("pass");
        verify(collection).find(any());
    }

    @Test
    void testFindByIdThrowsInternalServerErrorWhenEntityNotFound() {
        // GIVEN
        when(collection.find(any())).thenReturn(findIterable);
        when(findIterable.first()).thenReturn(null);

        // WHEN
        Throwable thrown = catchThrowable(() -> repository.findById("nonexistent@example.com"));

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
    }

    @Test
    void testInsertCallsInsertOneAndReturnsUser() {
        // GIVEN
        User user = new User();
        user.setId("dummyId");
        user.setUsername("bob");
        user.setEmail("bob@example.com");
        user.setPassword("pwd");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertThat(result).isSameAs(user);
        verify(collection).insertOne(any(MongoUserEntity.class));
    }

    @Test
    void testReplaceCallsReplaceOneWithUpsertAndReturnsUser() {
        // GIVEN
        User user = new User();
        user.setId("replaceId");
        user.setUsername("charlie");
        user.setEmail("charlie@example.com");
        user.setPassword("pwd123");
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        ArgumentCaptor<Bson> filterCaptor = ArgumentCaptor.forClass(Bson.class);
        ArgumentCaptor<MongoUserEntity> entityCaptor = ArgumentCaptor.forClass(MongoUserEntity.class);
        ArgumentCaptor<ReplaceOptions> optionsCaptor = ArgumentCaptor.forClass(ReplaceOptions.class);

        // WHEN
        User result = repository.replace(user);

        // THEN
        assertThat(result).isSameAs(user);
        verify(collection).replaceOne(filterCaptor.capture(), entityCaptor.capture(), optionsCaptor.capture());
        Bson filter = filterCaptor.getValue();
        MongoUserEntity capturedEntity = entityCaptor.getValue();
        ReplaceOptions options = optionsCaptor.getValue();
        assertThat(filter.toString()).contains("replaceId");
        assertThat(capturedEntity.getId()).isEqualTo(new ObjectId("replaceId"));
        assertThat(capturedEntity.getUsername()).isEqualTo("charlie");
        assertThat(capturedEntity.getEmail()).isEqualTo("charlie@example.com");
        assertThat(capturedEntity.getPassword()).isEqualTo("pwd123");
        assertThat(options.isUpsert()).isTrue();
    }

    @Test
    void testReplaceThrowsInternalServerErrorWhenException() {
        // GIVEN
        User user = new User();
        user.setId("errorId");
        user.setUsername("error");
        when(collection.replaceOne(any(), any(), any())).thenThrow(new RuntimeException("db error"));

        // WHEN
        Throwable thrown = catchThrowable(() -> repository.replace(user));

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
    }

    @Test
    void testRemoveByIdReturnsTrueWhenAcknowledged() {
        // GIVEN
        when(deleteResult.wasAcknowledged()).thenReturn(true);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById("someId");

        // THEN
        assertThat(result).isTrue();
        verify(collection).deleteOne(any());
    }

    @Test
    void testRemoveByIdReturnsFalseWhenNotAcknowledged() {
        // GIVEN
        when(deleteResult.wasAcknowledged()).thenReturn(false);
        when(collection.deleteOne(any())).thenReturn(deleteResult);

        // WHEN
        boolean result = repository.removeById("someId");

        // THEN
        assertThat(result).isFalse();
        verify(collection).deleteOne(any());
    }

    @Test
    void testRemoveByIdThrowsInternalServerErrorWhenException() {
        // GIVEN
        when(collection.deleteOne(any())).thenThrow(new RuntimeException("delete error"));

        // WHEN
        Throwable thrown = catchThrowable(() -> repository.removeById("exception@example.com"));

        // THEN
        assertThat(thrown).isInstanceOf(InternalServerError.class);
    }
}
