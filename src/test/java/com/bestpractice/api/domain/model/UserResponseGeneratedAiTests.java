package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // Set up the UserResponse object before each test
        userResponse = new UserResponse("123", "john.doe", "john.doe@example.com");
    }

    @Test
    void getId() {
        // GIVEN: A UserResponse object is created.
        // WHEN: getId() is called.
        // THEN: The id is returned.
        assertEquals("123", userResponse.getId());
    }

    @Test
    void getUsername() {
        // GIVEN: A UserResponse object is created.
        // WHEN: getUsername() is called.
        // THEN: The username is returned.
        assertEquals("john.doe", userResponse.getUsername());
    }

    @Test
    void getEmail() {
        // GIVEN: A UserResponse object is created.
        // WHEN: getEmail() is called.
        // THEN: The email is returned.
        assertEquals("john.doe@example.com", userResponse.getEmail());
    }
}
