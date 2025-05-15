package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
public class ForbiddenGeneratedAiTests {

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
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        Forbidden exception = new Forbidden("Access denied");
        assertNotNull(exception);
        assertEquals("Access denied", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause exception is provided to the constructor.
        // WHEN: The constructor is called with a cause exception.
        // THEN: A RuntimeException is created with the provided message and cause.
        Exception cause = new Exception("Something went wrong");
        Forbidden exception = new Forbidden(cause);
        assertNotNull(exception);
        assertEquals("Something went wrong", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndCause() {
        // GIVEN: A message and a cause exception are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause exception.
        // THEN: A RuntimeException is created with the provided message and cause.
        Exception cause = new Exception("Detailed error");
        Forbidden exception = new Forbidden("Failed operation", cause);
        assertNotNull(exception);
        assertEquals("Failed operation", exception.getMessage());
        assertEquals("Detailed error", exception.getCause().getMessage());
        assertSame(exception.getCause(), cause);
    }
}
