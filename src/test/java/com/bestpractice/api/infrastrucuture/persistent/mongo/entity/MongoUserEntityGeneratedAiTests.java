package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    void testConvertFromUserThrowsExceptionWhenInvalidObjectId() {
        // GIVEN
        User invalidUser = new User("invalid_id", "user", "user@example.com", "password"); // SECURITY-SENSITIVE

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(invalidUser));
    }

    @Test
    void testConvertToUser() {
        // GIVEN
        ObjectId objectId = new ObjectId();
        mongoUserEntity.setId(objectId);
        mongoUserEntity.setUsername("alice");
        mongoUserEntity.setEmail("alice@example.com");
        mongoUserEntity.setPassword("alicePassword"); // SECURITY-SENSITIVE

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(objectId.toString(), user.getId());
        assertEquals("alice", user.getUsername());
        assertEquals("alicePassword", user.getPassword());
    }

    @Test
    void testConvertToUserThrowsExceptionWhenIdIsNull() {
        // GIVEN
        mongoUserEntity.setId(null);
        mongoUserEntity.setUsername("bob");
        mongoUserEntity.setPassword("bobPassword"); // SECURITY-SENSITIVE

        // WHEN / THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }

    @Test
    void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "bob";
        String email = "bob@example.com";
        String password = "bobPassword"; // SECURITY-SENSITIVE

        // WHEN
        MongoUserEntity entity = new MongoUserEntity(id, username, email, password);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testConvertFromUserThrowsExceptionWhenUserIsNull() {
        // GIVEN
        User nullUser = null;

        // WHEN / THEN
        assertThrows(NullPointerException.class, () -> MongoUserEntity.convertFrom(nullUser));
    }
}
