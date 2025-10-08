package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN: creating a NotFound instance with default constructor
        NotFound exception = new NotFound();
        // THEN: message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Resource not found";
        // WHEN: creating a NotFound instance with the message
        NotFound exception = new NotFound(message);
        // THEN: message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: creating a NotFound instance with the cause
        NotFound exception = new NotFound(cause);
        // THEN: cause should match and message should contain cause description
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Invalid argument"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Data missing";
        Throwable cause = new NullPointerException("Null value");
        // WHEN: creating a NotFound instance with both message and cause
        NotFound exception = new NotFound(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingDefaultConstructorException() {
        // GIVEN: no message or cause
        // WHEN & THEN: assertThrows should catch the NotFound exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionWithMessage() {
        // GIVEN: a specific error message
        String message = "Entity not found";
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
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: assertThrows should catch the NotFound exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Underlying issue"));
    }

    @Test
    void testThrowingNotFoundExceptionWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Missing record";
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN & THEN: assertThrows should catch the NotFound exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
