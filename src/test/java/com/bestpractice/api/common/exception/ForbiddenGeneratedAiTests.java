package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No setup needed for this simple exception class
    }

    @Test
    void constructor_noArgs() {
        Forbidden exception = new Forbidden();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        Forbidden exception = new Forbidden("Access denied");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Access denied", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        String message = "Something went wrong";
        Throwable cause = new RuntimeException("Underlying error");
        Forbidden exception = new Forbidden(message, cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        String message = "Failed to authenticate";
        Throwable cause = new RuntimeException("Invalid credentials");
        Forbidden exception = new Forbidden(message, cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
