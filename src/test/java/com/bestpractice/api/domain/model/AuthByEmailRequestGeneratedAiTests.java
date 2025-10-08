package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    void testSetAndGetEmail() {
        // GIVEN: an email value to set
        String email = "test@example.com";

        // WHEN: setting the email on the request object
        authByEmailRequest.setEmail(email);

        // THEN: the retrieved email should match the set value
        assertEquals(email, authByEmailRequest.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a password value to set
        String password = "securePassword123";

        // WHEN: setting the password on the request object
        authByEmailRequest.setPassword(password);

        // THEN: the retrieved password should match the set value
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testEmailInitiallyNull() {
        // GIVEN: a newly created request object

        // WHEN: retrieving the email without setting it

        // THEN: the email should be null
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testPasswordInitiallyNull() {
        // GIVEN: a newly created request object

        // WHEN: retrieving the password without setting it

        // THEN: the password should be null
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailWithNullValue() {
        // GIVEN: a null email value
        String email = null;

        // WHEN: setting the email to null
        authByEmailRequest.setEmail(email);

        // THEN: the retrieved email should be null
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordWithNullValue() {
        // GIVEN: a null password value
        String password = null;

        // WHEN: setting the password to null
        authByEmailRequest.setPassword(password);

        // THEN: the retrieved password should be null
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailWithInvalidFormatDoesNotThrowException() {
        // GIVEN: an invalid email format
        String invalidEmail = "invalid-email-format";

        // WHEN: setting the invalid email
        authByEmailRequest.setEmail(invalidEmail);

        // THEN: the retrieved email should match the invalid format (no validation at setter level)
        assertEquals(invalidEmail, authByEmailRequest.getEmail());
    }
}
