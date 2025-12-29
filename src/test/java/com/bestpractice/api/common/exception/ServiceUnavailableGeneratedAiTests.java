package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ServiceUnavailableGeneratedAiTests {

    @Test
    void shouldThrowServiceUnavailableWhenMessageIsProvided() {
        // GIVEN
        String message = "Service is temporarily unavailable";

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception).isInstanceOf(ServiceUnavailable.class);
    }

    @Test
    void shouldThrowServiceUnavailableWhenCauseIsProvided() {
        // GIVEN
        Throwable cause = new RuntimeException("Internal error occurred");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN
        assertThat(exception.getCause()).isSameAs(cause);
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    void shouldThrowServiceUnavailableWhenMessageAndCauseAreProvided() {
        // GIVEN
        String message = "Service is unavailable due to timeout";
        Throwable cause = new RuntimeException("Timeout during processing");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void shouldHandleEmptyMessageAndNoCause() {
        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }
}
