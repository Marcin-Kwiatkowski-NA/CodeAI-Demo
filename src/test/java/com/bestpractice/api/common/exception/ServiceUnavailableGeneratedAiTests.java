package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(null)
                .hasNoCause();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Service is temporarily unavailable";

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message)
                .hasNoCause();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        RuntimeException cause = new RuntimeException("Underlying failure");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(cause.toString())
                .hasCause(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Service is temporarily unavailable";
        RuntimeException cause = new RuntimeException("Underlying failure");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN
        assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message)
                .hasCause(cause);
    }
}
