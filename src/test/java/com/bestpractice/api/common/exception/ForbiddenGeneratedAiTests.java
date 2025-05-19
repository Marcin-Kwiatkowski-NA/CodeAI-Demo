package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception class
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created without a message.
        Forbidden exception = new Forbidden();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        Forbidden exception = new Forbidden("Access denied");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Access denied", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause (Throwable) is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Access denied", exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        Exception cause = new Exception("Another error");
        Forbidden exception = new Forbidden("Access denied", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Access denied", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
