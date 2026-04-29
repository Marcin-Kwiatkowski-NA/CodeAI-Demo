package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        String expectedMessage = "Resource not available";

        // WHEN: setting the message
        errorResponse.setMessage(expectedMessage);

        // THEN: the retrieved message should match the expected value
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValuesAfterInitialization() {
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
        String expectedMessage = "Unexpected condition encountered";

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
    void testSetMessageWithEmptyString() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting message to an empty string
        errorResponse.setMessage("");

        // THEN: the retrieved message should be an empty string
        assertEquals("", errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithNegativeValue() {
        // GIVEN: an ErrorResponse instance and a negative status value
        int negativeStatus = -1;

        // WHEN: setting the status
        errorResponse.setStatus(negativeStatus);

        // THEN: the retrieved status should match the negative value (no exception expected)
        assertEquals(negativeStatus, errorResponse.getStatus());
    }

    @Test
    void testNoExceptionThrownForNullValues() {
        // GIVEN: an ErrorResponse instance

        // WHEN & THEN: setting null values should not throw exceptions
        errorResponse.setError(null);
        errorResponse.setMessage(null);
        errorResponse.setStatus(0);

        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
        assertEquals(0, errorResponse.getStatus());
    }

    @Test
    void testNoExceptionThrownWhenSettingValidValues() {
        // GIVEN: an ErrorResponse instance with valid values
        int validStatus = 200;
        String validError = "OK";
        String validMessage = "Request successful";

        // WHEN & THEN: setting valid values should not throw exceptions
        errorResponse.setStatus(validStatus);
        errorResponse.setError(validError);
        errorResponse.setMessage(validMessage);

        assertEquals(validStatus, errorResponse.getStatus());
        assertEquals(validError, errorResponse.getError());
        assertEquals(validMessage, errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownWhenSettingNullOrEmptyValues() {
        // GIVEN: an ErrorResponse instance

        // WHEN & THEN: setting null or empty values should not throw exceptions
        errorResponse.setError("");
        errorResponse.setMessage(null);
        errorResponse.setStatus(0);

        assertEquals("", errorResponse.getError());
        assertNull(errorResponse.getMessage());
        assertEquals(0, errorResponse.getStatus());
    }
}
