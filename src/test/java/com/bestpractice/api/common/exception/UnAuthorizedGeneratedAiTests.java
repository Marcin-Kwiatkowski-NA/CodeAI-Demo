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

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testDefaultConstructor() {
        UnAuthorized exception = new UnAuthorized();
        assertThat(exception).isInstanceOf(UnAuthorized.class);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        String message = "Unauthorized access";
        UnAuthorized exception = new UnAuthorized(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithNullMessage() {
        String message = null;
        UnAuthorized exception = new UnAuthorized(message);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithEmptyMessage() {
        String message = "";
        UnAuthorized exception = new UnAuthorized(message);
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessage() {
        String message = "   ";
        UnAuthorized exception = new UnAuthorized(message);
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithSingleCharacterMessage() {
        String message = "A";
        UnAuthorized exception = new UnAuthorized(message);
        assertEquals("A", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithLongMessage() {
        String message = "A".repeat(1000);
        UnAuthorized exception = new UnAuthorized(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Root cause");
        UnAuthorized exception = new UnAuthorized(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void testConstructorWithNullCause() {
        Throwable cause = null;
        UnAuthorized exception = new UnAuthorized(cause);
        assertEquals(null, exception.getCause());
        assertEquals(null, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Unauthorized with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");
        UnAuthorized exception = new UnAuthorized(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithEmptyMessageAndNullCause() {
        String message = "";
        Throwable cause = null;
        UnAuthorized exception = new UnAuthorized(message, cause);
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessageAndValidCause() {
        String message = " ";
        Throwable cause = new RuntimeException("Whitespace cause");
        UnAuthorized exception = new UnAuthorized(message, cause);
        assertEquals(" ", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithVeryLongMessageAndCause() {
        String message = "Unauthorized".repeat(500);
        Throwable cause = new Exception("Deep cause");
        UnAuthorized exception = new UnAuthorized(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithMessage() {
        String message = "Access denied";
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithCause() {
        Throwable cause = new RuntimeException("Underlying issue");
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Underlying issue");
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithMessageAndCause() {
        String message = "Unauthorized operation";
        Throwable cause = new IllegalStateException("Illegal state");
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithNullMessageAndNullCause() {
        String message = null;
        Throwable cause = null;
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(null, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithEmptyMessageAndCause() {
        String message = "";
        Throwable cause = new RuntimeException("Empty message cause");
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals("", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
