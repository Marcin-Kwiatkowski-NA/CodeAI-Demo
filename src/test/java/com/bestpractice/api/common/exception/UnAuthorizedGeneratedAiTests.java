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

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any state before each test if needed
    }

    @Test
    void givenNoArgs_whenConstructing_thenMessageIsNullAndCauseIsNull() {
        // GIVEN
        // No arguments provided

        // WHEN
        UnAuthorized exception = new UnAuthorized();

        // THEN
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessage_whenConstructing_thenMessageIsSetAndCauseIsNull() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCause_whenConstructing_thenCauseIsSetAndMessageMatchesCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructing_thenBothAreSet() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenNoArgsConstructor_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        // No arguments

        // WHEN & THEN
        assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized();
        });
    }

    @Test
    void givenMessageConstructor_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenCauseConstructor_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });

        // THEN
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void givenMessageAndCauseConstructor_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });

        // THEN
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
