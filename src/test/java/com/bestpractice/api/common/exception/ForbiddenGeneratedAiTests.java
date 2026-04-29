package com.bestpractice.api.common.exception;

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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void shouldCreateForbiddenWithNoArgs() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertEquals(Forbidden.class, exception.getClass());
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateForbiddenWithMessage() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertEquals(Forbidden.class, exception.getClass());
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateForbiddenWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertEquals(Forbidden.class, exception.getClass());
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void shouldCreateForbiddenWithMessageAndCause() {
        // GIVEN
        String message = "Forbidden action";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertEquals(Forbidden.class, exception.getClass());
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowForbiddenWhenExplicitlyThrown() {
        // GIVEN
        String message = "Explicit throw";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void shouldThrowForbiddenWithCauseWhenExplicitlyThrown() {
        // GIVEN
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Illegal state");
    }

    @Test
    void shouldThrowForbiddenWithMessageAndCauseWhenExplicitlyThrown() {
        // GIVEN
        String message = "Forbidden with cause";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
