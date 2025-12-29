package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class UnAuthorizedGeneratedAiTests {

    @Test
    void shouldThrowRuntimeExceptionWhenNoMessageProvided() {
        // GIVEN
        // WHEN
        UnAuthorized unAuthorized = new UnAuthorized();

        // THEN
        assertThat(unAuthorized).isNotNull();
        assertThat(unAuthorized.getMessage()).isNull();
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageProvided() {
        // GIVEN
        String message = "Unauthorized access attempt";
        // WHEN
        UnAuthorized unAuthorized = new UnAuthorized(message);

        // THEN
        assertThat(unAuthorized).isNotNull();
        assertThat(unAuthorized.getMessage()).isEqualTo(message);
    }

    @Test
    void shouldThrowRuntimeExceptionWithCauseProvided() {
        // GIVEN
        Throwable cause = new RuntimeException("Internal error");
        // WHEN
        UnAuthorized unAuthorized = new UnAuthorized(cause);

        // THEN
        assertThat(unAuthorized).isNotNull();
        assertThat(unAuthorized.getCause()).isEqualTo(cause);
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageAndCauseProvided() {
        // GIVEN
        String message = "Access denied";
        Throwable cause = new RuntimeException("Database connection failed");
        // WHEN
        UnAuthorized unAuthorized = new UnAuthorized(message, cause);

        // THEN
        assertThat(unAuthorized).isNotNull();
        assertThat(unAuthorized.getMessage()).isEqualTo(message);
        assertThat(unAuthorized.getCause()).isEqualTo(cause);
    }

    @Test
    void shouldNotAllowNullMessageInConstructor() {
        // GIVEN
        Throwable cause = new RuntimeException("Test cause");
        // WHEN & THEN
        assertThatThrownBy(() -> {
            new UnAuthorized(null, cause);
        }).isInstanceOf(NullPointerException.class);
    }

    @Test
    void shouldNotAllowNullCauseInConstructor() {
        // GIVEN
        String message = "Invalid token";
        // WHEN & THEN
        assertThatThrownBy(() -> {
            new UnAuthorized(message, null);
        }).isInstanceOf(NullPointerException.class);
    }
}
