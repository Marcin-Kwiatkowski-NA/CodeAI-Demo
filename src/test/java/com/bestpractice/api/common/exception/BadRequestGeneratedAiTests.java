package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    private BadRequest badRequest;

    @BeforeEach
    void setUp() {
        badRequest = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenBadRequestIsCreated_thenExceptionMessageIsNull() {
        // GIVEN
        // No arguments provided

        // WHEN
        badRequest = new BadRequest();

        // THEN
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenMessageArgument_whenBadRequestIsCreated_thenExceptionMessageMatches() {
        // GIVEN
        String message = "Bad request occurred";

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertEquals(message, badRequest.getMessage());
    }

    @Test
    void givenCauseArgument_whenBadRequestIsCreated_thenCauseMatches() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        badRequest = new BadRequest(cause);

        // THEN
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN
        String message = "Bad request occurred";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        badRequest = new BadRequest(message, cause);

        // THEN
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenNullMessage_whenBadRequestIsCreated_thenExceptionMessageIsNull() {
        // GIVEN
        String message = null;

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenNullCause_whenBadRequestIsCreated_thenCauseIsNull() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        badRequest = new BadRequest(cause);

        // THEN
        assertNull(badRequest.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenBadRequestIsCreated_thenMessageAndCauseAreNull() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        badRequest = new BadRequest(message, cause);

        // THEN
        assertNull(badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void givenEmptyMessage_whenBadRequestIsCreated_thenExceptionMessageIsEmpty() {
        // GIVEN
        String message = "";

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertEquals("", badRequest.getMessage());
    }

    @Test
    void givenWhitespaceMessage_whenBadRequestIsCreated_thenExceptionMessageMatches() {
        // GIVEN
        String message = "   ";

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertEquals("   ", badRequest.getMessage());
    }

    @Test
    void givenValidCause_whenBadRequestIsCreated_thenNoExceptionIsThrown() {
        // GIVEN
        Throwable validCause = new IllegalArgumentException("Valid argument");

        // WHEN
        badRequest = new BadRequest(validCause);

        // THEN
        assertEquals(validCause, badRequest.getCause());
    }

    @Test
    void givenMessageAndNullCause_whenBadRequestIsCreated_thenMessageMatchesAndCauseIsNull() {
        // GIVEN
        String message = "Bad request occurred";
        Throwable cause = null;

        // WHEN
        badRequest = new BadRequest(message, cause);

        // THEN
        assertEquals(message, badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void givenNullMessageAndValidCause_whenBadRequestIsCreated_thenMessageIsNullAndCauseMatches() {
        // GIVEN
        String message = null;
        Throwable cause = new IllegalArgumentException("Valid argument");

        // WHEN
        badRequest = new BadRequest(message, cause);

        // THEN
        assertNull(badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenMessageAndCause_whenBadRequestIsCreated_thenExceptionIsNotThrown() {
        // GIVEN
        String message = "Bad request occurred";
        Throwable cause = new IllegalArgumentException("Valid argument");

        // WHEN
        badRequest = new BadRequest(message, cause);

        // THEN
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }
}
