package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assertNotNull(exception);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A throwable object is provided to the constructor.
        // WHEN: The constructor is called with a throwable object.
        // THEN: A RuntimeException is created with the provided throwable object.
        ServiceUnavailable exception = new ServiceUnavailable(new NullPointerException());
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a throwable object are provided to the constructor.
        // WHEN: The constructor is called with a message and a throwable object.
        // THEN: A RuntimeException is created with the provided message and throwable object.
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", new IllegalArgumentException());
        assertNotNull(exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
    }
}
