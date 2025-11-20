package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        serviceUnavailable = null;
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenInstanceCreated() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        serviceUnavailable = new ServiceUnavailable();

        // THEN: Verify instance is created
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

        // THEN: Verify instance is created with the message
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

        // THEN: Verify instance is created with the cause
        assertNotNull(serviceUnavailable);
        assertEquals(cause.toString(), serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN: A message and cause argument
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the message and cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify instance is created with the message and cause
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

        // THEN: Verify instance is created with a null message
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN: Verify instance is created with a null cause
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and null cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and null cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify instance is created with null message and cause
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenEmptyMessage_whenConstructorCalled_thenInstanceCreatedWithEmptyMessage() {
        // GIVEN: An empty message
        String message = "";

        // WHEN: Constructor is called with the empty message
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN: Verify instance is created with an empty message
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenValidMessageAndCause_whenConstructorCalled_thenInstanceCreatedSuccessfully() {
        // GIVEN: A valid message and cause
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with valid message and cause
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN: Verify instance is created successfully
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }
}
