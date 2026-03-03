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

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No shared state to reset
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Service is down";

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testNullMessageConstructor() {
        // GIVEN
        String message = null;

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalStateException("Underlying error");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testNullCauseConstructor() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Service unavailable";
        Throwable cause = new IllegalArgumentException("Bad request");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testNullMessageAndCauseConstructor() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }
}
