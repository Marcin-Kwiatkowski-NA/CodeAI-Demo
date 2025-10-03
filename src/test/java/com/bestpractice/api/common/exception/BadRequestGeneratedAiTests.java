package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any required state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN: creating BadRequest with default constructor
        BadRequest exception = new BadRequest();
        // THEN: message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Bad request occurred";
        // WHEN: creating BadRequest with message constructor
        BadRequest exception = new BadRequest(message);
        // THEN: message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: creating BadRequest with cause constructor
        BadRequest exception = new BadRequest(cause);
        // THEN: cause should match and message should contain cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains(cause.toString()));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Bad request with cause";
        Throwable cause = new NullPointerException("Null value");
        // WHEN: creating BadRequest with message and cause constructor
        BadRequest exception = new BadRequest(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingBadRequestUsingDefaultConstructor() {
        // GIVEN: no message or cause
        // WHEN & THEN: expect BadRequest to be thrown
        assertThrows(BadRequest.class, () -> {
            throw new BadRequest();
        });
    }

    @Test
    void testThrowingBadRequestWithMessage() {
        // GIVEN: a specific error message
        String message = "Bad request occurred";
        // WHEN & THEN: expect BadRequest to be thrown with correct message
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN & THEN: expect BadRequest to be thrown with correct cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Bad request with cause";
        Throwable cause = new NullPointerException("Null value");
        // WHEN & THEN: expect BadRequest to be thrown with correct message and cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
