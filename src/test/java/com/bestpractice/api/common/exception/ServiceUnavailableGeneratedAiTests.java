package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided
        // WHEN: Creating instance using default constructor
        ServiceUnavailable exception = new ServiceUnavailable();
        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Service is unavailable";
        // WHEN: Creating instance using message constructor
        ServiceUnavailable exception = new ServiceUnavailable(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause (Throwable)
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: Creating instance using cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(cause);
        // THEN: Exception should contain the provided cause and message derived from cause
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Service temporarily down";
        Throwable cause = new IllegalStateException("Backend failure");
        // WHEN: Creating instance using message and cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableException() {
        // GIVEN: A message for the exception
        String message = "Service failure";
        // WHEN & THEN: Verify that throwing the exception behaves as expected
        assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new RuntimeException("Network issue");
        // WHEN & THEN: Verify that throwing the exception with cause behaves as expected
        assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: A message and cause for the exception
        String message = "Service unreachable";
        Throwable cause = new IllegalArgumentException("Invalid configuration");
        // WHEN & THEN: Verify that throwing the exception with message and cause behaves as expected
        assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
    }
}
