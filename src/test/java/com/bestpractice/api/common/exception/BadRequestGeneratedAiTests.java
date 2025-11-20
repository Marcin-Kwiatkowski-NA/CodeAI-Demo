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

        // WHEN: Creating a BadRequest instance
        badRequest = new BadRequest();

        // THEN: The exception message should be null
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenMessageArgument_whenBadRequestIsCreated_thenExceptionMessageMatches() {
        // GIVEN: A specific message
        String message = "Invalid request";

        // WHEN: Creating a BadRequest instance with the message
        badRequest = new BadRequest(message);

        // THEN: The exception message should match the provided message
        assertEquals(message, badRequest.getMessage());
    }

    @Test
    void givenCauseArgument_whenBadRequestIsCreated_thenCauseMatches() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Cause of error");

        // WHEN: Creating a BadRequest instance with the cause
        badRequest = new BadRequest(cause);

        // THEN: The cause should match the provided cause
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN: A specific message and cause
        String message = "Invalid request";
        Throwable cause = new IllegalArgumentException("Cause of error");

        // WHEN: Creating a BadRequest instance with the message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: The exception message and cause should match the provided values
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenNullMessage_whenBadRequestIsCreated_thenExceptionMessageIsNull() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Creating a BadRequest instance with the null message
        badRequest = new BadRequest(message);

        // THEN: The exception message should be null
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenNullCause_whenBadRequestIsCreated_thenCauseIsNull() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Creating a BadRequest instance with the null cause
        badRequest = new BadRequest(cause);

        // THEN: The cause should be null
        assertNull(badRequest.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenBadRequestIsCreated_thenMessageAndCauseAreNull() {
        // GIVEN: A null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: Creating a BadRequest instance with the null message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: The exception message and cause should be null
        assertNull(badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void givenEmptyMessage_whenBadRequestIsCreated_thenExceptionMessageIsEmpty() {
        // GIVEN: An empty message
        String message = "";

        // WHEN: Creating a BadRequest instance with the empty message
        badRequest = new BadRequest(message);

        // THEN: The exception message should be empty
        assertEquals("", badRequest.getMessage());
    }

    @Test
    void givenValidMessageAndNullCause_whenBadRequestIsCreated_thenMessageMatchesAndCauseIsNull() {
        // GIVEN: A valid message and a null cause
        String message = "Valid message";
        Throwable cause = null;

        // WHEN: Creating a BadRequest instance with the valid message and null cause
        badRequest = new BadRequest(message, cause);

        // THEN: The exception message should match the provided message and the cause should be null
        assertEquals(message, badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void givenValidCauseAndNullMessage_whenBadRequestIsCreated_thenCauseMatchesAndMessageIsNull() {
        // GIVEN: A valid cause and a null message
        Throwable cause = new IllegalArgumentException("Valid cause");
        String message = null;

        // WHEN: Creating a BadRequest instance with the valid cause and null message
        badRequest = new BadRequest(message, cause);

        // THEN: The cause should match the provided cause and the message should be null
        assertEquals(cause, badRequest.getCause());
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenValidMessageAndCause_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN: A valid message and a valid cause
        String message = "Valid message";
        Throwable cause = new IllegalArgumentException("Valid cause");

        // WHEN: Creating a BadRequest instance with the valid message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: The exception message and cause should match the provided values
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenNullMessageAndInvalidCause_whenBadRequestIsCreated_thenMessageIsNullAndCauseMatches() {
        // GIVEN: A null message and an invalid cause
        String message = null;
        Throwable cause = new RuntimeException("Invalid cause");

        // WHEN: Creating a BadRequest instance with the null message and invalid cause
        badRequest = new BadRequest(message, cause);

        // THEN: The exception message should be null and the cause should match the provided cause
        assertNull(badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenEmptyMessageAndValidCause_whenBadRequestIsCreated_thenMessageIsEmptyAndCauseMatches() {
        // GIVEN: An empty message and a valid cause
        String message = "";
        Throwable cause = new IllegalArgumentException("Valid cause");

        // WHEN: Creating a BadRequest instance with the empty message and valid cause
        badRequest = new BadRequest(message, cause);

        // THEN: The exception message should be empty and the cause should match the provided cause
        assertEquals("", badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }
}
