package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateInstanceWithDefaultConstructor() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessageConstructor() {
        String message = "Service is unavailable";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithCauseConstructor() {
        Throwable cause = new RuntimeException("Root cause");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void shouldCreateInstanceWithMessageAndCauseConstructor() {
        String message = "Service failed";
        Throwable cause = new RuntimeException("Underlying issue");
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowServiceUnavailableWhenExplicitlyThrown() {
        String message = "Service down";
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
    void shouldThrowServiceUnavailableWithOnlyCause() {
        Throwable cause = new IllegalStateException("Illegal state");
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Illegal state");
    }

    @Test
    void shouldHandleEmptyMessageGracefully() {
        String message = "";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleWhitespaceOnlyMessageGracefully() {
        String message = "   ";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleVeryLongMessageGracefully() {
        String longMessage = "A".repeat(10000);
        ServiceUnavailable exception = new ServiceUnavailable(longMessage);
        assertEquals(longMessage, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleSingleCharacterMessageGracefully() {
        String message = "X";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals("X", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleMessageWithSpecialCharactersGracefully() {
        String message = "!@#$%^&*()_+-=[]{}|;':,.<>?/";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleMessageWithUnicodeCharactersGracefully() {
        String message = "服务不可用 🚫";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleNullMessageAndNullCauseGracefully() {
        String message = null;
        Throwable cause = null;
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleNullMessageWithValidCauseGracefully() {
        String message = null;
        Throwable cause = new RuntimeException("Valid cause");
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertEquals(null, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldHandleValidMessageWithNullCauseGracefully() {
        String message = "Valid message";
        Throwable cause = null;
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        assertEquals("Valid message", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleMessageContainingNewlinesGracefully() {
        String message = "Service\nUnavailable\nError";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals("Service\nUnavailable\nError", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleMessageContainingTabsGracefully() {
        String message = "Service\tUnavailable";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals("Service\tUnavailable", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleMessageContainingMixedWhitespaceGracefully() {
        String message = " \t\n ";
        ServiceUnavailable exception = new ServiceUnavailable(message);
        assertEquals(" \t\n ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldPreserveCauseStackTrace() {
        Throwable cause = new RuntimeException("Stack trace test");
        ServiceUnavailable exception = new ServiceUnavailable("Message", cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getCause().getStackTrace()).isNotEmpty();
    }

    @Test
    void shouldNotModifyMessageWhenConstructedWithCauseOnly() {
        Throwable cause = new RuntimeException("Cause only");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertThat(exception.getMessage()).contains("Cause only");
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldSupportReThrowingWithoutMessageLoss() {
        ServiceUnavailable original = new ServiceUnavailable("Original message");
        ServiceUnavailable rethrown = assertThrows(ServiceUnavailable.class, () -> {
            throw original;
        });
        assertEquals("Original message", rethrown.getMessage());
    }
}
