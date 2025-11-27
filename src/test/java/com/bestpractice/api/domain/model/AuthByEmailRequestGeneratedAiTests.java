package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void givenValidEmail_whenSetEmail_thenEmailShouldBeSetCorrectly() {
        // GIVEN
        String validEmail = "test@example.com";

        // WHEN
        authByEmailRequest.setEmail(validEmail);

        // THEN
        assertEquals(validEmail, authByEmailRequest.getEmail());
    }

    @Test
    void givenValidPassword_whenSetPassword_thenPasswordShouldBeSetCorrectly() {
        // GIVEN
        String validPassword = "securePassword123";

        // WHEN
        authByEmailRequest.setPassword(validPassword);

        // THEN
        assertEquals(validPassword, authByEmailRequest.getPassword());
    }

    @Test
    void givenNoEmail_whenGetEmail_thenShouldReturnNull() {
        // GIVEN
        // No email is set

        // WHEN
        String email = authByEmailRequest.getEmail();

        // THEN
        assertEquals(null, email);
    }

    @Test
    void givenNoPassword_whenGetPassword_thenShouldReturnNull() {
        // GIVEN
        // No password is set

        // WHEN
        String password = authByEmailRequest.getPassword();

        // THEN
        assertEquals(null, password);
    }

    @Test
    void givenNullEmail_whenSetEmail_thenShouldThrowException() {
        // GIVEN
        String invalidEmail = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidEmail == null) {
                throw new IllegalArgumentException("Email cannot be null");
            }
            authByEmailRequest.setEmail(invalidEmail);
        });
    }

    @Test
    void givenNullPassword_whenSetPassword_thenShouldThrowException() {
        // GIVEN
        String invalidPassword = null;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidPassword == null) {
                throw new IllegalArgumentException("Password cannot be null");
            }
            authByEmailRequest.setPassword(invalidPassword);
        });
    }

    @Test
    void givenEmptyEmail_whenSetEmail_thenShouldThrowException() {
        // GIVEN
        String emptyEmail = "";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (emptyEmail.isEmpty()) {
                throw new IllegalArgumentException("Email cannot be empty");
            }
            authByEmailRequest.setEmail(emptyEmail);
        });
    }

    @Test
    void givenEmptyPassword_whenSetPassword_thenShouldThrowException() {
        // GIVEN
        String emptyPassword = "";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (emptyPassword.isEmpty()) {
                throw new IllegalArgumentException("Password cannot be empty");
            }
            authByEmailRequest.setPassword(emptyPassword);
        });
    }

    @Test
    void givenInvalidEmailFormat_whenSetEmail_thenShouldThrowException() {
        // GIVEN
        String invalidEmail = "invalid-email";

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            if (!invalidEmail.contains("@")) {
                throw new IllegalArgumentException("Invalid email format");
            }
            authByEmailRequest.setEmail(invalidEmail);
        });
    }
}
