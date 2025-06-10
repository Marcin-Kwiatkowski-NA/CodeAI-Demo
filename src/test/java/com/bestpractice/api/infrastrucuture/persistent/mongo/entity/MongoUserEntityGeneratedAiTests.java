package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MongoUserEntityGeneratedAiTests {

    private User user;
    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        user = new User("1", "testuser", "test@example.com", "password");
        mongoUserEntity = new MongoUserEntity(new ObjectId(), "testuser", "test@example.com", "password");
    }

    @Test
    public void testConvertFrom() {
        // GIVEN
        User user = new User("1", "testuser", "test@example.com", "password");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(result);
        assertEquals(user.getId(), result.getId().toString());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    public void testConvertTo() {
        // GIVEN
        MongoUserEntity mongoUserEntity = new MongoUserEntity(new ObjectId("507f191e810c19729de860ea"), "testuser", "test@example.com", "password");

        // WHEN
        User result = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(result);
        assertEquals(mongoUserEntity.getId().toString(), result.getId());
        assertEquals(mongoUserEntity.getUsername(), result.getUsername());
        assertEquals(mongoUserEntity.getPassword(), result.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        // GIVEN
        ObjectId newId = new ObjectId();
        String newUsername = "newuser";
        String newEmail = "newemail@example.com";
        String newPassword = "newpassword";

        // WHEN
        mongoUserEntity.setId(newId);
        mongoUserEntity.setUsername(newUsername);
        mongoUserEntity.setEmail(newEmail);
        mongoUserEntity.setPassword(newPassword);

        // THEN
        assertEquals(newId, mongoUserEntity.getId());
        assertEquals(newUsername, mongoUserEntity.getUsername());
        assertEquals(newEmail, mongoUserEntity.getEmail());
        assertEquals(newPassword, mongoUserEntity.getPassword());
    }
}
