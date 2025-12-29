package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class InternalServerErrorGeneratedAiTests {

    private InternalServerError exception;

    @BeforeEach
    void setUp() {
        exception = new InternalServerError();
    }

    @Test
    void shouldCreateInternalServerErrorWithDefaultConstructor() {
        // GIVEN
        // WHEN
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void shouldCreateInternalServerErrorWithMessage() {
        // GIVEN
        String message = "Test message";
        // WHEN
        exception = new InternalServerError(message);
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void shouldCreateInternalServerErrorWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Cause");
        // WHEN
        exception = new InternalServerError(cause);
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void shouldCreateInternalServerErrorWithMessageAndCause() {
        // GIVEN
        String message = "Test message";
        Throwable cause = new RuntimeException("Cause");
        // WHEN
        exception = new InternalServerError(message, cause);
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
