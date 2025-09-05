package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        Forbidden exception = new Forbidden();
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        Forbidden exception = new Forbidden("This is a forbidden action.");
        assertNotNull(exception);
        assertEquals("This is a forbidden action.", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause exception is provided to the constructor.
        // WHEN: The constructor is called with a cause exception.
        // THEN: A RuntimeException is created with the provided cause.
        Exception cause = new Exception("Something went wrong!");
        Forbidden exception = new Forbidden(cause);
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause exception are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause exception.
        // THEN: A RuntimeException is created with the provided message and cause.
        Exception cause = new Exception("Another error occurred.");
        Forbidden exception = new Forbidden("Forbidden", cause);
        assertNotNull(exception);
        assertEquals("Forbidden", exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(RuntimeException.class, exception.getClass());
    }
}
