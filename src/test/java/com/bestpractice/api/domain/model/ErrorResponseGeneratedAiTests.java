package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

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
        // Object created in @BeforeEach

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
    void testMutabilityIndependence() {
        // GIVEN
        errorResponse.setStatus(500);
        errorResponse.setError("Internal Server Error");
        errorResponse.setMessage("Unexpected condition encountered");

        // WHEN
        ErrorResponse anotherResponse = new ErrorResponse();
        anotherResponse.setStatus(200);
        anotherResponse.setError("OK");
        anotherResponse.setMessage("Success");

        // THEN
        assertEquals(500, errorResponse.getStatus());
        assertEquals("Internal Server Error", errorResponse.getError());
        assertEquals("Unexpected condition encountered", errorResponse.getMessage());

        assertEquals(200, anotherResponse.getStatus());
        assertEquals("OK", anotherResponse.getError());
        assertEquals("Success", anotherResponse.getMessage());
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
    void testNoExceptionThrownForNullValues() {
        // GIVEN
        // The class does not throw exceptions for nulls, so we verify normal behavior

        // WHEN
        errorResponse.setError(null);
        errorResponse.setMessage(null);

        // THEN
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testSetAndGetMultipleFieldsTogether() {
        // GIVEN
        int expectedStatus = 400;
        String expectedError = "Bad Request";
        String expectedMessage = "Invalid input data";

        // WHEN
        errorResponse.setStatus(expectedStatus);
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }
}
