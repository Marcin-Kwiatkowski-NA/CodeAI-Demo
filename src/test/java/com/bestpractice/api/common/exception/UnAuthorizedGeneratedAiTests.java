package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating an UnAuthorized instance with no arguments
        UnAuthorized exception = new UnAuthorized();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Unauthorized access";
        // WHEN: creating an UnAuthorized instance with a message
        UnAuthorized exception = new UnAuthorized(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating an UnAuthorized instance with a cause
        UnAuthorized exception = new UnAuthorized(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Root cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Unauthorized due to invalid token";
        Throwable cause = new RuntimeException("Token expired");
        // WHEN: creating an UnAuthorized instance with both message and cause
        UnAuthorized exception = new UnAuthorized(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedException() {
        // GIVEN: a specific message
        String message = "Unauthorized operation";
        // WHEN & THEN: assert that throwing the exception works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithCause() {
        // GIVEN: a specific message and cause
        String message = "Unauthorized operation with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN & THEN: assert that throwing the exception with cause works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithOnlyCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN & THEN: assert that throwing the exception with only cause works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Illegal state"));
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithoutMessageOrCause() {
        // GIVEN: no message or cause
        // WHEN & THEN: assert that throwing the exception with default constructor works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
