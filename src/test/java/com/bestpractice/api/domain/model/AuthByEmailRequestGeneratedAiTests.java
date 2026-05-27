package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
        // GIVEN
        String expectedEmail = "user@example.com";

        // WHEN
        authByEmailRequest.setEmail(expectedEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword123";

        // WHEN
        authByEmailRequest.setPassword(expectedPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testEmailInitiallyNull() {
        // GIVEN
        // No setup required

        // WHEN
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertNull(actualEmail);
    }

    @Test
    void testPasswordInitiallyNull() {
        // GIVEN
        // No setup required

        // WHEN
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertNull(actualPassword);
    }

    @Test
    void testSetEmailToNullDoesNotThrowException() {
        // GIVEN
        String email = null;

        // WHEN
        authByEmailRequest.setEmail(email);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertNull(actualEmail);
    }

    @Test
    void testSetPasswordToNullDoesNotThrowException() {
        // GIVEN
        String password = null;

        // WHEN
        authByEmailRequest.setPassword(password);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertNull(actualPassword);
    }
}
