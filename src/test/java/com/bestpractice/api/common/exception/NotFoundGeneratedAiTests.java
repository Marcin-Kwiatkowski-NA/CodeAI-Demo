package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import org.assertj.core.api.Assertions;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void shouldCreateNotFoundWithNoArgs() {
        // GIVEN
        // No setup required

        // WHEN
        NotFound exception = new NotFound();

        // THEN
        assertEquals(NotFound.class, exception.getClass());
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateNotFoundWithMessage() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        NotFound exception = new NotFound(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateNotFoundWithCause() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        Assertions.assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void shouldCreateNotFoundWithMessageAndCause() {
        // GIVEN
        String message = "Entity missing";
        Throwable cause = new NullPointerException("Null value");

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenExplicitlyThrown() {
        // GIVEN
        String message = "Explicit throw test";

        // WHEN & THEN
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowNotFoundExceptionWithCauseWhenExplicitlyThrown() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Error occurred", cause);
        });

        // THEN
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
