package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.bestpractice.api.common.exception.InternalServerError;

class InternalServerErrorGeneratedAiTests {

    private InternalServerError error;

    @BeforeEach
    void setUp() {
        error = null;
    }

    @Test
    void shouldThrowRuntimeExceptionWhenNoMessageProvided() {
        // GIVEN
        InternalServerError exception = new InternalServerError();

        // WHEN
        // THEN
        Assertions.assertNotNull(exception);
        Assertions.assertNull(exception.getMessage());
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageProvided() {
        // GIVEN
        String message = "An internal server error occurred";
        InternalServerError exception = new InternalServerError(message);

        // WHEN
        // THEN
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    void shouldThrowRuntimeExceptionWithCauseProvided() {
        // GIVEN
        Throwable cause = new RuntimeException("Cause message");
        InternalServerError exception = new InternalServerError(cause);

        // WHEN
        // THEN
        Assertions.assertNotNull(exception);
        Assertions.assertSame(cause, exception.getCause());
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageAndCauseProvided() {
        // GIVEN
        String message = "Server error during processing";
        Throwable cause = new RuntimeException("Database connection failed");
        InternalServerError exception = new InternalServerError(message, cause);

        // WHEN
        // THEN
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(message, exception.getMessage());
        Assertions.assertSame(cause, exception.getCause());
    }
}
