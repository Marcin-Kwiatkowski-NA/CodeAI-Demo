package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
        // WHEN: Creating a new NotFound instance using default constructor
        NotFound exception = new NotFound();
        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Resource not found";
        // WHEN: Creating a new NotFound instance with message
        NotFound exception = new NotFound(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause (Throwable)
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a new NotFound instance with cause
        NotFound exception = new NotFound(cause);
        // THEN: Exception should contain the provided cause and message should include cause details
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Data missing";
        Throwable cause = new NullPointerException("Null value");
        // WHEN: Creating a new NotFound instance with message and cause
        NotFound exception = new NotFound(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingNotFoundException() {
        // GIVEN: A message for the exception
        String message = "Entity not found";
        // WHEN & THEN: Ensure that throwing NotFound triggers the expected exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingNotFoundExceptionWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: Ensure that throwing NotFound with cause triggers the expected exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Error occurred", cause);
        });
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
