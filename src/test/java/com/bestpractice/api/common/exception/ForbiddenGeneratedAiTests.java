package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        // THEN: Exception should contain the provided message and no cause
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a new Forbidden instance with cause
        Forbidden exception = new Forbidden(cause);
        // THEN: Exception should contain the provided cause and message should include cause details
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", exception.getMessage());
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
        // GIVEN: A scenario where Forbidden exception is expected
        String message = "Access forbidden";
        // WHEN & THEN: Throwing and catching the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingForbiddenExceptionWithCause() {
        // GIVEN: A scenario where Forbidden exception with cause is expected
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN & THEN: Throwing and catching the Forbidden exception with cause
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden("Forbidden due to state", cause);
        });
        assertEquals("Forbidden due to state", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
