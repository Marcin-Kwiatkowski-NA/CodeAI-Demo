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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateNotFoundWithNoArgs() {
        NotFound exception = new NotFound();
        assertThat(exception).isInstanceOf(NotFound.class);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateNotFoundWithMessage() {
        String message = "Resource not found";
        NotFound exception = new NotFound(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateNotFoundWithCause() {
        Throwable cause = new IllegalArgumentException("Invalid argument");
        NotFound exception = new NotFound(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void shouldCreateNotFoundWithMessageAndCause() {
        String message = "Entity missing";
        Throwable cause = new NullPointerException("Null value");
        NotFound exception = new NotFound(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowNotFoundWhenExplicitlyThrown() {
        String message = "Explicit throw test";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowNotFoundWhenCauseIsNull() {
        Throwable cause = null;
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Null cause test", cause);
        });
        assertEquals("Null cause test", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldThrowNotFoundWhenMessageIsNull() {
        String message = null;
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(null, thrown.getMessage());
    }

    @Test
    void shouldThrowNotFoundWhenMessageAndCauseAreNull() {
        String message = null;
        Throwable cause = null;
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals(null, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldHandleEmptyMessageGracefully() {
        String message = "";
        NotFound exception = new NotFound(message);
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleWhitespaceOnlyMessageGracefully() {
        String message = "   ";
        NotFound exception = new NotFound(message);
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleVeryLongMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("x");
        }
        String longMessage = sb.toString();
        NotFound exception = new NotFound(longMessage);
        assertEquals(longMessage.length(), exception.getMessage().length());
        assertEquals(longMessage, exception.getMessage());
    }

    @Test
    void shouldHandleCauseWithEmptyMessage() {
        Throwable cause = new RuntimeException("");
        NotFound exception = new NotFound(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("java.lang.RuntimeException");
    }

    @Test
    void shouldHandleCauseWithWhitespaceMessage() {
        Throwable cause = new RuntimeException("   ");
        NotFound exception = new NotFound(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("   ");
    }

    @Test
    void shouldHandleCauseWithVeryLongMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5000; i++) {
            sb.append("y");
        }
        Throwable cause = new RuntimeException(sb.toString());
        NotFound exception = new NotFound(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("y");
    }

    @Test
    void shouldHandleMessageWithSpecialCharacters() {
        String message = "!@#$%^&*()_+{}|:\"<>?`~[];',./";
        NotFound exception = new NotFound(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleUnicodeMessage() {
        String message = "エラーが発生しました 🚀";
        NotFound exception = new NotFound(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleSingleCharacterMessage() {
        String message = "A";
        NotFound exception = new NotFound(message);
        assertEquals("A", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleNumericStringMessage() {
        String message = String.valueOf(Integer.MAX_VALUE);
        NotFound exception = new NotFound(message);
        assertEquals(String.valueOf(Integer.MAX_VALUE), exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleBoundaryNumericValuesInMessage() {
        String minValueMessage = String.valueOf(Integer.MIN_VALUE);
        String maxValueMessage = String.valueOf(Long.MAX_VALUE);
        NotFound minException = new NotFound(minValueMessage);
        NotFound maxException = new NotFound(maxValueMessage);
        assertEquals(minValueMessage, minException.getMessage());
        assertEquals(maxValueMessage, maxException.getMessage());
    }

    @Test
    void shouldPreserveCauseStackTrace() {
        Throwable cause = new IllegalStateException("Stack trace test");
        NotFound exception = new NotFound("Stack trace preserved", cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getCause().getStackTrace()).isNotEmpty();
    }

    @Test
    void shouldNotModifyMessageWhenConstructedWithNullCause() {
        String message = "Message without cause";
        NotFound exception = new NotFound(message, null);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }
}
