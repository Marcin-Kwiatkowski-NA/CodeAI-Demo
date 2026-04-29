package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided
        // WHEN: Creating NotFound using default constructor
        NotFound exception = new NotFound();
        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Resource not found";
        // WHEN: Creating NotFound with message
        NotFound exception = new NotFound(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating NotFound with cause
        NotFound exception = new NotFound(cause);
        // THEN: Exception should contain the provided cause and message derived from cause
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Entity missing";
        Throwable cause = new NullPointerException("Null reference");
        // WHEN: Creating NotFound with message and cause
        NotFound exception = new NotFound(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingNotFoundException() {
        // GIVEN: A message for the exception
        String message = "Item not found";
        // WHEN & THEN: Expect NotFound to be thrown
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        // THEN: Verify thrown exception message
        assertEquals(message, thrown.getMessage());
    }
}
