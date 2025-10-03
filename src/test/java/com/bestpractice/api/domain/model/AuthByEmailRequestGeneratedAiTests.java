package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void givenValidEmail_whenSetEmail_thenGetEmailReturnsSameValue() {
        // GIVEN: a valid email string
        String email = "test@example.com";

        // WHEN: setting the email
        authByEmailRequest.setEmail(email);

        // THEN: the getter should return the same email
        assertEquals(email, authByEmailRequest.getEmail());
    }

    @Test
    void givenNullEmail_whenSetEmail_thenGetEmailReturnsNull() {
        // GIVEN: a null email
        String email = null;

        // WHEN: setting the email to null
        authByEmailRequest.setEmail(email);

        // THEN: the getter should return null
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void givenValidPassword_whenSetPassword_thenGetPasswordReturnsSameValue() {
        // GIVEN: a valid password string
        String password = "securePassword123";

        // WHEN: setting the password
        authByEmailRequest.setPassword(password);

        // THEN: the getter should return the same password
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void givenNullPassword_whenSetPassword_thenGetPasswordReturnsNull() {
        // GIVEN: a null password
        String password = null;

        // WHEN: setting the password to null
        authByEmailRequest.setPassword(password);

        // THEN: the getter should return null
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void givenNewInstance_whenGetEmail_thenReturnsNull() {
        // GIVEN: a new instance without setting email

        // WHEN: getting email
        String result = authByEmailRequest.getEmail();

        // THEN: should be null
        assertNull(result);
    }

    @Test
    void givenNewInstance_whenGetPassword_thenReturnsNull() {
        // GIVEN: a new instance without setting password

        // WHEN: getting password
        String result = authByEmailRequest.getPassword();

        // THEN: should be null
        assertNull(result);
    }

    @Test
    void givenEmailChangedTwice_whenGetEmail_thenReturnsLatestValue() {
        // GIVEN: two different email values
        String firstEmail = "first@example.com";
        String secondEmail = "second@example.com";

        // WHEN: setting email twice
        authByEmailRequest.setEmail(firstEmail);
        authByEmailRequest.setEmail(secondEmail);

        // THEN: should return the latest value
        assertEquals(secondEmail, authByEmailRequest.getEmail());
    }

    @Test
    void givenPasswordChangedTwice_whenGetPassword_thenReturnsLatestValue() {
        // GIVEN: two different password values
        String firstPassword = "firstPass123";
        String secondPassword = "secondPass456";

        // WHEN: setting password twice
        authByEmailRequest.setPassword(firstPassword);
        authByEmailRequest.setPassword(secondPassword);

        // THEN: should return the latest value
        assertEquals(secondPassword, authByEmailRequest.getPassword());
    }
}
