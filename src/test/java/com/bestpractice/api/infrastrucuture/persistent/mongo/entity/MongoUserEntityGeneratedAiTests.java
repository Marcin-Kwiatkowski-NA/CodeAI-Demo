package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
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
        String username = "user1";
        String email = "user1@example.com";
        String password = "pass123";

        // WHEN
        MongoUserEntity entity = new MongoUserEntity(id, username, email, password);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testConvertFromUser() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "john_doe", "john@example.com", "password123");

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
        User invalidUser = new User("invalid_object_id", "user", "email@example.com", "password");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(invalidUser));
    }

    @Test
    void testConvertToUser() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "alice";
        String email = "alice@example.com";
        String password = "alicePass";
        mongoUserEntity = new MongoUserEntity(id, username, email, password);

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testConvertToUserWithNullFields() {
        // GIVEN
        mongoUserEntity.setId(new ObjectId());
        mongoUserEntity.setUsername(null);
        mongoUserEntity.setEmail(null);
        mongoUserEntity.setPassword(null);

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    void testConvertToUserThrowsExceptionWhenIdIsNull() {
        // GIVEN
        mongoUserEntity.setId(null);
        mongoUserEntity.setUsername("user");
        mongoUserEntity.setPassword("pass");

        // WHEN / THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }

    @Test
    void testConvertFromUserThrowsExceptionWhenUserIsNull() {
        // GIVEN
        User nullUser = null;

        // WHEN / THEN
        assertThrows(NullPointerException.class, () -> MongoUserEntity.convertFrom(nullUser));
    }
}
