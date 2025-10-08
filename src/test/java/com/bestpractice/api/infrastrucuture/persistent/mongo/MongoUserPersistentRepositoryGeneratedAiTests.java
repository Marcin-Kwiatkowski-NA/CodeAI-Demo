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

import java.util.Date;

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
        when(mongoDatabase.getCollection("users", MongoUserEntity.class)).thenReturn(collection);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    public void testNewIdGeneratesNonNullString() {
        // GIVEN - repository initialized

        // WHEN - generating new id
        String id = repository.newId();

        // THEN - id should not be null and should be a valid ObjectId string
        assertNotNull(id);
        assertDoesNotThrow(() -> new ObjectId(id));
    }

    @Test
    public void testFindByEmailReturnsUser() {
        // GIVEN - mock collection returns a MongoUserEntity
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(new ObjectId());
        entity.setEmail("test@example.com");
        entity.setUsername("username");
        entity.setPassword("pass");
        entity.setCreatedAt(new Date());
        when(collection.find(any())).thenReturn(new com.bestpractice.api.infrastrucuture.persistent.mongo.FakeFindIterable<>(entity));

        // WHEN - finding by email
        User result = repository.findByEmail("test@example.com");

        // THEN - result should match expected values
        assertNotNull(result);
        assertEquals("username", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void testFindByEmailThrowsInternalServerErrorOnException() {
        // GIVEN - mock collection throws exception
        when(collection.find(any())).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN - should throw InternalServerError
        assertThrows(InternalServerError.class, () -> repository.findByEmail("test@example.com"));
    }

    @Test
    public void testFindByIdReturnsUser() {
        // GIVEN - mock collection returns a MongoUserEntity
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(new ObjectId());
        entity.setEmail("test@example.com");
        entity.setUsername("username");
        entity.setPassword("pass");
        entity.setCreatedAt(new Date());
        when(collection.find(any())).thenReturn(new com.bestpractice.api.infrastrucuture.persistent.mongo.FakeFindIterable<>(entity));

        // WHEN - finding by id
        User result = repository.findById(entity.getId().toString());

        // THEN - result should match expected values
        assertNotNull(result);
        assertEquals("username", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void testInsertInsertsUser() {
        // GIVEN - a user to insert
        User user = new User("id", "username", "email@example.com", "pass");

        // WHEN - inserting user
        User result = repository.insert(user);

        // THEN - returned user should be same as inserted
        assertEquals(user, result);
        verify(collection, times(1)).insertOne(any(MongoUserEntity.class));
    }

    @Test
    public void testInsertThrowsInternalServerErrorOnException() {
        // GIVEN - mock collection throws exception
        doThrow(new RuntimeException("DB error")).when(collection).insertOne(any());

        // WHEN & THEN - should throw InternalServerError
        assertThrows(InternalServerError.class, () -> repository.insert(new User("id", "username", "email@example.com", "pass")));
    }
}