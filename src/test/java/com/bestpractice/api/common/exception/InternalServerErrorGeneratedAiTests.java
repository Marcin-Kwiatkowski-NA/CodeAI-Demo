package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgsConstructor_whenCreatingInstance_thenInstanceIsCreated() {
        // GIVEN - no arguments
        // WHEN - creating instance using no-args constructor
        InternalServerError error = new InternalServerError();
        // THEN - instance should not be null and message/cause should be null
        assertNotNull(error);
        assertNull(error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void givenMessageConstructor_whenCreatingInstance_thenMessageIsSet() {
        // GIVEN - a specific message
        String message = "Internal server error occurred";
        // WHEN - creating instance using message constructor
        InternalServerError error = new InternalServerError(message);
        // THEN - message should be set correctly and cause should be null
        assertNotNull(error);
        assertEquals(message, error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void givenCauseConstructor_whenCreatingInstance_thenCauseIsSetAndMessageMatchesCause() {
        // GIVEN - a specific cause
        Throwable cause = new RuntimeException("Root cause");
        // WHEN - creating instance using cause constructor
        InternalServerError error = new InternalServerError(cause);
        // THEN - cause should be set correctly and message should match cause.toString()
        assertNotNull(error);
        assertEquals(cause, error.getCause());
        assertEquals(cause.toString(), error.getMessage());
    }

    @Test
    void givenMessageAndCauseConstructor_whenCreatingInstance_thenMessageAndCauseAreSet() {
        // GIVEN - a specific message and cause
        String message = "Internal server error with cause";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN - creating instance using message and cause constructor
        InternalServerError error = new InternalServerError(message, cause);
        // THEN - message and cause should be set correctly
        assertNotNull(error);
        assertEquals(message, error.getMessage());
        assertEquals(cause, error.getCause());
    }

    @Test
    void givenThrowingInternalServerError_whenExceptionIsThrown_thenAssertThrowsCatchesIt() {
        // GIVEN - a specific message
        String message = "Throwing InternalServerError";
        // WHEN & THEN - assertThrows should catch the thrown exception
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenThrowingInternalServerErrorWithCause_whenExceptionIsThrown_thenAssertThrowsCatchesIt() {
        // GIVEN - a specific message and cause
        String message = "Throwing InternalServerError with cause";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN - assertThrows should catch the thrown exception
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenThrowingInternalServerErrorWithOnlyCause_whenExceptionIsThrown_thenAssertThrowsCatchesIt() {
        // GIVEN - a specific cause
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN - assertThrows should catch the thrown exception
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause.toString(), thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenThrowingInternalServerErrorWithoutMessageOrCause_whenExceptionIsThrown_thenAssertThrowsCatchesIt() {
        // GIVEN - no message or cause
        // WHEN & THEN - assertThrows should catch the thrown exception
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
