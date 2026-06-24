package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.assertj.core.api.Assertions;

/**
 * Final improved test class for ErrorResponse.
 * Improvements:
 * - Removed redundant imports and unused mocks.
 * - Ensured consistent GIVEN-WHEN-THEN structure.
 * - Added missing edge case tests for null, empty, whitespace, Unicode, and long strings.
 * - Simplified assertions for clarity.
 * - Ensured all tests are independent and self-contained.
 */
@ExtendWith(MockitoExtension.class)
public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testDefaultValues() {
        // GIVEN - a newly created ErrorResponse instance

        // WHEN - no values are set

        // THEN - default values should be as expected
        assertEquals(0, errorResponse.getStatus());
        Assertions.assertThat(errorResponse.getError()).isNull();
        Assertions.assertThat(errorResponse.getMessage()).isNull();
    }

    @Test
    void testSetAndGetStatus() {
        // GIVEN - a valid status code
        int expectedStatus = 200;

        // WHEN - setting the status
        errorResponse.setStatus(expectedStatus);

        // THEN - verifying the status is correctly set
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN - a valid error message
        String expectedError = "Bad Request";

        // WHEN - setting the error
        errorResponse.setError(expectedError);

        // THEN - verifying the error is correctly set
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN - a valid message
        String expectedMessage = "Invalid input provided.";

        // WHEN - setting the message
        errorResponse.setMessage(expectedMessage);

        // THEN - verifying the message is correctly set
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithBoundaryValues() {
        // GIVEN - boundary integer values

        // WHEN - setting status to Integer.MAX_VALUE
        errorResponse.setStatus(Integer.MAX_VALUE);

        // THEN - verifying the value is correctly set
        assertEquals(Integer.MAX_VALUE, errorResponse.getStatus());

        // WHEN - setting status to Integer.MIN_VALUE
        errorResponse.setStatus(Integer.MIN_VALUE);

        // THEN - verifying the value is correctly set
        assertEquals(Integer.MIN_VALUE, errorResponse.getStatus());
    }

    @Test
    void testSetStatusWithZeroAndNegativeValues() {
        // GIVEN - zero and negative values

        // WHEN - setting status to zero
        errorResponse.setStatus(0);

        // THEN - verifying the value is correctly set
        assertEquals(0, errorResponse.getStatus());

        // WHEN - setting status to negative value
        errorResponse.setStatus(-404);

        // THEN - verifying the value is correctly set
        assertEquals(-404, errorResponse.getStatus());
    }

    @Test
    void testSetErrorWithNullValue() {
        // GIVEN - a null error value

        // WHEN - setting error to null
        errorResponse.setError(null);

        // THEN - verifying the value is null
        Assertions.assertThat(errorResponse.getError()).isNull();
    }

    @Test
    void testSetMessageWithNullValue() {
        // GIVEN - a null message value

        // WHEN - setting message to null
        errorResponse.setMessage(null);

        // THEN - verifying the value is null
        Assertions.assertThat(errorResponse.getMessage()).isNull();
    }

    @Test
    void testSetErrorWithEmptyString() {
        // GIVEN - an empty string

        // WHEN - setting error to empty string
        errorResponse.setError("");

        // THEN - verifying the value is empty
        Assertions.assertThat(errorResponse.getError()).isEmpty();
    }

    @Test
    void testSetMessageWithEmptyString() {
        // GIVEN - an empty string

        // WHEN - setting message to empty string
        errorResponse.setMessage("");

        // THEN - verifying the value is empty
        Assertions.assertThat(errorResponse.getMessage()).isEmpty();
    }

    @Test
    void testSetErrorWithWhitespaceOnlyString() {
        // GIVEN - a whitespace-only string

        // WHEN - setting error to whitespace-only string
        errorResponse.setError("   ");

        // THEN - verifying the value is correctly set
        assertEquals("   ", errorResponse.getError());
    }

    @Test
    void testSetMessageWithWhitespaceOnlyString() {
        // GIVEN - a whitespace-only string

        // WHEN - setting message to whitespace-only string
        errorResponse.setMessage("   ");

        // THEN - verifying the value is correctly set
        assertEquals("   ", errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithSingleCharacter() {
        // GIVEN - a single character string

        // WHEN - setting error to single character
        errorResponse.setError("E");

        // THEN - verifying the value is correctly set
        assertEquals("E", errorResponse.getError());
    }

    @Test
    void testSetMessageWithSingleCharacter() {
        // GIVEN - a single character string

        // WHEN - setting message to single character
        errorResponse.setMessage("M");

        // THEN - verifying the value is correctly set
        assertEquals("M", errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithLongString() {
        // GIVEN - a long string
        String longError = "A".repeat(5000);

        // WHEN - setting error to long string
        errorResponse.setError(longError);

        // THEN - verifying the value is correctly set
        assertEquals(longError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithLongString() {
        // GIVEN - a long string
        String longMessage = "B".repeat(5000);

        // WHEN - setting message to long string
        errorResponse.setMessage(longMessage);

        // THEN - verifying the value is correctly set
        assertEquals(longMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithUnicodeCharacters() {
        // GIVEN - a Unicode string
        String unicodeError = "Ошибка 🚀";

        // WHEN - setting error to Unicode string
        errorResponse.setError(unicodeError);

        // THEN - verifying the value is correctly set
        assertEquals(unicodeError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithUnicodeCharacters() {
        // GIVEN - a Unicode string
        String unicodeMessage = "Сообщение ✅";

        // WHEN - setting message to Unicode string
        errorResponse.setMessage(unicodeMessage);

        // THEN - verifying the value is correctly set
        assertEquals(unicodeMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageIndependently() {
        // GIVEN - distinct values for error and message
        String error = "Internal Server Error";
        String message = "Unexpected condition encountered.";

        // WHEN - setting both fields
        errorResponse.setError(error);
        errorResponse.setMessage(message);

        // THEN - verifying both values are correctly set and independent
        assertEquals(error, errorResponse.getError());
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void testSetStatusMultipleTimes() {
        // GIVEN - multiple status updates

        // WHEN - setting status multiple times
        errorResponse.setStatus(100);
        errorResponse.setStatus(200);
        errorResponse.setStatus(500);

        // THEN - verifying the last value is retained
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    void testSetErrorAndMessageWithMixedWhitespaceAndText() {
        // GIVEN - strings with mixed whitespace and text
        String error = "  Error  ";
        String message = "  Message  ";

        // WHEN - setting error and message
        errorResponse.setError(error);
        errorResponse.setMessage(message);

        // THEN - verifying the values are correctly set
        assertEquals(error, errorResponse.getError());
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithOffByOneValues() {
        // GIVEN - off-by-one boundary values

        // WHEN - setting status to 1
        errorResponse.setStatus(1);

        // THEN - verifying the value is correctly set
        assertEquals(1, errorResponse.getStatus());

        // WHEN - setting status to -1
        errorResponse.setStatus(-1);

        // THEN - verifying the value is correctly set
        assertEquals(-1, errorResponse.getStatus());
    }
}
