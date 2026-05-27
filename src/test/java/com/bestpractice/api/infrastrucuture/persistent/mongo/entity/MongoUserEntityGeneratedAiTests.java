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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void testSettersAndGetters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(username);
        mongoUserEntity.setEmail(email);
        mongoUserEntity.setPassword(password);

        // THEN
        assertEquals(id, mongoUserEntity.getId());
        assertEquals(username, mongoUserEntity.getUsername());
        assertEquals(email, mongoUserEntity.getEmail());
        assertEquals(password, mongoUserEntity.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "john_doe";
        String email = "john@example.com";
        String password = "password123";

        // WHEN
        MongoUserEntity entity = new MongoUserEntity(id, username, email, password);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testConvertFromUserValidId() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "alice", "alice@example.com", "pass123");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals("alice", entity.getUsername());
        assertEquals("alice@example.com", entity.getEmail());
        assertEquals("pass123", entity.getPassword());
    }

    @Test
    void testConvertFromUserWithInvalidIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "bob", "bob@example.com", "secret");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertToUserValidEntity() {
        // GIVEN
        ObjectId id = new ObjectId();
        MongoUserEntity entity = new MongoUserEntity(id, "bob", "bob@example.com", "secret");

        // WHEN
        User user = entity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals("bob", user.getUsername());
        assertEquals("secret", user.getPassword());
    }

    @Test
    void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        MongoUserEntity entity = new MongoUserEntity(null, "charlie", "charlie@example.com", "pwd");

        // WHEN & THEN
        assertThrows(NullPointerException.class, entity::convertTo);
    }

    @Test
    void testConvertToUserWithNullFieldsHandledGracefully() {
        // GIVEN
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(new ObjectId());
        entity.setUsername(null);
        entity.setEmail(null);
        entity.setPassword(null);

        // WHEN
        User user = entity.convertTo();

        // THEN
        assertNotNull(user);
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNotNull(user.getId());
    }

    @Test
    void testConvertFromUserWithNullUserThrowsException() {
        // GIVEN
        User user = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "david", "david@example.com", "pwd123");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }
}
