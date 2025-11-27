package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

class UnAuthorizedGeneratedAiTests {

    private UnAuthorized unAuthorized;

    @BeforeEach
    void setUp() {
        unAuthorized = null; // Reset state before each test
    }

    @Test
    void givenNoArguments_whenConstructorCalled_thenExceptionMessageIsNull() {
        // GIVEN: No arguments provided

        // WHEN: UnAuthorized exception is instantiated
        unAuthorized = new UnAuthorized();

        // THEN: The exception message should be null
        assertNull(unAuthorized.getMessage());
    }

    @Test
    void givenMessageArgument_whenConstructorCalled_thenExceptionMessageMatches() {
        // GIVEN: A specific message
        String message = "Unauthorized access";

        // WHEN: UnAuthorized exception is instantiated with the message
        unAuthorized = new UnAuthorized(message);

        // THEN: The exception message should match the provided message
        assertEquals(message, unAuthorized.getMessage());
    }

    @Test
    void givenCauseArgument_whenConstructorCalled_thenCauseMatches() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: UnAuthorized exception is instantiated with the cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: The cause should match the provided cause
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenMessageAndCauseArguments_whenConstructorCalled_thenMessageAndCauseMatch() {
        // GIVEN: A specific message and cause
        String message = "Unauthorized access";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: UnAuthorized exception is instantiated with the message and cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: The exception message and cause should match the provided values
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
    }

    @Test
    void givenNullMessage_whenConstructorCalled_thenExceptionMessageIsNull() {
        // GIVEN: A null message
        String message = null;

        // WHEN: UnAuthorized exception is instantiated with the null message
        unAuthorized = new UnAuthorized(message);

        // THEN: The exception message should be null
        assertNull(unAuthorized.getMessage());
    }

    @Test
    void givenNullCause_whenConstructorCalled_thenCauseIsNull() {
        // GIVEN: A null cause
        Throwable cause = null;

        // WHEN: UnAuthorized exception is instantiated with the null cause
        unAuthorized = new UnAuthorized(cause);

        // THEN: The cause should be null
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenNullMessageAndCause_whenConstructorCalled_thenMessageAndCauseAreNull() {
        // GIVEN: Null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: UnAuthorized exception is instantiated with null message and cause
        unAuthorized = new UnAuthorized(message, cause);

        // THEN: The exception message and cause should be null
        assertNull(unAuthorized.getMessage());
        assertNull(unAuthorized.getCause());
    }

    @Test
    void givenValidCause_whenExceptionThrown_thenExceptionContainsCause() {
        // GIVEN: A valid cause
        Throwable cause = new RuntimeException("Valid cause");

        // WHEN: UnAuthorized exception is thrown with the cause
        Throwable thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });

        // THEN: The thrown exception should contain the cause
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenValidMessage_whenExceptionThrown_thenExceptionContainsMessage() {
        // GIVEN: A valid message
        String message = "Valid message";

        // WHEN: UnAuthorized exception is thrown with the message
        Throwable thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });

        // THEN: The thrown exception should contain the message
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void givenValidMessageAndCause_whenExceptionThrown_thenExceptionContainsMessageAndCause() {
        // GIVEN: A valid message and cause
        String message = "Valid message";
        Throwable cause = new RuntimeException("Valid cause");

        // WHEN: UnAuthorized exception is thrown with the message and cause
        Throwable thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });

        // THEN: The thrown exception should contain the message and cause
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
