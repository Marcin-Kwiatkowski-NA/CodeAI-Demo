package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "newuser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "new@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "newPassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalidObjectId", "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "pass");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
