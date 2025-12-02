package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        Throwable cause = new IllegalArgumentException("Cause of error");

        // WHEN: BadRequest is instantiated with the cause
        badRequest = new BadRequest(cause);

        // THEN: Cause should match the provided cause
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN: A specific message and cause
        String message = "Invalid request";
        Throwable cause = new IllegalArgumentException("Cause of error");

        // WHEN: BadRequest is instantiated with the message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message and cause should match the provided values
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenNullMessage_whenBadRequestIsCreated_thenExceptionMessageIsNull() {
        // GIVEN: A null message
        String message = null;

        // WHEN: BadRequest is instantiated with the null message
        badRequest = new BadRequest(message);

        // THEN: Exception message should be null
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenNullCause_whenBadRequestIsCreated_thenCauseIsNull() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: BadRequest is instantiated with the null cause
        badRequest = new BadRequest(cause);

        // THEN: Cause should be null
        assertNull(badRequest.getCause());
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
    void givenEmptyMessage_whenBadRequestIsCreated_thenExceptionMessageIsEmpty() {
        // GIVEN: An empty message
        String message = "";

        // WHEN: BadRequest is instantiated with the empty message
        badRequest = new BadRequest(message);

        // THEN: Exception message should be empty
        assertEquals(message, badRequest.getMessage());
    }

    @Test
    void givenEmptyMessageAndValidCause_whenBadRequestIsCreated_thenMessageIsEmptyAndCauseMatches() {
        // GIVEN: An empty message and a valid cause
        String message = "";
        Throwable cause = new IllegalArgumentException("Valid cause");

        // WHEN: BadRequest is instantiated with the empty message and valid cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message should be empty and cause should match
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenValidMessageAndNullCause_whenBadRequestIsCreated_thenMessageMatchesAndCauseIsNull() {
        // GIVEN: A valid message and null cause
        String message = "Valid message";
        Throwable cause = null;

        // WHEN: BadRequest is instantiated with the valid message and null cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message should match and cause should be null
        assertEquals(message, badRequest.getMessage());
        assertNull(badRequest.getCause());
    }
}
