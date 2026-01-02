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

class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for NotFound, but method provided for completeness.
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No additional setup required for default constructor.

        // WHEN
        NotFound exception = new NotFound();

        // THEN
        assertThat(exception).isNotNull();
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
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Missing entity";
        Throwable cause = new NullPointerException("Null pointer");

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testNullMessageConstructor() {
        // GIVEN
        String message = null;

        // WHEN
        NotFound exception = new NotFound(message);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testNullCauseConstructor() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndNullCauseConstructor() {
        // GIVEN
        String message = "Some message";
        Throwable cause = null;

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testNullMessageAndNullCauseConstructor() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }
}
