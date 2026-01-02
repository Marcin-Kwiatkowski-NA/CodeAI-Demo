package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this immutable exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions needed for the default constructor

        // WHEN
        Forbidden exception = new Forbidden();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructorWithNonNull() {
        // GIVEN
        String message = "Access denied";

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructorWithNull() {
        // GIVEN
        String message = null;

        // WHEN
        Forbidden exception = new Forbidden(message);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructorWithNonNull() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testCauseConstructorWithNull() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        Forbidden exception = new Forbidden(cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndCauseConstructorWithNonNull() {
        // GIVEN
        String message = "Forbidden operation";
        Throwable cause = new NullPointerException("Null value");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageAndCauseConstructorWithNullMessage() {
        // GIVEN
        String message = null;
        Throwable cause = new NullPointerException("Null value");

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageAndCauseConstructorWithNullCause() {
        // GIVEN
        String message = "Forbidden operation";
        Throwable cause = null;

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndCauseConstructorWithNullBoth() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        Forbidden exception = new Forbidden(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testExceptionIsThrownAndCaught() {
        // GIVEN
        Forbidden exception = new Forbidden("Error");

        // WHEN & THEN
        try {
            throw exception;
        } catch (Forbidden e) {
            assertThat(e).isSameAs(exception);
            assertThat(e.getMessage()).isEqualTo("Error");
        }
    }
}
