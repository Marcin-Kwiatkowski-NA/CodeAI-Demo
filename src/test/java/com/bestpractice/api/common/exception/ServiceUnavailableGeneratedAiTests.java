package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state needed before each test
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A throwable object is provided to the constructor.
        // WHEN: The constructor is called with the throwable object.
        // THEN: A RuntimeException is created with the throwable object as the cause.
        Throwable cause = new RuntimeException("Underlying error");
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a throwable object are provided to the constructor.
        // WHEN: The constructor is called with the message and the throwable object.
        Throwable cause = new RuntimeException("Underlying error");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
