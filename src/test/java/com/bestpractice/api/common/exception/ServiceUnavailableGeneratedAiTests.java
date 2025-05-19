package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state needed before each test.  No specific reset needed for this class.
    }

    @Test
    void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A cause (Throwable) is provided to the constructor.
        // WHEN: The constructor is called with a cause.
        // THEN: A RuntimeException is created with the provided cause.
        Throwable cause = new NullPointerException("Something went wrong");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with a message and a cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        Throwable cause = new NullPointerException("Another error");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
        assertEquals("Service unavailable", exception.getMessage());
    }
}
