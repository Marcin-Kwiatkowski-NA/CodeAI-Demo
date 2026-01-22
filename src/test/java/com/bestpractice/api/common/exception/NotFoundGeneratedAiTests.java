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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions

        // WHEN
        NotFound exception = new NotFound();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        NotFound exception = new NotFound(message);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid ID");

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Item missing";
        Throwable cause = new IllegalStateException("State error");

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageConstructorWithNull() {
        // GIVEN
        String message = null;

        // WHEN
        NotFound exception = new NotFound(message);

        // THEN
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    void testCauseConstructorWithNull() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertThat(exception.getCause()).isNull();
        // The message should be the string representation of the cause, which is "null"
        assertThat(exception.getMessage()).isEqualTo("null");
    }
}
