package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
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
        int status = 404;

        // WHEN
        errorResponse.setStatus(status);

        // THEN
        assertEquals(404, errorResponse.getStatus());
    }

    @Test
    void givenValidError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN
        String error = "Not Found";

        // WHEN
        errorResponse.setError(error);

        // THEN
        assertEquals("Not Found", errorResponse.getError());
    }

    @Test
    void givenValidMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN
        String message = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(message);

        // THEN
        assertEquals("The requested resource was not found.", errorResponse.getMessage());
    }

    @Test
    void givenNullError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN
        String error = null;

        // WHEN
        errorResponse.setError(error);

        // THEN
        assertEquals(null, errorResponse.getError());
    }

    @Test
    void givenNullMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN
        String message = null;

        // WHEN
        errorResponse.setMessage(message);

        // THEN
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void givenNegativeStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN
        int status = -1;

        // WHEN
        errorResponse.setStatus(status);

        // THEN
        assertEquals(-1, errorResponse.getStatus());
    }

    @Test
    void givenEmptyError_whenSetError_thenErrorIsUpdatedCorrectly() {
        // GIVEN
        String error = "";

        // WHEN
        errorResponse.setError(error);

        // THEN
        assertEquals("", errorResponse.getError());
    }

    @Test
    void givenEmptyMessage_whenSetMessage_thenMessageIsUpdatedCorrectly() {
        // GIVEN
        String message = "";

        // WHEN
        errorResponse.setMessage(message);

        // THEN
        assertEquals("", errorResponse.getMessage());
    }

    @Test
    void givenZeroStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN
        int status = 0;

        // WHEN
        errorResponse.setStatus(status);

        // THEN
        assertEquals(0, errorResponse.getStatus());
    }

    @Test
    void givenMaxIntStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN
        int status = Integer.MAX_VALUE;

        // WHEN
        errorResponse.setStatus(status);

        // THEN
        assertEquals(Integer.MAX_VALUE, errorResponse.getStatus());
    }

    @Test
    void givenMinIntStatus_whenSetStatus_thenStatusIsUpdatedCorrectly() {
        // GIVEN
        int status = Integer.MIN_VALUE;

        // WHEN
        errorResponse.setStatus(status);

        // THEN
        assertEquals(Integer.MIN_VALUE, errorResponse.getStatus());
    }
}
