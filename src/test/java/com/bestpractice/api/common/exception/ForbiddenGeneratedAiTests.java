package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: A Forbidden instance created using the default constructor
        Forbidden exception;

        // WHEN: The exception is instantiated
        exception = new Forbidden();

        // THEN: The exception should have no message or cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A message to pass to the exception
        String message = "Access denied";

        // WHEN: The exception is instantiated with a message
        Forbidden exception = new Forbidden(message);

        // THEN: The exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A cause to pass to the exception
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: The exception is instantiated with a cause
        Forbidden exception = new Forbidden(cause);

        // THEN: The exception should contain the provided cause
        assertEquals(cause, exception.getCause());

        // AND: Verify that throwing the exception behaves as expected
        assertThatThrownBy(() -> { throw exception; })
            .isInstanceOf(Forbidden.class)
            .hasCause(cause);
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and cause to pass to the exception
        String message = "Forbidden operation";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: The exception is instantiated with both message and cause
        Forbidden exception = new Forbidden(message, cause);

        // THEN: The exception should contain both the message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testExceptionThrownProperly() {
        // GIVEN: A Forbidden exception with a message
        String message = "Access restricted";

        // WHEN & THEN: Verify that throwing the exception behaves as expected
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN: The thrown exception should contain the correct message
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testExceptionThrownWithCause() {
        // GIVEN: A Forbidden exception with a cause
        Throwable cause = new IllegalStateException("Invalid state");

        // WHEN & THEN: Verify that throwing the exception behaves as expected
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN: The thrown exception should contain the correct cause
        assertEquals(cause, thrown.getCause());
    }
}
