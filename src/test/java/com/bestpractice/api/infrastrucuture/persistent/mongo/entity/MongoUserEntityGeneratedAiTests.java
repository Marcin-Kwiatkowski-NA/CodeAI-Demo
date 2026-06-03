package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void testSettersAndGetters() {
        ObjectId id = new ObjectId();
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword"; // security-sensitive

        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(username);
        mongoUserEntity.setEmail(email);
        mongoUserEntity.setPassword(password);

        assertEquals(id, mongoUserEntity.getId());
        assertEquals(username, mongoUserEntity.getUsername());
        assertEquals(email, mongoUserEntity.getEmail());
        assertEquals(password, mongoUserEntity.getPassword());
    }

    @Test
    void testAllArgsConstructor() {
        ObjectId id = new ObjectId();
        String username = "user1";
        String email = "user1@example.com";
        String password = "pass123"; // security-sensitive

        MongoUserEntity entity = new MongoUserEntity(id, username, email, password);

        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testConvertFromUser() {
        String id = new ObjectId().toString();
        String username = "convertUser";
        String email = "convert@example.com";
        String password = "convertPass"; // security-sensitive
        User user = new User(id, username, email, password);

        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        assertNotNull(entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
    }

    @Test
    void testConvertToUser() {
        ObjectId id = new ObjectId();
        String username = "mongoUser";
        String email = "mongo@example.com";
        String password = "mongoPass"; // security-sensitive
        mongoUserEntity = new MongoUserEntity(id, username, email, password);

        User user = mongoUserEntity.convertTo();

        assertEquals(id.toString(), user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testSettersWithEmptyStrings() {
        ObjectId id = new ObjectId();
        String username = "";
        String email = "";
        String password = ""; // security-sensitive

        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(username);
        mongoUserEntity.setEmail(email);
        mongoUserEntity.setPassword(password);

        assertEquals("", mongoUserEntity.getUsername());
        assertEquals("", mongoUserEntity.getEmail());
        assertEquals("", mongoUserEntity.getPassword());
    }

    @Test
    void testSettersWithWhitespaceStrings() {
        ObjectId id = new ObjectId();
        String username = "   ";
        String email = "   ";
        String password = "   "; // security-sensitive

        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername(username);
        mongoUserEntity.setEmail(email);
        mongoUserEntity.setPassword(password);

        assertEquals("   ", mongoUserEntity.getUsername());
        assertEquals("   ", mongoUserEntity.getEmail());
        assertEquals("   ", mongoUserEntity.getPassword());
    }

    @Test
    void testConvertFromUserWithEmptyFields() {
        String id = new ObjectId().toString();
        User user = new User(id, "", "", ""); // security-sensitive

        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        assertEquals("", entity.getUsername());
        assertEquals("", entity.getEmail());
        assertEquals("", entity.getPassword());
    }

    @Test
    void testConvertFromUserWithWhitespaceFields() {
        String id = new ObjectId().toString();
        User user = new User(id, "   ", "   ", "   "); // security-sensitive

        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        assertEquals("   ", entity.getUsername());
        assertEquals("   ", entity.getEmail());
        assertEquals("   ", entity.getPassword());
    }

    @Test
    void testConvertToUserWithEmptyFields() {
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, "", "", ""); // security-sensitive

        User user = mongoUserEntity.convertTo();

        assertEquals(id.toString(), user.getId());
        assertEquals("", user.getUsername());
        assertEquals("", user.getPassword());
    }

    @Test
    void testConvertToUserWithWhitespaceFields() {
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, "   ", "   ", "   "); // security-sensitive

        User user = mongoUserEntity.convertTo();

        assertEquals(id.toString(), user.getId());
        assertEquals("   ", user.getUsername());
        assertEquals("   ", user.getPassword());
    }

    @Test
    void testConvertToUserWithNullUsernameAndPassword() {
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, null, null, null);

        User user = mongoUserEntity.convertTo();

        assertNotNull(user);
        assertEquals(id.toString(), user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }

    @Test
    void testConvertFromUserWithLongStrings() {
        String id = new ObjectId().toString();
        String longString = "a".repeat(1000);
        User user = new User(id, longString, longString, longString); // security-sensitive

        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        assertEquals(longString, entity.getUsername());
        assertEquals(longString, entity.getEmail());
        assertEquals(longString, entity.getPassword());
    }

    @Test
    void testConvertToUserDoesNotThrowWithLongStrings() {
        ObjectId id = new ObjectId();
        String longString = "b".repeat(1000);
        mongoUserEntity = new MongoUserEntity(id, longString, longString, longString); // security-sensitive

        assertDoesNotThrow(() -> mongoUserEntity.convertTo());
    }

    @Test
    void testConvertFromUserWithSingleCharacterFields() {
        String id = new ObjectId().toString();
        User user = new User(id, "a", "b", "c"); // security-sensitive

        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        assertEquals("a", entity.getUsername());
        assertEquals("b", entity.getEmail());
        assertEquals("c", entity.getPassword());
    }

    @Test
    void testConvertToUserWithSingleCharacterFields() {
        ObjectId id = new ObjectId();
        mongoUserEntity = new MongoUserEntity(id, "x", "y", "z"); // security-sensitive

        User user = mongoUserEntity.convertTo();

        assertEquals(id.toString(), user.getId());
        assertEquals("x", user.getUsername());
        assertEquals("z", user.getPassword());
    }

    @Test
    void testConvertFromUserWithNullEmail() {
        String id = new ObjectId().toString();
        User user = new User(id, "user", null, "password"); // security-sensitive

        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        assertEquals("user", entity.getUsername());
        assertNull(entity.getEmail());
        assertEquals("password", entity.getPassword());
    }
}
