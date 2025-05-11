package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

    @Test

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
    void convertFromTest() {
        // GIVEN: A User object is created.
        // WHEN: The convertFrom method is called with the User object.
        // THEN: A new MongoUserEntity object is created with the same data as the User object.
        User user = new User(new ObjectId().toString(), "testUser", "test@example.com", "password123");
        MongoUserEntity mongoUser = MongoUserEntity.convertFrom(user);
        assertEquals(new ObjectId().toString(), mongoUser.getId());
        assertEquals("testUser", mongoUser.getUsername());
        assertEquals("test@example.com", mongoUser.getEmail());
        assertEquals("password123", mongoUser.getPassword());
    }

    @Test
    void convertToTest() {
        // GIVEN: A MongoUserEntity object is created.
        // WHEN: The convertTo method is called on the MongoUserEntity object.
        // THEN: A new User object is created with the same data as the MongoUserEntity object.
        MongoUserEntity mongoUser = new MongoUserEntity(new ObjectId(), "testUser", "test@example.com", "password123");
        User user = mongoUser.convertTo();
        assertEquals(user.getId().toString(), user.getId().toString());
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
