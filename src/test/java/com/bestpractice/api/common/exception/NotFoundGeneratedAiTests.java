package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NotFoundGeneratedAiTests {

    private NotFound notFound;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        notFound = null;
    }

    @Test
    void givenDefaultConstructor_whenInstantiated_thenNoMessageOrCause() {
        // GIVEN
        // No preconditions required

        // WHEN
        notFound = new NotFound();

        // THEN
        assertNull(notFound.getMessage(), "Message should be null for default constructor");
        assertNull(notFound.getCause(), "Cause should be null for default constructor");
    }

    @Test
    void givenMessageConstructor_whenInstantiated_thenMessageIsSet() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        notFound = new NotFound(message);

        // THEN
        assertEquals(message, notFound.getMessage(), "Message should match the input");
        assertNull(notFound.getCause(), "Cause should be null when only message is provided");
    }

    @Test
    void givenCauseConstructor_whenInstantiated_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(cause);

        // THEN
        assertEquals(cause.toString(), notFound.getMessage(), "Message should match the cause's toString");
        assertEquals(cause, notFound.getCause(), "Cause should match the input cause");
    }

    @Test
    void givenMessageAndCauseConstructor_whenInstantiated_thenMessageAndCauseAreSet() {
        // GIVEN
        String message = "Resource not found";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertEquals(message, notFound.getMessage(), "Message should match the input");
        assertEquals(cause, notFound.getCause(), "Cause should match the input cause");
    }

    @Test
    void givenNullMessageAndCause_whenInstantiated_thenNoExceptionsThrown() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        notFound = new NotFound(message, cause);

        // THEN
        assertNull(notFound.getMessage(), "Message should be null when null is provided");
        assertNull(notFound.getCause(), "Cause should be null when null is provided");
    }

    @Test
    void givenNullCause_whenInstantiated_thenNoExceptionsThrown() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        notFound = new NotFound(cause);

        // THEN
        assertNull(notFound.getMessage(), "Message should be null when cause is null");
        assertNull(notFound.getCause(), "Cause should be null when cause is null");
    }

    @Test
    void givenEmptyMessage_whenInstantiated_thenMessageIsEmpty() {
        // GIVEN
        String message = "";

        // WHEN
        notFound = new NotFound(message);

        // THEN
        assertEquals(message, notFound.getMessage(), "Message should be empty when empty string is provided");
        assertNull(notFound.getCause(), "Cause should be null when only message is provided");
    }

    @Test
    void givenNullMessage_whenInstantiated_thenMessageIsNull() {
        // GIVEN
        String message = null;

        // WHEN
        notFound = new NotFound(message);

        // THEN
        assertNull(notFound.getMessage(), "Message should be null when null is provided");
        assertNull(notFound.getCause(), "Cause should be null when only message is provided");
    }
}
