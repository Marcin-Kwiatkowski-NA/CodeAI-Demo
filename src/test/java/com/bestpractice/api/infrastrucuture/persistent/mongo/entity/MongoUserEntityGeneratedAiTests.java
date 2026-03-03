package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.bson.types.ObjectId;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Security-sensitive: the class maps user credentials between MongoDB and the domain model
class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity entity;
    private User user;
    private ObjectId objectId;

    @BeforeEach
    void setUp() {
        entity = new MongoUserEntity();
        user = new User();
        objectId = new ObjectId();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        ObjectId newId = new ObjectId();
        // WHEN
        entity.setId(newId);
        // THEN
        assertEquals(newId, entity.getId());
    }

    @Test
    void testSetAndGetUsername() {
        // GIVEN
        String username = "testUser";
        // WHEN
        entity.setUsername(username);
        // THEN
        assertEquals(username, entity.getUsername());
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN
        String email = "user@example.com";
        // WHEN
        entity.setEmail(email);
        // THEN
        assertEquals(email, entity.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String password = "secret";
        // WHEN
        entity.setPassword(password);
        // THEN
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testConstructorWithParameters() {
        // GIVEN
        String username = "john_doe";
        String email = "john@example.com";
        String password = "pass123";
        // WHEN
        MongoUserEntity constructed = new MongoUserEntity(objectId, username, email, password);
        // THEN
        assertEquals(objectId, constructed.getId());
        assertEquals(username, constructed.getUsername());
        assertEquals(email, constructed.getEmail());
        assertEquals(password, constructed.getPassword());
    }

    @Test
    void testDefaultConstructorFields() {
        // GIVEN & WHEN
        // THEN
        assertEquals(null, entity.getId());
        assertEquals(null, entity.getUsername());
        assertEquals(null, entity.getEmail());
        assertEquals(null, entity.getPassword());
    }

    @Test
    void testConvertFrom() {
        // GIVEN
        String idStr = objectId.toHexString();
        user.setId(idStr);
        user.setUsername("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("pass123");
        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);
        // THEN
        assertEquals(objectId, result.getId());
        assertEquals("john_doe", result.getUsername());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("pass123", result.getPassword());
    }

    @Test
    void testConvertTo() {
        // GIVEN
        entity.setId(objectId);
        entity.setUsername("alice");
        entity.setPassword("alicepwd");
        // WHEN
        User result = entity.convertTo();
        // THEN
        assertEquals(objectId.toString(), result.getId());
        assertEquals("alice", result.getUsername());
        assertEquals("alicepwd", result.getPassword());
        assertEquals(null, result.getEmail());
    }

    @Test
    void testConvertFromWithNullEmail() {
        // GIVEN
        String idStr = objectId.toHexString();
        user.setId(idStr);
        user.setUsername("john_doe");
        user.setEmail(null);
        user.setPassword("pass123");
        // WHEN
        MongoUserEntity result = MongoUserEntity.convertFrom(user);
        // THEN
        assertEquals(objectId, result.getId());
        assertEquals("john_doe", result.getUsername());
        assertEquals(null, result.getEmail());
        assertEquals("pass123", result.getPassword());
    }

    @Test
    void testConvertFromWithNullUser() {
        // GIVEN
        User nullUser = null;
        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> MongoUserEntity.convertFrom(nullUser));
    }

    @Test
    void testConvertFromWithInvalidId() {
        // GIVEN
        user.setId("invalid_hex_string");
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertFromWithNullId() {
        // GIVEN
        user.setId(null);
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertToWithNullId() {
        // GIVEN
        entity.setId(null);
        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> entity.convertTo());
    }
}
