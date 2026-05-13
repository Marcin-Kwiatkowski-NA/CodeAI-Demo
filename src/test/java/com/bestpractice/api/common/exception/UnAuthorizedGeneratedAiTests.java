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
import org.assertj.core.api.Assertions;

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided
        // WHEN: Creating UnAuthorized using default constructor
        UnAuthorized exception = new UnAuthorized();
        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Unauthorized access";
        // WHEN: Creating UnAuthorized with message
        UnAuthorized exception = new UnAuthorized(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating UnAuthorized with cause
        UnAuthorized exception = new UnAuthorized(cause);
        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        Assertions.assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Access denied";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating UnAuthorized with message and cause
        UnAuthorized exception = new UnAuthorized(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedException() {
        // GIVEN: A message for the exception
        String message = "Unauthorized operation";
        // WHEN & THEN: Verify that UnAuthorized can be thrown and caught properly
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalStateException("Invalid state");
        // WHEN & THEN: Verify that UnAuthorized with cause can be thrown and caught properly
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        Assertions.assertThat(thrown.getMessage()).contains("Invalid state");
    }
}
