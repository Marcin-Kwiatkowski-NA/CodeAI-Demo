package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

        // WHEN: no setters are called

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
        String expectedMessage = "Unexpected condition encountered";

        // WHEN: setting all properties
        errorResponse.setStatus(expectedStatus);
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);

        // THEN: all getters should return the expected values
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSettersDoNotThrowExceptions() {
        // GIVEN: valid input values
        int status = 200;
        String error = "OK";
        String message = "Success";

        // WHEN & THEN: setters should not throw any exceptions
        assertDoesNotThrow(() -> {
            errorResponse.setStatus(status);
            errorResponse.setError(error);
            errorResponse.setMessage(message);
        });
    }

    @Test
    void testSetNullValues() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting null values for string fields
        assertDoesNotThrow(() -> {
            errorResponse.setError(null);
            errorResponse.setMessage(null);
        });

        // THEN: getters should return null
        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
    }
}
