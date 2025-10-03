package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConflictGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenMessageAndCauseAreNull() {
        // GIVEN
        // No arguments provided

        // WHEN
        Conflict conflict = new Conflict();

        // THEN
        assertNull(conflict.getMessage());
        assertNull(conflict.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSetAndCauseIsNull() {
        // GIVEN
        String message = "Conflict occurred";

        // WHEN
        Conflict conflict = new Conflict(message);

        // THEN
        assertEquals(message, conflict.getMessage());
        assertNull(conflict.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSetAndMessageIsCauseToString() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Conflict conflict = new Conflict(cause);

        // THEN
        assertEquals(cause.toString(), conflict.getMessage());
        assertEquals(cause, conflict.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Conflict with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        Conflict conflict = new Conflict(message, cause);

        // THEN
        assertEquals(message, conflict.getMessage());
        assertEquals(cause, conflict.getCause());
    }

    @Test
    void givenConflictInstance_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Thrown conflict";
        Conflict conflict = new Conflict(message);

        // WHEN & THEN
        Conflict thrown = assertThrows(Conflict.class, () -> {
            throw conflict;
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenConflictWithCause_whenThrowing_thenAssertThrowsCatchesItAndCauseMatches() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");
        Conflict conflict = new Conflict("Conflict with cause", cause);

        // WHEN & THEN
        Conflict thrown = assertThrows(Conflict.class, () -> {
            throw conflict;
        });
        assertEquals("Conflict with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenNullMessageAndNullCause_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        Conflict conflict = new Conflict(null, null);

        // WHEN & THEN
        Conflict thrown = assertThrows(Conflict.class, () -> {
            throw conflict;
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCauseOnly_whenThrowing_thenAssertThrowsCatchesItAndMessageMatchesCauseToString() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");
        Conflict conflict = new Conflict(cause);

        // WHEN & THEN
        Conflict thrown = assertThrows(Conflict.class, () -> {
            throw conflict;
        });
        assertEquals(cause.toString(), thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
