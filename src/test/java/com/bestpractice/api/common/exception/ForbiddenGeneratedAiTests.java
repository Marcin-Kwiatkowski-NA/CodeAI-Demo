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

class ForbiddenGeneratedAiTests {

    private Forbidden forbidden;

    @BeforeEach
    void setUp() {
        forbidden = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenInstanceCreated() {
        // GIVEN: No arguments provided

        // WHEN: Constructor is called
        forbidden = new Forbidden();

        // THEN: Verify the instance is created and has no message or cause
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenMessage_whenConstructorCalled_thenInstanceCreatedWithMessage() {
        // GIVEN: A message is provided
        String message = "Access Denied";

        // WHEN: Constructor is called with the message
        forbidden = new Forbidden(message);

        // THEN: Verify the instance is created with the correct message
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenCause_whenConstructorCalled_thenInstanceCreatedWithCause() {
        // GIVEN: A cause is provided
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Constructor is called with the cause
        forbidden = new Forbidden(cause);

        // THEN: Verify the instance is created with the correct cause
        assertNotNull(forbidden);
        assertEquals(cause.toString(), forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithMessageAndCause() {
        // GIVEN: A message and cause are provided
        String message = "Access Denied";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Constructor is called with the message and cause
        forbidden = new Forbidden(message, cause);

        // THEN: Verify the instance is created with the correct message and cause
        assertNotNull(forbidden);
        assertEquals(message, forbidden.getMessage());
        assertEquals(cause, forbidden.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenInstanceCreatedWithNullMessage() {
        // GIVEN: A null message is provided

        // WHEN: Constructor is called with null message
        forbidden = new Forbidden((String) null);

        // THEN: Verify the instance is created with null message
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenInstanceCreatedWithNullCause() {
        // GIVEN: A null cause is provided

        // WHEN: Constructor is called with null cause
        forbidden = new Forbidden((Throwable) null);

        // THEN: Verify the instance is created with null cause
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenInstanceCreatedWithNullMessageAndCause() {
        // GIVEN: Null message and cause are provided

        // WHEN: Constructor is called with null message and cause
        forbidden = new Forbidden(null, null);

        // THEN: Verify the instance is created with null message and cause
        assertNotNull(forbidden);
        assertNull(forbidden.getMessage());
        assertNull(forbidden.getCause());
    }
}
