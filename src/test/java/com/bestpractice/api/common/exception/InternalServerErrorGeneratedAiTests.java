package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class InternalServerErrorGeneratedAiTests {

    @Test
    void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        InternalServerError exception = new InternalServerError();
        Assertions.assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        InternalServerError exception = new InternalServerError("Something went wrong!");
        Assertions.assertInstanceOf(RuntimeException.class, exception);
        Assertions.assertEquals("Something went wrong!", exception.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A Throwable cause is provided to the constructor.
        // WHEN: The constructor is called with a cause.
        // THEN: A RuntimeException is created with the provided cause.
        InternalServerError exception = new InternalServerError(new NullPointerException());
        Assertions.assertInstanceOf(RuntimeException.class, exception);
        Assertions.assertEquals(new NullPointerException(), exception.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A message and a Throwable cause are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        InternalServerError exception = new InternalServerError("Error occurred", new IllegalArgumentException("Invalid input"));
        Assertions.assertInstanceOf(RuntimeException.class, exception);
        Assertions.assertEquals("Error occurred", exception.getMessage());
        Assertions.assertEquals(new IllegalArgumentException("Invalid input"), exception.getCause());
    }
}
