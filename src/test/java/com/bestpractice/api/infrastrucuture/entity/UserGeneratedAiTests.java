package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

public class UserGeneratedAiTests {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testGetAndSetId() {
        // GIVEN: A new User object is created.
        // WHEN: The id is set to "testId".
        user.setId("testId");
        // THEN: The id is set to "testId".
        assertEquals("testId", user.getId());
    }

    @Test
    void testGetUsername() {
        // GIVEN: A new User object is created.
        // WHEN: The username is set to "testUser".
        user.setUsername("testUser");
        // THEN: The username is "testUser".
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void testGetEmail() {
        // GIVEN: A new User object is created.
        // WHEN: The email is set to "test@example.com".
        user.setEmail("test@example.com");
        // THEN: The email is "test@example.com".
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        // GIVEN: A new User object is created.
        // WHEN: The password is set to "testPassword".
        user.setPassword("testPassword");
        // THEN: The password is "testPassword".
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    void testOnPrePersist() {
        // GIVEN: A new User object is created.
        // WHEN: The onPrePersist method is called.
        // THEN: The createdAt field is set to the current date.
        user.onPrePersist();
        // THEN: The createdAt field contains the current date.
        assertEquals(new Date(), user.getCreatedAt());
    }
}
