package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructed_thenMessageIsNullAndCauseIsNull() {
        // GIVEN
        // No setup needed

        // WHEN
        NotFound exception = new NotFound();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructed_thenMessageIsSetAndCauseIsNull() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        NotFound exception = new NotFound(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructed_thenCauseIsSetAndMessageIsCauseToString() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructed_thenBothAreSet() {
        // GIVEN
        String message = "Resource missing";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenThrowingNotFound_whenCaught_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Not found error";

        // WHEN & THEN
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenThrowingNotFoundWithCause_whenCaught_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Error occurred", cause);
        });
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenThrowingNotFoundWithOnlyCause_whenCaught_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new UnsupportedOperationException("Unsupported");

        // WHEN & THEN
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
        assertEquals(cause.toString(), thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenNullMessageAndNullCause_whenConstructed_thenMessageAndCauseAreNull() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }
}
