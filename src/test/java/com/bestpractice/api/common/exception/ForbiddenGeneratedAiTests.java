package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ForbiddenGeneratedAiTests {

    private Forbidden forbidden;

    @BeforeEach
    void setUp() {
        forbidden = null;
    }

    @Test
    void shouldThrowRuntimeExceptionWhenNoMessageProvided() {
        // GIVEN
        Forbidden actual = new Forbidden();

        // WHEN
        // THEN
        Assertions.assertNotNull(actual);
        assertEquals(null, actual.getMessage());
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageWhenMessageProvided() {
        // GIVEN
        String message = "Access denied";
        Forbidden actual = new Forbidden(message);

        // WHEN
        // THEN
        Assertions.assertNotNull(actual);
        assertEquals(message, actual.getMessage());
    }

    @Test
    void shouldThrowRuntimeExceptionWithCauseWhenCauseProvided() {
        // GIVEN
        Throwable cause = new RuntimeException("Internal error");
        Forbidden actual = new Forbidden(cause);

        // WHEN
        // THEN
        Assertions.assertNotNull(actual);
        assertEquals(cause, actual.getCause());
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageAndCauseWhenBothProvided() {
        // GIVEN
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Authentication failed");
        Forbidden actual = new Forbidden(message, cause);

        // WHEN
        // THEN
        Assertions.assertNotNull(actual);
        assertEquals(message, actual.getMessage());
        assertEquals(cause, actual.getCause());
    }

    @Test
    void shouldHaveCorrectStackTraceWhenThrown() {
        // GIVEN
        String message = "Forbidden action";
        Throwable cause = new RuntimeException("Database error");
        Forbidden actual = new Forbidden(message, cause);

        // WHEN
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            throw actual;
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
