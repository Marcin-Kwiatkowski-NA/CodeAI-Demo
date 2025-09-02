package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.runners.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

package com.bestpractice.api.common.exception;

public class ForbiddenGeneratedAiTests {

    private Forbidden forbidden;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        forbidden = new Forbidden();
    }

    @org.junit.jupiter.api.Test
    void constructor_no_args() {
        // GIVEN: A new Forbidden exception object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The exception object is initialized with no arguments.
        assertNotNull(forbidden);
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message() {
        // GIVEN: A new Forbidden exception object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The exception object is initialized with the provided message.
        String message = "Access denied";
        forbidden = new Forbidden(message);
        assertEquals(message, forbidden.getMessage());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_cause() {
        // GIVEN: A new Forbidden exception object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The exception object is initialized with the provided cause.
        String causeMessage = "Underlying problem";
        Exception cause = new Exception("Something went wrong");
        forbidden = new Forbidden(cause);
        assertEquals(cause, forbidden.getCause());
    }

    @org.junit.jupiter.api.Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new Forbidden exception object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Invalid credentials";
        Exception cause = new Exception("Authentication failed");
        forbidden = new Forbidden(message, cause);
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }
}
