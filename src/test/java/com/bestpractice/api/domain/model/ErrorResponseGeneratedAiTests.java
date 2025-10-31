package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testSetAndGetStatus() {
        // GIVEN: an ErrorResponse instance
        int expectedStatus = 404;

        // WHEN: setting the status
        errorResponse.setStatus(expectedStatus);

        // THEN: the retrieved status should match the expected value
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN: an ErrorResponse instance
        String expectedError = "Not Found";

        // WHEN: setting the error
        errorResponse.setError(expectedError);

        // THEN: the retrieved error should match the expected value
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN: an ErrorResponse instance
        String expectedMessage = "The requested resource was not found";

        // WHEN: setting the message
        errorResponse.setMessage(expectedMessage);

        // THEN: the retrieved message should match the expected value
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValues() {
        // GIVEN: a newly created ErrorResponse instance

        // WHEN: retrieving values without setting them

        // THEN: default values should be as expected
        assertEquals(0, errorResponse.getStatus());
        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithNullValue() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting the error to null
        errorResponse.setError(null);

        // THEN: the retrieved error should be null
        assertNull(errorResponse.getError());
    }

    @Test
    void testSetMessageWithNullValue() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting the message to null
        errorResponse.setMessage(null);

        // THEN: the retrieved message should be null
        assertNull(errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithNegativeValue() {
        // GIVEN: an ErrorResponse instance
        int negativeStatus = -1;

        // WHEN: setting a negative status
        errorResponse.setStatus(negativeStatus);

        // THEN: the retrieved status should match the negative value
        assertEquals(negativeStatus, errorResponse.getStatus());
    }

    @Test
    void testSetErrorMultipleTimes() {
        // GIVEN: an ErrorResponse instance
        String firstError = "Error1";
        String secondError = "Error2";

        // WHEN: setting error multiple times
        errorResponse.setError(firstError);
        errorResponse.setError(secondError);

        // THEN: the last set value should be retrieved
        assertEquals(secondError, errorResponse.getError());
    }

    @Test
    void testSetMessageMultipleTimes() {
        // GIVEN: an ErrorResponse instance
        String firstMessage = "Message1";
        String secondMessage = "Message2";

        // WHEN: setting message multiple times
        errorResponse.setMessage(firstMessage);
        errorResponse.setMessage(secondMessage);

        // THEN: the last set value should be retrieved
        assertEquals(secondMessage, errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownForNullValues() {
        // GIVEN: an ErrorResponse instance

        // WHEN & THEN: setting null values should not throw exceptions
        errorResponse.setError(null);
        errorResponse.setMessage(null);
        assertNull(errorResponse.getError());
        assertNull(errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownForAnyStatusValue() {
        // GIVEN: an ErrorResponse instance

        // WHEN & THEN: setting any int value should not throw exceptions
        errorResponse.setStatus(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, errorResponse.getStatus());

        errorResponse.setStatus(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, errorResponse.getStatus());
    }

    @Test
    void testNoExceptionThrownForEmptyStrings() {
        // GIVEN: an ErrorResponse instance

        // WHEN: setting empty strings
        errorResponse.setError("");
        errorResponse.setMessage("");

        // THEN: values should be set as empty strings
        assertEquals("", errorResponse.getError());
        assertEquals("", errorResponse.getMessage());
    }
}
