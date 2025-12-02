package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserResponseGeneratedAiTests {

    private static final String TEST_ID = "12345";
    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_EMAIL = "testuser@example.com";

    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        // Reset the state before each test
        userResponse = new UserResponse(TEST_ID, TEST_USERNAME, TEST_EMAIL);
    }

    @Test
    void givenValidUserResponse_whenGetId_thenReturnsCorrectId() {
        // GIVEN: A UserResponse object with a predefined ID

        // WHEN: The getId method is called
        String id = userResponse.getId();

        // THEN: The returned ID should match the predefined ID
        assertEquals(TEST_ID, id);
    }

    @Test
    void givenValidUserResponse_whenGetUsername_thenReturnsCorrectUsername() {
        // GIVEN: A UserResponse object with a predefined username

        // WHEN: The getUsername method is called
        String username = userResponse.getUsername();

        // THEN: The returned username should match the predefined username
        assertEquals(TEST_USERNAME, username);
    }

    @Test
    void givenValidUserResponse_whenGetEmail_thenReturnsCorrectEmail() {
        // GIVEN: A UserResponse object with a predefined email

        // WHEN: The getEmail method is called
        String email = userResponse.getEmail();

        // THEN: The returned email should match the predefined email
        assertEquals(TEST_EMAIL, email);
    }
}
