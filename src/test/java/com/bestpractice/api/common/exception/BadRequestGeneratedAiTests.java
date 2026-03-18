package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.assertj.core.api.Assertions.assertThat;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void shouldCreateBadRequestWithNoArgsConstructor() {
        // GIVEN
        // No preconditions required

        // WHEN
        BadRequest exception = new BadRequest();

        // THEN
        assertThat(exception).isInstanceOf(BadRequest.class);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithMessage() {
        // GIVEN
        String message = "Invalid request";

        // WHEN
        BadRequest exception = new BadRequest(message);

        // THEN
        assertThat(exception).isInstanceOf(BadRequest.class);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithCause() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Cause message");

        // WHEN
        BadRequest exception = new BadRequest(cause);

        // THEN
        assertThat(exception).isInstanceOf(BadRequest.class);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Cause message");
    }

    @Test
    void shouldCreateBadRequestWithMessageAndCause() {
        // GIVEN
        String message = "Bad request occurred";
        Throwable cause = new NullPointerException("Null pointer");

        // WHEN
        BadRequest exception = new BadRequest(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(BadRequest.class);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowBadRequestWhenExplicitlyThrown() {
        // GIVEN
        String message = "Explicit bad request";

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertThat(thrown).isInstanceOf(BadRequest.class);
    }

    @Test
    void shouldThrowBadRequestWithCauseWhenExplicitlyThrown() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest("Bad request with cause", cause);
        });

        // THEN
        assertEquals("Bad request with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
