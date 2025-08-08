package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        NotFound exception = new NotFound();
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        NotFound exception = new NotFound("Resource not found");
        assertNotNull(exception);
        assertEquals("Resource not found", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A Throwable cause is provided to the constructor.
        // WHEN: The constructor is called with a cause.
        // THEN: A RuntimeException is created with the cause.
        NotFound exception = new NotFound(new NullPointerException());
        assertNotNull(exception);
        assertEquals(NullPointerException.class, exception.getCause().getClass());
    }

    @Test
    void constructor_withMsgAndCause() {
        // GIVEN: A message and a Throwable cause are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause.
        // THEN: A RuntimeException is created with the message and the cause.
        NotFound exception = new NotFound("Resource not found", new IllegalArgumentException());
        assertNotNull(exception);
        assertEquals("Resource not found", exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
    }
}
