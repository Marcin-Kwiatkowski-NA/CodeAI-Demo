package com.bestpractice.api.common.exception;

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
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(badRequest.getMessage()).isNull();
    }

    @Test
    void givenMessageArgument_whenBadRequestIsCreated_thenExceptionMessageMatches() {
        // GIVEN: A specific message
        String message = "Bad request occurred";

        // WHEN: BadRequest is instantiated with the message
        badRequest = new BadRequest(message);

        // THEN: Exception message should match the provided message
        assertThat(badRequest.getMessage()).isEqualTo(message);
    }

    @Test
    void givenCauseArgument_whenBadRequestIsCreated_thenCauseMatches() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: BadRequest is instantiated with the cause
        badRequest = new BadRequest(cause);

        // THEN: Cause should match the provided cause
        assertThat(badRequest.getCause()).isEqualTo(cause);
    }

    @Test
    void givenMessageAndCauseArguments_whenBadRequestIsCreated_thenMessageAndCauseMatch() {
        // GIVEN: A specific message and cause
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: BadRequest is instantiated with the message and cause
        badRequest = new BadRequest(message, cause);

        // THEN: Exception message and cause should match the provided values
        assertThat(badRequest.getMessage()).isEqualTo(message);
        assertThat(badRequest.getCause()).isEqualTo(cause);
    }
}
