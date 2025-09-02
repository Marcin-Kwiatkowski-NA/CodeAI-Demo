package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import org.junit.jupiter.api.extension.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
@MockitoJUnitRunner
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
        MongoUserEntity mongoUser = MongoUserEntity.convertFrom(user);
        // THEN the id should be the same as the user's id
        assertEquals(new ObjectId("64f8e4d7848999894d9f9a1a"), mongoUser.getId());
        // THEN the username should be the same as the user's username
        assertEquals("testUser", mongoUser.getUsername());
        // THEN the email should be the same as the user's email
        assertEquals("test@example.com", mongoUser.getEmail());
        // THEN the password should be the same as the user's password
        assertEquals("password123", mongoUser.getPassword());
    }

    @Test
    void testConvertTo() {
        // GIVEN a MongoUserEntity object
        MongoUserEntity mongoUser = new MongoUserEntity(new ObjectId("64f8e4d7848999894d9f9a1a"), "testUser", "test@example.com", "password123");
        // WHEN the convertTo method is called
        // THEN a User object should be created with the same data
        User user = mongoUser.convertTo();
        // THEN the id should be the same as the mongoUser's id
        assertEquals("64f8e4d7848999894d9f9a1a", user.getId());
        // THEN the username should be the same as the mongoUser's username
        assertEquals("testUser", user.getUsername());
        // THEN the password should be the same as the mongoUser's password
        assertEquals("password123", user.getPassword());
        // THEN the email should be the same as the mongoUser's email
        assertEquals("test@example.com", user.getEmail());
    }
}
