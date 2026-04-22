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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConflictGeneratedAiTests {

    private String testMessage;
    private Throwable testCause;

    @BeforeEach
    void setUp() {
        testMessage = "Conflict occurred";
        testCause = new RuntimeException("Root cause");
    }

    @Test
    void givenNoArgs_whenConstructingConflict_thenShouldCreateInstance() {
        // GIVEN
        // No arguments

        // WHEN
        Conflict conflict = new Conflict();

        // THEN
        assertNotNull(conflict);
        assertNull(conflict.getMessage());
        assertNull(conflict.getCause());
    }

    @Test
    void givenMessage_whenConstructingConflict_thenShouldContainMessage() {
        // GIVEN
        String message = testMessage;

        // WHEN
        Conflict conflict = new Conflict(message);

        // THEN
        assertNotNull(conflict);
        assertEquals(message, conflict.getMessage());
        assertNull(conflict.getCause());
    }

    @Test
    void givenCause_whenConstructingConflict_thenShouldContainCause() {
        // GIVEN
        Throwable cause = testCause;

        // WHEN
        Conflict conflict = new Conflict(cause);

        // THEN
        assertNotNull(conflict);
        assertEquals(cause, conflict.getCause());
        // The message of a Throwable constructor with cause is cause.toString()
        assertEquals(cause.toString(), conflict.getMessage());
    }

    @Test
    void givenMessageAndCause_whenConstructingConflict_thenShouldContainBoth() {
        // GIVEN
        String message = testMessage;
        Throwable cause = testCause;

        // WHEN
        Conflict conflict = new Conflict(message, cause);

        // THEN
        assertNotNull(conflict);
        assertEquals(message, conflict.getMessage());
        assertEquals(cause, conflict.getCause());
    }

    @Test
    void givenConflictInstance_whenThrowingException_thenShouldBeCaught() {
        // GIVEN
        Conflict conflict = new Conflict(testMessage);

        // WHEN & THEN
        Conflict thrown = assertThrows(Conflict.class, () -> {
            throw conflict;
        });
        assertEquals(testMessage, thrown.getMessage());
    }

    @Test
    void givenNullMessageAndCause_whenConstructingConflict_thenShouldHandleGracefully() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        Conflict conflict = new Conflict(message, cause);

        // THEN
        assertNotNull(conflict);
        assertNull(conflict.getMessage());
        assertNull(conflict.getCause());
    }
}
