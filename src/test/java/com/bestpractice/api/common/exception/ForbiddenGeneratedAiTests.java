package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenInstanceCreated() {
        // GIVEN
        // No arguments

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
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
    void givenCause_whenConstructing_thenCauseIsSet() {
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
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void givenMessageAndCause_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenNullMessage_whenConstructing_thenMessageIsNull() {
        // GIVEN
        String message = null;

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenNullCause_whenConstructing_thenCauseIsNull() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertNotNull(exception);
        assertNull(exception.getCause());
        assertNull(exception.getMessage());
    }

    @Test
    void givenNullMessageAndCause_whenConstructing_thenBothAreNull() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN & THEN
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });

        // THEN
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
