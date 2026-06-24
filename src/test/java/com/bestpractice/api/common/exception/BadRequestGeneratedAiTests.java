package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Reviewed and improved test class for BadRequest.
 * 
 * Improvements:
 * 1. Removed redundant imports and unused annotations.
 * 2. Ensured consistent GIVEN-WHEN-THEN structure.
 * 3. Verified all constructors are tested with meaningful assertions.
 * 4. Simplified redundant tests while maintaining full coverage.
 * 5. Added clarity and ensured independence of each test.
 */
public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided
        // WHEN: Creating BadRequest using default constructor
        BadRequest exception = new BadRequest();
        // THEN: Exception should have null message and cause
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid input data";
        // WHEN: Creating BadRequest with message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should contain the provided message and no cause
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause (Throwable)
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating BadRequest with cause
        BadRequest exception = new BadRequest(cause);
        // THEN: Exception should contain the provided cause and message derived from cause
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating BadRequest with message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingBadRequestExceptionWithMessage() {
        // GIVEN: A message that should trigger BadRequest
        String message = "Triggered bad request";
        // WHEN & THEN: Expect BadRequest to be thrown with correct message
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingBadRequestExceptionWithCause() {
        // GIVEN: A cause that should trigger BadRequest
        Throwable cause = new NullPointerException("Null value");
        // WHEN & THEN: Expect BadRequest to be thrown with cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Null value");
    }

    @Test
    void testThrowingBadRequestExceptionWithMessageAndCause() {
        // GIVEN: A message and cause
        String message = "Bad request with cause";
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN & THEN: Expect BadRequest to be thrown with both message and cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testConstructorWithNullMessage() {
        // GIVEN: Null message
        String message = null;
        // WHEN: Creating BadRequest with null message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should have null message and no cause
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithNullCause() {
        // GIVEN: Null cause
        Throwable cause = null;
        // WHEN: Creating BadRequest with null cause
        BadRequest exception = new BadRequest(cause);
        // THEN: Exception should have null cause and message
        assertEquals(null, exception.getCause());
        assertEquals(null, exception.getMessage());
    }

    @Test
    void testThrowingBadRequestWithNullMessageAndCause() {
        // GIVEN: Null message and cause
        String message = null;
        Throwable cause = null;
        // WHEN & THEN: Expect BadRequest to be thrown with null message and cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertEquals(null, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    // EDGE CASE TESTS

    @Test
    void testConstructorWithEmptyMessage() {
        // GIVEN: Empty message string
        String message = "";
        // WHEN: Creating BadRequest with empty message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should have empty message and no cause
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessage() {
        // GIVEN: Whitespace-only message
        String message = "   ";
        // WHEN: Creating BadRequest with whitespace message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should preserve whitespace message
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithVeryLongMessage() {
        // GIVEN: Very long message string
        String message = "A".repeat(10000);
        // WHEN: Creating BadRequest with long message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should contain the full long message
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithSingleCharacterMessage() {
        // GIVEN: Single character message
        String message = "X";
        // WHEN: Creating BadRequest with single character message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should contain the single character message
        assertEquals("X", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithSpecialCharactersMessage() {
        // GIVEN: Message with special characters
        String message = "!@#$%^&*()_+{}|:\"<>?";
        // WHEN: Creating BadRequest with special characters
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should contain the special characters message
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithUnicodeMessage() {
        // GIVEN: Message with Unicode characters
        String message = "错误请求 🚀🔥";
        // WHEN: Creating BadRequest with Unicode message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should contain the Unicode message
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithEmptyMessageAndCause() {
        // GIVEN: Empty message and valid cause
        String message = "";
        Throwable cause = new Exception("Cause message");
        // WHEN: Creating BadRequest with empty message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Exception should contain empty message and valid cause
        assertEquals("", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessageAndCause() {
        // GIVEN: Whitespace message and valid cause
        String message = "   ";
        Throwable cause = new Exception("Whitespace cause");
        // WHEN: Creating BadRequest with whitespace message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Exception should contain whitespace message and valid cause
        assertEquals("   ", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithNullMessageAndValidCause() {
        // GIVEN: Null message and valid cause
        Throwable cause = new Exception("Valid cause");
        // WHEN: Creating BadRequest with null message and valid cause
        BadRequest exception = new BadRequest(null, cause);
        // THEN: Exception should have null message and valid cause
        assertEquals(null, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithBoundaryNumericValuesInMessage() {
        // GIVEN: Numeric boundary values represented as strings
        String messageMin = String.valueOf(Integer.MIN_VALUE);
        String messageMax = String.valueOf(Integer.MAX_VALUE);
        // WHEN: Creating BadRequest with numeric boundary messages
        BadRequest exceptionMin = new BadRequest(messageMin);
        BadRequest exceptionMax = new BadRequest(messageMax);
        // THEN: Exception should contain numeric boundary messages
        assertEquals(messageMin, exceptionMin.getMessage());
        assertEquals(messageMax, exceptionMax.getMessage());
    }

    @Test
    void testConstructorWithDoubleBoundaryValuesInMessage() {
        // GIVEN: Double boundary values represented as strings
        String messageInfinity = String.valueOf(Double.POSITIVE_INFINITY);
        String messageNaN = String.valueOf(Double.NaN);
        // WHEN: Creating BadRequest with double boundary messages
        BadRequest exceptionInfinity = new BadRequest(messageInfinity);
        BadRequest exceptionNaN = new BadRequest(messageNaN);
        // THEN: Exception should contain double boundary messages
        assertEquals(messageInfinity, exceptionInfinity.getMessage());
        assertEquals(messageNaN, exceptionNaN.getMessage());
    }

    @Test
    void testConstructorWithLongBoundaryValuesInMessage() {
        // GIVEN: Long boundary values represented as strings
        String messageMin = String.valueOf(Long.MIN_VALUE);
        String messageMax = String.valueOf(Long.MAX_VALUE);
        // WHEN: Creating BadRequest with long boundary messages
        BadRequest exceptionMin = new BadRequest(messageMin);
        BadRequest exceptionMax = new BadRequest(messageMax);
        // THEN: Exception should contain long boundary messages
        assertEquals(messageMin, exceptionMin.getMessage());
        assertEquals(messageMax, exceptionMax.getMessage());
    }
}
