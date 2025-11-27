package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void givenDefaultConstructor_whenInstantiated_thenExceptionMessageIsNull() {
        // GIVEN
        // No specific setup required

        // WHEN
        notFound = new NotFound();

        // THEN
        assertNull(notFound.getMessage(), "Message should be null for default constructor");
    }

    @Test
    void givenMessageConstructor_whenInstantiated_thenExceptionMessageMatches() {
        // GIVEN
        String expectedMessage = "Resource not found";

        // WHEN
        notFound = new NotFound(expectedMessage);

        // THEN
        assertEquals(expectedMessage, notFound.getMessage(), "Message should match the provided value");
    }

    @Test
    void givenCauseConstructor_whenInstantiated_thenCauseMatches() {
        // GIVEN
        Throwable expectedCause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(expectedCause);

        // THEN
        assertEquals(expectedCause, notFound.getCause(), "Cause should match the provided throwable");
    }

    @Test
    void givenMessageAndCauseConstructor_whenInstantiated_thenMessageAndCauseMatch() {
        // GIVEN
        String expectedMessage = "Resource not found";
        Throwable expectedCause = new IllegalArgumentException("Invalid argument");

        // WHEN
        notFound = new NotFound(expectedMessage, expectedCause);

        // THEN
        assertEquals(expectedMessage, notFound.getMessage(), "Message should match the provided value");
        assertEquals(expectedCause, notFound.getCause(), "Cause should match the provided throwable");
    }

    @Test
    void givenNullMessageAndCause_whenInstantiated_thenMessageAndCauseAreNull() {
        // GIVEN
        String expectedMessage = null;
        Throwable expectedCause = null;

        // WHEN
        notFound = new NotFound(expectedMessage, expectedCause);

        // THEN
        assertNull(notFound.getMessage(), "Message should be null when null is provided");
        assertNull(notFound.getCause(), "Cause should be null when null is provided");
    }

    @Test
    void givenNullCauseConstructor_whenInstantiated_thenCauseIsNull() {
        // GIVEN
        Throwable expectedCause = null;

        // WHEN
        notFound = new NotFound(expectedCause);

        // THEN
        assertNull(notFound.getCause(), "Cause should be null when null is provided");
    }

    @Test
    void givenEmptyMessageConstructor_whenInstantiated_thenMessageIsEmpty() {
        // GIVEN
        String expectedMessage = "";

        // WHEN
        notFound = new NotFound(expectedMessage);

        // THEN
        assertEquals(expectedMessage, notFound.getMessage(), "Message should be empty when empty string is provided");
    }
}
