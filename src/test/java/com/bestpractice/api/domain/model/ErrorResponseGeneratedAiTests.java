package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.MockitoExtension
public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    void testGetStatus() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getStatus() method is called.
        // THEN: The status property is returned.
        assertEquals(0, errorResponse.getStatus());
    }

    void testSetStatus() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setStatus() method is called with a value of 500.
        // THEN: The status property is set to 500.
        errorResponse.setStatus(500);
        assertEquals(500, errorResponse.getStatus());
    }

    void testGetError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getError() method is called.
        // THEN: The error property is returned.
        assertEquals("", errorResponse.getError());
    }

    void testSetError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setError() method is called with a value of "Internal Server Error".
        // THEN: The error property is set to "Internal Server Error".
        errorResponse.setError("Internal Server Error");
        assertEquals("Internal Server Error", errorResponse.getError());
    }

    void testGetMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The getMessage() method is called.
        // THEN: The message property is returned.
        assertEquals("", errorResponse.getMessage());
    }

    void testSetMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setMessage() method is called with a value of "Something went wrong".
        // THEN: The message property is set to "Something went wrong".
        errorResponse.setMessage("Something went wrong");
        assertEquals("Something went wrong", errorResponse.getMessage());
    }
}
