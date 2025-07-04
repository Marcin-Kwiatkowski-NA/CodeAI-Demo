package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state if needed for subsequent tests.  No specific reset is needed for this class.
    }

    @Test
    void constructor_no_args() {
        // GIVEN: No arguments are provided to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        UnAuthorized exception = new UnAuthorized();
        assert exception instanceof RuntimeException;
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        UnAuthorized exception = new UnAuthorized("Invalid credentials");
        assert exception instanceof RuntimeException;
        assert exception.getMessage().equals("Invalid credentials");
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A cause is provided to the constructor.
        // WHEN: The constructor is called with a cause.
        // THEN: A RuntimeException is created with the provided cause.
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assert exception instanceof RuntimeException;
        assert exception.getCause() == null;
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        UnAuthorized exception = new UnAuthorized("Authentication failed", new IllegalArgumentException("Invalid parameter"));
        assert exception instanceof RuntimeException;
        assert exception.getMessage().equals("Authentication failed");
        assert exception.getCause() == null;
    }
}
