package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
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
    void testDefaultValuesAreNull() {
        // GIVEN
        // A new instance is created in @BeforeEach

        // WHEN
        String email = authByEmailRequest.getEmail();
        String password = authByEmailRequest.getPassword();

        // THEN
        assertNull(email);
        assertNull(password);
    }

    @Test
    void testSetEmailToNull() {
        // GIVEN
        String expectedEmail = null;

        // WHEN
        authByEmailRequest.setEmail(expectedEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertNull(actualEmail);
    }

    @Test
    void testSetPasswordToNull() {
        // GIVEN
        String expectedPassword = null;

        // WHEN
        authByEmailRequest.setPassword(expectedPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertNull(actualPassword);
    }

    @Test
    void testSetEmailWithEmptyString() {
        // GIVEN
        String expectedEmail = "";

        // WHEN
        authByEmailRequest.setEmail(expectedEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testSetPasswordWithEmptyString() {
        // GIVEN
        String expectedPassword = "";

        // WHEN
        authByEmailRequest.setPassword(expectedPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testNoExceptionThrownWhenSettingNullValues() {
        // GIVEN
        // Null values are allowed in setters

        // WHEN & THEN
        authByEmailRequest.setEmail(null);
        authByEmailRequest.setPassword(null);
        assertNull(authByEmailRequest.getEmail());
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testMultipleSetCallsOverridePreviousValues() {
        // GIVEN
        String firstEmail = "first@example.com";
        String secondEmail = "second@example.com";
        String firstPassword = "firstPass";
        String secondPassword = "secondPass";

        // WHEN
        authByEmailRequest.setEmail(firstEmail);
        authByEmailRequest.setEmail(secondEmail);
        authByEmailRequest.setPassword(firstPassword);
        authByEmailRequest.setPassword(secondPassword);

        // THEN
        assertEquals(secondEmail, authByEmailRequest.getEmail());
        assertEquals(secondPassword, authByEmailRequest.getPassword());
    }
}
