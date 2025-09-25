package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no arguments
        // WHEN: creating a Forbidden instance using the default constructor
        Forbidden exception = new Forbidden();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Access denied";
        // WHEN: creating a Forbidden instance with the message
        Forbidden exception = new Forbidden(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a Forbidden instance with the cause
        Forbidden exception = new Forbidden(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Access denied with cause";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a Forbidden instance with both message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingForbiddenExceptionNoArgs() {
        // GIVEN: no arguments
        // WHEN & THEN: assertThrows should capture the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingForbiddenExceptionWithMessage() {
        // GIVEN: a specific message
        String message = "Access denied";
        // WHEN & THEN: assertThrows should capture the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingForbiddenExceptionWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: assertThrows should capture the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        assertEquals(cause.toString(), thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingForbiddenExceptionWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Access denied with cause";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: assertThrows should capture the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
