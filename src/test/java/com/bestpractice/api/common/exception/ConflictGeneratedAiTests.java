package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConflictGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state if needed for subsequent tests.  Not needed for this class.
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is thrown with no message.
        Conflict conflict = new Conflict();
        assertNotNull(conflict);
        assertEquals(RuntimeException.class, conflict.getClass());
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is thrown with the provided message.
        Conflict conflict = new Conflict("Conflict occurred");
        assertNotNull(conflict);
        assertEquals("Conflict occurred", conflict.getMessage());
        assertEquals(RuntimeException.class, conflict.getClass());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A Throwable cause is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is thrown with the cause.
        Exception cause = new Exception("Some underlying problem");
        Conflict conflict = new Conflict(cause);
        assertNotNull(conflict);
        assertEquals(cause, conflict.getCause());
        assertEquals(RuntimeException.class, conflict.getClass());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is thrown with the message and cause.
        Exception cause = new Exception("Another underlying problem");
        Conflict conflict = new Conflict("Conflict with cause", cause);
        assertNotNull(conflict);
        assertEquals("Conflict with cause", conflict.getMessage());
        assertEquals(cause, conflict.getCause());
        assertEquals(RuntimeException.class, conflict.getClass());
    }
}
