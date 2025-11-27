package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        serviceUnavailable = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionCreated() {
        // GIVEN
        // No arguments provided

        // WHEN
        serviceUnavailable = new ServiceUnavailable();

        // THEN
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenExceptionCreatedWithMessage() {
        // GIVEN
        String message = "Service is unavailable";

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenCause_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals(cause.toString(), serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessageAndNullCause_whenConstructorCalled_thenExceptionCreatedWithNullValues() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionCreatedWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionCreatedWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
    }

    @Test
    void givenInvalidArguments_whenConstructorCalled_thenExceptionHandled() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN & THEN
        ServiceUnavailable exception = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }
}
