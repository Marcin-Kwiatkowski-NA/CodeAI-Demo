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

@Test
public class UserRequestGeneratedAiTests {

    @BeforeEach
    public void setUp() {
        // Reset the UserRequest object before each test
        UserRequest userRequest = new UserRequest();
    }

    @Test
    public void testConvert() {
        // GIVEN: Create a UserRequest object
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: Convert the UserRequest to a User object
        User user = userRequest.convert("id123", "encodedPassword");

        // THEN: Assert that the User object has the correct values
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("encodedPassword", user.getPassword());
        assertEquals("id123", user.getId());
    }

    @Test
    public void testSettersAndGetters() {
        // GIVEN: Create a UserRequest object
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: Set the username
        userRequest.setUsername("newUsername");

        // THEN: Assert that the username has been updated
        assertEquals("newUsername", userRequest.getUsername());

        // WHEN: Set the email
        userRequest.setEmail("newEmail@example.com");

        // THEN: Assert that the email has been updated
        assertEquals("newEmail@example.com", userRequest.getEmail());

        // WHEN: Set the password
        userRequest.setPassword("anotherPassword");

        // THEN: Assert that the password has been updated
        assertEquals("anotherPassword", userRequest.getPassword());
    }
}
