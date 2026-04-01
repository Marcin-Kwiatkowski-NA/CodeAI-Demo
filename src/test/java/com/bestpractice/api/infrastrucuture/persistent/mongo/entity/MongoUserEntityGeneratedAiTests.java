package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        ObjectId expectedId = new ObjectId();

        // WHEN
        mongoUserEntity.setId(expectedId);

        // THEN
        assertEquals(expectedId, mongoUserEntity.getId());
    }

    @Test
    void testSetAndGetUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        mongoUserEntity.setUsername(expectedUsername);

        // THEN
        assertEquals(expectedUsername, mongoUserEntity.getUsername());
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        mongoUserEntity.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, mongoUserEntity.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword"; // SECURITY-SENSITIVE

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "john_doe", "john@example.com", "password123"); // SECURITY-SENSITIVE

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals("john_doe", result.getUsername());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password123", result.getPassword());
    }

    @Test
    void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User invalidUser = new User("invalid_object_id", "user", "user@example.com", "pass"); // SECURITY-SENSITIVE

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(invalidUser));
    }

    @Test
    void testConvertToUser() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, "alice", "alice@example.com", "alicePass"); // SECURITY-SENSITIVE

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals("alice", user.getUsername());
        assertEquals("alicePass", user.getPassword());
    }

    @Test
    void testConvertToUserWhenIdIsNullThrowsException() {
        // GIVEN
        mongoUserEntity = new MongoUserEntity(null, "bob", "bob@example.com", "bobPass"); // SECURITY-SENSITIVE

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }

    @Test
    void testConstructorWithParameters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "bob";
        String email = "bob@example.com";
        String password = "bobPass"; // SECURITY-SENSITIVE

        // WHEN
        MongoUserEntity entity = new MongoUserEntity(id, username, email, password);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testConvertFromUserWithNullUserThrowsException() {
        // GIVEN
        User nullUser = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoUserEntity.convertFrom(nullUser));
    }

    @Test
    void testConvertToUserWithMissingFields() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, null, null, null);

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
    }
}
