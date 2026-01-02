package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void testDefaultValues() {
        // GIVEN
        // no preconditions

        // WHEN
        int status = errorResponse.getStatus();
        String error = errorResponse.getError();
        String message = errorResponse.getMessage();

        // THEN
        assertThat(status).isEqualTo(0);
        assertThat(error).isNull();
        assertThat(message).isNull();
    }

    @Test
    void testStatusGetterAndSetter() {
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    void testErrorGetterAndSetter() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertThat(errorResponse.getError()).isEqualTo(expectedError);
    }

    @Test
    void testMessageGetterAndSetter() {
        // GIVEN
        String expectedMessage = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertThat(errorResponse.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void testIndependentProperties() {
        // GIVEN
        int status = 500;
        String error = "Internal Server Error";
        String message = "Something went wrong.";

        // WHEN
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(status);
        assertThat(errorResponse.getError()).isEqualTo(error);
        assertThat(errorResponse.getMessage()).isEqualTo(message);
    }

    @Test
    void testNullErrorAndMessage() {
        // GIVEN
        String nullError = null;
        String nullMessage = null;

        // WHEN
        errorResponse.setError(nullError);
        errorResponse.setMessage(nullMessage);

        // THEN
        assertThat(errorResponse.getError()).isNull();
        assertThat(errorResponse.getMessage()).isNull();
    }

    @Test
    void testEmptyStringErrorAndMessage() {
        // GIVEN
        String emptyError = "";
        String emptyMessage = "";

        // WHEN
        errorResponse.setError(emptyError);
        errorResponse.setMessage(emptyMessage);

        // THEN
        assertThat(errorResponse.getError()).isEqualTo(emptyError);
        assertThat(errorResponse.getMessage()).isEqualTo(emptyMessage);
    }

    @Test
    void testStatusNegativeValue() {
        // GIVEN
        int negativeStatus = -1;

        // WHEN
        errorResponse.setStatus(negativeStatus);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(negativeStatus);
    }

    @Test
    void testStatusLargeValue() {
        // GIVEN
        int largeStatus = 9999;

        // WHEN
        errorResponse.setStatus(largeStatus);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(largeStatus);
    }

    @Test
    void testErrorAndMessageIndependence() {
        // GIVEN
        String errorValue = "Error";
        String messageValue = "Message";

        // WHEN
        errorResponse.setError(errorValue);

        // THEN
        assertThat(errorResponse.getError()).isEqualTo(errorValue);
        assertThat(errorResponse.getMessage()).isNull();

        // WHEN
        errorResponse.setMessage(messageValue);

        // THEN
        assertThat(errorResponse.getMessage()).isEqualTo(messageValue);
        assertThat(errorResponse.getError()).isEqualTo(errorValue);
    }
}
