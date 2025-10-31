package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
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
    void testGetEmailReturnsSetValue() {
        // GIVEN: an email is set
        String expectedEmail = "user@example.com";
        authByEmailRequest.setEmail(expectedEmail);

        // WHEN: getEmail is called
        String actualEmail = authByEmailRequest.getEmail();

        // THEN: the returned email should match the set value
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void testGetPasswordReturnsSetValue() {
        // GIVEN: a password is set
        String expectedPassword = "securePassword123";
        authByEmailRequest.setPassword(expectedPassword);

        // WHEN: getPassword is called
        String actualPassword = authByEmailRequest.getPassword();

        // THEN: the returned password should match the set value
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void testSetEmailOverwritesPreviousValue() {
        // GIVEN: an initial email is set
        authByEmailRequest.setEmail("initial@example.com");

        // WHEN: a new email is set
        String newEmail = "new@example.com";
        authByEmailRequest.setEmail(newEmail);

        // THEN: getEmail should return the new email
        assertEquals(newEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordOverwritesPreviousValue() {
        // GIVEN: an initial password is set
        authByEmailRequest.setPassword("initialPass");

        // WHEN: a new password is set
        String newPassword = "newPass123";
        authByEmailRequest.setPassword(newPassword);

        // THEN: getPassword should return the new password
        assertEquals(newPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailAllowsNullValue() {
        // GIVEN: a null email value
        String nullEmail = null;

        // WHEN: setEmail is called with null
        authByEmailRequest.setEmail(nullEmail);

        // THEN: getEmail should return null
        assertEquals(null, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordAllowsNullValue() {
        // GIVEN: a null password value
        String nullPassword = null;

        // WHEN: setPassword is called with null
        authByEmailRequest.setPassword(nullPassword);

        // THEN: getPassword should return null
        assertEquals(null, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailWithInvalidFormatDoesNotThrowException() {
        // GIVEN: an invalid email format
        String invalidEmail = "invalid-email-format";

        // WHEN: setEmail is called with invalid format
        authByEmailRequest.setEmail(invalidEmail);

        // THEN: getEmail should return the same invalid value (no exception thrown by setter)
        assertEquals(invalidEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordWithEmptyStringDoesNotThrowException() {
        // GIVEN: an empty password string
        String emptyPassword = "";

        // WHEN: setPassword is called with empty string
        authByEmailRequest.setPassword(emptyPassword);

        // THEN: getPassword should return the empty string (no exception thrown by setter)
        assertEquals(emptyPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailThrowsNullPointerWhenObjectIsNull() {
        // GIVEN: a null AuthByEmailRequest reference
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN: calling setEmail on null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.setEmail("test@example.com"));
    }

    @Test
    void testSetPasswordThrowsNullPointerWhenObjectIsNull() {
        // GIVEN: a null AuthByEmailRequest reference
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN: calling setPassword on null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.setPassword("password123"));
    }
}
