package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void testSetAndGetError() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);
        String actualError = errorResponse.getError();

        // THEN
        assertEquals(expectedError, actualError);
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN
        String expectedMessage = "The requested resource was not found.";

        // WHEN
        errorResponse.setMessage(expectedMessage);
        String actualMessage = errorResponse.getMessage();

        // THEN
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDefaultValues() {
        // GIVEN
        // No values set

        // WHEN
        int defaultStatus = errorResponse.getStatus();
        String defaultError = errorResponse.getError();
        String defaultMessage = errorResponse.getMessage();

        // THEN
        assertEquals(0, defaultStatus);
        assertEquals(null, defaultError);
        assertEquals(null, defaultMessage);
    }

    @Test
    void testSetNullValues() {
        // GIVEN
        String nullError = null;
        String nullMessage = null;

        // WHEN
        errorResponse.setError(nullError);
        errorResponse.setMessage(nullMessage);

        // THEN
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownWhenSettingValues() {
        // GIVEN
        int status = 200;
        String error = "OK";
        String message = "Success";

        // WHEN & THEN
        // Expect no exception to be thrown
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);

        assertEquals(status, errorResponse.getStatus());
        assertEquals(error, errorResponse.getError());
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void testSetNegativeStatusValue() {
        // GIVEN
        int negativeStatus = -1;

        // WHEN
        errorResponse.setStatus(negativeStatus);
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertEquals(negativeStatus, actualStatus);
    }

    @Test
    void testSetEmptyErrorAndMessage() {
        // GIVEN
        String emptyError = "";
        String emptyMessage = "";

        // WHEN
        errorResponse.setError(emptyError);
        errorResponse.setMessage(emptyMessage);

        // THEN
        assertEquals(emptyError, errorResponse.getError());
        assertEquals(emptyMessage, errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownWhenSettingNullValues() {
        // GIVEN
        String nullError = null;
        String nullMessage = null;

        // WHEN & THEN
        // Expect no exception to be thrown
        errorResponse.setError(nullError);
        errorResponse.setMessage(nullMessage);

        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }
}
