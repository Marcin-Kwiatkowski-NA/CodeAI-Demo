package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN - Reset any shared state before each test
    }

    @Test
    void shouldCreateInstanceWithDefaultConstructor() {
        // GIVEN
        UnAuthorized exception;

        // WHEN
        exception = new UnAuthorized();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessage() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertNotNull(exception.getMessage());
    }

    @Test
    void shouldCreateInstanceWithMessageAndCause() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowUnAuthorizedWhenExplicitlyThrown() {
        // GIVEN
        String message = "Explicit unauthorized throw";

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });

        // THEN
        assertNotNull(thrown);
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowUnAuthorizedWithCauseWhenExplicitlyThrown() {
        // GIVEN
        Throwable cause = new RuntimeException("Security-sensitive cause");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized("Unauthorized with cause", cause);
        });

        // THEN
        assertNotNull(thrown);
        assertEquals("Unauthorized with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
