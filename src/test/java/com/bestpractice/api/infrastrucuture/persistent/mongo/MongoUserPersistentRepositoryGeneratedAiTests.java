package com.bestpractice.api.infrastrucuture.persistent.mongo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoUserPersistentRepository repository;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    @BeforeEach
    void setUp() {
        mongoClient = MongoClient.create("mongodb://localhost:27017/");
        mongoDatabase = mongoClient.getDatabase("testdb");
    }

    @Test
    void newId_returns_valid_objectid() {
        String id = repository.newId();
        assertNotNull(id, "ID should not be null");
        assertTrue(id.matches("\\b[a-fA-F0-9]{24}\\b"), "ID should be a valid ObjectId");
    }

    @Test
    void findByEmail_returns_user_by_email() {
        // Create a test user
        User user = new User("123", "test@example.com", "email", "password");
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
        repository.insert(user);

        // Find the user by email
        User foundUser = repository.findByEmail("test@example.com");

        // Assert that the found user is the same as the created user
        assertEquals("123", foundUser.getId());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("email", foundUser.getUsername());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById_returns_user_by_id() {
        // Create a test user
        User user = new User("123", "test@example.com", "email", "password");
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
        repository.insert(user);

        // Find the user by id
        User foundUser = repository.findById("123");

        // Assert that the found user is the same as the created user
        assertEquals("123", foundUser.getId());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("email", foundUser.getUsername());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void insert_inserts_user_into_database() {
        User user = new User("123", "test@example.com", "email", "password");
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
        User insertedUser = repository.insert(user);

        assertEquals("123", insertedUser.getId());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("email", insertedUser.getUsername());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace_replaces_user_by_id() {
        // Create a test user
        User user = new User("123", "test@example.com", "email", "password");
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
        repository.insert(user);

        // Replace the user by id
        User replacedUser = repository.replace("123", user);

        // Assert that the replaced user is the same as the original user
        assertEquals("123", replacedUser.getId());
        assertEquals("test@example.com", replacedUser.getEmail());
        assertEquals("email", replacedUser.getUsername());
        assertEquals("password", replacedUser.getPassword());
    }

    @Test
    void removeById_removes_user_by_id() {
        // Create a test user
        User user = new User("123", "test@example.com", "email", "password");
        repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);
        repository.insertjava
        // Remove the user by id
        repository.removeById("123");

        // Assert that the user is removed
        assertThrows(NoSuchElementException.class, () -> repository.findById("123"));
    }

    @AfterEach
    void tearDown() {
        repository.removeById("123");
    }
}