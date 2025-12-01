package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

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
        errorResponse = new ErrorResponse();
    }

    @Test
    void givenStatusValue_whenSetStatus_thenStatusShouldBeUpdated() {
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);

        // THEN
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void givenErrorValue_whenSetError_thenErrorShouldBeUpdated() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void givenMessageValue_whenSetMessage_thenMessageShouldBeUpdated() {
        // GIVEN
        String expectedMessage = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertEquals(expectedMessage, errorResponse.getMessage());
    }
}
