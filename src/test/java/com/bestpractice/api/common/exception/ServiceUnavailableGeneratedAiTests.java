package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any shared state before each test
    }

    @Test
    void shouldCreateInstanceWithDefaultConstructor() {
        // GIVEN: No input parameters

        // WHEN: Creating instance using default constructor
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN: Verify instance is created and has no message or cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessage() {
        // GIVEN: A specific error message
        String message = "Service is unavailable";

        // WHEN: Creating instance using message constructor
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is set correctly
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Creating instance using cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN: Verify cause is set correctly
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Service temporarily unavailable";
        Throwable cause = new RuntimeException("Network issue");

        // WHEN: Creating instance using message and cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: Verify both message and cause are set correctly
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowServiceUnavailableWhenExplicitlyThrown() {
        // GIVEN: A message for the exception
        String message = "Explicit throw test";

        // WHEN & THEN: Verify that throwing the exception results in correct type and message
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowServiceUnavailableWithNullMessage() {
        // GIVEN: Null message
        String message = null;

        // WHEN & THEN: Verify that throwing the exception with null message works correctly
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertNull(thrown.getMessage());
    }

    @Test
    void shouldThrowServiceUnavailableWithNullCause() {
        // GIVEN: Null cause
        Throwable cause = null;

        // WHEN & THEN: Verify that throwing the exception with null cause works correctly
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable("Null cause test", cause);
        });
        assertEquals("Null cause test", thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void shouldThrowServiceUnavailableWithOnlyCause() {
        // GIVEN: A cause exception
        Throwable cause = new IllegalStateException("Underlying issue");

        // WHEN & THEN: Verify that throwing the exception with cause works correctly
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void shouldHandleEmptyMessageGracefully() {
        // GIVEN: An empty string message
        String message = "";

        // WHEN: Creating instance with empty message
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is empty but valid
        assertNotNull(exception);
        assertEquals("", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldHandleWhitespaceOnlyMessage() {
        // GIVEN: A whitespace-only message
        String message = "   ";

        // WHEN: Creating instance with whitespace message
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is preserved as whitespace
        assertNotNull(exception);
        assertEquals("   ", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldHandleVeryLongMessage() {
        // GIVEN: A very long message string
        String longMessage = "A".repeat(10_000);

        // WHEN: Creating instance with long message
        ServiceUnavailable exception = new ServiceUnavailable(longMessage);

        // THEN: Verify message is stored correctly
        assertNotNull(exception);
        assertEquals(longMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldHandleNestedCauseChain() {
        // GIVEN: A nested cause chain
        Throwable rootCause = new IllegalArgumentException("Root");
        Throwable midCause = new RuntimeException("Mid", rootCause);
        Throwable topCause = new IllegalStateException("Top", midCause);

        // WHEN: Creating instance with nested cause
        ServiceUnavailable exception = new ServiceUnavailable("Nested cause test", topCause);

        // THEN: Verify top cause and nested structure
        assertNotNull(exception);
        assertEquals("Nested cause test", exception.getMessage());
        assertEquals(topCause, exception.getCause());
        assertEquals(midCause, exception.getCause().getCause());
        assertEquals(rootCause, exception.getCause().getCause().getCause());
    }

    @Test
    void shouldHandleNullMessageAndNullCauseGracefully() {
        // GIVEN: Both message and cause are null
        String message = null;
        Throwable cause = null;

        // WHEN: Creating instance with null message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: Verify both are null
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldHandleSingleCharacterMessage() {
        // GIVEN: A single character message
        String message = "X";

        // WHEN: Creating instance with single character message
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is stored correctly
        assertNotNull(exception);
        assertEquals("X", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldHandleUnicodeMessage() {
        // GIVEN: A Unicode message
        String message = "⚠️ Service unavailable 🚫";

        // WHEN: Creating instance with Unicode message
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is stored correctly
        assertNotNull(exception);
        assertEquals("⚠️ Service unavailable 🚫", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldHandleNullThrowableMessageGracefully() {
        // GIVEN: Throwable with null message
        Throwable cause = new RuntimeException((String) null);

        // WHEN: Creating instance with cause having null message
        ServiceUnavailable exception = new ServiceUnavailable("Null throwable message", cause);

        // THEN: Verify message and cause are set correctly
        assertNotNull(exception);
        assertEquals("Null throwable message", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertNull(exception.getCause().getMessage());
    }

    @Test
    void shouldHandleThrowableWithEmptyMessage() {
        // GIVEN: Throwable with empty message
        Throwable cause = new RuntimeException("");

        // WHEN: Creating instance with cause having empty message
        ServiceUnavailable exception = new ServiceUnavailable("Empty throwable message", cause);

        // THEN: Verify message and cause are set correctly
        assertNotNull(exception);
        assertEquals("Empty throwable message", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals("", exception.getCause().getMessage());
    }
}
