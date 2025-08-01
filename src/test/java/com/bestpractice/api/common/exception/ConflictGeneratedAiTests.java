package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class ConflictGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        Conflict conflict = new Conflict();
        assert conflict != null;
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        Conflict conflict = new Conflict("This is a conflict message");
        assert conflict != null;
        assertEquals("This is a conflict message", conflict.getMessage());
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The constructor is called with the Throwable object.
        // THEN: A RuntimeException is created with the Throwable object as the cause.
        Conflict conflict = new Conflict(new NullPointerException());
        assert conflict != null;
        assertEquals("Caused by: java.lang.NullPointerException", conflict.getMessage());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        // WHEN: The constructor is called with the message and the Throwable object.
        // THEN: A RuntimeException is created with the message and the Throwable object as the cause.
        Conflict conflict = new Conflict("Error occurred", new IllegalArgumentException("Invalid argument"));
        assert conflict != null;
        assertEquals("Error occurred", conflict.getMessage());
        assertEquals("Caused by: java.lang.IllegalArgumentException", conflict.getCause().getMessage());
    }
}
