package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
    void testGetStatus() {
        // GIVEN
        int expectedStatus = 404;
        errorResponse.setStatus(expectedStatus);

        // WHEN
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void testSetStatus() {
        // GIVEN
        int expectedStatus = 500;

        // WHEN
        errorResponse.setStatus(expectedStatus);

        // THEN
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testGetError() {
        // GIVEN
        String expectedError = "Not Found";
        errorResponse.setError(expectedError);

        // WHEN
        String actualError = errorResponse.getError();

        // THEN
        assertEquals(expectedError, actualError);
    }

    @Test
    void testSetError() {
        // GIVEN
        String expectedError = "Internal Server Error";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testGetMessage() {
        // GIVEN
        String expectedMessage = "Resource not found";
        errorResponse.setMessage(expectedMessage);

        // WHEN
        String actualMessage = errorResponse.getMessage();

        // THEN
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testSetMessage() {
        // GIVEN
        String expectedMessage = "An unexpected error occurred";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertEquals(expectedMessage, errorResponse.getMessage());
    }
}
