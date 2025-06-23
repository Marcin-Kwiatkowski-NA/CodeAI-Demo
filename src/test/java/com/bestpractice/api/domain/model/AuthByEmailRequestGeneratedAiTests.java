package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.domain.model.AuthByEmailRequest;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Restrict;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthByEmailRequestTest {

    @Test
    void testGetEmailReturnsCorrectValue() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        assertEquals("test@example.com", request.getEmail());
    }

    @Test
    void testGetEmailReturnsPassword() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        assertEquals("password", request.getPassword());
    }

    @Test
    void testGetPasswordReturnsPassword() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        assertEquals("password", request.getPassword());
    }

    @Test
    void testGetPasswordReturnsEmptyString() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        assertNull(request.getPassword());
    }

    @Test
    void testGetPasswordReturnsString() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        assertNotNull(request.getPassword());
        assertEquals("password", request.getPassword());
    }

    @Test
    void testGetEmailReturnsEmptyString() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        assertNull(request.getEmail());
    }

    @Test
    void testGetPasswordReturnsString() {
        AuthByEmailRequest request = new AuthByEmailRequest();
        assertNotNull(request.getPassword());
        assertEquals("password", request.getPassword());
    }
}
