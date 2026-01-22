package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void resetState() {
        // No shared state to reset for this immutable exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        IllegalArgumentException cause = new IllegalArgumentException("Bad argument");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Forbidden operation";
        IllegalArgumentException cause = new IllegalArgumentException("Invalid input");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageConstructorWithNull() {
        // GIVEN
        String nullMessage = null;

        // WHEN
        Forbidden exception = new Forbidden(nullMessage);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructorWithNull() {
        // GIVEN
        Throwable nullCause = null;

        // WHEN
        Forbidden exception = new Forbidden(nullCause);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndCauseConstructorWithNulls() {
        // GIVEN
        String nullMessage = null;
        Throwable nullCause = null;

        // WHEN
        Forbidden exception = new Forbidden(nullMessage, nullCause);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }
}
