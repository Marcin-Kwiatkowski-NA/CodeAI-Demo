package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest("test@example.com", "secretPassword");
    }

    @Test
    void getEmail() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The getEmail() method is called.
        // THEN: The email property's value is returned.
        String email = authByEmailRequest.getEmail();
        assertEquals("test@example.com", email);
    }

    @Test
    void setPassword() {
        // GIVEN: An AuthByEmailRequest object is created.
        // WHEN: The setPassword() method is called with a new password value.
        // THEN: The password property is updated with the provided value.
        String newPassword = "newSecretPassword";
        authByEmailRequest.setPassword(newPassword);
        assertEquals(newPassword, authByEmailRequest.getPassword());
    }
}
