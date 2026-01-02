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

class ServiceUnavailableGeneratedAiTests {

    private ServiceUnavailable exception;

    @BeforeEach
    void setUp() {
        exception = null;
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions

        // WHEN
        exception = new ServiceUnavailable();

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(ServiceUnavailable.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructorWithNonNullMessage() {
        // GIVEN
        String expectedMessage = "Service is currently unavailable";

        // WHEN
        exception = new ServiceUnavailable(expectedMessage);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructorWithNullMessage() {
        // GIVEN
        String nullMessage = null;

        // WHEN
        exception = new ServiceUnavailable(nullMessage);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructorWithNonNullCause() {
        // GIVEN
        Throwable cause = new IllegalStateException("Underlying failure");

        // WHEN
        exception = new ServiceUnavailable(cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testCauseConstructorWithNullCause() {
        // GIVEN
        Throwable nullCause = null;

        // WHEN
        exception = new ServiceUnavailable(nullCause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndCauseConstructorWithNonNullValues() {
        // GIVEN
        String expectedMessage = "Service unavailable due to maintenance";
        Throwable cause = new RuntimeException("Database down");

        // WHEN
        exception = new ServiceUnavailable(expectedMessage, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageAndCauseConstructorWithNullMessage() {
        // GIVEN
        String nullMessage = null;
        Throwable cause = new RuntimeException("Database down");

        // WHEN
        exception = new ServiceUnavailable(nullMessage, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageAndCauseConstructorWithNullCause() {
        // GIVEN
        String expectedMessage = "Service unavailable due to maintenance";
        Throwable nullCause = null;

        // WHEN
        exception = new ServiceUnavailable(expectedMessage, nullCause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndCauseConstructorWithBothNull() {
        // GIVEN
        String nullMessage = null;
        Throwable nullCause = null;

        // WHEN
        exception = new ServiceUnavailable(nullMessage, nullCause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }
}
