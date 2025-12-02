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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NotFoundGeneratedAiTests {

    private NotFound notFound;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        notFound = null;
    }

    @Test
    void givenNoArgsConstructor_whenInstantiated_thenObjectIsCreated() {
        // GIVEN: No preconditions

        // WHEN: Instantiating NotFound using the no-args constructor
        notFound = new NotFound();

        // THEN: Verify the object is created and has no message or cause
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenMessageConstructor_whenInstantiated_thenMessageIsSet() {
        // GIVEN: A specific error message
        String errorMessage = "Resource not found";

        // WHEN: Instantiating NotFound with the message constructor
        notFound = new NotFound(errorMessage);

        // THEN: Verify the message is set correctly
        assertNotNull(notFound);
        assertEquals(errorMessage, notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenCauseConstructor_whenInstantiated_thenCauseIsSet() {
        // GIVEN: A specific cause (Throwable)
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Instantiating NotFound with the cause constructor
        notFound = new NotFound(cause);

        // THEN: Verify the cause is set correctly
        assertNotNull(notFound);
        assertEquals(cause.toString(), notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenMessageAndCauseConstructor_whenInstantiated_thenMessageAndCauseAreSet() {
        // GIVEN: A specific error message and cause
        String errorMessage = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Instantiating NotFound with the message and cause constructor
        notFound = new NotFound(errorMessage, cause);

        // THEN: Verify the message and cause are set correctly
        assertNotNull(notFound);
        assertEquals(errorMessage, notFound.getMessage());
        assertEquals(cause, notFound.getCause());
    }

    @Test
    void givenNullMessageConstructor_whenInstantiated_thenMessageIsNull() {
        // GIVEN: A null message
        String errorMessage = null;

        // WHEN: Instantiating NotFound with a null message
        notFound = new NotFound(errorMessage);

        // THEN: Verify the message is null
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullCauseConstructor_whenInstantiated_thenCauseIsNull() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Instantiating NotFound with a null cause
        notFound = new NotFound(cause);

        // THEN: Verify the cause is null
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenNullMessageAndCauseConstructor_whenInstantiated_thenMessageAndCauseAreNull() {
        // GIVEN: A null message and null cause
        String errorMessage = null;
        Throwable cause = null;

        // WHEN: Instantiating NotFound with a null message and null cause
        notFound = new NotFound(errorMessage, cause);

        // THEN: Verify the message and cause are null
        assertNotNull(notFound);
        assertNull(notFound.getMessage());
        assertNull(notFound.getCause());
    }

    @Test
    void givenExceptionThrown_whenChecked_thenExceptionIsHandledCorrectly() {
        // GIVEN: A specific error message
        String errorMessage = "Resource not found";

        // WHEN: Instantiating NotFound and throwing it
        RuntimeException thrownException = assertThrows(NotFound.class, () -> {
            throw new NotFound(errorMessage);
        });

        // THEN: Verify the exception message
        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Test
    void givenExceptionWithCauseThrown_whenChecked_thenExceptionAndCauseAreHandledCorrectly() {
        // GIVEN: A specific error message and cause
        String errorMessage = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Instantiating NotFound and throwing it
        RuntimeException thrownException = assertThrows(NotFound.class, () -> {
            throw new NotFound(errorMessage, cause);
        });

        // THEN: Verify the exception message and cause
        assertEquals(errorMessage, thrownException.getMessage());
        assertEquals(cause, thrownException.getCause());
    }
}
