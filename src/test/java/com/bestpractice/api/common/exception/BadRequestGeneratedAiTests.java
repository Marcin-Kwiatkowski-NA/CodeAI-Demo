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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no input parameters
        // WHEN: creating the exception with no arguments
        BadRequest exception = new BadRequest();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Bad request occurred";
        // WHEN: creating the exception with a message
        BadRequest exception = new BadRequest(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating the exception with a cause
        BadRequest exception = new BadRequest(cause);
        // THEN: the cause should match and message should contain cause description
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Root cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Bad request with cause";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: creating the exception with both message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingBadRequestUsingDefaultConstructor() {
        // GIVEN: no input parameters
        // WHEN & THEN: throwing BadRequest should be caught by assertThrows
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest();
        });
        assertThat(thrown).isInstanceOf(BadRequest.class);
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithMessage() {
        // GIVEN: a specific error message
        String message = "Bad request occurred";
        // WHEN & THEN: throwing BadRequest with message should be caught and message should match
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertThat(thrown).isInstanceOf(BadRequest.class);
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: throwing BadRequest with cause should be caught and cause should match
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });
        assertThat(thrown).isInstanceOf(BadRequest.class);
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Root cause"));
    }

    @Test
    void testThrowingBadRequestWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Bad request with cause";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: throwing BadRequest with message and cause should be caught and both should match
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertThat(thrown).isInstanceOf(BadRequest.class);
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
