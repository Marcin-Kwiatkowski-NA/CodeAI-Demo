package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating a BadRequest instance with the cause
        badRequest = new BadRequest(cause);

        // THEN: The cause should match the provided cause
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN: A specific message and cause
        String message = "Invalid request";
        Throwable cause = new IllegalArgumentException("Invalid argument");

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

        // WHEN: Creating a BadRequest instance with a null message
        badRequest = new BadRequest(message);

        // THEN: The exception message should be null
        assertNull(badRequest.getMessage());
    }

    @Test
    void givenNullCause_whenBadRequestIsCreated_thenCauseIsNull() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Creating a BadRequest instance with a null cause
        badRequest = new BadRequest(cause);

        // THEN: The cause should be null
        assertNull(badRequest.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenBadRequestIsCreated_thenMessageAndCauseAreNull() {
        // GIVEN: A null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: Creating a BadRequest instance with a null message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: The exception message and cause should be null
        assertNull(badRequest.getMessage());
        assertNull(badRequest.getCause());
    }
}
