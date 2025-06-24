package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.InnerAutoDetectExtensionFactory;
import org.junit.jupiter.api.extension.InnerExtensionExecuter;

import java.util.Arrays;
import java.util.List;

public class ServiceUnavailableGeneratedAiTests {

    @ExtendWith(InnerAutoDetectExtensionFactory.class)
    public static class ServiceUnavailableTest extends ServiceUnavailable {

        @Test
        public void constructor_noArgs() {
            // GIVEN: No arguments are provided to the constructor.
            // WHEN: The constructor is called without arguments.
            // THEN: A RuntimeException is created with no message.
            ServiceUnavailable exception = new ServiceUnavailable();
            assert exception instanceof RuntimeException;
        }

        @Test
        public void constructor_withMessage() {
            // GIVEN: A message is provided to the constructor.
            // WHEN: The constructor is called with a message.
            // THEN: A RuntimeException is created with the provided message.
            ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
            assert exception instanceof RuntimeException;
            String msg = exception.getMessage();
            assert msg.equals("Service is temporarily unavailable");
        }

        @Test
        public void constructor_withCause() {
            // GIVEN: A cause is provided to the constructor.
            // WHEN: The constructor is called with a cause.
            // THEN: A RuntimeException is created with the provided cause.
            ServiceUnavailable exception = new ServiceUnavailable(new NullPointerException());
            assert exception instanceof RuntimeException;
            Throwable cause = exception.getCause();
            assert cause instanceof NullPointerException;
        }

        @Test
        public void constructor_withMessageAndCause() {
            // GIVEN: A message and a cause are provided to the constructor.
            // WHEN: The constructor is called with a message and a cause.
            // THEN: A RuntimeException is created with the provided message and cause.
            ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", new IllegalArgumentException());
            assert exception instanceof RuntimeException;
            String msg = exception.getMessage();
            Throwable cause = exception.getCause();
            assert msg.equals("Service unavailable");
            assert cause instanceof IllegalArgumentException;
        }
    }
}
