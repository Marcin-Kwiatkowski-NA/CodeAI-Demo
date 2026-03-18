package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserResponseGeneratedAiTests {

    private static final String TEST_ID = "123";
    private static final String TEST_USERNAME = "testUser";
    private static final String TEST_EMAIL = "test@example.com";

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userResponse = new UserResponse(TEST_ID, TEST_USERNAME, TEST_EMAIL);
    }

    @Test
    void shouldReturnCorrectId() {
        // GIVEN
        String expectedId = TEST_ID;

        // WHEN
        String actualId = userResponse.getId();

        // THEN
        assertEquals(expectedId, actualId);
    }

    @Test
    void shouldReturnCorrectUsername() {
        // GIVEN
        String expectedUsername = TEST_USERNAME;

        // WHEN
        String actualUsername = userResponse.getUsername();

        // THEN
        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void shouldReturnCorrectEmail() {
        // GIVEN
        String expectedEmail = TEST_EMAIL;

        // WHEN
        String actualEmail = userResponse.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
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
        assertNull(id);
        assertNull(username);
        assertNull(email);
    }

    @Test
    void shouldNotThrowExceptionWhenCreatingWithNullValues() {
        // GIVEN
        String id = null;
        String username = null;
        String email = null;

        // WHEN
        UserResponse response = new UserResponse(id, username, email);

        // THEN
        assertNull(response.getId());
        assertNull(response.getUsername());
        assertNull(response.getEmail());
    }
}
