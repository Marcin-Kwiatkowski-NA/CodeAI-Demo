package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testSetAndGetStatus() {
        int expectedStatus = 404;
        errorResponse.setStatus(expectedStatus);
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        String expectedError = "Not Found";
        errorResponse.setError(expectedError);
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        String expectedMessage = "The requested resource was not found.";
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testMultipleFieldAssignmentsIndependently() {
        int expectedStatus = 500;
        String expectedError = "Internal Server Error";
        String expectedMessage = "An unexpected error occurred.";
        errorResponse.setStatus(expectedStatus);
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValuesBeforeSetting() {
        assertEquals(0, errorResponse.getStatus());
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithNullDoesNotThrowException() {
        assertDoesNotThrow(() -> errorResponse.setError(null));
        assertEquals(null, errorResponse.getError());
    }

    @Test
    void testSetMessageWithNullDoesNotThrowException() {
        assertDoesNotThrow(() -> errorResponse.setMessage(null));
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithExtremeValuesDoesNotThrowException() {
        assertDoesNotThrow(() -> errorResponse.setStatus(Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE, errorResponse.getStatus());
        assertDoesNotThrow(() -> errorResponse.setStatus(Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, errorResponse.getStatus());
    }

    @Test
    void testGettersDoNotThrowExceptionWhenUnset() {
        assertDoesNotThrow(() -> errorResponse.getError());
        assertDoesNotThrow(() -> errorResponse.getMessage());
        assertDoesNotThrow(() -> errorResponse.getStatus());
    }

    @Test
    void testSetErrorWithEmptyString() {
        String emptyError = "";
        errorResponse.setError(emptyError);
        assertEquals(emptyError, errorResponse.getError());
    }

    @Test
    void testSetErrorWithWhitespaceString() {
        String whitespaceError = "   ";
        errorResponse.setError(whitespaceError);
        assertEquals(whitespaceError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithEmptyString() {
        String emptyMessage = "";
        errorResponse.setMessage(emptyMessage);
        assertEquals(emptyMessage, errorResponse.getMessage());
    }

    @Test
    void testSetMessageWithWhitespaceString() {
        String whitespaceMessage = "   ";
        errorResponse.setMessage(whitespaceMessage);
        assertEquals(whitespaceMessage, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithZeroAndNegativeOne() {
        int zeroStatus = 0;
        int negativeOneStatus = -1;
        errorResponse.setStatus(zeroStatus);
        assertEquals(zeroStatus, errorResponse.getStatus());
        errorResponse.setStatus(negativeOneStatus);
        assertEquals(negativeOneStatus, errorResponse.getStatus());
    }

    @Test
    void testSetStatusWithOne() {
        int oneStatus = 1;
        errorResponse.setStatus(oneStatus);
        assertEquals(oneStatus, errorResponse.getStatus());
    }

    @Test
    void testSetErrorWithLongString() {
        String longError = "A".repeat(10000);
        errorResponse.setError(longError);
        assertEquals(longError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithLongString() {
        String longMessage = "B".repeat(10000);
        errorResponse.setMessage(longMessage);
        assertEquals(longMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithSingleCharacter() {
        String singleCharError = "E";
        errorResponse.setError(singleCharError);
        assertEquals(singleCharError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithSingleCharacter() {
        String singleCharMessage = "M";
        errorResponse.setMessage(singleCharMessage);
        assertEquals(singleCharMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithUnicodeCharacters() {
        String unicodeError = "エラー🚀";
        errorResponse.setError(unicodeError);
        assertEquals(unicodeError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithUnicodeCharacters() {
        String unicodeMessage = "メッセージ✨";
        errorResponse.setMessage(unicodeMessage);
        assertEquals(unicodeMessage, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithBoundaryAroundZero() {
        int negativeBoundary = -1;
        int positiveBoundary = 1;
        errorResponse.setStatus(negativeBoundary);
        assertEquals(negativeBoundary, errorResponse.getStatus());
        errorResponse.setStatus(positiveBoundary);
        assertEquals(positiveBoundary, errorResponse.getStatus());
    }

    @Test
    void testSetErrorAndMessageIndependence() {
        String errorText = "ErrorText";
        String messageText = "MessageText";
        errorResponse.setError(errorText);
        errorResponse.setMessage(messageText);
        assertEquals(errorText, errorResponse.getError());
        assertEquals(messageText, errorResponse.getMessage());
    }

    @Test
    void testSetStatusDoesNotAffectErrorOrMessage() {
        String errorText = "Error";
        String messageText = "Message";
        errorResponse.setError(errorText);
        errorResponse.setMessage(messageText);
        errorResponse.setStatus(200);
        assertEquals(errorText, errorResponse.getError());
        assertEquals(messageText, errorResponse.getMessage());
        assertEquals(200, errorResponse.getStatus());
    }
}
