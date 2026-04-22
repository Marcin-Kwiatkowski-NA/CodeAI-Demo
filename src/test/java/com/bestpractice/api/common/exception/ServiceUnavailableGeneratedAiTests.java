package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No parameters provided

        // WHEN: Creating the exception using the default constructor
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN: The message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Service is temporarily unavailable";

        // WHEN: Creating the exception with a message
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: The message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A cause exception
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Creating the exception with a cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN: The cause should match and message should contain cause details
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Service failed";
        Throwable cause = new IllegalStateException("Dependency error");

        // WHEN: Creating the exception with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: Both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableException() {
        // GIVEN: A message for the exception
        String message = "Service unavailable error";

        // WHEN & THEN: Verify that throwing the exception works as expected
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });

        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: Verify that throwing the exception with a cause works as expected
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });

        assertEquals(cause, thrown.getCause());
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Critical service failure";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN: Verify that throwing the exception with both message and cause works as expected
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });

        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
