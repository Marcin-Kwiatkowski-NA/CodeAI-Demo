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

class UserResponseGeneratedAiTests {

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
    void givenValidId_whenGetId_thenReturnsCorrectId() {
        // GIVEN: A UserResponse object initialized with a valid ID

        // WHEN: getId is called
        String id = userResponse.getId();

        // THEN: The returned ID matches the expected value
        assertEquals(TEST_ID, id);
    }

    @Test
    void givenValidUsername_whenGetUsername_thenReturnsCorrectUsername() {
        // GIVEN: A UserResponse object initialized with a valid username

        // WHEN: getUsername is called
        String username = userResponse.getUsername();

        // THEN: The returned username matches the expected value
        assertEquals(TEST_USERNAME, username);
    }

    @Test
    void givenValidEmail_whenGetEmail_thenReturnsCorrectEmail() {
        // GIVEN: A UserResponse object initialized with a valid email

        // WHEN: getEmail is called
        String email = userResponse.getEmail();

        // THEN: The returned email matches the expected value
        assertEquals(TEST_EMAIL, email);
    }
}
