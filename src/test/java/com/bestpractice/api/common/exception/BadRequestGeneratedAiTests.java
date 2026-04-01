package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void shouldCreateBadRequestWithNoArgsConstructor() {
        // GIVEN: No specific setup required

        // WHEN: Creating BadRequest using no-args constructor
        BadRequest exception = new BadRequest();

        // THEN: Exception should be created with null message and cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid input provided";

        // WHEN: Creating BadRequest with message constructor
        BadRequest exception = new BadRequest(message);

        // THEN: Exception should contain the provided message
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating BadRequest with cause constructor
        BadRequest exception = new BadRequest(cause);

        // THEN: Exception should contain the provided cause
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Bad request occurred";
        Throwable cause = new NullPointerException("Null value");

        // WHEN: Creating BadRequest with message and cause constructor
        BadRequest exception = new BadRequest(message, cause);

        // THEN: Exception should contain both message and cause
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowBadRequestWhenExplicitlyThrown() {
        // GIVEN: A message for the exception
        String message = "Explicit bad request";

        // WHEN & THEN: Expect BadRequest to be thrown
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        // THEN: Verify the thrown exception message
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowBadRequestWithCauseWhenExplicitlyThrown() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN: Expect BadRequest to be thrown with cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });

        // THEN: Verify the thrown exception cause
        assertEquals(cause, thrown.getCause());
    }
}
