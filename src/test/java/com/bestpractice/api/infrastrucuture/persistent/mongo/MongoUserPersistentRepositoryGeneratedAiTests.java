package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoUserPersistentRepositoryGeneratedAiTests.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    @Test
    void newId() {
        // GIVEN: A new MongoUserPersistentRepository instance is created.
        MongoClient mongoClient = MongoClient.builder().build();
        MongoDatabase mongoDatabase = MongoDatabase.builder().build();
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);

        // WHEN: The newId() method is called.
        String newId = repository.newId();

        // THEN: A new ObjectId string is returned.
        assertNotNull(newId, "newId should not return null");
        assertEquals(newId.length(), 12, "newId should return a 12 character ObjectId");
    }

    @Test
    void findByEmail() {
        // GIVEN: A MongoUserPersistentRepository instance is created.
        MongoClient mongoClient = MongoClient.builder().build();
        MongoDatabase mongoDatabase = MongoDatabase.builder().build();
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);

        // GIVEN: A sample user is inserted into the database.
        MongoUserEntity user = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "password");
        repository.insert(new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()));

        // WHEN: The findByEmail() method is called with "test@example.com" as the email.
        User foundUser = repository.findByEmail("test@example.com");

        // THEN: The user with email "test@example.com" is found and returned.
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testUser", foundUser.getUsername(), "Username should match");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
        assertEquals("password", foundUser.getPassword(), "Password should match");
    }

    @Test
    void findById() {
        // GIVEN: A MongoUserPersistentRepository instance is created.
        MongoClient mongoClient = MongoClient.builder().build();
        MongoDatabase mongoDatabase = MongoDatabase.builder().build();
        MongoUserPersistentRepository repository = new MongoUserPersistentRepository(mongoClient, mongoDatabase);

        // GIVEN: A sample user is inserted into the database.
        MongoUserEntity user = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "password");
        repository.insert(new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword()));

        // WHEN: The findById() method is called with the user's ID.
        User foundUser = repository.findById(user.getId().toString());

        // THEN: The user with the given ID is found and returned.
        assertNotNull(foundUser, "User should not be null");
        assertEquals("testUser", foundUser.getUsername(), "Username should match");
        assertEquals("test@example.com", foundUser.getEmail(), "Email should match");
        assertEquals("password", foundUser.getPassword(), "Password should match");
    }
}
