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

class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No mutable state to reset for this exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions needed for default constructor

        // WHEN
        BadRequest exception = new BadRequest();

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Invalid request parameters";

        // WHEN
        BadRequest exception = new BadRequest(message);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Underlying issue");

        // WHEN
        BadRequest exception = new BadRequest(cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageCauseConstructor() {
        // GIVEN
        String message = "Request failed";
        Throwable cause = new IllegalStateException("State error");

        // WHEN
        BadRequest exception = new BadRequest(message, cause);

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
        BadRequest exception = new BadRequest(message);

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
        BadRequest exception = new BadRequest(cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testNullMessageCauseConstructor() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        BadRequest exception = new BadRequest(message, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }
}
