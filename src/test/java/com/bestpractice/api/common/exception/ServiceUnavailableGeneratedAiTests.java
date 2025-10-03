package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceUnavailableGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        ServiceUnavailable exception = new ServiceUnavailable();
        // WHEN: The constructor is called without arguments.
        // THEN: A ServiceUnavailable exception is created with a null message.
        assertNotNull(exception);
        assertNull(exception.getMessage());
    }

    @Test
    public void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        // WHEN: The constructor is called with a message.
        // THEN: A ServiceUnavailable exception is created with the provided message.
        assertNotNull(exception);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        // GIVEN: A cause exception is provided to the constructor.
        Exception cause = new Exception("Underlying problem");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // WHEN: The constructor is called with a cause exception.
        // THEN: A ServiceUnavailable exception is created with the provided cause.
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause exception are provided to the constructor.
        Exception cause = new Exception("Root cause");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        // WHEN: The constructor is called with a message and a cause.
        // THEN: A ServiceUnavailable exception is created with the provided message and cause.
        assertNotNull(exception);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @BeforeEach
    public void setUp() {
        // Reset state before each test.  This is a placeholder, no specific reset is needed
        // for this class.
    }
}
