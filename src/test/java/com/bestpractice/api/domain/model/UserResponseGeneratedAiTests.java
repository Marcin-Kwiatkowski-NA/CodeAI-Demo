package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for {@link UserResponse}.
 * This class verifies that the getters return the expected values and that null handling works correctly.
 */
public class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // GIVEN: Initialize a fresh instance before each test
        userResponse = new UserResponse("123", "testUser", "test@example.com");
    }

    @Test
    void shouldReturnCorrectId() {
        // GIVEN: A UserResponse instance with a known id
        String expectedId = "123";

        // WHEN: Retrieving the id
        String actualId = userResponse.getId();

        // THEN: The id should match the expected value
        assertEquals(expectedId, actualId, "The returned id should match the expected value");
    }

    @Test
    void shouldReturnCorrectUsername() {
        // GIVEN: A UserResponse instance with a known username
        String expectedUsername = "testUser";

        // WHEN: Retrieving the username
        String actualUsername = userResponse.getUsername();

        // THEN: The username should match the expected value
        assertEquals(expectedUsername, actualUsername, "The returned username should match the expected value");
    }

    @Test
    void shouldReturnCorrectEmail() {
        // GIVEN: A UserResponse instance with a known email
        String expectedEmail = "test@example.com";

        // WHEN: Retrieving the email
        String actualEmail = userResponse.getEmail();

        // THEN: The email should match the expected value
        assertEquals(expectedEmail, actualEmail, "The returned email should match the expected value");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // GIVEN: A UserResponse instance with null fields
        UserResponse nullUserResponse = new UserResponse(null, null, null);

        // WHEN: Retrieving the fields
        String id = nullUserResponse.getId();
        String username = nullUserResponse.getUsername();
        String email = nullUserResponse.getEmail();

        // THEN: All fields should be null
        assertNull(id, "Id should be null when initialized with null");
        assertNull(username, "Username should be null when initialized with null");
        assertNull(email, "Email should be null when initialized with null");
    }
}
