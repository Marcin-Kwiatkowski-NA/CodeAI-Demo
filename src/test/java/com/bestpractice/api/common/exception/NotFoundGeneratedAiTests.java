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

class NotFoundGeneratedAiTests {

    private NotFound notFound;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        notFound = null;
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionMessageIsNull() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        notFound = new NotFound();

        // THEN: Exception message should be null
        assertNull(notFound.getMessage());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenExceptionMessageMatches() {
        // GIVEN: A specific message
        String message = "Resource not found";

        // WHEN: Constructor is called with the message
        notFound = new NotFound(message);

        // THEN: Exception message should match the provided message
        assertEquals(message, notFound.getMessage());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenCauseMatches() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Constructor is called with the cause
        notFound = new NotFound(cause);

        // THEN: Cause should match the provided cause
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenMessageAndCauseMatch() {
        // GIVEN: A specific message and cause
        String message = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Constructor is called with the message and cause
        notFound = new NotFound(message, cause);

        // THEN: Exception message and cause should match the provided values
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenMessageAndCauseAreNull() {
        // GIVEN: Null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with null message and cause
        notFound = new NotFound(message, cause);

        // THEN: Exception message and cause should be null
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenCauseIsNull() {
        // GIVEN: Null cause
        Throwable cause = null;

        // WHEN: Constructor is called with null cause
        notFound = new NotFound(cause);

        // THEN: Cause should be null
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenMessageIsNull() {
        // GIVEN: Null message
        String message = null;

        // WHEN: Constructor is called with null message
        notFound = new NotFound(message);

        // THEN: Message should be null
        assertNull(notFound.getMessage());
    }

    @Test
    void givenValidMessageAndNullCause_whenConstructorCalled_thenMessageMatchesAndCauseIsNull() {
        // GIVEN: A valid message and null cause
        String message = "Resource not found";
        Throwable cause = null;

        // WHEN: Constructor is called with valid message and null cause
        notFound = new NotFound(message, cause);

        // THEN: Message should match the provided message and cause should be null
        assertEquals(message, notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullMessageAndValidCause_whenConstructorCalled_thenMessageIsNullAndCauseMatches() {
        // GIVEN: Null message and a valid cause
        String message = null;
        Throwable cause = new IllegalArgumentException("Valid argument");

        // WHEN: Constructor is called with null message and valid cause
        notFound = new NotFound(message, cause);

        // THEN: Message should be null and cause should match the provided cause
        assertNull(notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }
}
