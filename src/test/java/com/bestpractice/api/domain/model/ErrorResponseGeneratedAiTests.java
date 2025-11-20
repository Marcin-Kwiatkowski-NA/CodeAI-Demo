package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
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
    void givenStatusValue_whenSetStatus_thenStatusShouldBeUpdated() {
        // GIVEN
        int status = 404;

        // WHEN
        errorResponse.setStatus(status);

        // THEN
        assertEquals(status, errorResponse.getStatus());
    }

    @Test
    void givenErrorValue_whenSetError_thenErrorShouldBeUpdated() {
        // GIVEN
        String error = "Not Found";

        // WHEN
        errorResponse.setError(error);

        // THEN
        assertEquals(error, errorResponse.getError());
    }

    @Test
    void givenMessageValue_whenSetMessage_thenMessageShouldBeUpdated() {
        // GIVEN
        String message = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(message);

        // THEN
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void givenNullErrorValue_whenSetError_thenErrorShouldBeNull() {
        // GIVEN
        String error = null;

        // WHEN
        errorResponse.setError(error);

        // THEN
        assertEquals(error, errorResponse.getError());
    }

    @Test
    void givenNullMessageValue_whenSetMessage_thenMessageShouldBeNull() {
        // GIVEN
        String message = null;

        // WHEN
        errorResponse.setMessage(message);

        // THEN
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void givenNegativeStatusValue_whenSetStatus_thenStatusShouldBeUpdated() {
        // GIVEN
        int status = -1;

        // WHEN
        errorResponse.setStatus(status);

        // THEN
        assertEquals(status, errorResponse.getStatus());
    }
}
