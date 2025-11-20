package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN
        String message = "Access Denied";

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        forbidden = new Forbidden(cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals(cause.toString(), forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN
        String message = "Access Denied";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertNotNull(forbidden);
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
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenEmptyMessage_whenConstructorCalled_thenInstanceCreatedWithEmptyMessage() {
        // GIVEN
        String message = "";

        // WHEN
        forbidden = new Forbidden(message);

        // THEN
        assertNotNull(forbidden);
        assertEquals("", forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenEmptyMessageAndNullCause_whenConstructorCalled_thenInstanceCreatedWithEmptyMessageAndNullCause() {
        // GIVEN
        String message = "";
        Throwable cause = null;

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals("", forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenValidMessageAndCause_whenConstructorCalled_thenInstanceCreatedCorrectly() {
        // GIVEN
        String message = "Access Denied";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        forbidden = new Forbidden(message, cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenValidCause_whenConstructorCalled_thenInstanceCreatedCorrectlyWithCauseMessage() {
        // GIVEN
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN
        forbidden = new Forbidden(cause);

        // THEN
        assertNotNull(forbidden);
        assertEquals(cause.toString(), forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }
}
