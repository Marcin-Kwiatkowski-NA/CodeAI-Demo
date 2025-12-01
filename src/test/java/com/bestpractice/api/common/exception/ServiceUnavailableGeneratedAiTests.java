package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

        // THEN: Verify the instance is created and message is null
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN: A message argument
        String message = "Service is unavailable";

        // WHEN: Constructor is called with the message
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN: Verify the instance is created with the correct message
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN: A cause argument
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Verify the instance is created with the correct cause
        assertNotNull(serviceUnavailable);
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN: A message and a cause argument
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the message and cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify the instance is created with the correct message and cause
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Constructor is called with the null message
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN: Verify the instance is created with a null message
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Verify the instance is created with a null cause
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and a null cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and null cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify the instance is created with a null message and null cause
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }
}
