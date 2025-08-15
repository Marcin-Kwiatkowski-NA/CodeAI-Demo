package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Test
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
        // THEN: The status is returned.
        assertEquals(0, errorResponse.getStatus());
    }

    @Test
    void setStatus() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setStatus() method is called with a non-zero status.
        // THEN: The status is set to the provided value.
        errorResponse.setStatus(500);
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    void getError() {
        // GIVEN: A new ErrorResponse object is created and the error is set.
        // WHEN: The getError() method is called.
        // THEN: The error is returned.
        errorResponse.setError("Internal Server Error");
        assertEquals("Internal Server Error", errorResponse.getError());
    }

    @Test
    void setError() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setError() method is called with a new error message.
        // THEN: The error is set to the provided message.
        errorResponse.setError("Bad Request");
        assertEquals("Bad Request", errorResponse.getError());
    }

    @Test
    void getMessage() {
        // GIVEN: A new ErrorResponse object is created and the message is set.
        // WHEN: The getMessage() method is called.
        // THEN: The message is returned.
        errorResponse.setMessage("Invalid Input");
        assertEquals("Invalid Input", errorResponse.getMessage());
    }

    @Test
    void setMessage() {
        // GIVEN: A new ErrorResponse object is created.
        // WHEN: The setMessage() method is called with a new message.
        // THEN: The message is set to the provided message.
        errorResponse.setMessage("Something went wrong");
        assertEquals("Something went wrong", errorResponse.getMessage());
    }
}
