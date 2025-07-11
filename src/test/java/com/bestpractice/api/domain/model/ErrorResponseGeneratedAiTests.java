package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @AfterEach
    void tearDown() {
        errorResponse = null;
    }

    @Test
    void getStatus() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getStatus() method is called.
        // THEN: The status attribute is returned.
        assertEquals(0, errorResponse.getStatus());
    }

    @Test
    void setStatus() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setStatus() method is called with a non-zero value.
        // THEN: The status attribute is set to the provided value.
        errorResponse.setStatus(1);
        assertEquals(1, errorResponse.getStatus());
    }

    @Test
    void getError() {
        // GIVEN: A new ErrorResponse object is created and the error attribute is set to a value.
        // WHEN: The getError() method is called.
        // THEN: The error attribute is returned.
        errorResponse.setError("Invalid Input");
        assertEquals("Invalid Input", errorResponse.getError());
    }

    @Test
    void setError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setError() method is called with a new error message.
        // THEN: The error attribute is set to the provided value.
        errorResponse.setError("Something went wrong");
        assertEquals("Something went wrong", errorResponse.getError());
    }

    @Test
    void getMessage() {
        // GIVEN: A new ErrorResponse object is created and the message attribute is set to a value.
        // WHEN: The getMessage() method is called.
        // THEN: The message attribute is returned.
        errorResponse.setMessage("Detailed Error Message");
        assertEquals("Detailed Error Message", errorResponse.getMessage());
    }

    @Test
    void setMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setMessage() method is called with a new message.
        // THEN: The message attribute is set to the provided value.
        errorResponse.setMessage("Fix this");
        assertEquals("Fix this", errorResponse.getMessage());
    }
}
