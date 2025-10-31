package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no parameters
        // WHEN: creating BadRequest using default constructor
        BadRequest badRequest = new BadRequest();

        // THEN: verify message and cause are null
        assertNull(badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a message string
        String message = "Invalid request";

        // WHEN: creating BadRequest with message
        BadRequest badRequest = new BadRequest(message);

        // THEN: verify message is set and cause is null
        assertEquals(message, badRequest.getMessage());
        assertNull(badRequest.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a throwable cause
        Throwable cause = new IllegalArgumentException("Cause");

        // WHEN: creating BadRequest with cause
        BadRequest badRequest = new BadRequest(cause);

        // THEN: verify cause is set and message matches cause.toString()
        assertEquals(cause, badRequest.getCause());
        assertTrue(badRequest.getMessage().contains("Cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a message and a throwable cause
        String message = "Invalid request with cause";
        Throwable cause = new IllegalArgumentException("Cause");

        // WHEN: creating BadRequest with message and cause
        BadRequest badRequest = new BadRequest(message, cause);

        // THEN: verify both message and cause are set
        assertEquals(message, badRequest.getMessage());
        assertEquals(cause, badRequest.getCause());
    }

    @Test
    void testThrowingBadRequestWithMessage() {
        // GIVEN: a message string
        String message = "Bad request occurred";

        // WHEN & THEN: assert that throwing BadRequest with message is caught
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        // THEN: verify exception details
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: a throwable cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: assert that throwing BadRequest with cause is caught
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });

        // THEN: verify exception details
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Invalid argument"));
    }

    @Test
    void testThrowingBadRequestWithMessageAndCause() {
        // GIVEN: a message and a throwable cause
        String message = "Bad request with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: assert that throwing BadRequest with message and cause is caught
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });

        // THEN: verify exception details
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithoutMessageOrCause() {
        // GIVEN: no parameters
        // WHEN & THEN: assert that throwing BadRequest with default constructor is caught
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest();
        });

        // THEN: verify exception details
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
