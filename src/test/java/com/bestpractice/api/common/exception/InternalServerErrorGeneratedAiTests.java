package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructing_thenExceptionCreated() {
        // GIVEN
        // No arguments

        // WHEN
        InternalServerError exception = new InternalServerError();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSet() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN
        InternalServerError exception = new InternalServerError(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        InternalServerError exception = new InternalServerError(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error occurred";

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Internal error with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
