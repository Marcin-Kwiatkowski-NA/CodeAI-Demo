package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConflictGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        Conflict conflict = new Conflict();
        assertNotNull(conflict);
        assertEquals(RuntimeException.class, conflict.getClass());
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the provided message.
        // THEN: A RuntimeException is created with the provided message.
        Conflict conflict = new Conflict("This is a conflict message.");
        assertNotNull(conflict);
        assertEquals("This is a conflict message.", conflict.getMessage());
        assertEquals(RuntimeException.class, conflict.getClass());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause exception is provided to the constructor.
        // WHEN: The constructor is called with the cause exception.
        // THEN: A RuntimeException is created with the provided cause exception.
        Conflict conflict = new Conflict(new NullPointerException());
        assertNotNull(conflict);
        assertEquals(NullPointerException.class, conflict.getCause().getClass());
    }

    @Test
    void constructor_withMsgAndCause() {
        // GIVEN: A message and a cause exception are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        Conflict conflict = new Conflict("Error occurred", new IllegalArgumentException());
        assertNotNull(conflict);
        assertEquals("Error occurred", conflict.getMessage());
        assertEquals(IllegalArgumentException.class, conflict.getCause().getClass());
    }
}
