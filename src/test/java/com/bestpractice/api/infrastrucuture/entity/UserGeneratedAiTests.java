package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testGetAndSetId() {
        // GIVEN: A new User object
        // WHEN: The id is set to "testId"
        user.setId("testId");
        // THEN: The id should be "testId"
        assertEquals("testId", user.getId());
    }

    @Test
    void testGetUsername() {
        // GIVEN: A new User object
        // WHEN: The username is set to "testUser"
        user.setUsername("testUser");
        // THEN: The username should be "testUser"
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void testGetEmail() {
        // GIVEN: A new User object
        // WHEN: The email is set to "test@example.com"
        user.setEmail("test@example.com");
        // THEN: The email should be "test@example.com"
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        // GIVEN: A new User object
        // WHEN: The password is set to "testPassword"
        user.setPassword("testPassword");
        // THEN: The password should be "testPassword"
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    void testConstructor() {
        // GIVEN: Parameters for the constructor
        // WHEN: The User object is instantiated
        // THEN: The id, username, email, and password should be set correctly
        User newUser = new User("id123", "newUser", "email@test.com", "secret");
        assertEquals("id123", newUser.getId());
        assertEquals("newUser", newUser.getUsername());
        assertEquals("email@test.com", newUser.getEmail());
        assertEquals("secret", newUser.getPassword());
    }
}
