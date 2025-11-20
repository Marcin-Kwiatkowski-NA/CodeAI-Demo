package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
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

        // THEN: Verify exception is created
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenExceptionCreatedWithMessage() {
        // GIVEN: A message is provided
        String message = "Resource not found";

        // WHEN: Constructor is called with the message
        notFound = new NotFound(message);

        // THEN: Verify exception is created with the message
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenCause_whenConstructorCalled_thenExceptionCreatedWithCause() {
        // GIVEN: A cause is provided
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the cause
        notFound = new NotFound(cause);

        // THEN: Verify exception is created with the cause
        assertNotNull(notFound);
        assertEquals(cause.toString(), notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A message and a cause are provided
        String message = "Resource not found";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN: Constructor is called with the message and cause
        notFound = new NotFound(message, cause);

        // THEN: Verify exception is created with the message and cause
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionCreatedWithNullMessage() {
        // GIVEN: A null message is provided
        String message = null;

        // WHEN: Constructor is called with the null message
        notFound = new NotFound(message);

        // THEN: Verify exception is created with a null message
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenExceptionCreatedWithNullCause() {
        // GIVEN: A null cause is provided
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        notFound = new NotFound(cause);

        // THEN: Verify exception is created with a null cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenExceptionCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and a null cause are provided
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and null cause
        notFound = new NotFound(message, cause);

        // THEN: Verify exception is created with a null message and null cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenEmptyMessage_whenConstructorCalled_thenExceptionCreatedWithEmptyMessage() {
        // GIVEN: An empty message is provided
        String message = "";

        // WHEN: Constructor is called with the empty message
        notFound = new NotFound(message);

        // THEN: Verify exception is created with an empty message
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenWhitespaceMessage_whenConstructorCalled_thenExceptionCreatedWithWhitespaceMessage() {
        // GIVEN: A whitespace message is provided
        String message = "   ";

        // WHEN: Constructor is called with the whitespace message
        notFound = new NotFound(message);

        // THEN: Verify exception is created with the whitespace message
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertNull(notFound.getCause());
    }
}
