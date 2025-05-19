package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class ConflictGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception class
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created without a message.
        Conflict conflict = new Conflict();
        assertNotNull(conflict);
        assertInstanceOf(RuntimeException.class, conflict);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        Conflict conflict = new Conflict("Conflict occurred");
        assertNotNull(conflict);
        assertInstanceOf(RuntimeException.class, conflict);
        assertEquals("Conflict occurred", conflict.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A Throwable cause is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the provided cause.
        Conflict conflict = new Conflict(new NullPointerException());
        assertNotNull(conflict);
        assertInstanceOf(RuntimeException.class, conflict);
        assertSame(NullPointerException.class, conflict.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        Conflict conflict = new Conflict("Error", new IllegalArgumentException());
        assertNotNull(conflict);
        assertInstanceOf(RuntimeException.class, conflict);
        assertEquals("Error", conflict.getMessage());
        assertSame(IllegalArgumentException.class, conflict.getCause());
    }
}

class MyExtension {}
