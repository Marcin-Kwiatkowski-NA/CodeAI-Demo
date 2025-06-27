package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.Extension.DefaultExtension()
public class UserRequestGeneratedAiTests {

    @Test
    public void testConvert() {
        // GIVEN: Setup
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: Convert UserRequest to User
        User user = userRequest.convert("id123", "encodedPassword");

        // THEN: Verify the converted User object
        assertEquals("id123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        // GIVEN: Setup
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: Set username, email, and password
        userRequest.setUsername("newUsername");
        userRequest.setEmail("newEmail");
        userRequest.setPassword("newPassword");

        // THEN: Verify the updated values
        assertEquals("newUsername", userRequest.getUsername());
        assertEquals("newEmail", userRequest.getEmail());
        assertEquals("newPassword", userRequest.getPassword());
    }

    @BeforeEach
    public void setUp() {
        // Reset UserRequest object before each test
        UserRequest userRequest = new UserRequest();
    }
}
