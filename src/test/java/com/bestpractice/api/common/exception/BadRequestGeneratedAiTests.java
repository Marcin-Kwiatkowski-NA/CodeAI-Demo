package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BadRequestGeneratedAiTests {

    private BadRequest badRequest;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        badRequest = null;
    }

    @Test
    void givenNoArguments_whenBadRequestIsCreated_thenExceptionMessageIsNull() {
        // GIVEN: No arguments provided

        // WHEN: BadRequest is instantiated
        badRequest = new BadRequest();

        // THEN: Exception message should be null
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenMessageArgument_whenBadRequestIsCreated_thenExceptionMessageMatches() {
        // GIVEN: A specific message
        String message = "Invalid request";

        // WHEN: BadRequest is instantiated with the message
        badRequest = new BadRequest(message);

        // THEN: Exception message should match the provided message
        assertEquals(message, badRequest.getMessage());
    }

    @Test
    void givenCauseArgument_whenBadRequestIsCreated_thenCauseMatches() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: BadRequest is instantiated with the cause
        badRequest = new BadRequest(cause);

        // THEN: Cause should match the provided cause
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN: A specific message and cause
        String message = "Invalid request";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: BadRequest is instantiated with the message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message and cause should match the provided values
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenBadRequestIsCreated_thenMessageAndCauseAreNull() {
        // GIVEN: Null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: BadRequest is instantiated with null message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message and cause should be null
        assertNull(badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void givenNullCause_whenBadRequestIsCreated_thenCauseIsNull() {
        // GIVEN: Null cause
        Throwable cause = null;

        // WHEN: BadRequest is instantiated with null cause
        badRequest = new BadRequest(cause);

        // THEN: Cause should be null
        assertNull(badRequest.getCause());
    }

    @Test
    void givenNullMessage_whenBadRequestIsCreated_thenMessageIsNull() {
        // GIVEN: Null message
        String message = null;

        // WHEN: BadRequest is instantiated with null message
        badRequest = new BadRequest(message);

        // THEN: Message should be null
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenValidMessageAndNullCause_whenBadRequestIsCreated_thenMessageMatchesAndCauseIsNull() {
        // GIVEN: A valid message and null cause
        String message = "Valid message";
        Throwable cause = null;

        // WHEN: BadRequest is instantiated with valid message and null cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message should match the provided message and cause should be null
        assertEquals(message, badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void givenNullMessageAndValidCause_whenBadRequestIsCreated_thenMessageIsNullAndCauseMatches() {
        // GIVEN: Null message and a valid cause
        String message = null;
        Throwable cause = new IllegalArgumentException("Valid cause");

        // WHEN: BadRequest is instantiated with null message and valid cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message should be null and cause should match the provided cause
        assertNull(badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenEmptyMessage_whenBadRequestIsCreated_thenMessageIsEmpty() {
        // GIVEN: An empty message
        String message = "";

        // WHEN: BadRequest is instantiated with an empty message
        badRequest = new BadRequest(message);

        // THEN: Exception message should be empty
        assertEquals("", badRequest.getMessage());
    }
}
