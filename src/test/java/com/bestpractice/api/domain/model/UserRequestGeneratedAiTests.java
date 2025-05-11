package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class UserRequestGeneratedAiTests {

    @Test
    public void testConvert() {
        // GIVEN: Setup the UserRequest object with some values.
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername("testUser");
        userRequest.setEmail("test@example.com");
        userRequest.setPassword("password123");

        // WHEN: The UserRequest object is converted to a User object.
        User user = userRequest.convert("123", "encodedPassword");

        // THEN: Verify the converted User object's attributes.
        assertEquals("123", user.getId());
        assertEquals("encodedPassword", user.getPassword());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void testSetters() {
        // GIVEN: Create a UserRequest object.
        UserRequest userRequest = new UserRequest();

        // WHEN: Set the username.
        userRequest.setUsername("newUsername");

        // THEN: Verify the username is set correctly.
        assertEquals("newUsername", userRequest.getUsername());

        // WHEN: Set the email.
        userRequest.setEmail("newEmail@example.com");

        // THEN: Verify the email is set correctly.
        assertEquals("newEmail@example.com", userRequest.getEmail());

        // WHEN: Set the password.
        userRequest.setPassword("anotherPassword");

        // THEN: Verify the password is set correctly.
        assertEquals("anotherPassword", userRequest.getPassword());
    }

    @BeforeEach
    public void setUp() {
        // Reset the UserRequest object before each test.
        UserRequest userRequest = new UserRequest();
    }
}
