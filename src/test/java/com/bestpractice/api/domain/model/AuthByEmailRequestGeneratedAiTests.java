package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void setEmailAndGetEmail() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The email property is set to "test@example.com".
        authByEmailRequest.setEmail("test@example.com");
        // THEN: The email property is set to "test@example.com".
        String email = authByEmailRequest.getEmail();
        Objects.equals(email, "test@example.com");
    }

    @Test
    void setPasswordAndGetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The password property is set to "secretPassword".
        authByEmailRequest.setPassword("secretPassword");
        // THEN: The password property is set to "secretPassword".
        String password = authByEmailRequest.getPassword();
        Objects.equals(password, "secretPassword");
    }

    @Test
    void setEmailAndSetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The email property is set to "test@example.com" and the password property is set to "secretPassword".
        authByEmailRequest.setEmail("test@example.com");
        authByEmailRequest.setPassword("secretPassword");
        // THEN: Both the email and password properties are set correctly.
        String email = authByEmailRequest.getEmail();
        String password = authByEmailRequest.getPassword();
        Objects.equals(email, "test@example.com");
        Objects.equals(password, "secretPassword");
    }
}
