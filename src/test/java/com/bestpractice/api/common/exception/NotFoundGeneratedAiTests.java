package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A runtime exception is thrown with no message.
        NotFound exception = new NotFound();
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A runtime exception is thrown with the provided message.
        NotFound exception = new NotFound("Resource not found");
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("Resource not found", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A runtime exception is thrown with the cause.
        Exception cause = new Exception("Underlying error");
        NotFound exception = new NotFound(cause);
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A runtime exception is thrown with the message and cause.
        Exception cause = new Exception("Another underlying error");
        NotFound exception = new NotFound("Resource not found", cause);
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertSame(cause, exception.getCause());
        assertEquals("Resource not found", exception.getMessage());
    }
}
