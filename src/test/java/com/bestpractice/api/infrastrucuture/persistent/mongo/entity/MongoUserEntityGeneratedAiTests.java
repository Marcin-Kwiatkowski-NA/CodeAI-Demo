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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
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
    void testConstructorInitialization() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "constructorUser";
        String email = "constructor@example.com";
        String password = "constructorPassword";

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
        User user = new User("507f1f77bcf86cd799439011", "convertUser", "convert@example.com", "convertPassword");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    void testConvertToUser() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername("toUser");
        mongoUserEntity.setEmail("toUser@example.com");
        mongoUserEntity.setPassword("toUserPassword");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(id.toString(), user.getId());
        assertEquals("toUser", user.getUsername());
        assertEquals("toUserPassword", user.getPassword());
    }

    @Test
    void testConvertFromUserWithInvalidIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "user", "email@example.com", "password");

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "user", "email@example.com", "password");

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertFromUserWithNullValuesExceptId() {
        // GIVEN
        ObjectId id = new ObjectId();
        User user = new User(id.toString(), null, null, null);

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity.getId());
        assertNull(entity.getUsername());
        assertNull(entity.getEmail());
        assertNull(entity.getPassword());
    }

    @Test
    void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity.setId(null);
        mongoUserEntity.setUsername("user");
        mongoUserEntity.setPassword("password");

        // WHEN THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
