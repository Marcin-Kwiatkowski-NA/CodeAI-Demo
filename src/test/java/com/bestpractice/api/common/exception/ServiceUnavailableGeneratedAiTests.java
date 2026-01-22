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

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void resetState() {
        // No mutable state to reset
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions required

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN
        assertThat(exception).isInstanceOf(ServiceUnavailable.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN
        String message = "Service is temporarily unavailable";

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN
        assertThat(exception).isInstanceOf(ServiceUnavailable.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN
        assertThat(exception).isInstanceOf(ServiceUnavailable.class);
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN
        String message = "Service failed due to timeout";
        Throwable cause = new RuntimeException("Underlying timeout");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(ServiceUnavailable.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
