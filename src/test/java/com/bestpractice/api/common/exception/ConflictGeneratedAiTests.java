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
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConflictGeneratedAiTests {

    private Conflict conflict;

    @BeforeEach
    void setUp() {
        conflict = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConflictIsCreated_thenExceptionMessageIsNull() {
        // GIVEN
        // No arguments provided

        // WHEN
        conflict = new Conflict();

        // THEN
        assertEquals(null, conflict.getMessage());
    }

    @Test
    void givenMessageArgument_whenConflictIsCreated_thenExceptionMessageMatches() {
        // GIVEN
        String message = "Conflict occurred";

        // WHEN
        conflict = new Conflict(message);

        // THEN
        assertEquals(message, conflict.getMessage());
    }

    @Test
    void givenCauseArgument_whenConflictIsCreated_thenCauseMatches() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN
        conflict = new Conflict(cause);

        // THEN
        assertEquals(cause, conflict.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConflictIsCreated_thenMessageAndCauseMatch() {
        // GIVEN
        String message = "Conflict occurred";
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN
        conflict = new Conflict(message, cause);

        // THEN
        assertEquals(message, conflict.getMessage());
        assertEquals(cause, conflict.getCause());
    }

    @Test
    void givenNullMessage_whenConflictIsCreated_thenExceptionMessageIsNull() {
        // GIVEN
        String message = null;

        // WHEN
        conflict = new Conflict(message);

        // THEN
        assertEquals(null, conflict.getMessage());
    }

    @Test
    void givenNullCause_whenConflictIsCreated_thenCauseIsNull() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        conflict = new Conflict(cause);

        // THEN
        assertEquals(null, conflict.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConflictIsCreated_thenMessageAndCauseAreNull() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        conflict = new Conflict(message, cause);

        // THEN
        assertEquals(null, conflict.getMessage());
        assertEquals(null, conflict.getCause());
    }

    @Test
    void givenThrowable_whenConflictIsCreated_thenAssertThrowsWorks() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN
        Conflict thrownConflict = assertThrows(Conflict.class, () -> {
            throw new Conflict(cause);
        });

        // THEN
        assertEquals(cause, thrownConflict.getCause());
    }

    @Test
    void givenMessageAndThrowable_whenConflictIsCreated_thenAssertThrowsWorks() {
        // GIVEN
        String message = "Conflict occurred";
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN
        Conflict thrownConflict = assertThrows(Conflict.class, () -> {
            throw new Conflict(message, cause);
        });

        // THEN
        assertEquals(message, thrownConflict.getMessage());
        assertEquals(cause, thrownConflict.getCause());
    }

    @Test
    void givenEmptyMessage_whenConflictIsCreated_thenExceptionMessageIsEmpty() {
        // GIVEN
        String message = "";

        // WHEN
        conflict = new Conflict(message);

        // THEN
        assertEquals("", conflict.getMessage());
    }

    @Test
    void givenEmptyMessageAndNullCause_whenConflictIsCreated_thenMessageIsEmptyAndCauseIsNull() {
        // GIVEN
        String message = "";
        Throwable cause = null;

        // WHEN
        conflict = new Conflict(message, cause);

        // THEN
        assertEquals("", conflict.getMessage());
        assertEquals(null, conflict.getCause());
    }

    @Test
    void givenNonRuntimeExceptionCause_whenConflictIsCreated_thenCauseMatches() {
        // GIVEN
        Throwable cause = new Exception("Non-runtime exception");

        // WHEN
        conflict = new Conflict(cause);

        // THEN
        assertEquals(cause, conflict.getCause());
    }

    @Test
    void givenMessageAndNonRuntimeExceptionCause_whenConflictIsCreated_thenMessageAndCauseMatch() {
        // GIVEN
        String message = "Conflict occurred";
        Throwable cause = new Exception("Non-runtime exception");

        // WHEN
        conflict = new Conflict(message, cause);

        // THEN
        assertEquals(message, conflict.getMessage());
        assertEquals(cause, conflict.getCause());
    }
}
