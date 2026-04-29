package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgsConstructor_whenCreatingInstance_thenMessageAndCauseShouldBeNull() {
        // GIVEN
        // No preconditions

        // WHEN
        UnAuthorized exception = new UnAuthorized();

        // THEN
        assertNull(exception.getMessage(), "Message should be null for no-arg constructor");
        assertNull(exception.getCause(), "Cause should be null for no-arg constructor");
    }

    @Test
    void givenMessageConstructor_whenCreatingInstance_thenMessageShouldBeSet() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals(message, exception.getMessage(), "Message should match the one provided");
        assertNull(exception.getCause(), "Cause should be null when only message is provided");
    }

    @Test
    void givenCauseConstructor_whenCreatingInstance_thenCauseShouldBeSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertEquals(cause, exception.getCause(), "Cause should match the one provided");
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void givenMessageAndCauseConstructor_whenCreatingInstance_thenBothShouldBeSet() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertEquals(message, exception.getMessage(), "Message should match the one provided");
        assertEquals(cause, exception.getCause(), "Cause should match the one provided");
    }

    @Test
    void givenExceptionInstance_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Unauthorized throw test";

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        }, "Expected UnAuthorized to be thrown");

        assertEquals(message, thrown.getMessage(), "Thrown exception message should match");
    }

    @Test
    void givenNullMessageAndCause_whenCreatingInstance_thenNoExceptionShouldBeThrown() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertNull(exception.getMessage(), "Message should be null when null is passed");
        assertNull(exception.getCause(), "Cause should be null when null is passed");
    }
}
