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
    void testSetEmailToNull() {
        // GIVEN
        String nullEmail = null;

        // WHEN
        authByEmailRequest.setEmail(nullEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertNull(actualEmail);
    }

    @Test
    void testSetPasswordToNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        authByEmailRequest.setPassword(nullPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertNull(actualPassword);
    }

    @Test
    void testSetEmailWithInvalidFormat() {
        // GIVEN
        String invalidEmail = "invalid-email-format";

        // WHEN
        authByEmailRequest.setEmail(invalidEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(invalidEmail, actualEmail);
    }

    @Test
    void testSetPasswordEmptyString() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        authByEmailRequest.setPassword(emptyPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(emptyPassword, actualPassword);
    }

    @Test
    void testGetEmailAfterSettingNullReturnsNull() {
        // GIVEN
        authByEmailRequest.setEmail(null);

        // WHEN
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertNull(actualEmail);
    }

    @Test
    void testGetPasswordAfterSettingNullReturnsNull() {
        // GIVEN
        authByEmailRequest.setPassword(null);

        // WHEN
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertNull(actualPassword);
    }

    @Test
    void testSetEmailWithWhitespaceOnlyString() {
        // GIVEN
        String whitespaceEmail = "   ";

        // WHEN
        authByEmailRequest.setEmail(whitespaceEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(whitespaceEmail, actualEmail);
    }

    @Test
    void testSetPasswordWithWhitespaceOnlyString() {
        // GIVEN
        String whitespacePassword = "   ";

        // WHEN
        authByEmailRequest.setPassword(whitespacePassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(whitespacePassword, actualPassword);
    }

    @Test
    void testSetEmailWithSingleCharacter() {
        // GIVEN
        String singleCharEmail = "a";

        // WHEN
        authByEmailRequest.setEmail(singleCharEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(singleCharEmail, actualEmail);
    }

    @Test
    void testSetPasswordWithSingleCharacter() {
        // GIVEN
        String singleCharPassword = "p";

        // WHEN
        authByEmailRequest.setPassword(singleCharPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(singleCharPassword, actualPassword);
    }

    @Test
    void testSetEmailWithVeryLongString() {
        // GIVEN
        String longEmail = "a".repeat(10000) + "@example.com";

        // WHEN
        authByEmailRequest.setEmail(longEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(longEmail, actualEmail);
    }

    @Test
    void testSetPasswordWithVeryLongString() {
        // GIVEN
        String longPassword = "p".repeat(10000);

        // WHEN
        authByEmailRequest.setPassword(longPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(longPassword, actualPassword);
    }

    @Test
    void testSetEmailWithMixedCaseCharacters() {
        // GIVEN
        String mixedCaseEmail = "User@Example.Com";

        // WHEN
        authByEmailRequest.setEmail(mixedCaseEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(mixedCaseEmail, actualEmail);
    }

    @Test
    void testSetPasswordWithSpecialCharacters() {
        // GIVEN
        String specialCharPassword = "!@#$%^&*()_+";

        // WHEN
        authByEmailRequest.setPassword(specialCharPassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(specialCharPassword, actualPassword);
    }

    @Test
    void testSetEmailWithTrailingSpaces() {
        // GIVEN
        String emailWithSpaces = "user@example.com   ";

        // WHEN
        authByEmailRequest.setEmail(emailWithSpaces);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(emailWithSpaces, actualEmail);
    }

    @Test
    void testSetPasswordWithTrailingSpaces() {
        // GIVEN
        String passwordWithSpaces = "securePassword123   ";

        // WHEN
        authByEmailRequest.setPassword(passwordWithSpaces);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(passwordWithSpaces, actualPassword);
    }

    @Test
    void testSetEmailWithUnicodeCharacters() {
        // GIVEN
        String unicodeEmail = "üser@exämple.com";

        // WHEN
        authByEmailRequest.setEmail(unicodeEmail);
        String actualEmail = authByEmailRequest.getEmail();

        // THEN
        assertEquals(unicodeEmail, actualEmail);
    }

    @Test
    void testSetPasswordWithUnicodeCharacters() {
        // GIVEN
        String unicodePassword = "pässwördÜñîçødë";

        // WHEN
        authByEmailRequest.setPassword(unicodePassword);
        String actualPassword = authByEmailRequest.getPassword();

        // THEN
        assertEquals(unicodePassword, actualPassword);
    }

    @Test
    void testSetEmailAndPasswordTogether() {
        // GIVEN
        String email = "combined@example.com";
        String password = "CombinedPass123";

        // WHEN
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setPassword(password);

        // THEN
        assertEquals(email, authByEmailRequest.getEmail());
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testResetEmailAfterSettingValue() {
        // GIVEN
        String email = "reset@example.com";

        // WHEN
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setEmail(null);

        // THEN
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testResetPasswordAfterSettingValue() {
        // GIVEN
        String password = "ResetPass123";

        // WHEN
        authByEmailRequest.setPassword(password);
        authByEmailRequest.setPassword(null);

        // THEN
        assertNull(authByEmailRequest.getPassword());
    }
}
