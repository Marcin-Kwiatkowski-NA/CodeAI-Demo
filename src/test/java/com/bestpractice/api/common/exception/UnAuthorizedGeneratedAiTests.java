package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UnAuthorizedGeneratedAiTests {

    private UnAuthorized unAuthorized;

    @BeforeEach
    void setUp() {
        unAuthorized = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenInstanceCreated() {
        // GIVEN
        // No arguments provided

        // WHEN
        unAuthorized = new UnAuthorized();

        // THEN
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        unAuthorized = new UnAuthorized(message);

        // THEN
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        unAuthorized = new UnAuthorized(cause);

        // THEN
        assertNotNull(unAuthorized);
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        unAuthorized = new UnAuthorized(message, cause);

        // THEN
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN
        String message = null;

        // WHEN
        unAuthorized = new UnAuthorized(message);

        // THEN
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        unAuthorized = new UnAuthorized(cause);

        // THEN
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        unAuthorized = new UnAuthorized(message, cause);

        // THEN
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }
}
