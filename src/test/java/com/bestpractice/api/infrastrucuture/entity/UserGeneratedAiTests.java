package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    public void testUserConstructor() {
        // GIVEN a new User object is created
        // WHEN the constructor is called with valid arguments
        // THEN the User object's properties are initialized correctly
        User user = new User("123", "testUser", "test@example.com", "password");
        assertEquals("123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testGettersAndSetters() {
        // GIVEN a User object
        User user = new User("456", "anotherUser", "another@example.com", "anotherPassword");

        // WHEN the getters and setters are called
        String id = user.getId();
        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        // THEN the getters return the correct values
        assertEquals("456", id);
        assertEquals("anotherUser", username);
        assertEquals("another@example.com", email);
        assertEquals("anotherPassword", password);

        user.setId("789");
        user.setUsername("newUser");
        user.setEmail("new@example.com");
        user.setPassword("newPassword");

        assertEquals("789", user.getId());
        assertEquals("newUser", user.getUsername());
        assertEquals("new@example.com", user.getEmail());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testCreatedAt() {
        // GIVEN a new User object is created
        // WHEN the onPrePersist method is called
        // THEN the createdAt field is initialized with the current date and time
        User user = new User("111", "testUser", "test@example.com", "password");
        Date createdAt = user.getCreatedAt();
        assertEquals(new Date(), createdAt);
    }
}
