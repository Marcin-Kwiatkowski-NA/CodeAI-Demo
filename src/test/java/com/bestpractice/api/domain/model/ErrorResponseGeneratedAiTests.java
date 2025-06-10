package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    public void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    public void testSetAndGetStatus() {
        // GIVEN
        int expectedStatus = 404;
        // WHEN
        errorResponse.setStatus(expectedStatus);
        int actualStatus = errorResponse.getStatus();
        // THEN
        assertEquals(expectedStatus, actualStatus, "The status should be set and retrieved correctly.");
    }

    @Test
    public void testSetAndGetError() {
        // GIVEN
        String expectedError = "Not Found";
        // WHEN
        errorResponse.setError(expectedError);
        String actualError = errorResponse.getError();
        // THEN
        assertEquals(expectedError, actualError, "The error should be set and retrieved correctly.");
    }

    @Test
    public void testSetAndGetMessage() {
        // GIVEN
        String expectedMessage = "Resource not found";
        // WHEN
        errorResponse.setMessage(expectedMessage);
        String actualMessage = errorResponse.getMessage();
        // THEN
        assertEquals(expectedMessage, actualMessage, "The message should be set and retrieved correctly.");
    }

    @Test
    public void testDefaultValues() {
        // GIVEN
        ErrorResponse defaultErrorResponse = new ErrorResponse();
        // WHEN
        int status = defaultErrorResponse.getStatus();
        String error = defaultErrorResponse.getError();
        String message = defaultErrorResponse.getMessage();
        // THEN
        assertEquals(0, status, "Default status should be 0.");
        assertNull(error, "Default error should be null.");
        assertNull(message, "Default message should be null.");
    }
}
