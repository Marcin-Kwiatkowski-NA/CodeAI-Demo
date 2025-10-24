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
        // GIVEN: No message or cause
        // WHEN: Creating NotFound using default constructor
        NotFound exception = new NotFound();

        // THEN: Message and cause should be null
        assertNull(exception.getMessage(), "Message should be null for default constructor");
        assertNull(exception.getCause(), "Cause should be null for default constructor");
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Resource not found";

        // WHEN: Creating NotFound with the message
        NotFound exception = new NotFound(message);

        // THEN: The message should match and cause should be null
        assertEquals(message, exception.getMessage(), "Message should match the provided one");
        assertNull(exception.getCause(), "Cause should be null when only message is provided");
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating NotFound with the cause
        NotFound exception = new NotFound(cause);

        // THEN: The cause should match and message should contain cause details
        assertEquals(cause, exception.getCause(), "Cause should match the provided one");
        assertTrue(exception.getMessage().contains("Invalid argument"), "Message should contain cause details");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Resource missing";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Creating NotFound with both message and cause
        NotFound exception = new NotFound(message, cause);

        // THEN: Both message and cause should match
        assertEquals(message, exception.getMessage(), "Message should match the provided one");
        assertEquals(cause, exception.getCause(), "Cause should match the provided one");
    }

    @Test
    void testThrowingNotFoundExceptionWithMessage() {
        // GIVEN: A specific error message
        String message = "Resource not found";

        // WHEN & THEN: Throwing NotFound should be caught by assertThrows
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        }, "Expected NotFound to be thrown");
        assertEquals(message, thrown.getMessage(), "Thrown exception message should match");
        assertNull(thrown.getCause(), "Thrown exception cause should be null");
    }

    @Test
    void testThrowingNotFoundExceptionWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN: Throwing NotFound should be caught by assertThrows
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        }, "Expected NotFound to be thrown");
        assertEquals(cause, thrown.getCause(), "Thrown exception cause should match");
        assertTrue(thrown.getMessage().contains("Illegal state"), "Thrown exception message should contain cause details");
    }

    @Test
    void testThrowingNotFoundExceptionWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Resource missing";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN: Throwing NotFound should be caught by assertThrows
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        }, "Expected NotFound to be thrown");
        assertEquals(message, thrown.getMessage(), "Thrown exception message should match");
        assertEquals(cause, thrown.getCause(), "Thrown exception cause should match");
    }
}
