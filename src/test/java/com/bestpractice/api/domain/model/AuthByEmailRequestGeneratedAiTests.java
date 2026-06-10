package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Improved test class for AuthByEmailRequest.
 * This class focuses on correctness, clarity, and independence of tests.
 * Each test follows the GIVEN-WHEN-THEN structure and covers edge cases.
 */
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

        // THEN
        assertEquals(expectedEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN
        String expectedPassword = "securePassword123";

        // WHEN
        authByEmailRequest.setPassword(expectedPassword);

        // THEN
        assertEquals(expectedPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testEmailInitiallyNull() {
        // GIVEN
        // No setup required

        // WHEN
        String email = authByEmailRequest.getEmail();

        // THEN
        assertNull(email);
    }

    @Test
    void testPasswordInitiallyNull() {
        // GIVEN
        // No setup required

        // WHEN
        String password = authByEmailRequest.getPassword();

        // THEN
        assertNull(password);
    }

    @Test
    void testSetEmailToNull() {
        // GIVEN
        String nullEmail = null;

        // WHEN
        authByEmailRequest.setEmail(nullEmail);

        // THEN
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordToNull() {
        // GIVEN
        String nullPassword = null;

        // WHEN
        authByEmailRequest.setPassword(nullPassword);

        // THEN
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailEmptyString() {
        // GIVEN
        String emptyEmail = "";

        // WHEN
        authByEmailRequest.setEmail(emptyEmail);

        // THEN
        assertEquals(emptyEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordEmptyString() {
        // GIVEN
        String emptyPassword = "";

        // WHEN
        authByEmailRequest.setPassword(emptyPassword);

        // THEN
        assertEquals(emptyPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailWhitespaceOnly() {
        // GIVEN
        String whitespaceEmail = "   ";

        // WHEN
        authByEmailRequest.setEmail(whitespaceEmail);

        // THEN
        assertEquals(whitespaceEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordWhitespaceOnly() {
        // GIVEN
        String whitespacePassword = "   ";

        // WHEN
        authByEmailRequest.setPassword(whitespacePassword);

        // THEN
        assertEquals(whitespacePassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailSingleCharacter() {
        // GIVEN
        String singleCharEmail = "a";

        // WHEN
        authByEmailRequest.setEmail(singleCharEmail);

        // THEN
        assertEquals(singleCharEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordSingleCharacter() {
        // GIVEN
        String singleCharPassword = "p";

        // WHEN
        authByEmailRequest.setPassword(singleCharPassword);

        // THEN
        assertEquals(singleCharPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailVeryLongString() {
        // GIVEN
        String longEmail = "a".repeat(5000) + "@example.com";

        // WHEN
        authByEmailRequest.setEmail(longEmail);

        // THEN
        assertEquals(longEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordVeryLongString() {
        // GIVEN
        String longPassword = "a".repeat(10000);

        // WHEN
        authByEmailRequest.setPassword(longPassword);

        // THEN
        assertEquals(longPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailSpecialCharacters() {
        // GIVEN
        String specialEmail = "user+test@example-domain.com";

        // WHEN
        authByEmailRequest.setEmail(specialEmail);

        // THEN
        assertEquals(specialEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordSpecialCharacters() {
        // GIVEN
        String specialPassword = "!@#$%^&*()_+";

        // WHEN
        authByEmailRequest.setPassword(specialPassword);

        // THEN
        assertEquals(specialPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailMixedCase() {
        // GIVEN
        String mixedCaseEmail = "User@Example.Com";

        // WHEN
        authByEmailRequest.setEmail(mixedCaseEmail);

        // THEN
        assertEquals(mixedCaseEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordMixedCase() {
        // GIVEN
        String mixedCasePassword = "PassWord123";

        // WHEN
        authByEmailRequest.setPassword(mixedCasePassword);

        // THEN
        assertEquals(mixedCasePassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailWithLeadingAndTrailingSpaces() {
        // GIVEN
        String emailWithSpaces = "   user@example.com   ";

        // WHEN
        authByEmailRequest.setEmail(emailWithSpaces);

        // THEN
        assertEquals(emailWithSpaces, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordWithLeadingAndTrailingSpaces() {
        // GIVEN
        String passwordWithSpaces = "   securePassword123   ";

        // WHEN
        authByEmailRequest.setPassword(passwordWithSpaces);

        // THEN
        assertEquals(passwordWithSpaces, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailUnicodeCharacters() {
        // GIVEN
        String unicodeEmail = "üser@exämple.com";

        // WHEN
        authByEmailRequest.setEmail(unicodeEmail);

        // THEN
        assertEquals(unicodeEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordUnicodeCharacters() {
        // GIVEN
        String unicodePassword = "pässwördÜñîçødë";

        // WHEN
        authByEmailRequest.setPassword(unicodePassword);

        // THEN
        assertEquals(unicodePassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailAndPasswordTogether() {
        // GIVEN
        String email = "user@example.com";
        String password = "password123";

        // WHEN
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setPassword(password);

        // THEN
        assertEquals(email, authByEmailRequest.getEmail());
        assertEquals(password, authByEmailRequest.getPassword());
    }

    @Test
    void testResetEmailAndPassword() {
        // GIVEN
        authByEmailRequest.setEmail("user@example.com");
        authByEmailRequest.setPassword("password123");

        // WHEN
        authByEmailRequest.setEmail(null);
        authByEmailRequest.setPassword(null);

        // THEN
        assertNull(authByEmailRequest.getEmail());
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testEmailAndPasswordIndependence() {
        // GIVEN
        String email = "user@example.com";
        String password = "password123";

        // WHEN
        authByEmailRequest.setEmail(email);
        authByEmailRequest.setPassword(password);

        // THEN
        assertThat(authByEmailRequest.getEmail()).isEqualTo(email);
        assertThat(authByEmailRequest.getPassword()).isEqualTo(password);
    }

    @Test
    void testEmailAndPasswordMutability() {
        // GIVEN
        String initialEmail = "first@example.com";
        String updatedEmail = "second@example.com";
        String initialPassword = "firstPass";
        String updatedPassword = "secondPass";

        // WHEN
        authByEmailRequest.setEmail(initialEmail);
        authByEmailRequest.setPassword(initialPassword);
        authByEmailRequest.setEmail(updatedEmail);
        authByEmailRequest.setPassword(updatedPassword);

        // THEN
        assertEquals(updatedEmail, authByEmailRequest.getEmail());
        assertEquals(updatedPassword, authByEmailRequest.getPassword());
    }
}
