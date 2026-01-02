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

class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this immutable exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions needed

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "An internal server error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        IllegalArgumentException cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isSameAs(cause);
        assertThat(exception.getCause()).hasMessage("Invalid argument");
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Error with cause";
        IllegalStateException cause = new IllegalStateException("State error");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
        assertThat(exception.getCause()).hasMessage("State error");
    }

    @Test
    void testExceptionInheritance() {
        // GIVEN
        // No preconditions needed

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertThat(exception).isAssignableTo(RuntimeException.class);
    }
}
