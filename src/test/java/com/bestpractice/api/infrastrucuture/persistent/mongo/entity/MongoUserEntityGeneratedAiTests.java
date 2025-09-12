package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class MongoUserEntityGeneratedAiTests {

    @Test
    void constructorTest() {
        // GIVEN: Setup
        ObjectId id = new ObjectId();
        String username = "testUser";
        String email = "test@example.com";
        String password = "testPassword";

        // WHEN: Create a MongoUserEntity
        MongoUserEntity mongoUserEntity = new MongoUserEntity(id, username, email, password);

        // THEN: Verify the entity's properties
        assertEquals(id.toString(), mongoUserEntity.getId());
        assertEquals(username, mongoUserEntity.getUsername());
        assertEquals(email, mongoUserEntity.getEmail());
        assertEquals(password, mongoUserEntity.getPassword());
    }

    @Test
    void setIdTest() {
        // GIVEN: Setup
        MongoUserEntity mongoUserEntity = new MongoUserEntity();

        // WHEN: Set a new ID
        ObjectId newId = new ObjectId();
        mongoUserEntity.setId(newId);

        // THEN: Verify the ID has been updated
        assertEquals(newId.toString(), mongoUserEntity.getId());
    }

    @Test
    void setUsernameTest() {
        // GIVEN: Setup
        MongoUserEntity mongoUserEntity = new MongoUserEntity();

        // WHEN: Set a new username
        String newUsername = "newUser";
        mongoUserEntity.setUsername(newUsername);

        // THEN: Verify the username has been updated
        assertEquals(newUsername, mongoUserEntity.getUsername());
    }

    @Test
    void setEmailTest() {
        // GIVEN: Setup
        MongoUserEntity mongoUserEntity = new MongoUserEntity();

        // WHEN: Set a new email
        String newEmail = "new@example.com";
        mongoUserEntity.setEmail(newEmail);

        // THEN: Verify the email has been updated
        assertEquals(newEmail, mongoUserEntity.getEmail());
    }

    @Test
    void setPasswordTest() {
        // GIVEN: Setup
        MongoUserEntity mongoUserEntity = new MongoUserEntity();

        // WHEN: Set a new password
        String newPassword = "newPassword";
        mongoUserEntity.setPassword(newPassword);

        // THEN: Verify the password has been updated
        assertEquals(newPassword, mongoUserEntity.getPassword());
    }

    @Test
    void convertFromTest() {
        // GIVEN: Setup
        User user = new User("user123", "user", "user@example.com", "password");

        // WHEN: Convert the User object to a MongoUserEntity
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);

        // THEN: Verify the converted entity's properties
        assertEquals(new ObjectId(), mongoUserEntity.getId());
        assertEquals(user.getUsername(), mongoUserEntity.getUsername());
        assertEquals(user.getEmail(), mongoUserEntity.getEmail());
        assertEquals(user.getPassword(), mongoUserEntity.getPassword());
    }

    @Test
    void convertToTest() {
        // GIVEN: Setup
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "testPassword");

        // WHEN: Convert the MongoUserEntity to a User object
        User user = mongoUserEntity.convertTo();

        // THEN: Verify the converted user's properties
        assertEquals("user123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
    }
}
