package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

class ForbiddenGeneratedAiTests {

    private Forbidden forbidden;

    @BeforeEach
    void setUp() {
        forbidden = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenForbiddenIsCreated_thenInstanceIsNotNull() {
        // GIVEN
        // No arguments provided

        // WHEN
        forbidden = new Forbidden();

        // THEN
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenMessageArgument_whenForbiddenIsCreated_thenMessageIsSetCorrectly() {
        // GIVEN
        String message = "Access Denied";

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenCauseArgument_whenForbiddenIsCreated_thenCauseIsSetCorrectly() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        forbidden = new Forbidden(cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals(cause.toString(), forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenForbiddenIsCreated_thenMessageAndCauseAreSetCorrectly() {
        // GIVEN
        String message = "Access Denied";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenForbiddenIsCreated_thenInstanceHandlesNullValues() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenEmptyMessage_whenForbiddenIsCreated_thenMessageIsEmpty() {
        // GIVEN
        String message = "";

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenWhitespaceMessage_whenForbiddenIsCreated_thenMessageIsWhitespace() {
        // GIVEN
        String message = "   ";

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullCause_whenForbiddenIsCreated_thenInstanceHandlesNullCause() {
        // GIVEN
        String message = "Access Denied";
        Throwable cause = null;

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullMessage_whenForbiddenIsCreated_thenInstanceHandlesNullMessage() {
        // GIVEN
        String message = null;
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }
}
