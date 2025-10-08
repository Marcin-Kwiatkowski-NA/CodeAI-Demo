package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "john_doe", "john@example.com", "pass123");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
        assertNotNull(entity.getId());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectId() {
        // GIVEN
        User user = new User("invalid_object_id", "john_doe", "john@example.com", "pass123");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullId() {
        // GIVEN
        User user = new User(null, "john_doe", "john@example.com", "pass123");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        ObjectId objectId = new ObjectId();
        mongoUserEntity = new MongoUserEntity(objectId, "jane_doe", "jane@example.com", "pass456");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(objectId.toString(), user.getId());
        assertEquals("jane_doe", user.getUsername());
        assertEquals("pass456", user.getPassword());
    }

    @Test
    public void testConvertToUserWhenIdIsNull() {
        // GIVEN
        mongoUserEntity = new MongoUserEntity(null, "jane_doe", "jane@example.com", "pass456");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
