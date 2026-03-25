package com.bestpractice.api.common.exception;

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
import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
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
        // GIVEN: A throwable cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating NotFound with cause
        NotFound exception = new NotFound(cause);
        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Data missing";
        Throwable cause = new NullPointerException("Null value");
        // WHEN: Creating NotFound with message and cause
        NotFound exception = new NotFound(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingNotFoundException() {
        // GIVEN: A message for the exception
        String message = "Entity not found";
        // WHEN & THEN: Verify that NotFound can be thrown and caught properly
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        // THEN: Verify message correctness
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingNotFoundExceptionWithCause() {
        // GIVEN: A message and cause for the exception
        String message = "Record missing";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: Verify that NotFound can be thrown and caught properly with cause
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        // THEN: Verify message and cause correctness
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
