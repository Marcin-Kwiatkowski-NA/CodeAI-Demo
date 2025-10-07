package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
        when(mongoDatabase.getCollection(any(), eq(MongoUserEntity.class))).thenReturn(collection);
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
        MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "user", "email@test.com", "pass");
        when(collection.find(any())).thenReturn(new TestFindIterable(entity));

        // WHEN
        User result = repository.findByEmail("email@test.com");

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    public void testFindByEmailThrowsInternalServerErrorOnException() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findByEmail("email@test.com"));
    }

    @Test
    public void testFindByIdReturnsUser() {
        // GIVEN
        MongoUserEntity entity = new MongoUserEntity(new ObjectId(), "user", "email@test.com", "pass");
        when(collection.find(any())).thenReturn(new TestFindIterable(entity));

        // WHEN
        User result = repository.findById(new ObjectId().toString());

        // THEN
        assertNotNull(result);
        assertEquals("user", result.getUsername());
    }

    @Test
    public void testFindByIdThrowsInternalServerErrorOnException() {
        // GIVEN
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.findById(new ObjectId().toString()));
    }

    @Test
    public void testInsertSuccess() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");

        // WHEN
        User result = repository.insert(user);

        // THEN
        assertEquals(user, result);
        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
    }

    @Test
    public void testInsertThrowsInternalServerErrorOnException() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> repository.insert(user));
    }

    @Test
    public void testReplaceSuccess() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "user", "email@test.com", "pass");
        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(collection.replaceOne(any(), any(), any())).thenReturn(updateResult);

        // WHEN
        User result = repository.replace(user.getId(), user);

        // THEN
        assertEquals(user, result);
    }