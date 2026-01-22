package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testDefaultValues() {
        // GIVEN - a new ErrorResponse instance
        // WHEN - no setters called
        // THEN - default values should be 0 for status and null for error and message
        assertEquals(0, errorResponse.getStatus());
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testStatusGetterAndSetter() {
        // GIVEN
        int expectedStatus = 404;
        // WHEN
        errorResponse.setStatus(expectedStatus);
        int actualStatus = errorResponse.getStatus();
        // THEN
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void testErrorGetterAndSetter() {
        // GIVEN
        String expectedError = "Not Found";
        // WHEN
        errorResponse.setError(expectedError);
        String actualError = errorResponse.getError();
        // THEN
        assertEquals(expectedError, actualError);
    }

    @Test
    void testMessageGetterAndSetter() {
        // GIVEN
        String expectedMessage = "The requested resource was not found.";
        // WHEN
        errorResponse.setMessage(expectedMessage);
        String actualMessage = errorResponse.getMessage();
        // THEN
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testAllFieldsCombined() {
        // GIVEN
        int status = 500;
        String error = "Internal Server Error";
        String message = "An unexpected error occurred.";
        // WHEN
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);
        // THEN
        assertEquals(status, errorResponse.getStatus());
        assertEquals(error, errorResponse.getError());
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void testNullErrorAndMessage() {
        // GIVEN
        // WHEN
        errorResponse.setError(null);
        errorResponse.setMessage(null);
        // THEN
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }
}
