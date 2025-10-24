package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    public void setUp() {
        mongoUserEntity = new MongoUserEntity();
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439011"));
        mongoUserEntity.setUsername("testuser");
        mongoUserEntity.setEmail("test@example.com");
        mongoUserEntity.setPassword("securepassword");
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN: a new ObjectId
        ObjectId newId = new ObjectId("507f1f77bcf86cd799439012");

        // WHEN: setting the id
        mongoUserEntity.setId(newId);

        // THEN: the id should be updated
        assertEquals(newId, mongoUserEntity.getId());
    }

    @Test
    public void testSetAndGetUsername() {
        // GIVEN: a new username
        String newUsername = "newuser";

        // WHEN: setting the username
        mongoUserEntity.setUsername(newUsername);

        // THEN: the username should be updated
        assertEquals(newUsername, mongoUserEntity.getUsername());
    }

    @Test
    public void testSetAndGetEmail() {
        // GIVEN: a new email
        String newEmail = "new@example.com";

        // WHEN: setting the email
        mongoUserEntity.setEmail(newEmail);

        // THEN: the email should be updated
        assertEquals(newEmail, mongoUserEntity.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        // GIVEN: a new password
        String newPassword = "newsecurepassword";

        // WHEN: setting the password
        mongoUserEntity.setPassword(newPassword);

        // THEN: the password should be updated
        assertEquals(newPassword, mongoUserEntity.getPassword());
    }

    @Test
    public void testConvertFromUser() {
        // GIVEN: a User object
        User user = new User("507f1f77bcf86cd799439013", "convertuser", "convert@example.com", "convertpassword");

        // WHEN: converting from User to MongoUserEntity
        MongoUserEntity entity = MongoUserEntity.convertFrom(user);

        // THEN: the fields should match
        assertEquals(new ObjectId(user.getId()), entity.getId());
        assertEquals(user.getUsername(), entity.getUsername());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getPassword(), entity.getPassword());
    }

    @Test
    public void testConvertFromUserWithInvalidObjectIdThrowsException() {
        // GIVEN: a User object with invalid ObjectId string
        User user = new User("invalid_object_id", "user", "email@example.com", "password");

        // WHEN & THEN: converting should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertFromUserWithNullIdThrowsException() {
        // GIVEN: a User object with null id
        User user = new User(null, "user", "email@example.com", "password");

        // WHEN & THEN: converting should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> MongoUserEntity.convertFrom(user));
    }

    @Test
    public void testConvertToUser() {
        // GIVEN: a MongoUserEntity object with data
        mongoUserEntity.setId(new ObjectId("507f1f77bcf86cd799439014"));
        mongoUserEntity.setUsername("touser");
        mongoUserEntity.setEmail("touser@example.com");
        mongoUserEntity.setPassword("topassword");

        // WHEN: converting to User
        User user = mongoUserEntity.convertTo();

        // THEN: the fields should match
        assertEquals(mongoUserEntity.getId().toString(), user.getId());
        assertEquals(mongoUserEntity.getUsername(), user.getUsername());
        assertEquals(mongoUserEntity.getPassword(), user.getPassword());
    }

    @Test
    public void testConvertToUserWithNullIdThrowsException() {
        // GIVEN: a MongoUserEntity with null id
        mongoUserEntity.setId(null);

        // WHEN & THEN: converting should throw NullPointerException
        assertThrows(NullPointerException.class, () -> mongoUserEntity.convertTo());
    }
}
