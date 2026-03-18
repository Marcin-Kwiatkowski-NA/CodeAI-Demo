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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertNotNull(exception);
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
        assertNotNull(exception);
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
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void shouldCreateForbiddenWithMessageAndCause() {
        // GIVEN
        String message = "Forbidden access";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertNotNull(exception);
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

        assertNotNull(thrown);
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowForbiddenWithCauseWhenExplicitlyThrown() {
        // GIVEN
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        assertNotNull(thrown);
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void shouldThrowForbiddenWithMessageAndCauseWhenExplicitlyThrown() {
        // GIVEN
        String message = "Forbidden with cause";
        Throwable cause = new NullPointerException("Null pointer");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        assertNotNull(thrown);
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
