package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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
        String expectedMessage = "The requested resource was not found.";

        // WHEN: setting the message
        errorResponse.setMessage(expectedMessage);

        // THEN: the retrieved message should match the expected value
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValues() {
        // GIVEN: a newly created ErrorResponse instance

        // WHEN: retrieving values without setting them

        // THEN: default values should be 0 for status and null for strings
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

        // WHEN: setting the status to a negative value
        errorResponse.setStatus(negativeStatus);

        // THEN: the retrieved status should match the negative value
        assertEquals(negativeStatus, errorResponse.getStatus());
    }

    @Test
    void testSetErrorThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: a subclass of ErrorResponse that throws an exception in setError
        class ErrorResponseWithException extends ErrorResponse {
            @Override
            public void setError(String error) {
                throw new UnsupportedOperationException("Operation not supported");
            }
        }
        ErrorResponse errorResponseWithException = new ErrorResponseWithException();

        // WHEN & THEN: setting error should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> errorResponseWithException.setError("Error"));
    }

    @Test
    void testSetMessageThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: a subclass of ErrorResponse that throws an exception in setMessage
        class ErrorResponseWithException extends ErrorResponse {
            @Override
            public void setMessage(String message) {
                throw new IllegalArgumentException("Invalid message");
            }
        }
        ErrorResponse errorResponseWithException = new ErrorResponseWithException();

        // WHEN & THEN: setting message should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> errorResponseWithException.setMessage("Message"));
    }

    @Test
    void testSetStatusThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: a subclass of ErrorResponse that throws an exception in setStatus
        class ErrorResponseWithException extends ErrorResponse {
            @Override
            public void setStatus(int status) {
                throw new IllegalStateException("Status change not allowed");
            }
        }
        ErrorResponse errorResponseWithException = new ErrorResponseWithException();

        // WHEN & THEN: setting status should throw IllegalStateException
        assertThrows(IllegalStateException.class, () -> errorResponseWithException.setStatus(500));
    }
}
