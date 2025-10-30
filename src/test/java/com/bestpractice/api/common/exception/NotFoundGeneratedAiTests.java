package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a new NotFound instance
        NotFound exception = new NotFound();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific message
        String message = "Resource not found";
        // WHEN: creating a new NotFound instance with the message
        NotFound exception = new NotFound(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: creating a new NotFound instance with the cause
        NotFound exception = new NotFound(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Resource missing";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: creating a new NotFound instance with both message and cause
        NotFound exception = new NotFound(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionWithMessage() {
        // GIVEN: a specific message
        String message = "Resource not found";
        // WHEN & THEN: assertThrows should catch the NotFound exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN & THEN: assertThrows should catch the NotFound exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
        assertEquals(cause.toString(), thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Error occurred";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: assertThrows should catch the NotFound exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: assertThrows should catch the NotFound exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
