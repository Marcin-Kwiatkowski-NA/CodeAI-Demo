package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No specific setup required

        // WHEN: Creating a Forbidden instance using the default constructor
        Forbidden exception = new Forbidden();

        // THEN: Verify that the exception is created and has no message or cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Access denied";

        // WHEN: Creating a Forbidden instance with a message
        Forbidden exception = new Forbidden(message);

        // THEN: Verify that the message is correctly set
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating a Forbidden instance with a cause
        Forbidden exception = new Forbidden(cause);

        // THEN: Verify that the cause is correctly set
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        // The message should contain the cause's toString() representation
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Forbidden operation";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Creating a Forbidden instance with both message and cause
        Forbidden exception = new Forbidden(message, cause);

        // THEN: Verify that both message and cause are correctly set
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingForbiddenException() {
        // GIVEN: A message to be used when throwing the exception
        String message = "Access restricted";

        // WHEN & THEN: Verify that Forbidden exception is thrown correctly
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        assertNotNull(thrown);
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingForbiddenExceptionWithCause() {
        // GIVEN: A cause to be used when throwing the exception
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN: Verify that Forbidden exception with cause is thrown correctly
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        assertNotNull(thrown);
        assertEquals(cause, thrown.getCause());
        assertEquals("java.lang.IllegalStateException: Illegal state", thrown.getMessage());
    }

    @Test
    void testThrowingForbiddenExceptionWithMessageAndCause() {
        // GIVEN: A message and cause to be used when throwing the exception
        String message = "Operation not permitted";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN: Verify that Forbidden exception with message and cause is thrown correctly
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        assertNotNull(thrown);
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
