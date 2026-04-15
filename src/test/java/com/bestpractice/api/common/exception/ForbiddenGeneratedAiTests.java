package com.bestpractice.api.common.exception;

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
        assertThat(exception).isInstanceOf(Forbidden.class);
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
        assertThat(exception).isInstanceOf(Forbidden.class);
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
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void shouldCreateForbiddenWithMessageAndCause() {
        // GIVEN
        String message = "Forbidden access";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(Forbidden.class);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowForbiddenWhenExplicitlyThrown() {
        // GIVEN
        String message = "Explicit forbidden throw";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertThat(thrown).isInstanceOf(Forbidden.class);
    }

    @Test
    void shouldThrowForbiddenWithCauseWhenExplicitlyThrown() {
        // GIVEN
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden("Error occurred", cause);
        });

        // THEN
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
        assertThat(thrown).isInstanceOf(Forbidden.class);
    }
}
