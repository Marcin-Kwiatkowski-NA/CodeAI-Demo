package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ServiceUnavailableGeneratedAiTests.class})
class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertEquals("Service Unavailable", exception.getMessage());
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assertNotNull(exception);
        assertEquals("Service Unavailable", exception.getMessage());
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The constructor is called with a Throwable object.
        // THEN: A RuntimeException is created with the Throwable object as the cause.
        Throwable cause = new NullPointerException("Null pointer exception");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        // WHEN: The constructor is called with a message and a Throwable object.
        // THEN: A RuntimeException is created with the message and the Throwable object as the cause.
        Throwable cause = new NullPointerException("Null pointer exception");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
