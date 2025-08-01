package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
class UserResponseGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    void testGetId() {
        // GIVEN a UserResponse object
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        // WHEN we call getId()
        String id = userResponse.getId();
        // THEN the returned id should be "123"
        assertEquals("123", id);
    }

    @Test
    void testGetUsername() {
        // GIVEN a UserResponse object
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        // WHEN we call getUsername()
        String username = userResponse.getUsername();
        // THEN the returned username should be "john.doe"
        assertEquals("john.doe", username);
    }

    @Test
    void testGetEmail() {
        // GIVEN a UserResponse object
        UserResponse userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
        // WHEN we call getEmail()
        String email = userResponse.getEmail();
        // THEN the returned email should be "john.doe@example.com"
        assertEquals("john.doe@example.com", email);
    }
}

class MyExtension implements Extension {}
