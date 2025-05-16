package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testConstructor() {
        // GIVEN: A new User object is created.
        // WHEN: The constructor is called with provided values.
        // THEN: The user's id, username, email, and password are initialized with the given values.
        User newUser = new User("123", "testUser", "test@example.com", "password123");
        assertEquals("123", newUser.getId());
        assertEquals("testUser", newUser.getUsername());
        assertEquals("test@example.com", newUser.getEmail());
        assertEquals("password123", newUser.getPassword());
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN: A User object is created.
        // WHEN: The getters and setters for each field are called.
        // THEN: The values of the fields are correctly retrieved and set.

        user.setUsername("newUsername");
        user.setEmail("newEmail@example.com");
        user.setPassword("newPassword");

        assertEquals("newUsername", user.getUsername());
        assertEquals("newEmail@example.com", user.getEmail());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testCreatedAt() {
        // GIVEN: A User object is created.
        // WHEN: The onPrePersist method is called (simulated by calling the method directly).
        // THEN: The createdAt field is initialized with the current date and time.

        user.onPrePersist();
        Date createdAt = user.getCreatedAt();
        assertNotNull(createdAt);
    }
}
