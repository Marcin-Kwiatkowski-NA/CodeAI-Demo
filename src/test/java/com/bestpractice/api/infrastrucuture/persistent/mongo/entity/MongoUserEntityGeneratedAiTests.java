package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MongoUserEntityGeneratedAiTests {

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
        String expectedPassword = "securePassword";

        // WHEN
        mongoUserEntity.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, mongoUserEntity.getPassword());
    }

    @Test
    void testConvertFrom() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "testUser", "test@example.com", "securePassword");

        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(user.getId(), result.getId().toString());
        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    void testConvertFromWithNullUser() {
        // GIVEN
        User user = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertTo() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername("testUser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");

        // WHEN
        User result = mongoUserEntity.convertTo();

        // THEN
        assertEquals(id.toString(), result.getId());
        assertEquals(mongoUserEntity.getUsername(), result.getUsername());
        assertEquals(mongoUserEntity.getPassword(), result.getPassword());
    }

    @Test
    void testConvertToWithNullId() {
        // GIVEN
        mongoUserEntity.setId(null);
        mongoUserEntity.setUsername("testUser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");

        // WHEN & THEN
        assertThrows(NullPointerException.class, mongoUserEntity::convertTo);
    }

    @Test
    void testConvertFromWithInvalidUserId() {
        // GIVEN
        User user = new User("invalidObjectId", "testUser", "test@example.com", "securePassword");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertFromWithEmptyUserId() {
        // GIVEN
        User user = new User("", "testUser", "test@example.com", "securePassword");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }
}
