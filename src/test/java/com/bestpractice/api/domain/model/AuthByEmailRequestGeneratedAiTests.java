package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

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
        // WHEN: The password field is set to "secretPassword".
        // THEN: The password field is set to "secretPassword".
        authByEmailRequest.setPassword("secretPassword");
        String retrievedPassword = authByEmailRequest.getPassword();
        assertEquals("secretPassword", retrievedPassword);
    }

    @Test
    void testBothFields() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The email field is set to "user@example.com" and the password field is set to "password123".
        // THEN: Both the email and password fields are set correctly.
        authByEmailRequest.setEmail("user@example.com");
        authByEmailRequest.setPassword("password123");
        String retrievedEmail = authByEmailRequest.getEmail();
        String retrievedPassword = authByEmailRequest.getPassword();
        assertEquals("user@example.com", retrievedEmail);
        assertEquals("password123", retrievedPassword);
    }
}
