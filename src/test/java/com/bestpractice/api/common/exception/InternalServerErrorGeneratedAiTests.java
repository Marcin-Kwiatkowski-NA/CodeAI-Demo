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

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No mutable state to reset for this exception class.
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions needed for default constructor.

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructorWithNonNullMessage() {
        // GIVEN
        String message = "Error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructorWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructorWithNonNullCause() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testCauseConstructorWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageAndCauseConstructorWithNonNullValues() {
        // GIVEN
        String message = "Composite error";
        Throwable cause = new NullPointerException("Null value");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageAndCauseConstructorWithNullMessage() {
        // GIVEN
        String message = null;
        Throwable cause = new NullPointerException("Null value");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageAndCauseConstructorWithNullCause() {
        // GIVEN
        String message = "Composite error";
        Throwable cause = null;

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testExceptionIsSubclassOfRuntimeException() {
        // GIVEN
        // No preconditions needed.

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testExceptionClassName() {
        // GIVEN
        // No preconditions needed.

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertThat(exception.getClass()).isEqualTo(InternalServerError.class);
    }

    @Test
    void testExceptionToStringContainsClassName() {
        // GIVEN
        String message = "Test message";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertThat(exception.toString()).contains(InternalServerError.class.getName());
    }
}
