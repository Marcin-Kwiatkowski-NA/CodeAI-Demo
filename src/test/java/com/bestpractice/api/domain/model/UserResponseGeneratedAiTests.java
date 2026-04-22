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
 * This class verifies that all public methods behave as expected.
 */
public class UserResponseGeneratedAiTests {

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // GIVEN - a valid UserResponse instance
        userResponse = new UserResponse("1", "testUser", "test@example.com");
    }

    @Test
    void shouldReturnCorrectId() {
        // GIVEN
        String expectedId = "1";

        // WHEN
        String actualId = userResponse.getId();

        // THEN
        assertEquals(expectedId, actualId, "The returned ID should match the expected value");
    }

    @Test
    void shouldReturnCorrectUsername() {
        // GIVEN
        String expectedUsername = "testUser";

        // WHEN
        String actualUsername = userResponse.getUsername();

        // THEN
        assertEquals(expectedUsername, actualUsername, "The returned username should match the expected value");
    }

    @Test
    void shouldReturnCorrectEmail() {
        // GIVEN
        String expectedEmail = "test@example.com";

        // WHEN
        String actualEmail = userResponse.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail, "The returned email should match the expected value");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // GIVEN
        UserResponse nullUserResponse = new UserResponse(null, null, null);

        // WHEN
        String id = nullUserResponse.getId();
        String username = nullUserResponse.getUsername();
        String email = nullUserResponse.getEmail();

        // THEN
        assertNull(id, "ID should be null when initialized with null");
        assertNull(username, "Username should be null when initialized with null");
        assertNull(email, "Email should be null when initialized with null");
    }
}
