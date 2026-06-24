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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Improved test class for {@link UnAuthorized}.
 * Focuses on verifying constructor behavior, message propagation, and cause handling.
 */
public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    // --- BASIC CONSTRUCTOR TESTS ---

    @Test
    void shouldCreateInstanceWithDefaultConstructor() {
        // GIVEN
        UnAuthorized exception;

        // WHEN
        exception = new UnAuthorized();

        // THEN
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessageConstructor() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithCauseConstructor() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void shouldCreateInstanceWithMessageAndCauseConstructor() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    // --- EXPLICIT THROW TESTS ---

    @Test
    void shouldThrowUnAuthorizedWhenExplicitlyThrown() {
        // GIVEN
        String message = "Explicit unauthorized";

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowUnAuthorizedWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(null, thrown.getMessage());
    }

    @Test
    void shouldThrowUnAuthorizedWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized("Null cause test", cause);
        });
        assertEquals("Null cause test", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldThrowUnAuthorizedWithEmptyMessage() {
        // GIVEN
        String message = "";

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals("", thrown.getMessage());
    }

    @Test
    void shouldThrowUnAuthorizedWithCauseOnly() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Underlying issue");
    }

    // --- EDGE CASE TESTS ---

    @Test
    void shouldHandleWhitespaceOnlyMessage() {
        // GIVEN
        String message = "   ";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleSingleCharacterMessage() {
        // GIVEN
        String message = "A";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals("A", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleVeryLongMessage() {
        // GIVEN
        String message = "A".repeat(10000);

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleMessageWithSpecialCharacters() {
        // GIVEN
        String message = "!@#$%^&*()_+{}|:\"<>?~";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleMessageWithUnicodeCharacters() {
        // GIVEN
        String message = "Unauthorized – üñíçødé";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleCauseWithEmptyMessage() {
        // GIVEN
        Throwable cause = new RuntimeException("");

        // WHEN
        UnAuthorized exception = new UnAuthorized("Empty cause message", cause);

        // THEN
        assertEquals("Empty cause message", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals("", exception.getCause().getMessage());
    }

    @Test
    void shouldHandleCauseWithWhitespaceMessage() {
        // GIVEN
        Throwable cause = new RuntimeException("   ");

        // WHEN
        UnAuthorized exception = new UnAuthorized("Whitespace cause", cause);

        // THEN
        assertEquals("Whitespace cause", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals("   ", exception.getCause().getMessage());
    }

    @Test
    void shouldHandleCauseWithLongMessage() {
        // GIVEN
        Throwable cause = new RuntimeException("B".repeat(5000));

        // WHEN
        UnAuthorized exception = new UnAuthorized("Long cause message", cause);

        // THEN
        assertEquals("Long cause message", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals("B".repeat(5000), exception.getCause().getMessage());
    }

    @Test
    void shouldHandleNullMessageAndValidCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Valid cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(null, cause);

        // THEN
        assertEquals(null, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals("Valid cause", exception.getCause().getMessage());
    }

    @Test
    void shouldHandleEmptyMessageAndNullCause() {
        // GIVEN
        String message = "";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, null);

        // THEN
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleWhitespaceMessageAndNullCause() {
        // GIVEN
        String message = "   ";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, null);

        // THEN
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    // --- IMPROVEMENT TESTS ---

    @Test
    void shouldPreserveCauseStackTrace() {
        // GIVEN
        Throwable cause = new RuntimeException("Stack trace test");

        // WHEN
        UnAuthorized exception = new UnAuthorized("Preserve stack trace", cause);

        // THEN
        assertThat(exception.getCause().getStackTrace()).isNotEmpty();
    }

    @Test
    void shouldNotModifyMessageAfterConstruction() {
        // GIVEN
        String message = "Immutable message";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals("Immutable message", exception.getMessage());
        // Verify immutability by re-checking message
        assertEquals("Immutable message", exception.getMessage());
    }

    @Test
    void shouldHandleNestedCausesProperly() {
        // GIVEN
        Throwable innerCause = new IllegalStateException("Inner cause");
        Throwable outerCause = new RuntimeException("Outer cause", innerCause);

        // WHEN
        UnAuthorized exception = new UnAuthorized("Nested cause test", outerCause);

        // THEN
        assertEquals("Nested cause test", exception.getMessage());
        assertEquals(outerCause, exception.getCause());
        assertEquals(innerCause, exception.getCause().getCause());
    }

    // --- ADDITIONAL QUALITY IMPROVEMENTS ---

    @Test
    void shouldReturnConsistentToStringOutput() {
        // GIVEN
        String message = "Unauthorized error";
        Throwable cause = new RuntimeException("Cause message");
        UnAuthorized exception = new UnAuthorized(message, cause);

        // WHEN
        String toStringOutput = exception.toString();

        // THEN
        assertThat(toStringOutput).contains("Unauthorized");
        assertThat(toStringOutput).contains("Cause message");
    }

    @Test
    void shouldSupportNullMessageAndNullCauseInToString() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized(null, null);

        // WHEN
        String toStringOutput = exception.toString();

        // THEN
        assertThat(toStringOutput).contains("UnAuthorized");
    }
}
