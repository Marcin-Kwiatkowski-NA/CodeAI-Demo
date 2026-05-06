package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void testSetAndGetEmail() {
        // GIVEN: a valid email string
        String email = "user@example.com";

        // WHEN: setting the email on the object
        authByEmailRequest.setEmail(email);

        // THEN: the getter should return the same email
        assertEquals(email, authByEmailRequest.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a valid password string
        String password = "securePassword123";

        // WHEN: setting the password on the object
        authByEmailRequest.setPassword(password);

        // THEN: the getter should return the same password
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testEmailInitiallyNull() {
        // GIVEN: a new instance of AuthByEmailRequest

        // WHEN: checking the initial state
        String initialEmail = authByEmailRequest.getEmail();

        // THEN: email should be null initially
        assertNull(initialEmail);
    }

    @Test
    void testPasswordInitiallyNull() {
        // GIVEN: a new instance of AuthByEmailRequest

        // WHEN: checking the initial state
        String initialPassword = authByEmailRequest.getPassword();

        // THEN: password should be null initially
        assertNull(initialPassword);
    }

    @Test
    void testSetEmailToNull() {
        // GIVEN: an existing email value
        authByEmailRequest.setEmail("user@example.com");

        // WHEN: setting email to null
        authByEmailRequest.setEmail(null);

        // THEN: email should be null
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordToNull() {
        // GIVEN: an existing password value
        authByEmailRequest.setPassword("securePassword123");

        // WHEN: setting password to null
        authByEmailRequest.setPassword(null);

        // THEN: password should be null
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailWithEmptyString() {
        // GIVEN: an empty email string
        String email = "";

        // WHEN: setting the email on the object
        authByEmailRequest.setEmail(email);

        // THEN: the getter should return the same empty string
        assertEquals(email, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordWithEmptyString() {
        // GIVEN: an empty password string
        String password = "";

        // WHEN: setting the password on the object
        authByEmailRequest.setPassword(password);

        // THEN: the getter should return the same empty string
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailAndPasswordTogether() {
        // GIVEN: valid email and password
        String email = "user@example.com";
        String password = "securePassword123";

        // WHEN: setting both fields
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setPassword(password);

        // THEN: both getters should return correct values
        assertEquals(email, authByEmailRequest.getEmail());
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testNoExceptionThrownWhenSettingNullValues() {
        // GIVEN: null values for email and password

        // WHEN & THEN: setting null should not throw any exception
        authByEmailRequest.setEmail(null);
        authByEmailRequest.setPassword(null);

        assertNull(authByEmailRequest.getEmail());
        assertNull(authByEmailRequest.getPassword());
    }
}
