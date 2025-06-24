package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Objects;

@Test
class AuthByEmailRequestGeneratedAiTests {

    private AuthByEmailRequest authByEmailRequest;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
    }

    @Test
    void getEmail() {
        // GIVEN: An AuthByEmailRequest object is created.
        // WHEN: The getEmail() method is called.
        // THEN: The email property's value is returned.
        String email = authByEmailRequest.getEmail();
        assertNotNull(email);
    }

    @Test
    void setEmail() {
        // GIVEN: An AuthByEmailRequest object is created.
        // WHEN: The setEmail() method is called with a new email value.
        String newEmail = "test@example.com";
        authByEmailRequest.setEmail(newEmail);
        assertEquals(newEmail, authByEmailRequest.getEmail());
    }

    @Test
    void getPassword() {
        // GIVEN: An AuthByEmailRequest object is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property's value is returned.
        String password = authByEmailRequest.getPassword();
        assertNotNull(password);
    }

    @Test
    void setPassword() {
        // GIVEN: An AuthByEmailRequest object is created.
        // WHEN: The setPassword() method is called with a new password value.
        String newPassword = "secretPassword";
        authByEmailRequest.setPassword(newPassword);
        assertEquals(newPassword, authByEmailRequest.getPassword());
    }
}
