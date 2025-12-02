package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        // THEN: Exception is created successfully
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenExceptionCreatedWithMessage() {
        // GIVEN: A specific message
        String message = "Unauthorized access";

        // WHEN: Constructor is called with the message
        unAuthorized = new UnAuthorized(message);

        // THEN: Exception is created with the provided message
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
    }

    @Test
    void givenCause_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Constructor is called with the cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Exception is created with the provided cause
        assertNotNull(unAuthorized);
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Constructor is called with the message and cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Exception is created with the provided message and cause
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

        // THEN: Exception is created with a null message
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Exception is created with a null cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and null cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and null cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Exception is created with a null message and null cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenException_whenConstructorCalled_thenAssertThrowsWorksCorrectly() {
        // GIVEN: A specific message
        String message = "Unauthorized access";

        // WHEN: Constructor is called with the message
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            throw new UnAuthorized(message);
        });

        // THEN: Exception is thrown with the provided message
        assertEquals(message, exception.getMessage());
    }

    @Test
    void givenExceptionWithCause_whenConstructorCalled_thenAssertThrowsWorksCorrectly() {
        // GIVEN: A specific message and cause
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Constructor is called with the message and cause
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            throw new UnAuthorized(message, cause);
        });

        // THEN: Exception is thrown with the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
