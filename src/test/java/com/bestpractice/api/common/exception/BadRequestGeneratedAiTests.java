package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        assertNull(badRequest.getMessage(), "Expected message to be null");
    }

    @Test
    void givenMessageArgument_whenBadRequestIsCreated_thenExceptionMessageMatches() {
        // GIVEN
        String message = "Bad request occurred";

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertEquals(message, badRequest.getMessage(), "Expected message to match the provided argument");
    }

    @Test
    void givenCauseArgument_whenBadRequestIsCreated_thenCauseMatches() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        badRequest = new BadRequest(cause);

        // THEN
        assertEquals(cause, badRequest.getCause(), "Expected cause to match the provided argument");
    }

    @Test
    void givenMessageAndCauseArguments_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN
        String message = "Bad request occurred";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        badRequest = new BadRequest(message, cause);

        // THEN
        assertEquals(message, badRequest.getMessage(), "Expected message to match the provided argument");
        assertEquals(cause, badRequest.getCause(), "Expected cause to match the provided argument");
    }

    @Test
    void givenNullMessage_whenBadRequestIsCreated_thenExceptionMessageIsNull() {
        // GIVEN
        String message = null;

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertNull(badRequest.getMessage(), "Expected message to be null");
    }

    @Test
    void givenNullCause_whenBadRequestIsCreated_thenCauseIsNull() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        badRequest = new BadRequest(cause);

        // THEN
        assertNull(badRequest.getCause(), "Expected cause to be null");
    }

    @Test
    void givenNullMessageAndCause_whenBadRequestIsCreated_thenMessageAndCauseAreNull() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        badRequest = new BadRequest(message, cause);

        // THEN
        assertNull(badRequest.getMessage(), "Expected message to be null");
        assertNull(badRequest.getCause(), "Expected cause to be null");
    }

    @Test
    void givenEmptyMessage_whenBadRequestIsCreated_thenExceptionMessageIsEmpty() {
        // GIVEN
        String message = "";

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertEquals(message, badRequest.getMessage(), "Expected message to be empty");
    }

    @Test
    void givenWhitespaceMessage_whenBadRequestIsCreated_thenExceptionMessageMatches() {
        // GIVEN
        String message = "   ";

        // WHEN
        badRequest = new BadRequest(message);

        // THEN
        assertEquals(message, badRequest.getMessage(), "Expected message to match whitespace");
    }
}
