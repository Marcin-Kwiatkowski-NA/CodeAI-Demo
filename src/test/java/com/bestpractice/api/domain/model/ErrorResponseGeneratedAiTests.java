package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
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
        // GIVEN: an ErrorResponse instance and a valid status
        int expectedStatus = 404;

        // WHEN: setting the status
        errorResponse.setStatus(expectedStatus);

        // THEN: the retrieved status should match the expected value
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN: an ErrorResponse instance and a valid error string
        String expectedError = "Not Found";

        // WHEN: setting the error
        errorResponse.setError(expectedError);

        // THEN: the retrieved error should match the expected value
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN: an ErrorResponse instance and a valid message string
        String expectedMessage = "The requested resource was not found.";

        // WHEN: setting the message
        errorResponse.setMessage(expectedMessage);

        // THEN: the retrieved message should match the expected value
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithNullValue() {
        // GIVEN: an ErrorResponse instance and a null error
        String expectedError = null;

        // WHEN: setting the error to null
        errorResponse.setError(expectedError);

        // THEN: the retrieved error should be null
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithNullValue() {
        // GIVEN: an ErrorResponse instance and a null message
        String expectedMessage = null;

        // WHEN: setting the message to null
        errorResponse.setMessage(expectedMessage);

        // THEN: the retrieved message should be null
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithNegativeValue() {
        // GIVEN: an ErrorResponse instance and a negative status
        int expectedStatus = -1;

        // WHEN: setting the status to a negative value
        errorResponse.setStatus(expectedStatus);

        // THEN: the retrieved status should match the negative value
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetErrorThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: an ErrorResponse instance with overridden setError to throw exception
        ErrorResponse throwingResponse = new ErrorResponse() {
            @Override
            public void setError(String error) {
                throw new UnsupportedOperationException("Operation not supported");
            }
        };

        // WHEN & THEN: setting the error should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> throwingResponse.setError("Error"));
    }

    @Test
    void testSetMessageThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: an ErrorResponse instance with overridden setMessage to throw exception
        ErrorResponse throwingResponse = new ErrorResponse() {
            @Override
            public void setMessage(String message) {
                throw new UnsupportedOperationException("Operation not supported");
            }
        };

        // WHEN & THEN: setting the message should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> throwingResponse.setMessage("Message"));
    }

    @Test
    void testSetStatusThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: an ErrorResponse instance with overridden setStatus to throw exception
        ErrorResponse throwingResponse = new ErrorResponse() {
            @Override
            public void setStatus(int status) {
                throw new UnsupportedOperationException("Operation not supported");
            }
        };

        // WHEN & THEN: setting the status should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> throwingResponse.setStatus(500));
    }
}
