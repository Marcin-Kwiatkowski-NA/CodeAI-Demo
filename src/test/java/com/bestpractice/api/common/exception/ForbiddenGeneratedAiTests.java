package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyCustomExtension.class)
class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A Forbidden exception is created with no message.
        Forbidden exception = new Forbidden();
        assertNotNull(exception);
        assertEquals(Forbidden.class, exception.getClass());
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A Forbidden exception is created with the provided message.
        Forbidden exception = new Forbidden("Access denied");
        assertNotNull(exception);
        assertEquals("Access denied", exception.getMessage());
        assertEquals(Forbidden.class, exception.getClass());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause exception is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A Forbidden exception is created with the provided message and cause.
        String causeMessage = "Invalid credentials";
        Throwable cause = new Throwable(causeMessage);
        Forbidden exception = new Forbidden(cause);
        assertNotNull(exception);
        assertEquals(causeMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(Forbidden.class, exception.getClass());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause exception are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A Forbidden exception is created with the provided message and cause.
        String causeMessage = "Authentication failed";
        Throwable cause = new Throwable(causeMessage);
        Forbidden exception = new Forbidden(causeMessage, cause);
        assertNotNull(exception);
        assertEquals(causeMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        assertEquals(Forbidden.class, exception.getClass());
    }
}

class MyCustomExtension implements Extension {}
