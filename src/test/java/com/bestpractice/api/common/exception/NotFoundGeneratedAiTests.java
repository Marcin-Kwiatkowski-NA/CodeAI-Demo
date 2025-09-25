package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
        // GIVEN: no parameters
        // WHEN: creating a new NotFound instance using default constructor
        NotFound exception = new NotFound();
        // THEN: message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Resource not found";
        // WHEN: creating a new NotFound instance with the message
        NotFound exception = new NotFound(message);
        // THEN: message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: creating a new NotFound instance with the cause
        NotFound exception = new NotFound(cause);
        // THEN: cause should match and message should contain cause details
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Resource missing";
        Throwable cause = new NullPointerException("Null value");
        // WHEN: creating a new NotFound instance with both message and cause
        NotFound exception = new NotFound(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingNotFoundWithMessage() {
        // GIVEN: a specific error message
        String message = "Entity not found";
        // WHEN & THEN: throwing NotFound should result in correct exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingNotFoundWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: throwing NotFound should result in correct exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Underlying issue");
    }

    @Test
    void testThrowingNotFoundWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Data missing";
        Throwable cause = new RuntimeException("Database error");
        // WHEN & THEN: throwing NotFound should result in correct exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
