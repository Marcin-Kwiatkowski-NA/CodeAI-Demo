package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void setId() {
        // GIVEN: A new MongoUserEntity instance
        // WHEN: The setId method is called with a new ObjectId
        // THEN: The id field of the MongoUserEntity is set to the provided ObjectId
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        assertEquals(id, mongoUserEntity.getId());
    }

    @Test
    void setUsername() {
        // GIVEN: A new MongoUserEntity instance
        // WHEN: The setUsername method is called with a username
        // THEN: The username field of the MongoUserEntity is set to the provided username
        String username = "testUser";
        mongoUserEntity.setUsername(username);
        assertEquals(username, mongoUserEntity.getUsername());
    }

    @Test
    void setEmail() {
        // GIVEN: A new MongoUserEntity instance
        // WHEN: The setEmail method is called with an email
        // THEN: The email field of the MongoUserEntity is set to the provided email
        String email = "test@example.com";
        mongoUserEntity.setEmail(email);
        assertEquals(email, mongoUserEntity.getEmail());
    }

    @Test
    void setPassword() {
        // GIVEN: A new MongoUserEntity instance
        // WHEN: The setPassword method is called with a password
        // THEN: The password field of the MongoUserEntity is set to the provided password
        String password = "testPassword";
        mongoUserEntity.setPassword(password);
        assertEquals(password, mongoUserEntity.getPassword());
    }

    @Test
    void convertFrom() {
        // GIVEN: A User object
        User user = new User("user123", "testUser", "test@example.com", "testPassword");
        // WHEN: The convertFrom method is called with the User object
        // THEN: A new MongoUserEntity is created with the same data as the User object
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        assertEquals("user123", mongoUserEntity.getUsername());
        assertEquals("test@example.com", mongoUserEntity.getEmail());
        assertEquals("testPassword", mongoUserEntity.getPassword());
    }

    @Test
    void convertTo() {
        // GIVEN: A MongoUserEntity instance
        MongoUserEntity mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId("id123");
        mongoUserEntity.setUsername("testUser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("testPassword");
        // WHEN: The convertTo method is called on the MongoUserEntity
        // THEN: A new User object is created with the same data as the MongoUserEntity
        User user = mongoUserEntity.convertTo();
        assertEquals("id123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
    }
}
