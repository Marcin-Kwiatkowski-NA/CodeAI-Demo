package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

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
        // GIVEN: Setup the UserRequest with valid data
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: The UserRequest converts the data to a User object
        User user = userRequest.convert("123", "encodedPassword");

        // THEN: Verify the converted User object
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("123", user.getId());
    }

    @Test
    void testConvert_emptyUsername() {
        // GIVEN: Setup the UserRequest with empty username
        userRequest.setUsername("");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: The UserRequest converts the data to a User object
        User user = userRequest.convert("123", "encodedPassword");

        // THEN: Verify the converted User object
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("123", user.getId());
    }

    @Test
    void testConvert_emptyEmail() {
        // GIVEN: Setup the UserRequest with empty email
        userRequest.setUsername("testUser");
        userRequest.setEmail("");
        userRequest.setPassword("password123");

        // WHEN: The UserRequest converts the data to a User object
        User user = userRequest.convert("123", "encodedPassword");

        // THEN: Verify the converted User object
        assertEquals("testUser", user.getUsername());
        assertEquals("", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("123", user.getId());
    }

    @Test
    void testConvert_emptyPassword() {
        // GIVEN: Setup the UserRequest with empty password
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("");

        // WHEN: The UserRequest converts the data to a User object
        User user = userRequest.convert("123", "encodedPassword");

        // THEN: Verify the converted User object
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("", user.getPassword());
        assertEquals("123", user.getId());
    }
}
