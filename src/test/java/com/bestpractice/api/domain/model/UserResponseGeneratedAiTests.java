package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for {@link UserResponse}.
 * This class verifies that all public methods behave as expected.
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
        // GIVEN: A UserResponse instance with a predefined id
        String expectedId = "123";

        // WHEN: Retrieving the id
        String actualId = userResponse.getId();

        // THEN: The id should match the expected value
        assertEquals(expectedId, actualId);
    }

    @Test
    void shouldReturnCorrectUsername() {
        // GIVEN: A UserResponse instance with a predefined username
        String expectedUsername = "testUser";

        // WHEN: Retrieving the username
        String actualUsername = userResponse.getUsername();

        // THEN: The username should match the expected value
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void shouldReturnCorrectEmail() {
        // GIVEN: A UserResponse instance with a predefined email
        String expectedEmail = "test@example.com";

        // WHEN: Retrieving the email
        String actualEmail = userResponse.getEmail();

        // THEN: The email should match the expected value
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // GIVEN: A UserResponse instance with null values
        UserResponse nullUserResponse = new UserResponse(null, null, null);

        // WHEN: Retrieving fields
        String id = nullUserResponse.getId();
        String username = nullUserResponse.getUsername();
        String email = nullUserResponse.getEmail();

        // THEN: All fields should be null
        assertNull(id);
        assertNull(username);
        assertNull(email);
    }

    @Test
    void shouldNotThrowExceptionWhenCreatingWithValidValues() {
        // GIVEN: Valid input values
        String id = "456";
        String username = "validUser";
        String email = "valid@example.com";

        // WHEN: Creating a new UserResponse
        UserResponse response = new UserResponse(id, username, email);

        // THEN: No exception should be thrown and values should be correctly assigned
        assertEquals(id, response.getId());
        assertEquals(username, response.getUsername());
        assertEquals(email, response.getEmail());
    }

    @Test
    void shouldNotThrowExceptionWhenCreatingWithNullValues() {
        // GIVEN: Null input values
        String id = null;
        String username = null;
        String email = null;

        // WHEN: Creating a new UserResponse with nulls
        UserResponse response = new UserResponse(id, username, email);

        // THEN: No exception should be thrown and all fields should remain null
        assertNull(response.getId());
        assertNull(response.getUsername());
        assertNull(response.getEmail());
    }
}
