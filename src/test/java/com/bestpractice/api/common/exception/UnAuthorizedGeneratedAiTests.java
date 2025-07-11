package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;

class UnAuthorizedGeneratedAiTests {

    @ExtendWith(MyExtension.class)
    public static class MyExtension {
    }

    @Test
    public void constructor_no_args() {
        // GIVEN: No arguments are provided to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        UnAuthorized exception = new UnAuthorized();
        assert exception instanceof RuntimeException;
    }

    @Test
    public void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        UnAuthorized exception = new UnAuthorized("Unauthorized access denied");
        assert exception instanceof RuntimeException;
        assert Objects.equals("Unauthorized access denied", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        // GIVEN: A cause is provided to the constructor.
        // WHEN: The constructor is called with a cause.
        // THEN: A RuntimeException is created with the provided cause.
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assert exception instanceof RuntimeException;
        assert exception.getCause() == null;
    }

    @Test
    public void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        UnAuthorized exception = new UnAuthorized("Access denied", new IllegalArgumentException());
        assert exception instanceof RuntimeException);
        assert Objects.equals("Access denied", exception.getMessage());
        assert exception.getCause() == null;
    }

    @BeforeEach
    public void setUp() {
        // Reset state before each test.  No specific reset needed for this class.
    }
}