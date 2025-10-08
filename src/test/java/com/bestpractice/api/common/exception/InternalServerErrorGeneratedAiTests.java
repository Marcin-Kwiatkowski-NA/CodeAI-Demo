package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No parameters
        // WHEN: Creating an instance using the default constructor
        InternalServerError error = new InternalServerError();
        // THEN: The instance should not be null, message should be null, and cause should be null
        assertNotNull(error);
        assertNull(error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Internal server error occurred";
        // WHEN: Creating an instance using the constructor with message
        InternalServerError error = new InternalServerError(message);
        // THEN: The instance should contain the provided message and no cause
        assertNotNull(error);
        assertEquals(message, error.getMessage());
        assertNull(error.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause exception
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: Creating an instance using the constructor with cause
        InternalServerError error = new InternalServerError(cause);
        // THEN: The instance should contain the provided cause and message should match cause.toString()
        assertNotNull(error);
        assertEquals(cause, error.getCause());
        assertEquals(cause.toString(), error.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific error message and cause
        String message = "Internal server error with cause";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: Creating an instance using the constructor with message and cause
        InternalServerError error = new InternalServerError(message, cause);
        // THEN: The instance should contain both the provided message and cause
        assertNotNull(error);
        assertEquals(message, error.getMessage());
        assertEquals(cause, error.getCause());
    }

    @Test
    void testThrowingInternalServerErrorWithMessage() {
        // GIVEN: A specific error message
        String message = "Internal server error occurred";
        // WHEN & THEN: Throwing the exception should be caught by assertThrows and message should match
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingInternalServerErrorWithCause() {
        // GIVEN: A specific cause exception
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: Throwing the exception should be caught by assertThrows and cause should match
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void testThrowingInternalServerErrorWithMessageAndCause() {
        // GIVEN: A specific error message and cause
        String message = "Internal server error with cause";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: Throwing the exception should be caught by assertThrows and both message and cause should match
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> {
            throw new InternalServerError(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
