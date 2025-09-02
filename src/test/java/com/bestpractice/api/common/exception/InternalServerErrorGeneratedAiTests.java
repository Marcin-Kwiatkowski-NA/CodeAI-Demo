package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InternalServerErrorGeneratedAiTests {

    private InternalServerError internalServerError;

    @BeforeEach
    void setUp() {
        internalServerError = new InternalServerError();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The InternalServerError constructor is called.
        // THEN: A new InternalServerError object is created, inheriting from RuntimeException.
        assertNotNull(internalServerError);
        assertInstanceOf(RuntimeException.class, internalServerError);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The InternalServerError constructor is called with a message.
        String message = "An unexpected error occurred.";
        internalServerError = new InternalServerError(message);
        assertEquals(message, internalServerError.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A Throwable cause is provided to the constructor.
        Throwable cause = new NullPointerException("Something went wrong");
        internalServerError = new InternalServerError(cause);
        assertSame(cause, internalServerError.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a Throwable cause are provided to the constructor.
        String message = "Internal Server Error";
        Throwable cause = new IllegalArgumentException("Invalid input");
        internalServerError = new InternalServerError(message, cause);
        assertEquals(message, internalServerError.getMessage());
        assertSame(cause, internalServerError.getCause());
    }
}
