package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

import static org.junit.jupiter.api.Assertions.*;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @Test
    void testSetAndGetEmail() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The email field is set to "test@example.com".
        // THEN: The email field is set to "test@example.com".
        authByEmailRequest.setEmail("test@example.com");
        String retrievedEmail = authByEmailRequest.getEmail();
        assertEquals("test@example.com", retrievedEmail);
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The password field is set to "securePassword".
        // THEN: The password field is set to "securePassword".
        authByEmailRequest.setPassword("securePassword");
        String retrievedPassword = authByEmailRequest.getPassword();
        assertEquals("securePassword", retrievedPassword);
    }

    @Test
    void testBothFields() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The email field is set to "another@example.com" and the password field is set to "anotherSecurePassword".
        // THEN: Both the email and password fields are set correctly.
        authByEmailRequest.setEmail("another@example.com");
        authByEmailRequest.setPassword("anotherSecurePassword");
        String retrievedEmail = authByEmailRequest.getEmail();
        String retrievedPassword = authByEmailRequest.getPassword();
        assertEquals("another@example.com", retrievedEmail);
        assertEquals("anotherSecurePassword", retrievedPassword);
    }
}
