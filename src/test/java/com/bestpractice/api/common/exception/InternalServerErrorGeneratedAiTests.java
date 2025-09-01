package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MockitoRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

@RunWith(MockitoRunner.class)
public class InternalServerErrorGeneratedAiTests {

    private InternalServerError internalServerError;

    @BeforeEach
    void setUp() {
        internalServerError = new InternalServerError();
    }

    @Test
    void testConstructorWithoutArguments() {
        // GIVEN: No arguments are provided to the constructor.
        // WHEN: The InternalServerError constructor is called.
        // THEN: A new InternalServerError object is created with a default message.
        assertNotNull(internalServerError);
        assertEquals("InternalServerError", internalServerError.getClass().getSimpleName());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The InternalServerError constructor is called with a message.
        String message = "Something went wrong!";
        internalServerError = new InternalServerError(message);
        assertEquals(message, internalServerError.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A throwable cause is provided to the constructor.
        // WHEN: The InternalServerError constructor is called with a cause.
        Throwable cause = new NullPointerException("NullPointerException occurred");
        internalServerError = new InternalServerError(cause);
        assertEquals(cause, internalServerError.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        String msg = "Error occurred";
        Throwable cause = new IllegalArgumentException("Invalid argument");
        internalServerError = new InternalServerError(msg, cause);
        assertEquals(msg, internalServerError.getMessage());
        assertEquals(cause, internalServerError.getCause());
    }

    @Test
    void testGetMessage() {
        // GIVEN: The InternalServerError object is created.
        // WHEN: The getMessage() method is called.
        // THEN: The message associated with the object is returned.
        String message = "Test Message";
        internalServerError = new InternalServerError(message);
        assertEquals(message, internalServerError.getMessage());
    }

    @Test
    void testGetCause() {
        // GIVEN: The InternalServerError object is created with a cause.
        // WHEN: The getCause() method is called.
        Throwable cause = new NullPointerException("NullPointerException");
        internalServerError = new InternalServerError(cause);
        assertEquals(cause, internalServerError.getCause());
    }
}
