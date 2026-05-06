package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating NotFound with cause
        NotFound exception = new NotFound(cause);
        // THEN: Exception should contain the provided cause and message should match cause.toString()
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
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
        // WHEN & THEN: Verify that NotFound is thrown correctly
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionWithCause() {
        // GIVEN: A message and cause for the exception
        String message = "Missing data";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: Verify that NotFound is thrown correctly with cause
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingNotFoundExceptionWithOnlyCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalStateException("Illegal state");
        // WHEN & THEN: Verify that NotFound is thrown correctly with only cause
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }
}
