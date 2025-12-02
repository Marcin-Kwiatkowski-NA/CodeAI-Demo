package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

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
    void givenStatusValue_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);

        // THEN
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void givenErrorValue_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void givenMessageValue_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN
        String expectedMessage = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void givenNullError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN
        String nullError = null;

        // WHEN
        errorResponse.setError(nullError);

        // THEN
        assertEquals(nullError, errorResponse.getError());
    }

    @Test
    void givenNullMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN
        String nullMessage = null;

        // WHEN
        errorResponse.setMessage(nullMessage);

        // THEN
        assertEquals(nullMessage, errorResponse.getMessage());
    }

    @Test
    void givenNegativeStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN
        int negativeStatus = -1;

        // WHEN
        errorResponse.setStatus(negativeStatus);

        // THEN
        assertEquals(negativeStatus, errorResponse.getStatus());
    }

    @Test
    void givenEmptyError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN
        String emptyError = "";

        // WHEN
        errorResponse.setError(emptyError);

        // THEN
        assertEquals(emptyError, errorResponse.getError());
    }

    @Test
    void givenEmptyMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN
        String emptyMessage = "";

        // WHEN
        errorResponse.setMessage(emptyMessage);

        // THEN
        assertEquals(emptyMessage, errorResponse.getMessage());
    }
}
