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

/**
 * Improved and verified test class for MongoUserEntity.
 * Improvements:
 * - Ensured consistent GIVEN-WHEN-THEN structure.
 * - Added missing edge cases for null and long string values.
 * - Verified exception handling correctness.
 * - Removed redundant imports and unused mocks.
 * - Ensured all tests are independent and self-contained.
 */
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
    void testConvertFromUserValidInput() {
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
    void testConvertToUserValidInput() {
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
    void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "charlie", "charlie@example.com", "pwd");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
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
        User user = new User(null, "david", "david@example.com", "pwd");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertToUserThrowsExceptionWhenIdIsNull() {
        // GIVEN
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(null);
        entity.setUsername("eve");
        entity.setPassword("pwd");

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
        assertEquals(entity.getId().toString(), user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    void testSettersWithEmptyStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        String empty = "";

        // WHEN
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(empty);
        mongoUserEntity.setEmail(empty);
        mongoUserEntity.setPassword(empty);

        // THEN
        assertEquals(id, mongoUserEntity.getId());
        assertEquals("", mongoUserEntity.getUsername());
        assertEquals("", mongoUserEntity.getEmail());
        assertEquals("", mongoUserEntity.getPassword());
    }

    @Test
    void testSettersWithWhitespaceStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        String whitespace = "   ";

        // WHEN
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(whitespace);
        mongoUserEntity.setEmail(whitespace);
        mongoUserEntity.setPassword(whitespace);

        // THEN
        assertEquals(id, mongoUserEntity.getId());
        assertEquals("   ", mongoUserEntity.getUsername());
        assertEquals("   ", mongoUserEntity.getEmail());
        assertEquals("   ", mongoUserEntity.getPassword());
    }

    @Test
    void testConvertFromUserWithEmptyStrings() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "", "", "");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals("", entity.getUsername());
        assertEquals("", entity.getEmail());
        assertEquals("", entity.getPassword());
    }

    @Test
    void testConvertFromUserWithWhitespaceStrings() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "   ", "   ", "   ");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertNotNull(entity.getId());
        assertEquals("   ", entity.getUsername());
        assertEquals("   ", entity.getEmail());
        assertEquals("   ", entity.getPassword());
    }

    @Test
    void testConvertToUserWithEmptyStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        MongoUserEntity entity = new MongoUserEntity(id, "", "", "");

        // WHEN
        User user = entity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals("", user.getUsername());
        assertEquals("", user.getPassword());
    }

    @Test
    void testConvertToUserWithWhitespaceStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        MongoUserEntity entity = new MongoUserEntity(id, "   ", "   ", "   ");

        // WHEN
        User user = entity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals("   ", user.getUsername());
        assertEquals("   ", user.getPassword());
    }

    @Test
    void testConvertFromUserWithSingleCharacterStrings() {
        // GIVEN
        User user = new User(new ObjectId().toString(), "a", "b", "c");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertNotNull(entity);
        assertEquals("a", entity.getUsername());
        assertEquals("b", entity.getEmail());
        assertEquals("c", entity.getPassword());
    }

    @Test
    void testConvertToUserWithSingleCharacterStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        MongoUserEntity entity = new MongoUserEntity(id, "x", "y", "z");

        // WHEN
        User user = entity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals("x", user.getUsername());
        assertEquals("z", user.getPassword());
    }

    @Test
    void testSettersWithLongStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        String longString = "a".repeat(1000);

        // WHEN
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(longString);
        mongoUserEntity.setEmail(longString);
        mongoUserEntity.setPassword(longString);

        // THEN
        assertEquals(id, mongoUserEntity.getId());
        assertEquals(longString, mongoUserEntity.getUsername());
        assertEquals(longString, mongoUserEntity.getEmail());
        assertEquals(longString, mongoUserEntity.getPassword());
    }
}
