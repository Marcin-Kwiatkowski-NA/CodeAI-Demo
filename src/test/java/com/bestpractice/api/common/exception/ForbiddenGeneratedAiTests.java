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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No input parameters
        // WHEN: Creating a Forbidden instance using the default constructor
        Forbidden exception = new Forbidden();
        // THEN: Verify the exception is created and has no message or cause
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Access denied";
        // WHEN: Creating a Forbidden instance with a message
        Forbidden exception = new Forbidden(message);
        // THEN: Verify the message is correctly set
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a Forbidden instance with a cause
        Forbidden exception = new Forbidden(cause);
        // THEN: Verify the cause is correctly set
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Forbidden operation";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating a Forbidden instance with both message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: Verify both message and cause are correctly set
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingForbiddenExceptionWithMessage() {
        // GIVEN: A scenario where Forbidden should be thrown with a message
        String message = "Access restricted";
        // WHEN & THEN: Verify that Forbidden is thrown correctly
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingForbiddenExceptionWithCause() {
        // GIVEN: A scenario where Forbidden should be thrown with a cause
        Throwable cause = new IllegalStateException("Invalid state");
        // WHEN & THEN: Verify that Forbidden is thrown correctly with cause
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingForbiddenExceptionWithMessageAndCause() {
        // GIVEN: A scenario where Forbidden should be thrown with both message and cause
        String message = "Operation not permitted";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: Verify that Forbidden is thrown correctly with message and cause
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
