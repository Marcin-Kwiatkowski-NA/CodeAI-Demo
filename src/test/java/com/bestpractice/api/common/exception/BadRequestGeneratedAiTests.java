package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any shared state before each test
    }

    @Test
    void shouldCreateBadRequestWithNoArgsConstructor() {
        // GIVEN: No specific setup required

        // WHEN: Creating BadRequest using no-args constructor
        BadRequest exception = new BadRequest();

        // THEN: Verify exception is created and has no message or cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithMessage() {
        // GIVEN: A message for the exception
        String message = "Invalid input provided";

        // WHEN: Creating BadRequest with message
        BadRequest exception = new BadRequest(message);

        // THEN: Verify message is set correctly
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

        // THEN: Verify cause is set correctly
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void shouldCreateBadRequestWithMessageAndCause() {
        // GIVEN: A message and a cause for the exception
        String message = "Request failed";
        Throwable cause = new NullPointerException("Null value");

        // WHEN: Creating BadRequest with message and cause
        BadRequest exception = new BadRequest(message, cause);

        // THEN: Verify both message and cause are set correctly
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowBadRequestWhenExplicitlyThrown() {
        // GIVEN: A message for the exception
        String message = "Explicit throw test";

        // WHEN & THEN: Verify that throwing BadRequest triggers the expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        // THEN: Verify the thrown exception has the correct message
        assertNotNull(thrown);
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowBadRequestWithCauseWhenExplicitlyThrown() {
        // GIVEN: A cause for the exception
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN: Verify that throwing BadRequest with cause triggers the expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest("Error occurred", cause);
        });

        // THEN: Verify the thrown exception has the correct message and cause
        assertNotNull(thrown);
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
