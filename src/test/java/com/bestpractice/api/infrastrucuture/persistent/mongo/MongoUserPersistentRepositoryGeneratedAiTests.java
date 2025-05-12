package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.mongodb.client.MongoClient;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoUserPersistentRepositoryGeneratedAiTests.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoUserPersistentRepository repository;
    private MongoClient mongoClient;
    private MongoClient.Cursor<org.bson.Document> testdb;
    private MongoClient.Cursor<org.bson.Document> testdb_cursor;

    @BeforeEach
    void setUp() {
        mongoClient = MongoClient.builder().build();
        testdb = MongoClient.builder().getDatabase("testdb").build();
        repository = new MongoUserPersistentRepository(mongoClient, testdb);
    }

    @Test
    void newId() {
        String id = repository.newId();
        assertNotNull(id, "New ID should not be null");
        assertTrue(id.matches("\\b[a-zA-Z0-9]{20}\\b"), "New ID should be 20 characters long");
    }

    @Test
    void findByEmail() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);

        // WHEN
        User foundUser = repository.findByEmail("test@example.com");

        // THEN
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void findById() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);
        String id = repository.newId();

        // WHEN
        User foundUser = repository.findById(id);

        // THEN
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void insert() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");

        // WHEN
        User insertedUser = repository.insert(user);

        // THEN
        assertEquals("testUser", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);
        String id = repository.newId();

        // WHEN
        User replacedUser = repository.replace(id, user);

        // THEN
        assertEquals("testUser", replacedUser.getUsername());
        assertEquals("test@example.com", replacedUser.getEmail());
        assertEquals("password", replacedUser.getPassword());
    }

    @Test
    void removeById() {
        // GIVEN
        User user = new User("1", "testUser", "test@example.com", "password");
        repository.insert(user);
        String id = repository.newId();

        // WHEN
        boolean removed = repository.removeById(id);

        // THEN
        assertFalse(removed, "Should be removed");
    }
}
