package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
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
        // WHEN: The setStatus() method is called with a value of 500.
        // THEN: The status attribute is set to 500.
        errorResponse.setStatus(500);
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    void getError() {
        // GIVEN: A new ErrorResponse object is created and the error attribute is set to "Invalid Input".
        errorResponse.setError("Invalid Input");
        // WHEN: The getError() method is called.
        // THEN: The error attribute is returned.
        assertEquals("Invalid Input", errorResponse.getError());
    }

    @Test
    void setError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setError() method is called with the value "Authentication Failed".
        // THEN: The error attribute is set to "Authentication Failed".
        errorResponse.setError("Authentication Failed");
        assertEquals("Authentication Failed", errorResponse.getError());
    }

    @Test
    void getMessage() {
        // GIVEN: A new ErrorResponse object is created and the message attribute is set to "Something went wrong".
        errorResponse.setMessage("Something went wrong");
        // WHEN: The getMessage() method is called.
        // THEN: The message attribute is returned.
        assertEquals("Something went wrong", errorResponse.getMessage());
    }

    @Test
    void setMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setMessage() method is called with the value "Internal Server Error".
        // THEN: The message attribute is set to "Internal Server Error".
        errorResponse.setMessage("Internal Server Error");
        assertEquals("Internal Server Error", errorResponse.getMessage());
    }
}
