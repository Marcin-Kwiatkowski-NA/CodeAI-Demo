package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(id).isEqualTo(TEST_ID);
    }

    @Test
    void givenValidUsername_whenGetUsername_thenReturnsCorrectUsername() {
        // GIVEN: A UserResponse object initialized with a valid username

        // WHEN: getUsername is called
        String username = userResponse.getUsername();

        // THEN: The returned username matches the expected value
        assertThat(username).isEqualTo(TEST_USERNAME);
    }

    @Test
    void givenValidEmail_whenGetEmail_thenReturnsCorrectEmail() {
        // GIVEN: A UserResponse object initialized with a valid email

        // WHEN: getEmail is called
        String email = userResponse.getEmail();

        // THEN: The returned email matches the expected value
        assertThat(email).isEqualTo(TEST_EMAIL);
    }
}
