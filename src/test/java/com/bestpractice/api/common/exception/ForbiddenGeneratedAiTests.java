package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ForbiddenGeneratedAiTests {

    private Forbidden forbidden;

    @BeforeEach
    void setUp() {
        forbidden = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenInstanceCreated() {
        // GIVEN
        // No arguments provided

        // WHEN
        forbidden = new Forbidden();

        // THEN
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN
        String message = "Access Denied";

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenCause_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN
        forbidden = new Forbidden(cause);

        // THEN
        assertEquals(cause.toString(), forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN
        String message = "Access Denied";
        Throwable cause = new RuntimeException("Underlying exception");

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        forbidden = new Forbidden(cause);

        // THEN
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }
}
