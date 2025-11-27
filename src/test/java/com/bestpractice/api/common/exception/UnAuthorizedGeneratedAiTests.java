package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        unAuthorized = new UnAuthorized();

        // THEN: Verify instance is created
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN: A message argument
        String message = "Unauthorized access";

        // WHEN: Constructor is called with the message
        unAuthorized = new UnAuthorized(message);

        // THEN: Verify instance is created with the message
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN: A cause argument
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Constructor is called with the cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Verify instance is created with the cause
        assertNotNull(unAuthorized);
        assertEquals(cause.toString(), unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN: A message and a cause argument
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Constructor is called with the message and cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Verify instance is created with the message and cause
        assertNotNull(unAuthorized);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN: A null message
        String message = null;

        // WHEN: Constructor is called with the null message
        unAuthorized = new UnAuthorized(message);

        // THEN: Verify instance is created with a null message
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: Constructor is called with the null cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: Verify instance is created with a null cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN: A null message and a null cause
        String message = null;
        Throwable cause = null;

        // WHEN: Constructor is called with the null message and null cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: Verify instance is created with a null message and null cause
        assertNotNull(unAuthorized);
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }
}
