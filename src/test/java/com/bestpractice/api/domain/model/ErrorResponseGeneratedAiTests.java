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
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        String expectedMessage = "Resource not available";

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
    void testMultipleFieldSetAndGet() {
        // GIVEN: an ErrorResponse instance with multiple fields
        int expectedStatus = 500;
        String expectedError = "Internal Server Error";
        String expectedMessage = "Unexpected condition encountered";

        // WHEN: setting all fields
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
    void testSetMessageWithEmptyString() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting message to empty string
        errorResponse.setMessage("");

        // THEN: the retrieved message should be empty
        assertTrue(errorResponse.getMessage().isEmpty());
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
    void testNoExceptionThrownForValidSetters() {
        // GIVEN: valid values for all fields
        int status = 200;
        String error = "OK";
        String message = "Success";

        // WHEN & THEN: ensure no exception is thrown
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

        // WHEN & THEN: ensure no exception is thrown
        errorResponse.setError(null);
        errorResponse.setMessage(null);

        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
    }

    @Test
    void testSettersDoNotThrowExceptions() {
        // GIVEN: an ErrorResponse instance

        // WHEN & THEN: verify that no setter throws an exception
        try {
            errorResponse.setStatus(100);
            errorResponse.setError("Error");
            errorResponse.setMessage("Message");
        } catch (Exception e) {
            // THEN: fail if any exception is thrown
            throw new AssertionError("Setters should not throw exceptions", e);
        }

        assertEquals(100, errorResponse.getStatus());
        assertEquals("Error", errorResponse.getError());
        assertEquals("Message", errorResponse.getMessage());
    }

    @Test
    void testSettersAcceptExtremeValues() {
        // GIVEN: extreme integer and string values
        int extremeStatus = Integer.MAX_VALUE;
        String longError = "E".repeat(1000);
        String longMessage = "M".repeat(1000);

        // WHEN: setting extreme values
        errorResponse.setStatus(extremeStatus);
        errorResponse.setError(longError);
        errorResponse.setMessage(longMessage);

        // THEN: verify values are stored correctly
        assertEquals(extremeStatus, errorResponse.getStatus());
        assertEquals(longError, errorResponse.getError());
        assertEquals(longMessage, errorResponse.getMessage());
    }
}
