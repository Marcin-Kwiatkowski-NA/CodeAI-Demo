package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals(expectedStatus, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertEquals(expectedError, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN
        String expectedMessage = "Resource not available";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultValues() {
        // GIVEN & WHEN
        // THEN
        assertEquals(0, errorResponse.getStatus());
        assertEquals(null, errorResponse.getError());
        assertEquals(null, errorResponse.getMessage());
    }

    @Test
    void testMultipleSettersAndGettersTogether() {
        // GIVEN
        int expectedStatus = 500;
        String expectedError = "Internal Server Error";
        String expectedMessage = "Unexpected condition encountered";

        // WHEN
        errorResponse.setStatus(expectedStatus);
        errorResponse.setError(expectedError);
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertEquals(expectedStatus, errorResponse.getStatus());
        assertEquals(expectedError, errorResponse.getError());
        assertEquals(expectedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetStatusWithBoundaryValues() {
        // GIVEN
        int[] boundaryValues = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};

        // WHEN & THEN
        for (int value : boundaryValues) {
            errorResponse.setStatus(value);
            assertEquals(value, errorResponse.getStatus());
        }
    }

    @Test
    void testSetErrorWithEmptyString() {
        // GIVEN
        String emptyError = "";

        // WHEN
        errorResponse.setError(emptyError);

        // THEN
        assertEquals(emptyError, errorResponse.getError());
    }

    @Test
    void testSetErrorWithWhitespaceString() {
        // GIVEN
        String whitespaceError = "   ";

        // WHEN
        errorResponse.setError(whitespaceError);

        // THEN
        assertEquals(whitespaceError, errorResponse.getError());
    }

    @Test
    void testSetErrorWithUnicodeCharacters() {
        // GIVEN
        String unicodeError = "エラー発生";

        // WHEN
        errorResponse.setError(unicodeError);

        // THEN
        assertEquals(unicodeError, errorResponse.getError());
    }

    @Test
    void testSetErrorWithSpecialCharacters() {
        // GIVEN
        String specialError = "!@#$%^&*()_+";

        // WHEN
        errorResponse.setError(specialError);

        // THEN
        assertEquals(specialError, errorResponse.getError());
    }

    @Test
    void testSetErrorWithLongString() {
        // GIVEN
        String longError = "E".repeat(1000);

        // WHEN
        errorResponse.setError(longError);

        // THEN
        assertEquals(longError, errorResponse.getError());
    }

    @Test
    void testSetMessageWithEmptyString() {
        // GIVEN
        String emptyMessage = "";

        // WHEN
        errorResponse.setMessage(emptyMessage);

        // THEN
        assertEquals(emptyMessage, errorResponse.getMessage());
    }

    @Test
    void testSetMessageWithWhitespaceString() {
        // GIVEN
        String whitespaceMessage = "   ";

        // WHEN
        errorResponse.setMessage(whitespaceMessage);

        // THEN
        assertEquals(whitespaceMessage, errorResponse.getMessage());
    }

    @Test
    void testSetMessageWithUnicodeCharacters() {
        // GIVEN
        String unicodeMessage = "メッセージ内容";

        // WHEN
        errorResponse.setMessage(unicodeMessage);

        // THEN
        assertEquals(unicodeMessage, errorResponse.getMessage());
    }

    @Test
    void testSetMessageWithSpecialCharacters() {
        // GIVEN
        String specialMessage = "<html><body>Error</body></html>";

        // WHEN
        errorResponse.setMessage(specialMessage);

        // THEN
        assertEquals(specialMessage, errorResponse.getMessage());
    }

    @Test
    void testSetMessageWithLongString() {
        // GIVEN
        String longMessage = "M".repeat(1000);

        // WHEN
        errorResponse.setMessage(longMessage);

        // THEN
        assertEquals(longMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageWithNull() {
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
    void testSetErrorAndMessageWithLeadingAndTrailingSpaces() {
        // GIVEN
        String errorWithSpaces = "  Error  ";
        String messageWithSpaces = "  Message  ";

        // WHEN
        errorResponse.setError(errorWithSpaces);
        errorResponse.setMessage(messageWithSpaces);

        // THEN
        assertEquals(errorWithSpaces, errorResponse.getError());
        assertEquals(messageWithSpaces, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageWithMixedContent() {
        // GIVEN
        String mixedError = "Error123!@#";
        String mixedMessage = "Message_456$%^";

        // WHEN
        errorResponse.setError(mixedError);
        errorResponse.setMessage(mixedMessage);

        // THEN
        assertEquals(mixedError, errorResponse.getError());
        assertEquals(mixedMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageWithEmptyAndWhitespaceTogether() {
        // GIVEN
        String emptyError = "";
        String whitespaceMessage = " ";

        // WHEN
        errorResponse.setError(emptyError);
        errorResponse.setMessage(whitespaceMessage);

        // THEN
        assertEquals(emptyError, errorResponse.getError());
        assertEquals(whitespaceMessage, errorResponse.getMessage());
    }

    @Test
    void testSetAndGetStatusSequentially() {
        // GIVEN
        int firstStatus = 200;
        int secondStatus = 404;

        // WHEN
        errorResponse.setStatus(firstStatus);
        errorResponse.setStatus(secondStatus);

        // THEN
        assertEquals(secondStatus, errorResponse.getStatus());
    }

    @Test
    void testSetErrorAndMessageSequentially() {
        // GIVEN
        String firstError = "Initial Error";
        String secondError = "Updated Error";
        String firstMessage = "Initial Message";
        String secondMessage = "Updated Message";

        // WHEN
        errorResponse.setError(firstError);
        errorResponse.setError(secondError);
        errorResponse.setMessage(firstMessage);
        errorResponse.setMessage(secondMessage);

        // THEN
        assertEquals(secondError, errorResponse.getError());
        assertEquals(secondMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageWithSameValue() {
        // GIVEN
        String sameValue = "Duplicate";

        // WHEN
        errorResponse.setError(sameValue);
        errorResponse.setMessage(sameValue);

        // THEN
        assertEquals(sameValue, errorResponse.getError());
        assertEquals(sameValue, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageWithDifferentEncodings() {
        // GIVEN
        String utf8Error = "Ошибка";
        String asciiMessage = "Error";

        // WHEN
        errorResponse.setError(utf8Error);
        errorResponse.setMessage(asciiMessage);

        // THEN
        assertEquals(utf8Error, errorResponse.getError());
        assertEquals(asciiMessage, errorResponse.getMessage());
    }

    @Test
    void testSetErrorAndMessageWithNumericStrings() {
        // GIVEN
        String numericError = "404";
        String numericMessage = "500";

        // WHEN
        errorResponse.setError(numericError);
        errorResponse.setMessage(numericMessage);

        // THEN
        assertEquals(numericError, errorResponse.getError());
        assertEquals(numericMessage, errorResponse.getMessage());
    }
}
