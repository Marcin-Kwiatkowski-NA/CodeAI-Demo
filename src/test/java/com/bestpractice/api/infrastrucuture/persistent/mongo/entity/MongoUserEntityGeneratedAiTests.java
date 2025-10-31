package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testGettersAndSetters() {
        // GIVEN
        ObjectId newId = new ObjectId("507f1f77bcf86cd799439012");
        String newUsername = "newuser";
        String newEmail = "new@example.com";
        String newPassword = "newPassword";

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

    @Test
    public void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439013", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertEquals(new ObjectId("507f1f77bcf86cd799439013"), entity.getId());
        assertEquals("convertUser", entity.getUsername());
        assertEquals("convert@example.com", entity.getEmail());
        assertEquals("convertPass", entity.getPassword());
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
        mongoUserEntity.setEmail("to@example.com");
        mongoUserEntity.setPassword("toPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals("507f1f77bcf86cd799439014", user.getId());
        assertEquals("toUser", user.getUsername());
        assertEquals("toPass", user.getPassword());
        assertNull(user.getEmail());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);
        mongoUserEntity.setUsername("username");
        mongoUserEntity.setPassword("password");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }

    @Test
    public void testConvertToUserWithEmptyUsername() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439015"));
        mongoUserEntity.setUsername("");
        mongoUserEntity.setPassword("password");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals("", user.getUsername());
        assertEquals("password", user.getPassword());
    }
}
