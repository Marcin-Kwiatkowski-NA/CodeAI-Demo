package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void givenValidEmail_whenSetEmail_thenGetEmailReturnsSameValue() {
        // GIVEN
        String expectedEmail = "user@example.com";

        // WHEN
        authByEmailRequest.setEmail(expectedEmail);

        // THEN
        assertEquals(expectedEmail, authByEmailRequest.getEmail());
    }

    @Test
    void givenValidPassword_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN
        String expectedPassword = "securePassword123";

        // WHEN
        authByEmailRequest.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, authByEmailRequest.getPassword());
    }

    @Test
    void givenNewInstance_whenGetEmail_thenReturnsNull() {
        // GIVEN
        // new instance created in setUp()

        // WHEN
        String email = authByEmailRequest.getEmail();

        // THEN
        assertNull(email);
    }

    @Test
    void givenNewInstance_whenGetPassword_thenReturnsNull() {
        // GIVEN
        // new instance created in setUp()

        // WHEN
        String password = authByEmailRequest.getPassword();

        // THEN
        assertNull(password);
    }

    @Test
    void givenNullEmail_whenSetEmail_thenGetEmailReturnsNull() {
        // GIVEN
        String email = null;

        // WHEN
        authByEmailRequest.setEmail(email);

        // THEN
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void givenNullPassword_whenSetPassword_thenGetPasswordReturnsNull() {
        // GIVEN
        String password = null;

        // WHEN
        authByEmailRequest.setPassword(password);

        // THEN
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void givenInvalidEmailFormat_whenSetEmail_thenGetEmailReturnsSameValue() {
        // GIVEN
        String invalidEmail = "invalid-email-format";

        // WHEN
        authByEmailRequest.setEmail(invalidEmail);

        // THEN
        assertEquals(invalidEmail, authByEmailRequest.getEmail());
    }

    @Test
    void givenEmptyPassword_whenSetPassword_thenGetPasswordReturnsEmptyString() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        authByEmailRequest.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, authByEmailRequest.getPassword());
    }

    @Test
    void givenNullObject_whenCallSetEmail_thenThrowsNullPointerException() {
        // GIVEN
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> nullRequest.setEmail("test@example.com"));
    }

    @Test
    void givenNullObject_whenCallSetPassword_thenThrowsNullPointerException() {
        // GIVEN
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> nullRequest.setPassword("password123"));
    }

    @Test
    void givenNullObject_whenCallGetEmail_thenThrowsNullPointerException() {
        // GIVEN
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> nullRequest.getEmail());
    }

    @Test
    void givenNullObject_whenCallGetPassword_thenThrowsNullPointerException() {
        // GIVEN
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> nullRequest.getPassword());
    }
}
