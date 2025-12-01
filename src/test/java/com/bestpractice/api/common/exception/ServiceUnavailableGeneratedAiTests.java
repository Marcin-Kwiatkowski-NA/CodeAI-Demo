package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        serviceUnavailable = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionCreated() {
        // GIVEN: No arguments provided

        // WHEN: ServiceUnavailable is instantiated
        serviceUnavailable = new ServiceUnavailable();

        // THEN: Verify the exception is created and message is null
        assertNotNull(serviceUnavailable);
        assertEquals(null, serviceUnavailable.getMessage());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenExceptionCreatedWithMessage() {
        // GIVEN: A message string
        String message = "Service is unavailable";

        // WHEN: ServiceUnavailable is instantiated with the message
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN: Verify the exception is created with the correct message
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
    }

    @Test
    void givenCause_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN: A cause (Throwable)
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: ServiceUnavailable is instantiated with the cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Verify the exception is created with the correct cause
        assertNotNull(serviceUnavailable);
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A message string and a cause
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: ServiceUnavailable is instantiated with the message and cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify the exception is created with the correct message and cause
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: ServiceUnavailable is instantiated with the null message
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN: Verify the exception is created and message is null
        assertNotNull(serviceUnavailable);
        assertEquals(null, serviceUnavailable.getMessage());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: ServiceUnavailable is instantiated with the null cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Verify the exception is created and cause is null
        assertNotNull(serviceUnavailable);
        assertEquals(null, serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and a null cause
        String message = null;
        Throwable cause = null;

        // WHEN: ServiceUnavailable is instantiated with the null message and cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify the exception is created with null message and cause
        assertNotNull(serviceUnavailable);
        assertEquals(null, serviceUnavailable.getMessage());
        assertEquals(null, serviceUnavailable.getCause());
    }
}
