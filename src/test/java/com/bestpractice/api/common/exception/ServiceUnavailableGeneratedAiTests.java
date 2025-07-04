package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class ServiceUnavailableGeneratedAiTests {

    public static class ServiceUnavailableTest extends ServiceUnavailable {

        @Test
        public void constructor_no_args() {
            // GIVEN: No preconditions set.
            // WHEN: The constructor is called without arguments.
            // THEN: A ServiceUnavailable exception is thrown with no message.
            ServiceUnavailable exception = new ServiceUnavailable();
            assert exception instanceof ServiceUnavailable;
        }

        @Test
        public void constructor_with_message() {
            // GIVEN: A message is provided.
            // WHEN: The constructor is called with a message.
            // THEN: A ServiceUnavailable exception is thrown with the provided message.
            String message = "Service is temporarily unavailable.";
            ServiceUnavailable exception = new ServiceUnavailable(message);
            assert exception instanceof ServiceUnavailable;
            assertEquals(message, exception.getMessage());
        }

        @Test
        public void constructor_with_cause() {
            // GIVEN: A cause exception is provided.
            // WHEN: The constructor is called with a cause exception.
            Throwable cause = new RuntimeException("Underlying problem");
            ServiceUnavailable exception = new ServiceUnavailable(cause);
            assert exception instanceof ServiceUnavailable;
            assertEquals(cause, exception.getCause());
        }

        @Test
        public void constructor_with_message_and_cause() {
            // GIVEN: A message and a cause exception are provided.
            String message = "Service unavailable due to error.";
            Throwable cause = new RuntimeException("Detailed error message");
            // WHEN: The constructor is called with a message and a cause.
            ServiceUnavailable exception = new ServiceUnavailable(message, cause);
            assert exception instanceof ServiceUnavailable;
            assertEquals(message, exception.getMessage());
            assertEquals(cause, exception.getCause());
        }
    }
}
