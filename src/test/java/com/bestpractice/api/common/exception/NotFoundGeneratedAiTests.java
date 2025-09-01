package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

@JUnit4
public class NotFoundGeneratedAiTests {

    private NotFound notFound;

    @BeforeEach
    void setUp() {
        notFound = new NotFound();
    }

    @Test
    void testConstructorWithoutArguments() {
        // GIVEN: A new NotFound object is created without any arguments.
        // WHEN: The constructor is called.
        // THEN: The NotFound object is created with no message.
        assertNotNull(notFound);
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A new NotFound object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The NotFound object is created with the specified message.
        String message = "Resource not found";
        notFound = new NotFound(message);
        assertEquals(message, notFound.getMessage());
    }

    @Test
    void testConstructorWithThrowable() {
        // GIVEN: A new NotFound object is created with a Throwable object.
        // WHEN: The constructor is called with a Throwable object.
        Throwable cause = new NullPointerException("Simulated cause");
        notFound = new NotFound(cause);
        assertSame(cause, notFound.getCause());
    }

    @Test
    void testConstructorWithMessageAndThrowable() {
        // GIVEN: A new NotFound object is created with a message and a Throwable object.
        // WHEN: The constructor is called with a message and a Throwable object.
        String message = "Error occurred";
        Throwable cause = new NullPointerException("Simulated cause");
        notFound = new NotFound(message, cause);
        assertEquals(message, notFound.getMessage());
        assertSame(cause, notFound.getCause());
    }
}
