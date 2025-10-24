package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

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
        // GIVEN: no parameters

        // WHEN: creating the exception using the default constructor
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN: message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Service is unavailable";

        // WHEN: creating the exception with a message
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: creating the exception with a cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN: cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: creating the exception with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a specific message
        String message = "Service is unavailable";

        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });

        // THEN: verify message and cause
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });

        // THEN: verify cause and message
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN: throwing the exception should be caught by assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });

        // THEN: verify message and cause
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
