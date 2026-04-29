package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
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
        // GIVEN: A message for the exception
        String message = "Invalid request data";

        // WHEN: Creating BadRequest with message
        BadRequest exception = new BadRequest(message);

        // THEN: Exception should contain the provided message
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating BadRequest with cause
        BadRequest exception = new BadRequest(cause);

        // THEN: Exception should contain the provided cause
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithMessageAndCause() {
        // GIVEN: A message and a cause for the exception
        String message = "Request failed";
        Throwable cause = new NullPointerException("Null value");

        // WHEN: Creating BadRequest with message and cause
        BadRequest exception = new BadRequest(message, cause);

        // THEN: Exception should contain both message and cause
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowBadRequestWhenExplicitlyThrown() {
        // GIVEN: A message for the exception
        String message = "Bad request occurred";

        // WHEN & THEN: Throwing BadRequest should result in expected exception
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

        // WHEN & THEN: Throwing BadRequest with cause should result in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });

        // THEN: Verify the thrown exception cause
        assertEquals(cause, thrown.getCause());
    }
}
