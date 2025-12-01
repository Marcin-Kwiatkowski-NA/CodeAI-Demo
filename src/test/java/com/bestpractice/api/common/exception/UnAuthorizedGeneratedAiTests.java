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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UnAuthorizedGeneratedAiTests {

    private UnAuthorized unAuthorized;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        unAuthorized = null;
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionCreated() {
        // GIVEN: No arguments provided

        // WHEN: UnAuthorized exception is instantiated
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

        // WHEN: UnAuthorized exception is instantiated with the message
        unAuthorized = new UnAuthorized(message);

        // THEN: Verify the exception contains the provided message
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenCause_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: UnAuthorized exception is instantiated with the cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Verify the exception contains the provided cause
        assertNotNull(unAuthorized);
        assertEquals(cause.toString(), unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: UnAuthorized exception is instantiated with the message and cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Verify the exception contains the provided message and cause
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: UnAuthorized exception is instantiated with the null message
        unAuthorized = new UnAuthorized(message);

        // THEN: Verify the exception contains a null message
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: UnAuthorized exception is instantiated with the null cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Verify the exception contains a null cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and null cause
        String message = null;
        Throwable cause = null;

        // WHEN: UnAuthorized exception is instantiated with the null message and null cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Verify the exception contains a null message and null cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }
}
