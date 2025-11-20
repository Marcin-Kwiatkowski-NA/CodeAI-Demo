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

public class UserResponseGeneratedAiTests {

    private static final String TEST_ID = "123";
    private static final String TEST_USERNAME = "testUser";
    private static final String TEST_EMAIL = "test@example.com";

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        userResponse = new UserResponse(TEST_ID, TEST_USERNAME, TEST_EMAIL);
    }

    @Test
    void givenUserResponse_whenGetId_thenReturnsCorrectId() {
        // GIVEN: A UserResponse object initialized with a specific ID
        // WHEN: The getId method is called
        String id = userResponse.getId();

        // THEN: The returned ID matches the expected value
        assertEquals(TEST_ID, id);
    }

    @Test
    void givenUserResponse_whenGetUsername_thenReturnsCorrectUsername() {
        // GIVEN: A UserResponse object initialized with a specific username
        // WHEN: The getUsername method is called
        String username = userResponse.getUsername();

        // THEN: The returned username matches the expected value
        assertEquals(TEST_USERNAME, username);
    }

    @Test
    void givenUserResponse_whenGetEmail_thenReturnsCorrectEmail() {
        // GIVEN: A UserResponse object initialized with a specific email
        // WHEN: The getEmail method is called
        String email = userResponse.getEmail();

        // THEN: The returned email matches the expected value
        assertEquals(TEST_EMAIL, email);
    }
}
