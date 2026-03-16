package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided
        // WHEN: Creating a new Forbidden instance using default constructor
        Forbidden exception = new Forbidden();
        // THEN: Exception should be created with null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Access denied";
        // WHEN: Creating a new Forbidden instance with message
        Forbidden exception = new Forbidden(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a new Forbidden instance with cause
        Forbidden exception = new Forbidden(cause);
        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Forbidden operation";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating a new Forbidden instance with message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingForbiddenException() {
        // GIVEN: A scenario where Forbidden exception should be thrown
        String message = "Access restricted";
        // WHEN & THEN: Verify that Forbidden exception is thrown correctly
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingForbiddenExceptionWithCause() {
        // GIVEN: A scenario where Forbidden exception should be thrown with a cause
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN & THEN: Verify that Forbidden exception is thrown correctly with cause
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden("Forbidden due to illegal state", cause);
        });
        assertEquals("Forbidden due to illegal state", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
