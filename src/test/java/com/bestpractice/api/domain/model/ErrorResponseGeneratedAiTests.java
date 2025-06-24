package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@ExtendWith(DisplayNameGenerator.class)
class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    @DisplayName("Test getStatus method")
    void testGetStatusMethod() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getStatus() method is called.
        // THEN: The status attribute is returned.
        int status = errorResponse.getStatus();
        assertEquals(0, status);
    }

    @Test
    @DisplayName("Test setStatus method")
    void testSetStatusMethod() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setStatus() method is called with a non-zero status value.
        // THEN: The status attribute is set to the provided value.
        errorResponse.setStatus(500);
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    @DisplayName("Test getError method")
    void testGetErrorMethod() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getError() method is called.
        // THEN: The error attribute is returned.
        String error = errorResponse.getError();
        assertNotNull(error);
        assertEquals("", error);
    }

    @Test
    @DisplayName("Test setError method")
    void testSetErrorMethod() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setError() method is called with an error message.
        // THEN: The error attribute is set to the provided error message.
        errorResponse.setError("Invalid Input");
        assertEquals("Invalid Input", errorResponse.getError());
    }

    @Test
    @DisplayName("Test getMessage method")
    void testGetMessageMethod() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getMessage() method is called.
        // THEN: The message attribute is returned.
        String message = errorResponse.getMessage();
        assertNotNull(message);
        assertEquals("", message);
    }

    @Test
    @DisplayName("Test setMessage method")
    void testSetMessageMethod() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setMessage() method is called with a message.
        // THEN: The message attribute is set to the provided message.
        errorResponse.setMessage("Something went wrong");
        assertEquals("Something went wrong", errorResponse.getMessage());
    }
}
