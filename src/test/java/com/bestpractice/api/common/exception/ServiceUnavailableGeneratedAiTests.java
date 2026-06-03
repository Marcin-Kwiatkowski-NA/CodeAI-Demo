package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateInstanceWithDefaultConstructor() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessage() {
        String message = "Service is temporarily unavailable";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithCause() {
        Throwable cause = new RuntimeException("Root cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void shouldCreateInstanceWithMessageAndCause() {
        String message = "Service failure";
        Throwable cause = new RuntimeException("Underlying issue");
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowServiceUnavailableWhenExplicitlyThrown() {
        String message = "Explicit throw test";
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowServiceUnavailableWithNullMessage() {
        String message = null;
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(null, thrown.getMessage());
    }

    @Test
    void shouldThrowServiceUnavailableWithNullCause() {
        Throwable cause = null;
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable("Null cause test", cause);
        });
        assertEquals("Null cause test", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldThrowServiceUnavailableWithEmptyMessage() {
        String message = "";
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals("", thrown.getMessage());
    }

    @Test
    void shouldHandleWhitespaceOnlyMessage() {
        String message = "   ";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertThat(exception).isNotNull();
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleSingleCharacterMessage() {
        String message = "A";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertThat(exception).isNotNull();
        assertEquals("A", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleVeryLongMessage() {
        String longMessage = "X".repeat(10000);
        ServiceUnavailable exception = new ServiceUnavailable(longMessage);
        assertThat(exception).isNotNull();
        assertEquals(longMessage, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleCauseWithEmptyMessage() {
        Throwable cause = new RuntimeException("");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("");
    }

    @Test
    void shouldHandleCauseWithWhitespaceMessage() {
        Throwable cause = new RuntimeException("   ");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("   ");
    }

    @Test
    void shouldHandleMessageAndCauseWithEmptyValues() {
        String message = "";
        Throwable cause = new RuntimeException("");
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertThat(exception).isNotNull();
        assertEquals("", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldHandleMessageAndCauseWithWhitespaceValues() {
        String message = "   ";
        Throwable cause = new RuntimeException("   ");
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertThat(exception).isNotNull();
        assertEquals("   ", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldPreserveMessageAndCauseWhenBothAreNull() {
        String message = null;
        Throwable cause = null;
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldPreserveCauseStackTrace() {
        Throwable cause = new RuntimeException("Stack trace test");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertThat(exception.getCause().getStackTrace()).isNotEmpty();
        assertThat(exception.getCause().getMessage()).isEqualTo("Stack trace test");
    }
}
