package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception class
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        InternalServerError exception = new InternalServerError();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        InternalServerError exception = new InternalServerError("Something went wrong!");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Something went wrong!", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause exception is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the cause.
        InternalServerError exception = new InternalServerError(new NullPointerException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(NullPointerException.class, exception.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause exception are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the message and cause.
        InternalServerError exception = new InternalServerError("Error occurred", new IllegalArgumentException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Error occurred", exception.getMessage());
        assertSame(IllegalArgumentException.class, exception.getCause());
    }
}
