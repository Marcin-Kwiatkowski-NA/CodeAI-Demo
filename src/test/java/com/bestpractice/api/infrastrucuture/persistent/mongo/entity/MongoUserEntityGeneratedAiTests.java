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

    // Test for default constructor
    @Test
    void givenDefaultConstructor_whenInstantiated_thenFieldsShouldBeNull() {
        // GIVEN
        // No setup required

        // WHEN
        MongoUserEntity entity = new MongoUserEntity();

        // THEN
        assertEquals(null, entity.getId());
        assertEquals(null, entity.getUsername());
        assertEquals(null, entity.getEmail());
        assertEquals(null, entity.getPassword());
    }

    // Test for parameterized constructor
    @Test
    void givenParameterizedConstructor_whenInstantiated_thenFieldsShouldBeSet() {
        // GIVEN
        ObjectId id = new ObjectId();
        String username = "testUser";
        String email = "test@example.com";
        String password = "securePassword";

        // WHEN
        MongoUserEntity entity = new MongoUserEntity(id, username, email, password);

        // THEN
        assertEquals(id, entity.getId());
        assertEquals(username, entity.getUsername());
        assertEquals(email, entity.getEmail());
        assertEquals(password, entity.getPassword());
    }

    // Test for setId and getId
    @Test
    void givenId_whenSet_thenShouldBeRetrievable() {
        // GIVEN
        ObjectId id = new ObjectId();

        // WHEN
        mongoUserEntity.setId(id);

        // THEN
        assertEquals(id, mongoUserEntity.getId());
    }

    // Test for setUsername and getUsername
    @Test
    void givenUsername_whenSet_thenShouldBeRetrievable() {
        // GIVEN
        String username = "testUser";

        // WHEN
        mongoUserEntity.setUsername(username);

        // THEN
        assertEquals(username, mongoUserEntity.getUsername());
    }

    // Test for setEmail and getEmail
    @Test
    void givenEmail_whenSet_thenShouldBeRetrievable() {
        // GIVEN
        String email = "test@example.com";

        // WHEN
        mongoUserEntity.setEmail(email);

        // THEN
        assertEquals(email, mongoUserEntity.getEmail());
    }

    // Test for setPassword and getPassword
    @Test
    void givenPassword_whenSet_thenShouldBeRetrievable() {
        // GIVEN
        String password = "securePassword";

        // WHEN
        mongoUserEntity.setPassword(password);

        // THEN
        assertEquals(password, mongoUserEntity.getPassword());
    }

    // Test for convertFrom method
    @Test
    void givenUser_whenConvertFrom_thenMongoUserEntityShouldBeCreated() {
        // GIVEN
        User user = new User("507f1f77bcf86cd799439011", "testUser", "test@example.com", "securePassword");

        // WHEN
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    // Test for convertFrom method with invalid ObjectId
    @Test
    void givenUserWithInvalidObjectId_whenConvertFrom_thenShouldThrowException() {
        // GIVEN
        User user = new User("invalidObjectId", "testUser", "test@example.com", "securePassword");

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    // Test for convertTo method
    @Test
    void givenMongoUserEntity_whenConvertTo_thenUserShouldBeCreated() {
        // GIVEN
        ObjectId id = new ObjectId();
        mongoUserEntity.setId(id);
        mongoUserEntity.setUsername("testUser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");

        // WHEN
        User user = mongoUserEntity.convertTo();

        // THEN
        assertEquals(id.toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    // Test for convertTo method with null id
    @Test
    void givenMongoUserEntityWithNullId_whenConvertTo_thenShouldThrowException() {
        // GIVEN
        mongoUserEntity.setId(null);
        mongoUserEntity.setUsername("testUser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securePassword");

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
