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

class ForbiddenGeneratedAiTests {

    private Forbidden forbidden;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        forbidden = null;
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenInstanceCreated() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        forbidden = new Forbidden();

        // THEN: Instance is created successfully
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN: A message argument
        String message = "Access Denied";

        // WHEN: Constructor is called with the message
        forbidden = new Forbidden(message);

        // THEN: Instance is created with the provided message
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN: A cause argument
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the cause
        forbidden = new Forbidden(cause);

        // THEN: Instance is created with the provided cause
        assertNotNull(forbidden);
        assertEquals(cause.toString(), forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN: A message and a cause argument
        String message = "Access Denied";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the message and cause
        forbidden = new Forbidden(message, cause);

        // THEN: Instance is created with the provided message and cause
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Constructor is called with the null message
        forbidden = new Forbidden(message);

        // THEN: Instance is created with a null message
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        forbidden = new Forbidden(cause);

        // THEN: Instance is created with a null cause
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullMessageAndNullCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and a null cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and null cause
        forbidden = new Forbidden(message, cause);

        // THEN: Instance is created with a null message and null cause
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenValidMessageAndCause_whenConstructorCalled_thenNoExceptionThrown() {
        // GIVEN: A valid message and cause
        String message = "Access Denied";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the valid message and cause
        forbidden = new Forbidden(message, cause);

        // THEN: No exception is thrown, and instance is created successfully
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }
}
