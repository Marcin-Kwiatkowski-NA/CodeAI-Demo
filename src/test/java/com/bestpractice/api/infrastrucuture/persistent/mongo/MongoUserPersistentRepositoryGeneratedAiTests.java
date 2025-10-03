package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;
    private MongoUserPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        mongoClient = mock(MongoClient.class);
        mongoDatabase = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(mongoDatabase.getCollection(anyString(), eq(MongoUserEntity.class))).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesValidObjectIdString() {
        // GIVEN

        // WHEN
        String id = repository.newId();

        // THEN
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindByEmailReturnsUser() {
        // GIVEN
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("1", "username", "email@example.com", "password");
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);
        var iterable = mock(com.mongodb.client.FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        User result = repository.findByEmail("email@example.com");

        // THEN
        assertNotNull(result);
        assertEquals(expectedUser.getEmail(), result.getEmail());
    }

    @Test
    public void testFindByEmailThrowsInternalServerErrorOnException() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@example.com"));
    }

    @Test
    public void testFindByIdReturnsUser() {
        // GIVEN
        MongoUserEntity mongoUserEntity = mock(MongoUserEntity.class);
        User expectedUser = new User("507f1f77bcf86cd799439011", "username", "email@example.com", "password");
        when(mongoUserEntity.convertTo()).thenReturn(expectedUser);
        var iterable = mock(com.mongodb.client.FindIterable.class);
        when(iterable.first()).thenReturn(mongoUserEntity);
        when(collection.find(any())).thenReturn(iterable);

        // WHEN
        User result = repository.findById("507f1f77bcf86cd799439011");

        // THEN
        assertNotNull(result);
        assertEquals(expectedUser.getId(), result.getId());
    }

    @Test
    public void testFindByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById("507f1f77bcf86cd799439011"));
    }

    @Test
    public void testInsertInsertsUser() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "password");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertEquals(user, result);
        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
    }

    @Test
    public void testInsertThrowsInternalServerErrorOnException() {
        // GIVEN
        User user = new User("1", "username", "email@example.com", "password");
        doThrow(new RuntimeException("Insert error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }
