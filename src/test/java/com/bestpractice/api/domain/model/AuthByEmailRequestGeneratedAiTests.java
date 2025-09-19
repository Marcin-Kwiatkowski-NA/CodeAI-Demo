package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@DisplayName("AuthByEmailRequestGeneratedAiTests")
class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
        authByEmailRequest.setEmail("test@example.com");
        authByEmailRequest.setPassword("secretPassword");
    }

    @Test
    @DisplayName("Test setEmail")
    void testSetEmail() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The setEmail method is called with a valid email address.
        // THEN: The email field is set to the provided email address.
        authByEmailRequest.setEmail("test@example.com");
        assertEquals("test@example.com", authByEmailRequest.getEmail());
    }

    @Test
    @DisplayName("Test getEmail") {
        // GIVEN: The email field is set to "test@example.com".
        // WHEN: The getEmail method is called.
        // THEN: The email address "test@example.com" is returned.
        String email = authByEmailRequest.getEmail();
        assertEquals("test@example.com", email);
    }

    @Test
    @DisplayName("Test setPassword")
    void testSetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The setPassword method is called with a valid password.
        // THEN: The password field is set to the provided password.
        authByEmailRequest.setPassword("secretPassword");
        assertEquals("secretPassword", authByEmailRequest.getPassword());
    }

    @Test
    @DisplayName("Test getPassword") {
        // GIVEN: The password field is set to "secretPassword".
        // WHEN: The getPassword method is called.
        // THEN: The password "secretPassword" is returned.
        String password = authByEmailRequest.getPassword();
        assertEquals("secretPassword", password);
    }
}
