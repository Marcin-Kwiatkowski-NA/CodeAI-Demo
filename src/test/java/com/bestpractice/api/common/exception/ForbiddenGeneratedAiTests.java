package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

class ForbiddenGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        Forbidden exception = new Forbidden();
        assert exception instanceof RuntimeException;
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        Forbidden exception = new Forbidden("Access denied");
        assert exception instanceof RuntimeException;
        assert exception.getMessage() == "Access denied";
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause (Throwable) is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the provided cause.
        Exception cause = new Exception("Underlying error");
        Forbidden exception = new Forbidden(cause);
        assert exception instanceof RuntimeException;
        assert exception.getCause() == cause;
    }

    @Test
    void constructor_withMsgAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        Exception cause = new Exception("Detailed error");
        Forbidden exception = new Forbidden("Access denied", cause);
        assert exception instanceof RuntimeException;
        assert exception.getMessage() == "Access denied";
        assert exception.getCause() == cause;
    }
}
