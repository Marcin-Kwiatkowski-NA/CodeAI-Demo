package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgs_whenConstructingBadRequest_thenMessageAndCauseAreNull() {
        // GIVEN
        // No arguments provided

        // WHEN
        BadRequest exception = new BadRequest();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructingBadRequest_thenMessageIsSet() {
        // GIVEN
        String message = "Invalid request";

        // WHEN
        BadRequest exception = new BadRequest(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructingBadRequest_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        BadRequest exception = new BadRequest(cause);

        // THEN
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructingBadRequest_thenBothAreSet() {
        // GIVEN
        String message = "Invalid request with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        BadRequest exception = new BadRequest(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenBadRequestInstance_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Throwing BadRequest";
        BadRequest exception = new BadRequest(message);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw exception;
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenBadRequestWithCause_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Throwing BadRequest with cause";
        Throwable cause = new RuntimeException("Root cause");
        BadRequest exception = new BadRequest(message, cause);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw exception;
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenNullMessage_whenThrowingBadRequest_thenAssertThrowsCatchesIt() {
        // GIVEN
        BadRequest exception = new BadRequest((String) null);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw exception;
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenNullCause_whenThrowingBadRequest_thenAssertThrowsCatchesIt() {
        // GIVEN
        BadRequest exception = new BadRequest((Throwable) null);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw exception;
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenMessageAndNullCause_whenThrowingBadRequest_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Message with null cause";
        BadRequest exception = new BadRequest(message, null);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw exception;
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCauseOnly_whenThrowingBadRequest_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Only cause provided");
        BadRequest exception = new BadRequest(cause);

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw exception;
        });
        assertEquals(cause.toString(), thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
