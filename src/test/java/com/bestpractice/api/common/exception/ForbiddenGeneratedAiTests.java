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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided
        // WHEN: Creating a new Forbidden instance using default constructor
        Forbidden exception = new Forbidden();
        // THEN: Exception should be created with null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Access denied";
        // WHEN: Creating a new Forbidden instance with message
        Forbidden exception = new Forbidden(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a new Forbidden instance with cause
        Forbidden exception = new Forbidden(cause);
        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        // The message should contain the cause's toString() representation
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Forbidden operation";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating a new Forbidden instance with message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingForbiddenException() {
        // GIVEN: A message for the exception
        String message = "Access forbidden";
        // WHEN & THEN: Ensure that throwing the exception behaves as expected
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingForbiddenExceptionWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalStateException("Invalid state");
        // WHEN & THEN: Ensure that throwing the exception with cause behaves as expected
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void testThrowingForbiddenExceptionWithMessageAndCause() {
        // GIVEN: A message and cause for the exception
        String message = "Forbidden action";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: Ensure that throwing the exception with message and cause behaves as expected
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
