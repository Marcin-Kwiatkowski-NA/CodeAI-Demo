package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ServiceUnavailableGeneratedAiTests")
public class ServiceUnavailableGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A runtime exception is thrown with no message.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    public void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A runtime exception is thrown with the provided message.
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        // GIVEN: A cause is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A runtime exception is thrown with the cause.
        ServiceUnavailable exception = new ServiceUnavailable(new NullPointerException());
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals(new NullPointerException(), exception.getCause());
    }

    @Test
    public void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A runtime exception is thrown with the message and cause.
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", new IllegalArgumentException());
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("Service unavailable", exception.getMessage());
        assertEquals(new IllegalArgumentException(), exception.getCause());
    }

    @Test
    public void test_no_mocks_needed() {
        // GIVEN: No external dependencies or mocks are required.
        // WHEN: The ServiceUnavailable exception is created.
        // THEN: The exception is thrown and its properties are verified.
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }
}
