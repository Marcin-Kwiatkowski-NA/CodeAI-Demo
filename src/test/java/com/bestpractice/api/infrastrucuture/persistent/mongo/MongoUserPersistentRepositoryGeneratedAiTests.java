package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Objects;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MongoUserPersistentRepositoryGeneratedAiTests.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: A new MongoUserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new ObjectId string is returned.
    }

    @Test
    void newId_returns_new_object_id() {
        // GIVEN: A new MongoUserPersistentRepository instance is created.
        // WHEN: The newId() method is called.
        // THEN: A new ObjectId string is returned.
        String newId = MongoUserPersistentRepository.this.newId();
        assertNotNull(newId, "New ID should not be null");
        assert(!newId.isEmpty(), "New ID should not be empty");
    }

    @Test
    void findByEmail_returns_user_by_email() {
        // GIVEN: A MongoUserEntity is created with a specific email.
        MongoUserEntity user = new MongoUserEntity("1", "testuser", "test@example.com", "password");
        // WHEN: The findByEmail("test@example.com") method is called.
        // THEN: The MongoUserEntity object is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        MongoUserEntity foundUser = repository.findByEmail("test@example.com");
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("1", foundUser.getId());
    }

    @Test
    void findById_returns_user_by_id() {
        // GIVEN: A MongoUserEntity is created with a specific ID.
        MongoUserEntity user = new MongoUserEntity("1", "testuser", "test@example.com", "password");
        // WHEN: The findById("1") method is called.
        // THEN: The MongoUserEntity object is returned.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        MongoUserEntity foundUser = repository.findById("1");
        assertEquals("1", foundUser.getId());
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("test@example.com", foundUser.getEmail());
        assertEquals("password", foundUser.getPassword());
    }

    @Test
    void insert_inserts_user_into_database() {
        // GIVEN: A User object is created.
        User user = new User("1", "testuser", "test@example.com", "password");
        // WHEN: The insert(user) method is called.
        // THEN: The user object is returned, and the user is inserted into the database.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        User insertedUser = repository.insert(user);
        assertEquals("1", insertedUser.getId());
        assertEquals("testuser", insertedUser.getUsername());
        assertEquals("test@example.com", insertedUser.getEmail());
        assertEquals("password", insertedUser.getPassword());
    }

    @Test
    void replace_replaces_user_by_id() {
        // GIVEN: A MongoUserEntity is created with a```java
        // is called.
        // THEN: The user object is returned, and the user is replaced in the database.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        User replacedUser = repository.replace("1", user);
        assertEquals("1", replacedUser.getId());
        assertEquals("testuser", replacedUser.getUsername());
        assertEquals("test@example.com", replacedUser.getEmail());
        assertEquals("password", replacedUser.getPassword());
    }

    @Test
    void removeById_removes_user_by_id() {
        // GIVEN: A MongoUserEntity is created with a specific ID.
        MongoUserEntity user = new MongoUserEntity("1", "testuser", "test@example.com", "password");
        // WHEN: The removeById("1") method is called.
        // THEN: The user is removed from the database, and the method returns true.
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(null, null);
        boolean removed = repository.removeById("1");
        assertTrue(removed);
    }
}
