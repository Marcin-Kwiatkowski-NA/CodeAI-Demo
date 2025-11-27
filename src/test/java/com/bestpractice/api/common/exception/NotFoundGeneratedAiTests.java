package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotFoundGeneratedAiTests {

    private NotFound notFound;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        notFound = null;
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionCreated() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        notFound = new NotFound();

        // THEN: Exception is created successfully
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenExceptionCreatedWithMessage() {
        // GIVEN: A message argument
        String message = "Resource not found";

        // WHEN: Constructor is called with the message
        notFound = new NotFound(message);

        // THEN: Exception is created with the provided message
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN: A cause argument
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Constructor is called with the cause
        notFound = new NotFound(cause);

        // THEN: Exception is created with the provided cause
        assertNotNull(notFound);
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A message and a cause argument
        String message = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Constructor is called with the message and cause
        notFound = new NotFound(message, cause);

        // THEN: Exception is created with the provided message and cause
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Constructor is called with the null message
        notFound = new NotFound(message);

        // THEN: Exception is created with a null message
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        notFound = new NotFound(cause);

        // THEN: Exception is created with a null cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and a null cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and cause
        notFound = new NotFound(message, cause);

        // THEN: Exception is created with a null message and cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }
}
