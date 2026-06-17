package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateBadRequestWithNoArgsConstructor() {
        BadRequest exception = new BadRequest();
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithMessage() {
        String message = "Invalid input provided";
        BadRequest exception = new BadRequest(message);
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithEmptyMessage() {
        String message = "";
        BadRequest exception = new BadRequest(message);
        assertThat(exception).isNotNull();
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithWhitespaceMessage() {
        String message = "   ";
        BadRequest exception = new BadRequest(message);
        assertThat(exception).isNotNull();
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithLongMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("x");
        }
        String longMessage = sb.toString();
        BadRequest exception = new BadRequest(longMessage);
        assertThat(exception).isNotNull();
        assertEquals(longMessage, exception.getMessage());
        assertEquals(10000, exception.getMessage().length());
    }

    @Test
    void shouldCreateBadRequestWithCause() {
        Throwable cause = new IllegalArgumentException("Invalid argument");
        BadRequest exception = new BadRequest(cause);
        assertThat(exception).isNotNull();
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void shouldCreateBadRequestWithMessageAndCause() {
        String message = "Request failed";
        Throwable cause = new NullPointerException("Null value");
        BadRequest exception = new BadRequest(message, cause);
        assertThat(exception).isNotNull();
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowBadRequestWhenExplicitlyThrown() {
        String message = "Explicit bad request";
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowBadRequestWithNullMessage() {
        String message = null;
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(null, thrown.getMessage());
    }

    @Test
    void shouldThrowBadRequestWithNullCause() {
        Throwable cause = null;
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest("Error occurred", cause);
        });
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldThrowBadRequestUsingCauseConstructorWithNull() {
        Throwable cause = null;
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });
        assertEquals(null, thrown.getCause());
    }

    @Test
    void shouldThrowBadRequestAndVerifyMessageAndCause() {
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void shouldHandleBoundaryCaseWithSingleCharacterMessage() {
        String message = "A";
        BadRequest exception = new BadRequest(message);
        assertThat(exception).isNotNull();
        assertEquals("A", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldHandleBoundaryCaseWithUnicodeMessage() {
        String message = "⚡️🔥💡";
        BadRequest exception = new BadRequest(message);
        assertThat(exception).isNotNull();
        assertEquals("⚡️🔥💡", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void shouldCreateBadRequestWithNullMessageAndNullCause() {
        String message = null;
        Throwable cause = null;
        BadRequest exception = new BadRequest(message, cause);
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }
}
