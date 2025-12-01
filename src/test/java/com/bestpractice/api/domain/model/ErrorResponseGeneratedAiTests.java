package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void givenValidStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);

        // THEN
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void givenValidError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void givenValidMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN
        String expectedMessage = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void givenNullError_whenSetError_thenErrorIsNull() {
        // GIVEN
        String nullError = null;

        // WHEN
        errorResponse.setError(nullError);

        // THEN
        assertEquals(nullError, errorResponse.getError());
    }

    @Test
    void givenNullMessage_whenSetMessage_thenMessageIsNull() {
        // GIVEN
        String nullMessage = null;

        // WHEN
        errorResponse.setMessage(nullMessage);

        // THEN
        assertEquals(nullMessage, errorResponse.getMessage());
    }

    @Test
    void givenZeroStatus_whenSetStatus_thenStatusIsZero() {
        // GIVEN
        int zeroStatus = 0;

        // WHEN
        errorResponse.setStatus(zeroStatus);

        // THEN
        assertEquals(zeroStatus, errorResponse.getStatus());
    }
}
