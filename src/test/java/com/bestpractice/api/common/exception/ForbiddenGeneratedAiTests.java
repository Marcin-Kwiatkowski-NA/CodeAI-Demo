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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDefaultConstructor() {
        Forbidden exception = new Forbidden();
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Access denied";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new IllegalArgumentException("Invalid argument");
        Forbidden exception = new Forbidden(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Forbidden operation";
        Throwable cause = new RuntimeException("Underlying issue");
        Forbidden exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithNullMessage() {
        Forbidden exception = new Forbidden((String) null);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        String message = "";
        Forbidden exception = new Forbidden(message);
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessage() {
        String message = "   ";
        Forbidden exception = new Forbidden(message);
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithLongMessage() {
        String message = "A".repeat(5000);
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithSpecialCharactersMessage() {
        String message = "!@#$%^&*()_+{}|:\"<>?";
        Forbidden exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithUnicodeMessage() {
        String message = "禁止 🚫 操作";
        Forbidden exception = new Forbidden(message);
        assertEquals("禁止 🚫 操作", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessageContainingNewlines() {
        String message = "Forbidden\nOperation\nDetected";
        Forbidden exception = new Forbidden(message);
        assertEquals("Forbidden\nOperation\nDetected", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithNullCause() {
        Forbidden exception = new Forbidden((Throwable) null);
        assertEquals(null, exception.getCause());
        assertEquals(null, exception.getMessage());
    }

    @Test
    void testConstructorWithNullMessageAndCause() {
        Forbidden exception = new Forbidden(null, null);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testThrowForbiddenExceptionExplicitly() {
        String message = "Explicit forbidden";
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowForbiddenExceptionWithCauseExplicitly() {
        Throwable cause = new IllegalStateException("Illegal state");
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden("Forbidden with cause", cause);
        });
        assertEquals("Forbidden with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testConstructorWithSingleCharacterMessage() {
        String message = "F";
        Forbidden exception = new Forbidden(message);
        assertEquals("F", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndNullCauseBoundary() {
        String message = "Boundary test message";
        Forbidden exception = new Forbidden(message, null);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessageContainingTabsBoundary() {
        String message = "Forbidden\tOperation\tDetected";
        Forbidden exception = new Forbidden(message);
        assertEquals("Forbidden\tOperation\tDetected", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testInheritanceFromRuntimeException() {
        Forbidden exception = new Forbidden("Test");
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}
