package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Objects;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAssertions.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoUserPersistentRepository repository;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    @BeforeEach
    void setUp() {
        mongoClient = new MongoClient();
        mongoDatabase = new MongoDatabase(mongoClient);
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
    }

    @Test
    void newId_returns_valid_object_id() {
        String id = repository.newId();
        assertNotNull(id, "New ID should not be null");
        assertTrue(id.matches("\\b[a-fA-F0-9]{24}\\b"), "ID should be a valid ObjectId string");
    }

    @Test
    void findByEmail_returns_user_by_email() {
        MongoUserEntity user = new MongoUserEntity("email1@example.com", "user1", "user1", "password123");
        repository.insert(user);
        User foundUser = repository.findByEmail("email1@example.com");
        assertEquals("email1@example.com", foundUser.getEmail());
        assertEquals("user1", foundUser.getUsername());
    }

    @Test
    void findById_returns_user_by_id() {
        MongoUserEntity user = new MongoUserEntity("_id", "user1", "user1", "password123");
        repository.insert(user);
        User foundUser = repository.findById("user1");
        assertEquals("user1", foundUser.getUsername());
    }

    @Test
    void insert_inserts_user_into_database() {
        User user = new User("email1@example.com", "user1", "user1", "password123");
        User insertedUser = repository.insert(user);
        assertEquals("email1@example.com", insertedUser.getEmail());
        assertEquals("user1", insertedUser.getUsername());
    }
}
