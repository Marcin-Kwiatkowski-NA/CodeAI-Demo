package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause
        // WHEN: Creating the exception using the default constructor
        NotFound exception = new NotFound();

        // THEN: The exception should have no message and no cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Resource not found";

        // WHEN: Creating the exception with the message constructor
        NotFound exception = new NotFound(message);

        // THEN: The exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating the exception with the cause constructor
        NotFound exception = new NotFound(cause);

        // THEN: The exception should have the provided cause and its message should match the cause's toString
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains(cause.toString()));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Resource missing";
        Throwable cause = new NullPointerException("Null value");

        // WHEN: Creating the exception with both message and cause
        NotFound exception = new NotFound(message, cause);

        // THEN: The exception should contain both the provided message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingDefaultConstructorException() {
        // GIVEN: No message or cause
        // WHEN & THEN: Throwing the exception should be caught by assertThrows
        assertThrows(NotFound.class, () -> {
            throw new NotFound();
        });
    }

    @Test
    void testThrowingMessageConstructorException() {
        // GIVEN: A specific error message
        String message = "Resource not found";

        // WHEN & THEN: Throwing the exception should be caught by assertThrows and message should match
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingCauseConstructorException() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: Throwing the exception should be caught by assertThrows and cause should match
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingMessageAndCauseConstructorException() {
        // GIVEN: A specific message and cause
        String message = "Resource missing";
        Throwable cause = new NullPointerException("Null value");

        // WHEN & THEN: Throwing the exception should be caught by assertThrows and both message and cause should match
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
