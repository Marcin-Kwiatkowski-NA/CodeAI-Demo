package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @Test
    void testConvert_validInput() {
        // GIVEN: Setup the UserRequest object with valid input values.
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: The convert method is called with "123" and "encodedPw".
        User user = userRequest.convert("123", "encodedPw");

        // THEN: Assert that the converted User object has the correct values.
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("123", user.getId());
    }

    @Test
    void testConvert_emptyUsername() {
        // GIVEN: Setup the UserRequest object with empty username.
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: The convert method is called with "123" and "encodedPw".
        User user = userRequest.convert("123", "encodedPw");

        // THEN: Assert that the converted User object has the correct values.
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("123", user.getId());
    }

    @Test
    void testConvert_emptyEmail() {
        // GIVEN: Setup the UserRequest object with empty email.
        userRequest.setUsername("testUser");
        userRequest.setPassword("password123");

        // WHEN: The convert method is called with "123" and "encodedPw".
        User user = userRequest.convert("123", "encodedPw");

        // THEN: Assert that the converted User object has the correct values.
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("123", user.getId());
    }
}
