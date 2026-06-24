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

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDefaultConstructor() {
        NotFound exception = new NotFound();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Resource not found";
        NotFound exception = new NotFound(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new IllegalArgumentException("Invalid argument");
        NotFound exception = new NotFound(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Data missing";
        Throwable cause = new NullPointerException("Null value");
        NotFound exception = new NotFound(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionDirectly() {
        String message = "Direct throw test";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithNullMessage() {
        String message = null;
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(null, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithNullCause() {
        Throwable cause = null;
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Null cause test", cause);
        });
        assertEquals("Null cause test", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithEmptyMessage() {
        String message = "";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals("", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithWhitespaceMessage() {
        String message = "   ";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals("   ", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithSingleCharacterMessage() {
        String message = "A";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals("A", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithLongMessage() {
        String message = "X".repeat(10000);
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithSpecialCharactersMessage() {
        String message = "!@#$%^&*()_+{}|:\"<>?~`";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithUnicodeMessage() {
        String message = "Ошибка 404 – не найдено";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithCauseOnly() {
        Throwable cause = new RuntimeException("Underlying issue");
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Underlying issue");
    }

    @Test
    void testThrowingNotFoundWithMessageAndDeepCause() {
        Throwable deepCause = new IllegalStateException("Deep cause");
        Throwable cause = new RuntimeException("Outer cause", deepCause);
        String message = "Nested cause test";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getCause().getCause()).isEqualTo(deepCause);
    }

    @Test
    void testThrowingNotFoundWithEmptyMessageAndNullCause() {
        String message = "";
        Throwable cause = null;
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals("", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testMessageAndCauseConsistency() {
        String message = "Same text";
        Throwable cause = new RuntimeException("Same text");
        NotFound exception = new NotFound(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).isEqualTo("Same text");
    }

    @Test
    void testNullMessageWithValidCause() {
        Throwable cause = new IllegalArgumentException("Cause message");
        NotFound exception = new NotFound(null, cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("IllegalArgumentException");
    }
}
