package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a BadRequest instance created with the default constructor
        BadRequest badRequest;

        // WHEN: instantiating BadRequest without arguments
        badRequest = new BadRequest();

        // THEN: the message and cause should be null
        assertNull(badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Invalid request";

        // WHEN: instantiating BadRequest with a message
        BadRequest badRequest = new BadRequest(message);

        // THEN: the message should match and cause should be null
        assertEquals(message, badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalArgumentException("Cause");

        // WHEN: instantiating BadRequest with a cause
        BadRequest badRequest = new BadRequest(cause);

        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, badRequest.getCause());
        assertTrue(badRequest.getMessage().contains("Cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Invalid request with cause";
        Throwable cause = new IllegalArgumentException("Cause");

        // WHEN: instantiating BadRequest with both message and cause
        BadRequest badRequest = new BadRequest(message, cause);

        // THEN: both message and cause should match
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void testThrowingBadRequestWithMessage() {
        // GIVEN: a specific error message
        String message = "Bad request occurred";

        // WHEN & THEN: throwing BadRequest with message should result in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: throwing BadRequest with cause should result in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Invalid argument"));
    }

    @Test
    void testThrowingBadRequestWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Bad request with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: throwing BadRequest with message and cause should result in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithoutMessageOrCause() {
        // GIVEN: no message or cause
        // WHEN & THEN: throwing BadRequest with default constructor should result in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
