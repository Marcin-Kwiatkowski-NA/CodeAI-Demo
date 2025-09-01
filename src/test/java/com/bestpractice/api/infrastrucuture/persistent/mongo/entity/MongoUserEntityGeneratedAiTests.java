package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.MockitoExtension;
import org.junit.jupiter.api.extension.MockitoJUnitRunner;
import org.junit.jupiter.api.Test;

@MockitoJUnitRunner(useMockitoExtension = true)
public class MongoUserEntityGeneratedAiTests {

    private MongoUserEntity mongoUserEntity;

    @BeforeEach
    void setUp() {
        mongoUserEntity = new MongoUserEntity();
    }

    @Test
    void testConvertFrom() {
        // GIVEN a User object
        User user = new User("user_id", "testUser", "test@example.com", "password123");
        // WHEN the convertFrom method is called
        // THEN a MongoUserEntity should be created with the same data
        MongoUserEntity convertedEntity = MongoUserEntity.convertFrom(user);
        // THEN the id should be the same as the user's id
        assertEquals(new ObjectId("64f4e98a98d7792834a99a1e"), convertedEntity.getId());
        // THEN the username should be the same as the user's username
        assertEquals("testUser", convertedEntity.getUsername());
        // THEN the email should be the same as the user's email
        assertEquals("test@example.com", convertedEntity.getEmail());
        // THEN the password should be the same as the user's password
        assertEquals("password123", convertedEntity.getPassword());
    }

    @Test
    void testConvertTo() {
        // GIVEN a MongoUserEntity object
        MongoUserEntity entity = new MongoUserEntity(new ObjectId("64f4e98a98d7792834a99a1e"), "testUser", "test@example.com", "password123");
        // WHEN the convertTo method is called
        // THEN a User object should be created with the same data
        User convertedUser = entity.convertTo();
        // THEN the id should be the same as the entity's id
        assertEquals("64f4e98a98d7792834a99a1e", convertedUser.getId());
        // THEN the username should be the same as the entity's username
        assertEquals("testUser", convertedUser.getUsername());
        // THEN the password should be the same as the entity's password
        assertEquals("password123", convertedUser.getPassword());
        // THEN the email should be the same as the entity's email
        assertEquals("test@example.com", convertedUser.getEmail());
    }
}
