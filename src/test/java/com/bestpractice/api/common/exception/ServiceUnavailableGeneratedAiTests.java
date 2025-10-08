package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new ServiceUnavailable instance
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: the exception should be created with no message and no cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Service is down";
        // WHEN: creating a new ServiceUnavailable instance with the message
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: the exception should contain the provided message and no cause
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause exception
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with the cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: the exception should contain the cause and the message should match cause.toString()
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific error message and cause
        String message = "Service is down";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating a new ServiceUnavailable instance with the message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: the exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific error message
        String message = "Service temporarily unavailable";
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause exception
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific error message and cause
        String message = "Service error";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
