package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

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
        int status = errorResponse.getStatus();
        assertEquals(0, status);
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
        // GIVEN: A new ErrorResponse object is created and the error attribute is set to "Internal Server Error".
        // WHEN: The getError() method is called.
        // THEN: The error attribute is returned.
        errorResponse.setError("Internal Server Error");
        assertEquals("Internal Server Error", errorResponse.getError());
    }

    @Test
    void setError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setError() method is called with the value "Bad Request".
        // THEN: The error attribute is set to "Bad Request".
        errorResponse.setError("Bad Request");
        assertEquals("Bad Request", errorResponse.getError());
    }

    @Test
    void getMessage() {
        // GIVEN: A new ErrorResponse object is created and the message attribute is set to "Invalid Input".
        // WHEN: The getMessage() method is called.
        // THEN: The message attribute is returned.
        errorResponse.setMessage("Invalid Input");
        assertEquals("Invalid Input", errorResponse.getMessage());
    }

    @Test
    void setMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setMessage() method is called with the value "Data Missing".
        // THEN: The message attribute is set to "Data Missing".
        errorResponse.setMessage("Data Missing");
        assertEquals("Data Missing", errorResponse.getMessage());
    }
}
