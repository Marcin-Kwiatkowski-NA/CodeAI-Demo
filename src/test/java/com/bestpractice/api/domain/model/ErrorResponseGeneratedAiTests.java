package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
        // GIVEN: an ErrorResponse instance and a status value
        int expectedStatus = 404;

        // WHEN: setting the status
        errorResponse.setStatus(expectedStatus);

        // THEN: the retrieved status should match the set value
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN: an ErrorResponse instance and an error value
        String expectedError = "Not Found";

        // WHEN: setting the error
        errorResponse.setError(expectedError);

        // THEN: the retrieved error should match the set value
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN: an ErrorResponse instance and a message value
        String expectedMessage = "The requested resource was not found";

        // WHEN: setting the message
        errorResponse.setMessage(expectedMessage);

        // THEN: the retrieved message should match the set value
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValues() {
        // GIVEN: a newly created ErrorResponse instance

        // WHEN: retrieving values without setting them

        // THEN: default values should be as expected (0 for int, null for Strings)
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
        // GIVEN: an ErrorResponse instance and a negative status value
        int negativeStatus = -1;

        // WHEN: setting the status to a negative value
        errorResponse.setStatus(negativeStatus);

        // THEN: the retrieved status should match the set value
        assertEquals(negativeStatus, errorResponse.getStatus());
    }
}
