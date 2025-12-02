package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        serviceUnavailable = null;
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionCreated() {
        // GIVEN: No arguments provided

        // WHEN: ServiceUnavailable is instantiated
        serviceUnavailable = new ServiceUnavailable();

        // THEN: Verify the exception is created with no message or cause
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenExceptionCreatedWithMessage() {
        // GIVEN: A specific message
        String message = "Service is unavailable";

        // WHEN: ServiceUnavailable is instantiated with the message
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN: Verify the exception is created with the correct message
        assertEquals(message, serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenCause_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: ServiceUnavailable is instantiated with the cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Verify the exception is created with the correct cause
        assertEquals(cause.toString(), serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: ServiceUnavailable is instantiated with the message and cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify the exception is created with the correct message and cause
        assertEquals(message, serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenException_whenThrown_thenCorrectExceptionIsThrown() {
        // GIVEN: A specific message
        String message = "Service is unavailable";

        // WHEN & THEN: Verify the exception is thrown with the correct message
        ServiceUnavailable thrownException = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrownException.getMessage());
    }

    @Test
    void givenCause_whenThrown_thenCorrectExceptionIsThrownWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN: Verify the exception is thrown with the correct cause
        ServiceUnavailable thrownException = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause.toString(), thrownException.getMessage());
        assertEquals(cause, thrownException.getCause());
    }

    @Test
    void givenMessageAndCause_whenThrown_thenCorrectExceptionIsThrownWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN: Verify the exception is thrown with the correct message and cause
        ServiceUnavailable thrownException = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrownException.getMessage());
        assertEquals(cause, thrownException.getCause());
    }
}
