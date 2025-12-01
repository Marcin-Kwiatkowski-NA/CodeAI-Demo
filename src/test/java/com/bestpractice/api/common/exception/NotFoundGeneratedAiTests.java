package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotFoundGeneratedAiTests {

    private NotFound notFound;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        notFound = null;
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionMessageIsNull() {
        // GIVEN
        // No arguments provided

        // WHEN
        notFound = new NotFound();

        // THEN
        assertEquals(null, notFound.getMessage());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenExceptionMessageMatches() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        notFound = new NotFound(message);

        // THEN
        assertEquals(message, notFound.getMessage());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenCauseMatches() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(cause);

        // THEN
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenMessageAndCauseMatch() {
        // GIVEN
        String message = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenExceptionHandlesNullValues() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals(null, notFound.getMessage());
        assertEquals(null, notFound.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionHandlesNullCause() {
        // GIVEN
        String message = "Resource not found";
        Throwable cause = null;

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals(message, notFound.getMessage());
        assertEquals(null, notFound.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionHandlesNullMessage() {
        // GIVEN
        String message = null;
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals(null, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenThrowable_whenConstructorCalled_thenExceptionCanBeThrown() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN
        assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
    }

    @Test
    void givenMessageAndThrowable_whenConstructorCalled_thenExceptionCanBeThrown() {
        // GIVEN
        String message = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN
        assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
    }

    @Test
    void givenEmptyMessage_whenConstructorCalled_thenExceptionHandlesEmptyMessage() {
        // GIVEN
        String message = "";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals("", notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenEmptyMessageAndNullCause_whenConstructorCalled_thenExceptionHandlesEmptyMessageAndNullCause() {
        // GIVEN
        String message = "";
        Throwable cause = null;

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals("", notFound.getMessage());
        assertEquals(null, notFound.getCause());
    }

    @Test
    void givenNonNullMessageAndNonNullCause_whenConstructorCalled_thenExceptionHandlesBothValues() {
        // GIVEN
        String message = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }
}
