package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable exception;

    @BeforeEach
    public void setUp() {
        // Reset before each test
        exception = null;
    }

    @Test
    public void testServiceUnavailableDefaultConstructor() {
        // GIVEN: No preconditions needed

        // WHEN: Creating an instance using the default constructor
        exception = new ServiceUnavailable();

        // THEN: The instance should not be null
        assertNotNull(exception);
    }

    @Test
    public void testServiceUnavailableStringConstructor() {
        // GIVEN: A message string
        String msg = "Service is unavailable";

        // WHEN: Creating an instance using the constructor with a message
        exception = new ServiceUnavailable(msg);

        // THEN: The instance should not be null and contain the correct message
        assertNotNull(exception);
        assertEquals(msg, exception.getMessage());
    }

    @Test
    public void testServiceUnavailableThrowableConstructor() {
        // GIVEN: A throwable cause
        Throwable cause = new Exception("Cause");

        // WHEN: Creating an instance using the constructor with a throwable cause
        exception = new ServiceUnavailable(cause);

        // THEN: The instance should not be null and contain the correct cause
        assertNotNull(exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    public void testServiceUnavailableStringThrowableConstructor() {
        // GIVEN: A message string and a throwable cause
        String msg = "Service is unavailable";
        Throwable cause = new Exception("Cause");

        // WHEN: Creating an instance using the constructor with both message and cause
        exception = new ServiceUnavailable(msg, cause);

        // THEN: The instance should not be null, contain the correct message, and have the correct cause
        assertNotNull(exception);
        assertEquals(msg, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
