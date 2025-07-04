package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.extension.ExtensionProvider.classExtensionMap;

@ExtendWith(MyExtension.class)
public class UserRequestGeneratedAiTests {

    @BeforeEach
    public void setUp() {
        // Reset the UserRequest object before each test.
        UserRequest userRequest = new UserRequest();
    }

    @Test
    public void testConvert() {
        // GIVEN: Create a UserRequest object with some data.
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: The UserRequest object is converted to a User object.
        User user = userRequest.convert("id123", "encodedPassword");

        // THEN: Verify that the converted User object has the correct data.
        assertEquals("id123", user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    public void testSettersAndGetters() {
        // GIVEN: Create a UserRequest object.
        UserRequest userRequest = new UserRequest();

        // WHEN: Set the username, email, and password.
        userRequest.setUsername("newTestUser");
        userRequest.setEmail("new@example.com");
        userRequest.setPassword("newPassword");

        // THEN: Verify that the getters return the correct values.
        assertEquals("newTestUser", userRequest.getUsername());
        assertEquals("new@example.com", userRequest.getEmail());
        assertEquals("newPassword", userRequest.getPassword());
    }
}
