package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

public class ForbiddenGeneratedAiTests {

    private Forbidden exception;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        exception = new Forbidden();
    }

    @org.junit.jupiter.api.Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception is initialized with no message and the cause is null.
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception is initialized with the provided message and the cause is null.
        String message = "Access denied";
        exception = new Forbidden(message);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception is initialized with the provided message and the cause.
        Throwable cause = new RuntimeException("Something went wrong");
        exception = new Forbidden(cause);
        assertEquals(cause.getMessage(), exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Invalid credentials";
        Throwable cause = new RuntimeException("Authentication failed");
        exception = new Forbidden(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
