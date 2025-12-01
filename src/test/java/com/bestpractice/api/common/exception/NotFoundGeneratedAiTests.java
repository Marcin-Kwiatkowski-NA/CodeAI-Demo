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
    void givenNoArguments_whenConstructorInvoked_thenExceptionCreated() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is invoked
        notFound = new NotFound();

        // THEN: Verify the exception is created with no message or cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenMessage_whenConstructorInvoked_thenExceptionCreatedWithMessage() {
        // GIVEN: A specific message
        String message = "Resource not found";

        // WHEN: Constructor is invoked with the message
        notFound = new NotFound(message);

        // THEN: Verify the exception is created with the provided message
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenCause_whenConstructorInvoked_thenExceptionCreatedWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Constructor is invoked with the cause
        notFound = new NotFound(cause);

        // THEN: Verify the exception is created with the provided cause
        assertNotNull(notFound);
        assertEquals(cause.toString(), notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorInvoked_thenExceptionCreatedWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Resource not found";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Constructor is invoked with the message and cause
        notFound = new NotFound(message, cause);

        // THEN: Verify the exception is created with the provided message and cause
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorInvoked_thenExceptionCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Constructor is invoked with the null message
        notFound = new NotFound(message);

        // THEN: Verify the exception is created with a null message
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullCause_whenConstructorInvoked_thenExceptionCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is invoked with the null cause
        notFound = new NotFound(cause);

        // THEN: Verify the exception is created with a null cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorInvoked_thenExceptionCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and null cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is invoked with the null message and null cause
        notFound = new NotFound(message, cause);

        // THEN: Verify the exception is created with a null message and null cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenEmptyMessage_whenConstructorInvoked_thenExceptionCreatedWithEmptyMessage() {
        // GIVEN: An empty message
        String message = "";

        // WHEN: Constructor is invoked with the empty message
        notFound = new NotFound(message);

        // THEN: Verify the exception is created with an empty message
        assertNotNull(notFound);
        assertEquals("", notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenEmptyMessageAndNullCause_whenConstructorInvoked_thenExceptionCreatedWithEmptyMessageAndNullCause() {
        // GIVEN: An empty message and a null cause
        String message = "";
        Throwable cause = null;

        // WHEN: Constructor is invoked with the empty message and null cause
        notFound = new NotFound(message, cause);

        // THEN: Verify the exception is created with an empty message and null cause
        assertNotNull(notFound);
        assertEquals("", notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenValidMessageAndCause_whenConstructorInvoked_thenExceptionCreatedCorrectly() {
        // GIVEN: A valid message and cause
        String message = "Valid message";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Constructor is invoked with the valid message and cause
        notFound = new NotFound(message, cause);

        // THEN: Verify the exception is created with the valid message and cause
        assertNotNull(notFound);
        assertEquals(message, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }
}
