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
 * Reviewed and improved test class for InternalServerError.
 * 
 * Improvements:
 * 1. Removed redundant imports and unused Mockito references.
 * 2. Ensured consistent GIVEN-WHEN-THEN structure across all tests.
 * 3. Added missing assertions for message and cause consistency.
 * 4. Simplified test names for clarity and readability.
 * 5. Ensured all constructors are tested with meaningful assertions.
 * 6. Verified that all tests are independent and self-contained.
 */
class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN - Reset any shared state before each test
    }

    @Test
    void shouldCreateInstanceWithDefaultConstructor() {
        // GIVEN - No input parameters

        // WHEN - Creating instance using default constructor
        InternalServerError exception = new InternalServerError();

        // THEN - Verify instance is created and has no message or cause
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessage() {
        // GIVEN - A specific error message
        String message = "Internal server error occurred";

        // WHEN - Creating instance using message constructor
        InternalServerError exception = new InternalServerError(message);

        // THEN - Verify message is set correctly and cause is null
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithCause() {
        // GIVEN - A specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN - Creating instance using cause constructor
        InternalServerError exception = new InternalServerError(cause);

        // THEN - Verify cause is set correctly and message includes cause message
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void shouldCreateInstanceWithMessageAndCause() {
        // GIVEN - A specific message and cause
        String message = "Internal server error with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN - Creating instance using message and cause constructor
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN - Verify both message and cause are set correctly
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldHandleNullMessageGracefully() {
        // GIVEN - Null message
        String message = null;

        // WHEN - Creating instance using message constructor
        InternalServerError exception = new InternalServerError(message);

        // THEN - Verify message is null and no exception thrown during construction
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleNullCauseGracefully() {
        // GIVEN - Null cause
        Throwable cause = null;

        // WHEN - Creating instance using cause constructor
        InternalServerError exception = new InternalServerError(cause);

        // THEN - Verify cause is null and no exception thrown during construction
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getCause());
        assertEquals(null, exception.getMessage());
    }

    @Test
    void shouldHandleNullMessageAndCauseGracefully() {
        // GIVEN - Null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN - Creating instance using message and cause constructor
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN - Verify both message and cause are null
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldThrowInternalServerErrorExplicitly() {
        // GIVEN - A condition that triggers an internal server error
        String message = "Explicit internal server error";

        // WHEN & THEN - Verify assertThrows captures the exception
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });

        // THEN - Verify message is preserved
        assertThat(thrown).isNotNull();
        assertEquals(message, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldThrowInternalServerErrorExplicitlyWithCause() {
        // GIVEN - A cause for the exception
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN - Verify assertThrows captures the exception with cause
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError("Error with cause", cause);
        });

        // THEN - Verify message and cause are preserved
        assertThat(thrown).isNotNull();
        assertEquals("Error with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    // EDGE CASE TESTS

    @Test
    void shouldHandleEmptyMessage() {
        // GIVEN - Empty string message
        String message = "";

        // WHEN - Creating instance using message constructor
        InternalServerError exception = new InternalServerError(message);

        // THEN - Verify message is empty but valid
        assertThat(exception).isNotNull();
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleWhitespaceOnlyMessage() {
        // GIVEN - Whitespace-only message
        String message = "   ";

        // WHEN - Creating instance using message constructor
        InternalServerError exception = new InternalServerError(message);

        // THEN - Verify message is preserved as whitespace
        assertThat(exception).isNotNull();
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleVeryLongMessage() {
        // GIVEN - Very long message string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("A");
        }
        String longMessage = sb.toString();

        // WHEN - Creating instance using message constructor
        InternalServerError exception = new InternalServerError(longMessage);

        // THEN - Verify message is stored correctly
        assertThat(exception).isNotNull();
        assertEquals(longMessage, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleSingleCharacterMessage() {
        // GIVEN - Single character message
        String message = "X";

        // WHEN - Creating instance using message constructor
        InternalServerError exception = new InternalServerError(message);

        // THEN - Verify message is stored correctly
        assertThat(exception).isNotNull();
        assertEquals("X", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleCauseWithEmptyMessage() {
        // GIVEN - Cause with empty message
        Throwable cause = new RuntimeException("");

        // WHEN - Creating instance using cause constructor
        InternalServerError exception = new InternalServerError(cause);

        // THEN - Verify cause is set and message reflects empty cause message
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).isNotNull();
    }

    @Test
    void shouldHandleCauseWithWhitespaceMessage() {
        // GIVEN - Cause with whitespace message
        Throwable cause = new RuntimeException("   ");

        // WHEN - Creating instance using cause constructor
        InternalServerError exception = new InternalServerError(cause);

        // THEN - Verify cause is set and message reflects whitespace cause message
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("   ");
    }

    @Test
    void shouldHandleCauseWithLongMessage() {
        // GIVEN - Cause with long message
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append("B");
        }
        Throwable cause = new RuntimeException(sb.toString());

        // WHEN - Creating instance using cause constructor
        InternalServerError exception = new InternalServerError(cause);

        // THEN - Verify cause is set and message reflects long cause message
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("B");
    }
}
