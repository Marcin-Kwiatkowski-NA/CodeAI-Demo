package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.runners.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnit4.class)
public class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        serviceUnavailable = new ServiceUnavailable();
    }

    @Test
    void testConstructorWithoutArguments() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A ServiceUnavailable object is created with no message.
        assertNotNull(serviceUnavailable);
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A message is passed to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A ServiceUnavailable object is created with the provided message.
        String message = "Service Unavailable";
        serviceUnavailable = new ServiceUnavailable(message);
        assertEquals(message, serviceUnavailable.getMessage());
    }

    @Test
    void testConstructorWithThrowable() {
        // GIVEN: A throwable object is passed to the constructor.
        Throwable cause = new NullPointerException("Test Cause");
        serviceUnavailable = new ServiceUnavailable(cause);
        assertSame(cause, serviceUnavailable.getCause());
    }

    @Test
    void testConstructorWithMessageAndThrowable() {
        // GIVEN: A message and a throwable object are passed to the constructor.
        String msg = "Service Unavailable";
        Throwable cause = new NullPointerException("Test Cause");
        serviceUnavailable = new ServiceUnavailable(msg, cause);
        assertEquals(msg, serviceUnavailable.getMessage());
        assertSame(cause, serviceUnavailable.getCause());
    }
}
