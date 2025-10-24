package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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

        // WHEN: setting the email on the request object
        authByEmailRequest.setEmail(email);

        // THEN: the retrieved email should match the set value
        assertEquals(email, authByEmailRequest.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: a valid password string
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
    void testSetEmailToNullDoesNotThrowException() {
        // GIVEN: a null email value
        String email = null;

        // WHEN: setting email to null
        authByEmailRequest.setEmail(email);

        // THEN: the email should be null
        assertNull(authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordToNullDoesNotThrowException() {
        // GIVEN: a null password value
        String password = null;

        // WHEN: setting password to null
        authByEmailRequest.setPassword(password);

        // THEN: the password should be null
        assertNull(authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailInvalidFormatStoresValue() {
        // GIVEN: an invalid email format
        String invalidEmail = "invalid-email";

        // WHEN: setting the invalid email
        authByEmailRequest.setEmail(invalidEmail);

        // THEN: the email should be stored as is (no validation in setter)
        assertEquals(invalidEmail, authByEmailRequest.getEmail());
    }

    @Test
    void testSetPasswordEmptyStringStoresValue() {
        // GIVEN: an empty password string
        String emptyPassword = "";

        // WHEN: setting the empty password
        authByEmailRequest.setPassword(emptyPassword);

        // THEN: the password should be stored as is
        assertEquals(emptyPassword, authByEmailRequest.getPassword());
    }

    @Test
    void testSetEmailThrowsNullPointerExceptionWhenObjectIsNull() {
        // GIVEN: a null AuthByEmailRequest reference
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN: calling setEmail on a null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.setEmail("test@example.com"));
    }

    @Test
    void testSetPasswordThrowsNullPointerExceptionWhenObjectIsNull() {
        // GIVEN: a null AuthByEmailRequest reference
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN: calling setPassword on a null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.setPassword("password"));
    }

    @Test
    void testGetEmailThrowsNullPointerExceptionWhenObjectIsNull() {
        // GIVEN: a null AuthByEmailRequest reference
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN: calling getEmail on a null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.getEmail());
    }

    @Test
    void testGetPasswordThrowsNullPointerExceptionWhenObjectIsNull() {
        // GIVEN: a null AuthByEmailRequest reference
        AuthByEmailRequest nullRequest = null;

        // WHEN & THEN: calling getPassword on a null reference should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.getPassword());
    }
}
