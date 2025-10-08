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
    void testThrowingBadRequestExceptionWithMessage() {
        // GIVEN: a message string
        String message = "Bad request occurred";

        // WHEN & THEN: assertThrows to verify exception is thrown
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingBadRequestExceptionWithCause() {
        // GIVEN: a cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: assertThrows to verify exception is thrown with cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Invalid argument"));
    }

    @Test
    void testThrowingBadRequestExceptionWithMessageAndCause() {
        // GIVEN: a message and a cause
        String message = "Bad request with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN & THEN: assertThrows to verify exception is thrown with message and cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingBadRequestExceptionWithoutMessageOrCause() {
        // GIVEN: no parameters
        // WHEN & THEN: assertThrows to verify exception is thrown without message or cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testMessageIntegrityAfterThrowing() {
        // GIVEN: a message string
        String message = "Integrity check";

        // WHEN: throwing and catching BadRequest
        try {
            throw new BadRequest(message);
        } catch (BadRequest e) {
            // THEN: verify message integrity
            assertEquals(message, e.getMessage());
            assertNull(e.getCause());
        }
    }

    @Test
    void testCauseIntegrityAfterThrowing() {
        // GIVEN: a cause
        Throwable cause = new IllegalStateException("State issue");

        // WHEN: throwing and catching BadRequest
        try {
            throw new BadRequest(cause);
        } catch (BadRequest e) {
            // THEN: verify cause integrity
            assertEquals(cause, e.getCause());
            assertTrue(e.getMessage().contains("State issue"));
        }
    }
}
