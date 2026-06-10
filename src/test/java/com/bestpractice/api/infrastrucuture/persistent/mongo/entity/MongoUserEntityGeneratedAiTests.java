package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        User user = new User("507f1f77bcf86cd799439011", "convertUser", "convert@example.com", "convertPass");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    void testConvertToUser() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "mongoUser";
        String email = "mongo@example.com";
        String password = "mongoPass";
        mongoUserEntity = new MongoUserEntity(id, username, email, password);

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(id.toString(), user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN
        User user = new User(null, "userNullId", "null@example.com", "pass");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertFromUserWithInvalidIdThrowsException() {
        // GIVEN
        User user = new User("invalid_object_id", "userInvalidId", "invalid@example.com", "pass");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertFromUserWithEmptyIdThrowsException() {
        // GIVEN
        User user = new User("", "userEmptyId", "empty@example.com", "pass");

        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    void testConvertToUserWithNullIdThrowsException() {
        // GIVEN
        mongoUserEntity = new MongoUserEntity(null, "userNullId", "null@example.com", "pass");

        // WHEN / THEN
        assertThatThrownBy(() -> mongoUserEntity.convertTo())
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("id");
    }

    @Test
    void testConvertFromUserWithWhitespaceFields() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", " ", " ", " ");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(" ", entity.getUsername());
        assertEquals(" ", entity.getEmail());
        assertEquals(" ", entity.getPassword());
    }

    @Test
    void testConvertFromUserWithEmptyFields() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "", "", "");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals("", entity.getUsername());
        assertEquals("", entity.getEmail());
        assertEquals("", entity.getPassword());
    }

    @Test
    void testConvertToUserWithEmptyFields() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, "", "", "");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals("", user.getUsername());
        assertEquals("", user.getPassword());
    }

    @Test
    void testConvertToUserWithWhitespaceFields() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, " ", " ", " ");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals(" ", user.getUsername());
        assertEquals(" ", user.getPassword());
    }

    @Test
    void testConvertFromUserWithSingleCharacterFields() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "a", "b", "c");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals("a", entity.getUsername());
        assertEquals("b", entity.getEmail());
        assertEquals("c", entity.getPassword());
    }

    @Test
    void testConvertToUserWithSingleCharacterFields() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, "a", "b", "c");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(id.toString(), user.getId());
        assertEquals("a", user.getUsername());
        assertEquals("c", user.getPassword());
    }

    @Test
    void testConvertFromUserWithLongStrings() {
        // GIVEN
        String longString = "x".repeat(1000);
        User user = new User("507f1f77bcf86cd799439011", longString, longString, longString);

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(longString, entity.getUsername());
        assertEquals(longString, entity.getEmail());
        assertEquals(longString, entity.getPassword());
    }

    @Test
    void testConvertToUserWithLongStrings() {
        // GIVEN
        ObjectId id = new ObjectId();
        String longString = "y".repeat(1000);
        mongoUserEntity = new MongoUserEntity(id, longString, longString, longString);

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(id.toString(), user.getId());
        assertEquals(longString, user.getUsername());
        assertEquals(longString, user.getPassword());
    }

    @Test
    void testConvertFromUserWithNullEmail() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "username", null, "password");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals("username", entity.getUsername());
        assertEquals(null, entity.getEmail());
        assertEquals("password", entity.getPassword());
    }

    @Test
    void testConvertToUserWithNullEmail() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, "username", null, "password");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
    }
}
