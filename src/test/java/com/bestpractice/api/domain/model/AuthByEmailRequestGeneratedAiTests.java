package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
        // GIVEN - prepare test data
        String testEmail = "user@example.com";

        // WHEN - set email
        authByEmailRequest.setEmail(testEmail);

        // THEN - verify email is correctly set and retrieved
        assertEquals(testEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN - prepare test data
        String testPassword = "securePassword123";

        // WHEN - set password
        authByEmailRequest.setPassword(testPassword);

        // THEN - verify password is correctly set and retrieved
        assertEquals(testPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testEmailInitiallyNull() {
        // GIVEN - new instance created in setup

        // WHEN - no email set

        // THEN - verify email is null initially
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testPasswordInitiallyNull() {
        // GIVEN - new instance created in setup

        // WHEN - no password set

        // THEN - verify password is null initially
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailToNullDoesNotThrowException() {
        // GIVEN - valid instance

        // WHEN - set email to null
        authByEmailRequest.setEmail(null);

        // THEN - verify no exception and value is null
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordToNullDoesNotThrowException() {
        // GIVEN - valid instance

        // WHEN - set password to null
        authByEmailRequest.setPassword(null);

        // THEN - verify no exception and value is null
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testResetStateBetweenTests() {
        // GIVEN - set values
        authByEmailRequest.setEmail("reset@example.com");
        authByEmailRequest.setPassword("resetPassword");

        // WHEN - reset state manually
        setUp();

        // THEN - verify reset state
        assertNull(authByEmailRequest.getEmail());
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testNoExceptionThrownWhenSettingEmptyStrings() {
        // GIVEN - valid instance

        // WHEN - set empty strings
        authByEmailRequest.setEmail("");
        authByEmailRequest.setPassword("");

        // THEN - verify values are empty strings
        assertEquals("", authByEmailRequest.getEmail());
        assertEquals("", authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailAndPasswordSequentially() {
        // GIVEN - prepare test data
        String email = "test@domain.com";
        String password = "pass123";

        // WHEN - set email and password sequentially
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setPassword(password);

        // THEN - verify both values are correctly set
        assertEquals(email, authByEmailRequest.getEmail());
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailAndPasswordToNullSequentiallyDoesNotThrowException() {
        // GIVEN - valid instance

        // WHEN - set both fields to null
        authByEmailRequest.setEmail(null);
        authByEmailRequest.setPassword(null);

        // THEN - verify both are null and no exception thrown
        assertNull(authByEmailRequest.getEmail());
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailAndPasswordMultipleTimes() {
        // GIVEN - prepare multiple values
        String firstEmail = "first@example.com";
        String secondEmail = "second@example.com";
        String firstPassword = "firstPass";
        String secondPassword = "secondPass";

        // WHEN - set values multiple times
        authByEmailRequest.setEmail(firstEmail);
        authByEmailRequest.setEmail(secondEmail);
        authByEmailRequest.setPassword(firstPassword);
        authByEmailRequest.setPassword(secondPassword);

        // THEN - verify last values are retained
        assertEquals(secondEmail, authByEmailRequest.getEmail());
        assertEquals(secondPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailAndPasswordWithSpecialCharacters() {
        // GIVEN - prepare special character data
        String specialEmail = "user+test@domain.co.uk";
        String specialPassword = "p@$$w0rd!";

        // WHEN - set special character values
        authByEmailRequest.setEmail(specialEmail);
        authByEmailRequest.setPassword(specialPassword);

        // THEN - verify values are correctly stored
        assertEquals(specialEmail, authByEmailRequest.getEmail());
        assertEquals(specialPassword, authByEmailRequest.getPassword());
    }
}
