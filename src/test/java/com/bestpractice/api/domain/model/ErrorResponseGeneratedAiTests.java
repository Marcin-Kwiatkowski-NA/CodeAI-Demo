package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @Test
    void getStatus() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getStatus() method is called.
        // THEN: The status field is returned.
        int status = errorResponse.getStatus();
        assertEquals(0, status);
    }

    @Test
    void setStatus() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setStatus() method is called with a non-zero status value.
        // THEN: The status field is set to the provided value.
        errorResponse.setStatus(500);
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    void getError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getError() method is called.
        // THEN: The error field is returned.
        String error = errorResponse.getError();
        assertNotNull(error);
        assertEquals("", error);
    }

    @Test
    void setError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setError() method is called with a non-empty error message.
        // THEN: The error field is set to the provided message.
        errorResponse.setError("Invalid Input");
        assertEquals("Invalid Input", errorResponse.getError());
    }

    @Test
    void getMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getMessage() method is called.
        // THEN: The message field is returned.
        String message = errorResponse.getMessage();
        assertNotNull(message);
        assertEquals("", message);
    }

    @Test
    void setMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setMessage() method is called with a non-empty message.
        // THEN: The message field is set to the provided message.
        errorResponse.setMessage("Something went wrong");
        assertEquals("Something went wrong", errorResponse.getMessage());
    }
}
