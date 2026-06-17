package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

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
        String expectedMessage = "Resource not available";
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValues() {
        assertEquals(0, errorResponse.getStatus());
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithNullValue() {
        errorResponse.setError(null);
        assertEquals(null, errorResponse.getError());
    }

    @Test
    void testSetMessageWithNullValue() {
        errorResponse.setMessage(null);
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithExtremeValues() {
        errorResponse.setStatus(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, errorResponse.getStatus());
        errorResponse.setStatus(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, errorResponse.getStatus());
    }

    @Test
    void testSetStatusWithZero() {
        int expectedStatus = 0;
        errorResponse.setStatus(expectedStatus);
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetStatusWithNegativeOne() {
        int expectedStatus = -1;
        errorResponse.setStatus(expectedStatus);
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetStatusWithOne() {
        int expectedStatus = 1;
        errorResponse.setStatus(expectedStatus);
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetErrorWithEmptyString() {
        String expectedError = "";
        errorResponse.setError(expectedError);
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetErrorWithWhitespaceOnlyString() {
        String expectedError = "   ";
        errorResponse.setError(expectedError);
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithEmptyString() {
        String expectedMessage = "";
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetMessageWithWhitespaceOnlyString() {
        String expectedMessage = "   ";
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetMessageWithSingleCharacter() {
        String expectedMessage = "A";
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithSingleCharacter() {
        String expectedError = "E";
        errorResponse.setError(expectedError);
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithLongString() {
        String expectedMessage = "A".repeat(10000);
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorWithLongString() {
        String expectedError = "B".repeat(10000);
        errorResponse.setError(expectedError);
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetErrorWithUnicodeCharacters() {
        String expectedError = "エラー🚀";
        errorResponse.setError(expectedError);
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithUnicodeCharacters() {
        String expectedMessage = "メッセージ✅";
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageIndependently() {
        String expectedError = "Error";
        String expectedMessage = "Message";
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetStatusDoesNotAffectErrorOrMessage() {
        errorResponse.setError("Error");
        errorResponse.setMessage("Message");
        errorResponse.setStatus(500);
        assertEquals("Error", errorResponse.getError());
        assertEquals("Message", errorResponse.getMessage());
        assertEquals(500, errorResponse.getStatus());
    }

    @Test
    void testSetErrorAndMessageToSameValue() {
        String sameValue = "Same";
        errorResponse.setError(sameValue);
        errorResponse.setMessage(sameValue);
        assertEquals(sameValue, errorResponse.getError());
        assertEquals(sameValue, errorResponse.getMessage());
    }

    @Test
    void testSetStatusMultipleTimesKeepsLastValue() {
        errorResponse.setStatus(100);
        errorResponse.setStatus(200);
        errorResponse.setStatus(300);
        assertEquals(300, errorResponse.getStatus());
    }

    @Test
    void testSetErrorMultipleTimesKeepsLastValue() {
        errorResponse.setError("First");
        errorResponse.setError("Second");
        assertEquals("Second", errorResponse.getError());
    }

    @Test
    void testSetMessageMultipleTimesKeepsLastValue() {
        errorResponse.setMessage("First");
        errorResponse.setMessage("Second");
        assertEquals("Second", errorResponse.getMessage());
    }

    @Test
    void testObjectStateAfterMultipleSetters() {
        int expectedStatus = 400;
        String expectedError = "Bad Request";
        String expectedMessage = "Invalid input";
        errorResponse.setStatus(expectedStatus);
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testAllFieldsCanBeReset() {
        errorResponse.setStatus(500);
        errorResponse.setError("Error");
        errorResponse.setMessage("Message");
        errorResponse.setStatus(0);
        errorResponse.setError(null);
        errorResponse.setMessage(null);
        assertEquals(0, errorResponse.getStatus());
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }
}
