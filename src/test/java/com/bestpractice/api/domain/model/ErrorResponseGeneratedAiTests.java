package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
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
        // GIVEN: an ErrorResponse instance and a status value
        int expectedStatus = 404;

        // WHEN: setting the status
        errorResponse.setStatus(expectedStatus);

        // THEN: the retrieved status should match the set value
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN: an ErrorResponse instance and an error string
        String expectedError = "Not Found";

        // WHEN: setting the error
        errorResponse.setError(expectedError);

        // THEN: the retrieved error should match the set value
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN: an ErrorResponse instance and a message string
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
        // GIVEN: an ErrorResponse instance and a negative status value
        int negativeStatus = -1;

        // WHEN: setting the status to a negative value
        errorResponse.setStatus(negativeStatus);

        // THEN: the retrieved status should match the set value
        assertEquals(negativeStatus, errorResponse.getStatus());
    }

    @Test
    void testSetErrorThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: an ErrorResponse instance with overridden setError to throw exception
        ErrorResponse localErrorResponse = new ErrorResponse() {
            @Override
            public void setError(String error) {
                throw new UnsupportedOperationException("Operation not supported");
            }
        };

        // WHEN & THEN: setting the error should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> localErrorResponse.setError("Error"));
    }

    @Test
    void testSetMessageThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: an ErrorResponse instance with overridden setMessage to throw exception
        ErrorResponse localErrorResponse = new ErrorResponse() {
            @Override
            public void setMessage(String message) {
                throw new UnsupportedOperationException("Operation not supported");
            }
        };

        // WHEN & THEN: setting the message should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> localErrorResponse.setMessage("Message"));
    }

    @Test
    void testSetStatusThrowsExceptionWhenUnsupportedOperation() {
        // GIVEN: an ErrorResponse instance with overridden setStatus to throw exception
        ErrorResponse localErrorResponse = new ErrorResponse() {
            @Override
            public void setStatus(int status) {
                throw new UnsupportedOperationException("Operation not supported");
            }
        };

        // WHEN & THEN: setting the status should throw UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> localErrorResponse.setStatus(500));
    }
}
