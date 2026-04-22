package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void shouldSetAndGetStatusCorrectly() {
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertEquals(expectedStatus, actualStatus, "Status should match the value set");
    }

    @Test
    void shouldSetAndGetErrorCorrectly() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);
        String actualError = errorResponse.getError();

        // THEN
        assertEquals(expectedError, actualError, "Error should match the value set");
    }

    @Test
    void shouldSetAndGetMessageCorrectly() {
        // GIVEN
        String expectedMessage = "Resource not available";

        // WHEN
        errorResponse.setMessage(expectedMessage);
        String actualMessage = errorResponse.getMessage();

        // THEN
        assertEquals(expectedMessage, actualMessage, "Message should match the value set");
    }

    @Test
    void shouldHandleEmptyStringValuesGracefully() {
        // GIVEN
        String emptyValue = "";

        // WHEN
        errorResponse.setError(emptyValue);
        errorResponse.setMessage(emptyValue);

        // THEN
        assertEquals(emptyValue, errorResponse.getError(), "Error should be empty string");
        assertEquals(emptyValue, errorResponse.getMessage(), "Message should be empty string");
    }

    @Test
    void shouldHandleNullValuesGracefully() {
        // GIVEN
        String nullValue = null;

        // WHEN
        errorResponse.setError(nullValue);
        errorResponse.setMessage(nullValue);

        // THEN
        assertNull(errorResponse.getError(), "Error should be null");
        assertNull(errorResponse.getMessage(), "Message should be null");
    }

    @Test
    void shouldHandleMultipleSetOperationsIndependently() {
        // GIVEN
        int firstStatus = 200;
        int secondStatus = 500;
        String firstError = "OK";
        String secondError = "Internal Server Error";

        // WHEN
        errorResponse.setStatus(firstStatus);
        errorResponse.setError(firstError);
        errorResponse.setStatus(secondStatus);
        errorResponse.setError(secondError);

        // THEN
        assertEquals(secondStatus, errorResponse.getStatus(), "Status should reflect latest value");
        assertEquals(secondError, errorResponse.getError(), "Error should reflect latest value");
    }
}
