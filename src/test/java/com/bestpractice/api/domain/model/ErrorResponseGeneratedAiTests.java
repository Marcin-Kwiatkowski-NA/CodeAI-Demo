package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        String expectedMessage = "Resource not available";

        // WHEN
        errorResponse.setMessage(expectedMessage);
        String actualMessage = errorResponse.getMessage();

        // THEN
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDefaultValues() {
        // GIVEN
        // No setup required for default values

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
    void testSetErrorWithNullValue() {
        // GIVEN
        String expectedError = null;

        // WHEN
        errorResponse.setError(expectedError);
        String actualError = errorResponse.getError();

        // THEN
        assertEquals(expectedError, actualError);
    }

    @Test
    void testSetMessageWithEmptyString() {
        // GIVEN
        String expectedMessage = "";

        // WHEN
        errorResponse.setMessage(expectedMessage);
        String actualMessage = errorResponse.getMessage();

        // THEN
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testSetStatusWithNegativeValue() {
        // GIVEN
        int expectedStatus = -1;

        // WHEN
        errorResponse.setStatus(expectedStatus);
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void testNoExceptionThrownForValidInputs() {
        // GIVEN
        int validStatus = 200;
        String validError = "OK";
        String validMessage = "Success";

        // WHEN
        errorResponse.setStatus(validStatus);
        errorResponse.setError(validError);
        errorResponse.setMessage(validMessage);

        // THEN
        assertEquals(validStatus, errorResponse.getStatus());
        assertEquals(validError, errorResponse.getError());
        assertEquals(validMessage, errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownForNullInputs() {
        // GIVEN
        String nullError = null;
        String nullMessage = null;

        // WHEN
        errorResponse.setError(nullError);
        errorResponse.setMessage(nullMessage);

        // THEN
        assertEquals(nullError, errorResponse.getError());
        assertEquals(nullMessage, errorResponse.getMessage());
    }

    @Test
    void testSetAndGetMultipleValuesSequentially() {
        // GIVEN
        int firstStatus = 400;
        int secondStatus = 500;
        String firstError = "Bad Request";
        String secondError = "Internal Server Error";
        String firstMessage = "Invalid input";
        String secondMessage = "Unexpected failure";

        // WHEN
        errorResponse.setStatus(firstStatus);
        errorResponse.setError(firstError);
        errorResponse.setMessage(firstMessage);

        // THEN
        assertEquals(firstStatus, errorResponse.getStatus());
        assertEquals(firstError, errorResponse.getError());
        assertEquals(firstMessage, errorResponse.getMessage());

        // WHEN (update values)
        errorResponse.setStatus(secondStatus);
        errorResponse.setError(secondError);
        errorResponse.setMessage(secondMessage);

        // THEN
        assertEquals(secondStatus, errorResponse.getStatus());
        assertEquals(secondError, errorResponse.getError());
        assertEquals(secondMessage, errorResponse.getMessage());
    }

    @Test
    void testNoExceptionThrownWhenSettingLargeStatusValue() {
        // GIVEN
        int largeStatus = Integer.MAX_VALUE;

        // WHEN
        errorResponse.setStatus(largeStatus);
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertEquals(largeStatus, actualStatus);
    }

    @Test
    void testNoExceptionThrownWhenSettingMinimumStatusValue() {
        // GIVEN
        int minStatus = Integer.MIN_VALUE;

        // WHEN
        errorResponse.setStatus(minStatus);
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertEquals(minStatus, actualStatus);
    }
}
