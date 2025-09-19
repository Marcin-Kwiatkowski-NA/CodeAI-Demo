package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceUnavailableGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    public void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        // GIVEN: A cause is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the cause.
        ServiceUnavailable exception = new ServiceUnavailable(new NullPointerException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(new NullPointerException(), exception.getCause());
    }

    @Test
    public void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the message and cause.
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", new IllegalArgumentException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(new IllegalArgumentException(), exception.getCause());
    }

    @Test
    public void test_no_resetting_required() {
        // GIVEN: No preconditions are set.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    public void test_null_cause() {
        // GIVEN: A message is provided to the constructor, and the cause is null.
        // WHEN: The constructor is called with the message and null cause.
        // THEN: A RuntimeException is created with the message and null cause.
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", null);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertNull(exception.getCause());
    }
}
