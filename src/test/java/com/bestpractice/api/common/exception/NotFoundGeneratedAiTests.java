package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotFoundGeneratedAiTests {

    private NotFound notFound;

    @BeforeEach
    void setUp() {
        notFound = null;
    }

    @Test
    void shouldThrowRuntimeExceptionWhenNoMessageProvided() {
        // GIVEN
        // WHEN
        notFound = new NotFound();

        // THEN
        assertThat(notFound).isNotNull();
        assertThat(notFound.getMessage()).isNull();
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageProvided() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        notFound = new NotFound(message);

        // THEN
        assertThat(notFound).isNotNull();
        assertThat(notFound.getMessage()).isEqualTo(message);
    }

    @Test
    void shouldThrowRuntimeExceptionWithCauseProvided() {
        // GIVEN
        Throwable cause = new RuntimeException("Cause occurred");

        // WHEN
        notFound = new NotFound(cause);

        // THEN
        assertThat(notFound).isNotNull();
        assertThat(notFound.getCause()).isSameAs(cause);
    }

    @Test
    void shouldThrowRuntimeExceptionWithMessageAndCauseProvided() {
        // GIVEN
        String message = "Resource not found";
        Throwable cause = new RuntimeException("Cause occurred");

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertThat(notFound).isNotNull();
        assertThat(notFound.getMessage()).isEqualTo(message);
        assertThat(notFound.getCause()).isSameAs(cause);
    }

    @Test
    void shouldHaveCorrectMessageWhenThrownWithMessage() {
        // GIVEN
        String message = "Item not found";

        // WHEN
        assertThatThrownBy(() -> new NotFound(message))
                .hasMessage(message);
    }

    @Test
    void shouldHaveCorrectCauseWhenThrownWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Internal error");

        // WHEN
        assertThatThrownBy(() -> new NotFound(cause))
                .hasCause(cause);
    }

    @Test
    void shouldHaveCorrectMessageAndCauseWhenThrownWithBoth() {
        // GIVEN
        String message = "User not found";
        Throwable cause = new RuntimeException("Database error");

        // WHEN
        assertThatThrownBy(() -> new NotFound(message, cause))
                .hasMessage(message)
                .hasCause(cause);
    }
}
