package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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
        String expectedEmail = "test@example.com";
        String actualEmail = authByEmailRequest.getEmail();
        Assertions.assertEquals(expectedEmail, actualEmail);
    }

    @Test
    void setPasswordAndGetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The password property is set to "secretPassword".
        authByEmailRequest.setPassword("secretPassword");
        // THEN: The password property is set to "secretPassword".
        String expectedPassword = "secretPassword";
        String actualPassword = authByEmailRequest.getPassword();
        Assertions.assertEquals(expectedPassword, actualPassword);
    }

    @Test
    void setEmailAndSetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The email property is set to "test@example.com" and the password property is set to "secretPassword".
        authByEmailRequest.setEmail("test@example.com");
        authByEmailRequest.setPassword("secretPassword");
        // THEN: Both the email and password properties are set correctly.
        String expectedEmail = "test@example.com";
        String expectedPassword = "secretPassword";
        String actualEmail = authByEmailRequest.getEmail();
        String actualPassword = authByEmailRequest.getPassword();
        Assertions.assertEquals(expectedEmail, actualEmail);
        Assertions.assertEquals(expectedPassword, actualPassword);
    }
}
