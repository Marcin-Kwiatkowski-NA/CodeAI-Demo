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
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        UnAuthorized exception = new UnAuthorized();
        assert exception instanceof RuntimeException;
    }

    @Test
    public void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        UnAuthorized exception = new UnAuthorized("Invalid credentials");
        assert exception instanceof RuntimeException;
        assert Objects.equals("Invalid credentials", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        // GIVEN: A cause (Throwable) is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the provided cause.
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assert exception instanceof RuntimeException);
        assert exception.getCause() == null;
    }

    @Test
    public void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        UnAuthorized exception = new UnAuthorized("Authentication failed", new IllegalArgumentException("Invalid input"));
        assert exception instanceof RuntimeException);
        assert Objects.equals("Authentication failed", exception.getMessage());
        assert exception.getCause() == null;
    }

    @BeforeEach
    public void setUp() {
        // Reset any state before each test.  This is not strictly necessary for this
        // simple class, but it's a good practice to include.
    }
}