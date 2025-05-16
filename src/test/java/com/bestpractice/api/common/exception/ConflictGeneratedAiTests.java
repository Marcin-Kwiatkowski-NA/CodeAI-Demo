package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConflictGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception class
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The Conflict constructor is called without arguments.
        // THEN: A Conflict exception is created with no message.
        Conflict conflict = new Conflict();
        assertNotNull(conflict);
        assertEquals(Conflict.class, conflict.getClass());
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The Conflict constructor is called with a message.
        // THEN: A Conflict exception is created with the provided message.
        Conflict conflict = new Conflict("Conflict occurred");
        assertNotNull(conflict);
        assertEquals("Conflict occurred", conflict.getMessage());
        assertEquals(Conflict.class, conflict.getClass());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A throwable cause is provided to the constructor.
        // WHEN: The Conflict constructor is called with a cause.
        // THEN: A Conflict exception is created with the provided cause.
        Conflict conflict = new Conflict(new RuntimeException("Underlying error"));
        assertNotNull(conflict);
        assertEquals("Underlying error", conflict.getMessage());
        assertEquals(Conflict.class, conflict.getClass());
    }

    @Test
    void constructor_withMsgAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The Conflict constructor is called with a message and a cause.
        // THEN: A Conflict exception is created with the provided message and cause.
        Conflict conflict = new Conflict("Conflict occurred", new RuntimeException("Underlying error"));
        assertNotNull(conflict);
        assertEquals("Underlying error", conflict.getMessage());
        assertEquals("Conflict occurred", conflict.getCause().getMessage());
        assertEquals(Conflict.class, conflict.getClass());
    }
}
