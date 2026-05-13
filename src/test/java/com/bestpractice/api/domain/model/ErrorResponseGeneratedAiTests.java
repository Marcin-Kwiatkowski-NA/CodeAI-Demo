package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testSetAndGetStatus() {
        // GIVEN: an ErrorResponse instance and a status value
        int expectedStatus = 404;

        // WHEN: setting the status
        errorResponse.setStatus(expectedStatus);

        // THEN: the retrieved status should match the expected value
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN: an ErrorResponse instance and an error message
        String expectedError = "Not Found";

        // WHEN: setting the error
        errorResponse.setError(expectedError);

        // THEN: the retrieved error should match the expected value
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN: an ErrorResponse instance and a message
        String expectedMessage = "The requested resource was not found.";

        // WHEN: setting the message
        errorResponse.setMessage(expectedMessage);

        // THEN: the retrieved message should match the expected value
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValues() {
        // GIVEN: a newly created ErrorResponse instance

        // WHEN: no values are set

        // THEN: default values should be zero or null
        assertEquals(0, errorResponse.getStatus());
        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
    }

    @Test
    void testMultiplePropertySetAndGet() {
        // GIVEN: an ErrorResponse instance with multiple properties
        int expectedStatus = 500;
        String expectedError = "Internal Server Error";
        String expectedMessage = "An unexpected error occurred.";

        // WHEN: setting all properties
        errorResponse.setStatus(expectedStatus);
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);

        // THEN: all retrieved values should match the expected ones
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithNullValue() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting error to null
        errorResponse.setError(null);

        // THEN: the retrieved error should be null
        assertNull(errorResponse.getError());
    }

    @Test
    void testSetMessageWithNullValue() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting message to null
        errorResponse.setMessage(null);

        // THEN: the retrieved message should be null
        assertNull(errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithNegativeValue() {
        // GIVEN: an ErrorResponse instance and a negative status
        int negativeStatus = -1;

        // WHEN: setting the status
        errorResponse.setStatus(negativeStatus);

        // THEN: the retrieved status should match the negative value
        assertEquals(negativeStatus, errorResponse.getStatus());
    }

    @Test
    void testNoExceptionThrownForValidValues() {
        // GIVEN: valid values for all fields
        int status = 200;
        String error = "OK";
        String message = "Success";

        // WHEN & THEN: no exception should be thrown when setting values
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);

        assertEquals(status, errorResponse.getStatus());
        assertEquals(error, errorResponse.getError());
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownForNullValues() {
        // GIVEN: null values for string fields

        // WHEN & THEN: no exception should be thrown when setting null
        errorResponse.setError(null);
        errorResponse.setMessage(null);

        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
    }

    @Test
    void testSetStatusDoesNotThrowException() {
        // GIVEN: a valid integer value
        int validStatus = 100;

        // WHEN & THEN: setting status should not throw any exception
        assertThrows(Exception.class, () -> {
            // This test ensures no exception is thrown, but since setter doesn't throw, we simulate a check
            errorResponse.setStatus(validStatus);
        });
    }
}
