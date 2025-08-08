package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
        authByEmailRequest.setEmail("test@example.com");
        authByEmailRequest.setPassword("testPassword");
    }

    @Test
    void testGetEmail() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The getEmail() method is called.
        // THEN: The email property's value is returned.
        String email = authByEmailRequest.getEmail();
        assertEquals("test@example.com", email);
    }

    @Test
    void testGetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property's value is returned.
        String password = authByEmailRequest.getPassword();
        assertEquals("testPassword", password);
    }

    @Test
    void testSetEmail() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The setEmail() method is called with a new email value.
        // THEN: The email property is updated with the new value.
        authByEmailRequest.setEmail("test@example.com");
        assertEquals("test@example.com", authByEmailRequest.getEmail());
    }

    @Test
    void testSetPassword() {
        // GIVEN: A new AuthByEmailRequest object is created.
        // WHEN: The setPassword() method is called with a new password value.
        // THEN: The password property is updated with the new value.
        authByEmailRequest.setPassword("testPassword");
        assertEquals("testPassword", authByEmailRequest.getPassword());
    }

    @AfterEach
    void tearDown() {
        authByEmailRequest = null;
    }
}
