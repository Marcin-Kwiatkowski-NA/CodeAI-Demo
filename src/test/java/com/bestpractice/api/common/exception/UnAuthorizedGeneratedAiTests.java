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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnAuthorizedGeneratedAiTests {

    private UnAuthorized unAuthorized;

    @BeforeEach
    void setUp() {
        unAuthorized = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionCreated() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        unAuthorized = new UnAuthorized();

        // THEN: Verify the exception is created with no message or cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenExceptionCreatedWithMessage() {
        // GIVEN: A specific message
        String message = "Unauthorized access";

        // WHEN: Constructor is called with the message
        unAuthorized = new UnAuthorized(message);

        // THEN: Verify the exception is created with the provided message
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenCause_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Constructor is called with the cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Verify the exception is created with the provided cause
        assertNotNull(unAuthorized);
        assertEquals(cause.toString(), unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Constructor is called with the message and cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Verify the exception is created with the provided message and cause
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Constructor is called with the null message
        unAuthorized = new UnAuthorized(message);

        // THEN: Verify the exception is created with a null message
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Verify the exception is created with a null cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Verify the exception is created with a null message and cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenEmptyMessage_whenConstructorCalled_thenExceptionCreatedWithEmptyMessage() {
        // GIVEN: An empty message
        String message = "";

        // WHEN: Constructor is called with the empty message
        unAuthorized = new UnAuthorized(message);

        // THEN: Verify the exception is created with the empty message
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }
}
