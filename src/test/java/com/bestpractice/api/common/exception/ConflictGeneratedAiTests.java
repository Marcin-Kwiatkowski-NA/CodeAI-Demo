package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConflictGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The Conflict constructor is called.
        // THEN: A Conflict exception is created with no message.
        Conflict conflict = new Conflict();
        assertNotNull(conflict);
        assertInstanceOf(Conflict.class, conflict);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The Conflict constructor is called with a message.
        // THEN: A Conflict exception is created with the provided message.
        Conflict conflict = new Conflict("This is a conflict message.");
        assertNotNull(conflict);
        assertInstanceOf(Conflict.class, conflict);
        assertEquals("This is a conflict message.", conflict.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A Throwable cause is provided to the constructor.
        // WHEN: The Conflict constructor is called with a cause.
        // THEN: A Conflict exception is created with the provided cause.
        Conflict conflict = new Conflict("This is a conflict message.", new NullPointerException());
        assertNotNull(conflict);
        assertInstanceOf(Conflict.class, conflict);
        assertEquals("This is a conflict message.", conflict.getMessage());
        assertEquals(new NullPointerException(), conflict.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The Conflict constructor is called with a message and a cause.
        // THEN: A Conflict exception is created with the provided message and cause.
        Conflict conflict = new Conflict("This is a conflict message.", new IllegalArgumentException());
        assertNotNull(conflict);
        assertInstanceOf(Conflict.class, conflict);
        assertEquals("This is a conflict message.", conflict.getMessage());
        assertEquals(new IllegalArgumentException(), conflict.getCause());
    }
}
