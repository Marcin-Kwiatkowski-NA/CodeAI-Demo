package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testSetAndGetStatus() {
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    void testSetAndGetError() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertThat(errorResponse.getError()).isEqualTo(expectedError);
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN
        String expectedMessage = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertThat(errorResponse.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void testDefaultValues() {
        // GIVEN
        // No action needed; object is freshly instantiated in @BeforeEach

        // WHEN
        int status = errorResponse.getStatus();
        String error = errorResponse.getError();
        String message = errorResponse.getMessage();

        // THEN
        assertThat(status).isZero();
        assertThat(error).isNull();
        assertThat(message).isNull();
    }

    @Test
    void testSetErrorToNull() {
        // GIVEN
        String nullError = null;

        // WHEN
        errorResponse.setError(nullError);

        // THEN
        assertThat(errorResponse.getError()).isNull();
    }

    @Test
    void testSetMessageToNull() {
        // GIVEN
        String nullMessage = null;

        // WHEN
        errorResponse.setMessage(nullMessage);

        // THEN
        assertThat(errorResponse.getMessage()).isNull();
    }

    @Test
    void testSetStatusToNegative() {
        // GIVEN
        int negativeStatus = -1;

        // WHEN
        errorResponse.setStatus(negativeStatus);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(negativeStatus);
    }

    @Test
    void testSetErrorToEmptyString() {
        // GIVEN
        String emptyError = "";

        // WHEN
        errorResponse.setError(emptyError);

        // THEN
        assertThat(errorResponse.getError()).isEqualTo(emptyError);
    }

    @Test
    void testSetMessageToEmptyString() {
        // GIVEN
        String emptyMessage = "";

        // WHEN
        errorResponse.setMessage(emptyMessage);

        // THEN
        assertThat(errorResponse.getMessage()).isEqualTo(emptyMessage);
    }
}
