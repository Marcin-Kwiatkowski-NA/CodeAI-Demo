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
import static org.junit.jupiter.api.Assertions.assertSame;

class ForbiddenGeneratedAiTests {

    private Forbidden forbidden;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        forbidden = null;
    }

    @Test
    void givenNoArgsConstructor_whenInstantiated_thenObjectIsCreated() {
        // GIVEN: No preconditions

        // WHEN: Instantiating Forbidden using the no-args constructor
        forbidden = new Forbidden();

        // THEN: The object should be created successfully
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
    }

    @Test
    void givenMessageConstructor_whenInstantiated_thenMessageIsSet() {
        // GIVEN: A specific error message
        String errorMessage = "Access is forbidden";

        // WHEN: Instantiating Forbidden with a message
        forbidden = new Forbidden(errorMessage);

        // THEN: The object should be created with the correct message
        assertNotNull(forbidden);
        assertEquals(errorMessage, forbidden.getMessage());
    }

    @Test
    void givenCauseConstructor_whenInstantiated_thenCauseIsSet() {
        // GIVEN: A specific cause (exception)
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Instantiating Forbidden with a cause
        forbidden = new Forbidden(cause);

        // THEN: The object should be created with the correct cause
        assertNotNull(forbidden);
        assertSame(cause, forbidden.getCause());
    }

    @Test
    void givenMessageAndCauseConstructor_whenInstantiated_thenMessageAndCauseAreSet() {
        // GIVEN: A specific error message and cause
        String errorMessage = "Access is forbidden";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Instantiating Forbidden with a message and cause
        forbidden = new Forbidden(errorMessage, cause);

        // THEN: The object should be created with the correct message and cause
        assertNotNull(forbidden);
        assertEquals(errorMessage, forbidden.getMessage());
        assertSame(cause, forbidden.getCause());
    }

    @Test
    void givenNullMessageConstructor_whenInstantiated_thenMessageIsNull() {
        // GIVEN: A null message
        String errorMessage = null;

        // WHEN: Instantiating Forbidden with a null message
        forbidden = new Forbidden(errorMessage);

        // THEN: The object should be created with a null message
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
    }

    @Test
    void givenNullCauseConstructor_whenInstantiated_thenCauseIsNull() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Instantiating Forbidden with a null cause
        forbidden = new Forbidden(cause);

        // THEN: The object should be created with a null cause
        assertNotNull(forbidden);
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullMessageAndCauseConstructor_whenInstantiated_thenMessageAndCauseAreNull() {
        // GIVEN: A null message and null cause
        String errorMessage = null;
        Throwable cause = null;

        // WHEN: Instantiating Forbidden with a null message and null cause
        forbidden = new Forbidden(errorMessage, cause);

        // THEN: The object should be created with null message and null cause
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenEmptyMessageConstructor_whenInstantiated_thenMessageIsEmpty() {
        // GIVEN: An empty message
        String errorMessage = "";

        // WHEN: Instantiating Forbidden with an empty message
        forbidden = new Forbidden(errorMessage);

        // THEN: The object should be created with an empty message
        assertNotNull(forbidden);
        assertEquals("", forbidden.getMessage());
    }
}
