package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void testConstructorWithParameters() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "user123";
        String email = "user123@example.com";
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
        User user = new User("507f1f77bcf86cd799439011", "convertUser", "convert@example.com", "convertPass");

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
        mongoUserEntity.setUsername("mongoUser");
        mongoUserEntity.setEmail("mongo@example.com");
        mongoUserEntity.setPassword("mongoPass");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(id.toString(), user.getId());
        assertEquals("mongoUser", user.getUsername());
        assertEquals("mongoPass", user.getPassword());
    }

    @Test
    void testConvertFromUserThrowsExceptionWhenIdIsNull() {
        // GIVEN
        User user = new User(null, "userNullId", "null@example.com", "nullPass");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> {
            MongoUserEntity.convertFrom(user);
        });
    }

    @Test
    void testConvertFromUserThrowsExceptionWhenIdIsInvalid() {
        // GIVEN
        User user = new User("invalid_object_id", "userInvalidId", "invalid@example.com", "invalidPass");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> {
            MongoUserEntity.convertFrom(user);
        });
    }

    @Test
    void testConvertFromUserWithValidIdAndNullFields() {
        // GIVEN
        ObjectId validId = new ObjectId();
        User user = new User(validId.toString(), null, null, null);

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity.getId());
        assertNull(entity.getUsername());
        assertNull(entity.getEmail());
        assertNull(entity.getPassword());
    }
}
