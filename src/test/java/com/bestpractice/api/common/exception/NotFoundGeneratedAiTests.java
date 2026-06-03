package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void givenNoArgsConstructor_whenCreatingInstance_thenMessageAndCauseAreNull() {
        NotFound exception = new NotFound();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenMessageConstructor_whenCreatingInstance_thenMessageIsSet() {
        String message = "Resource not found";
        NotFound exception = new NotFound(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenCauseConstructor_whenCreatingInstance_thenCauseIsSet() {
        Throwable cause = new IllegalArgumentException("Invalid argument");
        NotFound exception = new NotFound(cause);
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void givenMessageAndCauseConstructor_whenCreatingInstance_thenMessageAndCauseAreSet() {
        String message = "Data not found";
        Throwable cause = new NullPointerException("Null value");
        NotFound exception = new NotFound(message, cause);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenNullMessage_whenCreatingInstance_thenMessageIsNull() {
        NotFound exception = new NotFound((String) null);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenEmptyMessage_whenCreatingInstance_thenMessageIsEmptyString() {
        String message = "";
        NotFound exception = new NotFound(message);
        assertEquals("", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenWhitespaceMessage_whenCreatingInstance_thenMessageIsWhitespace() {
        String message = "   ";
        NotFound exception = new NotFound(message);
        assertEquals("   ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenLongMessage_whenCreatingInstance_thenMessageIsSetCorrectly() {
        String message = "A".repeat(10000);
        NotFound exception = new NotFound(message);
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenSingleCharacterMessage_whenCreatingInstance_thenMessageIsSetCorrectly() {
        String message = "X";
        NotFound exception = new NotFound(message);
        assertEquals("X", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenNullCause_whenCreatingInstance_thenCauseIsNull() {
        NotFound exception = new NotFound((Throwable) null);
        assertEquals(null, exception.getCause());
        assertEquals(null, exception.getMessage());
    }

    @Test
    void givenNullMessageAndCause_whenCreatingInstance_thenBothAreNull() {
        NotFound exception = new NotFound(null, null);
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenEmptyMessageAndNonNullCause_whenCreatingInstance_thenMessageIsEmptyAndCauseIsSet() {
        String message = "";
        Throwable cause = new RuntimeException("Cause message");
        NotFound exception = new NotFound(message, cause);
        assertEquals("", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenWhitespaceMessageAndNullCause_whenCreatingInstance_thenMessageIsWhitespaceAndCauseIsNull() {
        String message = " ";
        NotFound exception = new NotFound(message, null);
        assertEquals(" ", exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void givenInvalidUsage_whenThrowingNotFound_thenAssertThrowsCatchesIt() {
        String message = "Critical not found error";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenCause_whenThrowingNotFound_thenAssertThrowsCatchesItAndCauseIsVerified() {
        Throwable cause = new IllegalStateException("Underlying issue");
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Top-level not found", cause);
        });
        assertEquals("Top-level not found", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenEmptyMessage_whenThrowingNotFound_thenAssertThrowsCatchesItAndMessageIsEmpty() {
        String message = "";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals("", thrown.getMessage());
    }

    @Test
    void givenWhitespaceMessage_whenThrowingNotFound_thenAssertThrowsCatchesItAndMessageIsWhitespace() {
        String message = "   ";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals("   ", thrown.getMessage());
    }

    @Test
    void givenLongMessage_whenThrowingNotFound_thenAssertThrowsCatchesItAndMessageIsLong() {
        String message = "A".repeat(5000);
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenSingleCharacterMessage_whenThrowingNotFound_thenAssertThrowsCatchesItAndMessageIsSingleChar() {
        String message = "Z";
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals("Z", thrown.getMessage());
    }

    @Test
    void givenNullMessageAndCause_whenThrowingNotFound_thenAssertThrowsCatchesItAndBothAreNull() {
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(null, null);
        });
        assertEquals(null, thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }

    @Test
    void givenNullCause_whenThrowingNotFound_thenAssertThrowsCatchesItAndCauseIsNull() {
        Throwable cause = null;
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Error occurred", cause);
        });
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(null, thrown.getCause());
    }
}
