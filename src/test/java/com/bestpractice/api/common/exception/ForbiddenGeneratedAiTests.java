package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a Forbidden instance using default constructor
        Forbidden exception = new Forbidden();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Access denied";
        // WHEN: creating a Forbidden instance with message
        Forbidden exception = new Forbidden(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a Forbidden instance with cause
        Forbidden exception = new Forbidden(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Access denied with cause";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a Forbidden instance with message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingForbiddenExceptionWithMessage() {
        // GIVEN: a specific error message
        String message = "Access denied";
        // WHEN & THEN: assertThrows should capture the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        // THEN: verify message and cause
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
        // THEN: verify cause and message
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Underlying cause"));
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
        // THEN: verify message and cause
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
