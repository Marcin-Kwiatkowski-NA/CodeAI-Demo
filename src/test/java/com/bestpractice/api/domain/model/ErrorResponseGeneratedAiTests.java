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
    void testMultiplePropertySetAndGet() {
        // GIVEN: an ErrorResponse instance with multiple properties
        int expectedStatus = 500;
        String expectedError = "Internal Server Error";
        String expectedMessage = "An unexpected error occurred.";

        // WHEN: setting all properties
        errorResponse.setStatus(expectedStatus);
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);

        // THEN: all retrieved properties should match the expected values
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValuesAfterInitialization() {
        // GIVEN: a newly created ErrorResponse instance

        // WHEN: no properties are set

        // THEN: default values should be as expected
        assertEquals(0, errorResponse.getStatus());
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithNullValue() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting error to null
        errorResponse.setError(null);

        // THEN: the retrieved error should be null
        assertEquals(null, errorResponse.getError());
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
        int expectedStatus = -1;

        // WHEN: setting the status
        errorResponse.setStatus(expectedStatus);

        // THEN: the retrieved status should match the expected value
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testNoExceptionThrownForValidInputs() {
        // GIVEN: valid inputs for all fields

        // WHEN & THEN: no exception should be thrown
        errorResponse.setStatus(200);
        errorResponse.setError("OK");
        errorResponse.setMessage("Success");

        // THEN: verify all values are correctly set
        assertEquals(200, errorResponse.getStatus());
        assertEquals("OK", errorResponse.getError());
        assertEquals("Success", errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownForNullInputs() {
        // GIVEN: null inputs for string fields

        // WHEN & THEN: no exception should be thrown
        errorResponse.setError(null);
        errorResponse.setMessage(null);

        // THEN: verify null values are correctly set
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testSetStatusDoesNotThrowExceptionForExtremeValues() {
        // GIVEN: extreme integer values
        int minValue = Integer.MIN_VALUE;
        int maxValue = Integer.MAX_VALUE;

        // WHEN & THEN: setting extreme values should not throw exceptions
        errorResponse.setStatus(minValue);
        assertEquals(minValue, errorResponse.getStatus());

        errorResponse.setStatus(maxValue);
        assertEquals(maxValue, errorResponse.getStatus());
    }

    @Test
    void testSetErrorAndMessageWithLongStrings() {
        // GIVEN: very long strings
        String longError = "E".repeat(1000);
        String longMessage = "M".repeat(2000);

        // WHEN: setting long strings
        errorResponse.setError(longError);
        errorResponse.setMessage(longMessage);

        // THEN: verify values are correctly stored
        assertEquals(longError, errorResponse.getError());
        assertEquals(longMessage, errorResponse.getMessage());
    }
}
