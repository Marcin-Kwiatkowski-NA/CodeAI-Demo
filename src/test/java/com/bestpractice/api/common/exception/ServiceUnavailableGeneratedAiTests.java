package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable serviceUnavailable;

    @BeforeEach
    void setUp() {
        serviceUnavailable = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenInstanceCreated() {
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
    void givenMessageArgument_whenConstructorCalled_thenInstanceCreatedWithMessage() {
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
    void givenCauseArgument_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals(cause.toString(), serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN
        String message = "Service is unavailable";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullValues() {
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
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        serviceUnavailable = new ServiceUnavailable(cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
    }

    @Test
    void givenEmptyMessage_whenConstructorCalled_thenInstanceCreatedWithEmptyMessage() {
        // GIVEN
        String message = "";

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals("", serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }

    @Test
    void givenEmptyMessageAndValidCause_whenConstructorCalled_thenInstanceCreatedWithEmptyMessageAndCause() {
        // GIVEN
        String message = "";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals("", serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenNullMessageAndValidCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN
        String message = null;
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertNull(serviceUnavailable.getMessage());
        assertEquals(cause, serviceUnavailable.getCause());
    }

    @Test
    void givenValidMessageAndNullCause_whenConstructorCalled_thenInstanceCreatedWithMessageAndNullCause() {
        // GIVEN
        String message = "Service is unavailable";
        Throwable cause = null;

        // WHEN
        serviceUnavailable = new ServiceUnavailable(message, cause);

        // THEN
        assertNotNull(serviceUnavailable);
        assertEquals(message, serviceUnavailable.getMessage());
        assertNull(serviceUnavailable.getCause());
    }
}
