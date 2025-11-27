package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        // Reset the state before each test
        errorResponse = new ErrorResponse();
    }

    @Test
    void givenValidStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN: A valid status value
        int status = 404;

        // WHEN: Setting the status
        errorResponse.setStatus(status);

        // THEN: The status should be updated correctly
        assertEquals(status, errorResponse.getStatus());
    }

    @Test
    void givenValidError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN: A valid error value
        String error = "Not Found";

        // WHEN: Setting the error
        errorResponse.setError(error);

        // THEN: The error should be updated correctly
        assertEquals(error, errorResponse.getError());
    }

    @Test
    void givenValidMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN: A valid message value
        String message = "The requested resource was not found.";

        // WHEN: Setting the message
        errorResponse.setMessage(message);

        // THEN: The message should be updated correctly
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void givenNullError_whenSetError_thenErrorIsUpdatedToNull() {
        // GIVEN: A null error value
        String error = null;

        // WHEN: Setting the error
        errorResponse.setError(error);

        // THEN: The error should be updated to null
        assertEquals(error, errorResponse.getError());
    }

    @Test
    void givenNullMessage_whenSetMessage_thenMessageIsUpdatedToNull() {
        // GIVEN: A null message value
        String message = null;

        // WHEN: Setting the message
        errorResponse.setMessage(message);

        // THEN: The message should be updated to null
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void givenNegativeStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN: A negative status value
        int status = -1;

        // WHEN: Setting the status
        errorResponse.setStatus(status);

        // THEN: The status should be updated correctly
        assertEquals(status, errorResponse.getStatus());
    }

    @Test
    void givenInvalidStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN: An invalid status value
        int status = Integer.MAX_VALUE;

        // WHEN: Setting the status
        errorResponse.setStatus(status);

        // THEN: The status should be updated correctly
        assertEquals(status, errorResponse.getStatus());
    }

    @Test
    void givenEmptyError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN: An empty error value
        String error = "";

        // WHEN: Setting the error
        errorResponse.setError(error);

        // THEN: The error should be updated correctly
        assertEquals(error, errorResponse.getError());
    }

    @Test
    void givenEmptyMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN: An empty message value
        String message = "";

        // WHEN: Setting the message
        errorResponse.setMessage(message);

        // THEN: The message should be updated correctly
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void givenValidStatus_whenGetStatus_thenCorrectStatusIsReturned() {
        // GIVEN: A valid status value is set
        int status = 200;
        errorResponse.setStatus(status);

        // WHEN: Retrieving the status
        int retrievedStatus = errorResponse.getStatus();

        // THEN: The correct status should be returned
        assertEquals(status, retrievedStatus);
    }

    @Test
    void givenValidError_whenGetError_thenCorrectErrorIsReturned() {
        // GIVEN: A valid error value is set
        String error = "Internal Server Error";
        errorResponse.setError(error);

        // WHEN: Retrieving the error
        String retrievedError = errorResponse.getError();

        // THEN: The correct error should be returned
        assertEquals(error, retrievedError);
    }

    @Test
    void givenValidMessage_whenGetMessage_thenCorrectMessageIsReturned() {
        // GIVEN: A valid message value is set
        String message = "An unexpected error occurred.";
        errorResponse.setMessage(message);

        // WHEN: Retrieving the message
        String retrievedMessage = errorResponse.getMessage();

        // THEN: The correct message should be returned
        assertEquals(message, retrievedMessage);
    }
}
