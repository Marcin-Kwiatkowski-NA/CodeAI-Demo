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
    void testConstructorWithLongMessage() {
        String message = "A".repeat(10000);
        UnAuthorized exception = new UnAuthorized(message);
        assertEquals(message, exception.getMessage());
        assertEquals(10000, exception.getMessage().length());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithSpecialCharacterMessage() {
        String message = "!@#$%^&*()_+{}|:\"<>?~";
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
    void testConstructorWithEmptyMessageAndCause() {
        String message = "";
        Throwable cause = new RuntimeException("Some cause");
        UnAuthorized exception = new UnAuthorized(message, cause);
        assertEquals("", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithWhitespaceMessageAndNullCause() {
        String message = " ";
        Throwable cause = null;
        UnAuthorized exception = new UnAuthorized(message, cause);
        assertEquals(" ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedWithMessage() {
        String message = "Access denied";
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedWithCause() {
        Throwable cause = new RuntimeException("Underlying issue");
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedWithNullMessage() {
        String message = null;
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(null, thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedWithEmptyMessage() {
        String message = "";
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals("", thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedWithWhitespaceMessage() {
        String message = "   ";
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals("   ", thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedWithLongMessage() {
        String message = "B".repeat(5000);
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(5000, thrown.getMessage().length());
    }

    @Test
    void testThrowingUnAuthorizedWithSpecialCharacterMessage() {
        String message = "!@#$%^&*()_+";
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedWithNullMessageAndNullCause() {
        String message = null;
        Throwable cause = null;
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(null, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedWithEmptyMessageAndNonNullCause() {
        String message = "";
        Throwable cause = new RuntimeException("Cause exists");
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals("", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedWithWhitespaceMessageAndNullCause() {
        String message = " ";
        Throwable cause = null;
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(" ", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }
}
