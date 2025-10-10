package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class AuthResponseGeneratedAiTests {

    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        authResponse = new AuthResponse();
    }

    @Test
    void getStatus() {
        assertEquals(0, authResponse.getStatus());
    }

    @Test
    void setStatus() {
        authResponse.setStatus(500);
        assertEquals(500, authResponse.getStatus());
    }

    @Test
    void getError() {
        authResponse.setError("Internal Server Error");
        assertEquals("Internal Server Error", authResponse.getError());
    }

    @Test
    void setError() {
        authResponse.setError("Bad Request");
        assertEquals("Bad Request", authResponse.getError());
    }

    @Test
    void getMessage() {
        authResponse.setMessage("Something went wrong");
        assertEquals("Something went wrong", authResponse.getMessage());
    }

    @Test
    void setMessage() {
        authResponse.setMessage("Invalid Input");
        assertEquals("Invalid Input", authResponse.getMessage());
    }
}
