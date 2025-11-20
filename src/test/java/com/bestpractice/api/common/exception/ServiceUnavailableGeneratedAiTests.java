package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        serviceUnavailable = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenInstanceCreated() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        serviceUnavailable = new ServiceUnavailable();

        // THEN: Instance is created successfully
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN: A message argument
        String message = "Service is unavailable";

        // WHEN: Constructor is called with the message
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN: Instance is created with the provided message
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN: A cause argument
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Instance is created with the provided cause
        assertNotNull(serviceUnavailable);
        assertEquals(cause.toString(), serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN: A message and a cause argument
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the message and cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Instance is created with the provided message and cause
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullValues() {
        // GIVEN: Null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with null values
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Instance is created with null values
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN: A message and null cause
        String message = "Service is unavailable";
        Throwable cause = null;

        // WHEN: Constructor is called with a message and null cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Instance is created with the provided message and null cause
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN: Null message and a cause
        String message = null;
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with null message and a cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Instance is created with null message and the provided cause
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }
}
