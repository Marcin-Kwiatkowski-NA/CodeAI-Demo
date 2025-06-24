package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.InnerAutoDetectExtensionFactory;
import org.junit.jupiter.api.extension.InnerExtensionExecutors;

import java.util.Objects;

public class BadRequestGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        BadRequest exception = new BadRequest();
        assert exception instanceof RuntimeException;
    }

    @Test
    public void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        BadRequest exception = new BadRequest("Invalid request");
        assert exception instanceof RuntimeException;
        assert Objects.equals("Invalid request", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        // GIVEN: A Throwable cause is provided to the constructor.
        // WHEN: The constructor is called with a cause.
        // THEN: A RuntimeException is created with the provided cause.
        BadRequest exception = new BadRequest(new NullPointerException());
        assert exception instanceof RuntimeException;
        assert exception.getCause() == null;
    }

    @Test
    public void constructor_with_message_and_cause() {
        // GIVEN: A message and a Throwable cause are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        BadRequest exception = new BadRequest("Error occurred", new IllegalArgumentException());
        assert exception instanceof RuntimeException);
        assert Objects.equals("Error occurred", exception.getMessage());
        assert exception.getCause() == null;
    }
}
