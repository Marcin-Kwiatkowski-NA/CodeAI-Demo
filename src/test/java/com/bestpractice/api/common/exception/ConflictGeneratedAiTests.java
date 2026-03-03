package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConflictGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this immutable exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // WHEN
        Conflict conflict = new Conflict();
        // THEN
        assertThat(conflict).isNotNull();
        assertThat(conflict.getMessage()).isNull();
        assertThat(conflict.getCause()).isNull();
        assertThat(conflict).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Conflict detected";
        // WHEN
        Conflict conflict = new Conflict(message);
        // THEN
        assertThat(conflict.getMessage()).isEqualTo(message);
        assertThat(conflict.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");
        // WHEN
        Conflict conflict = new Conflict(cause);
        // THEN
        assertThat(conflict.getMessage()).isNull();
        assertThat(conflict.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Conflict with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN
        Conflict conflict = new Conflict(message, cause);
        // THEN
        assertThat(conflict.getMessage()).isEqualTo(message);
        assertThat(conflict.getCause()).isSameAs(cause);
    }

    @Test
    void testNullMessageConstructor() {
        // GIVEN
        // WHEN
        Conflict conflict = new Conflict((String) null);
        // THEN
        assertThat(conflict.getMessage()).isNull();
        assertThat(conflict.getCause()).isNull();
    }

    @Test
    void testNullCauseConstructor() {
        // GIVEN
        // WHEN
        Conflict conflict = new Conflict((Throwable) null);
        // THEN
        assertThat(conflict.getMessage()).isNull();
        assertThat(conflict.getCause()).isNull();
    }

    @Test
    void testNullMessageAndCauseConstructor() {
        // GIVEN
        // WHEN
        Conflict conflict = new Conflict((String) null, (Throwable) null);
        // THEN
        assertThat(conflict.getMessage()).isNull();
        assertThat(conflict.getCause()).isNull();
    }
}
