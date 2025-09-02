package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.runners.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.common.exception.NotFound;

@org.junit.jupiter.api.extension.ExtendWith(JUnit4.class)
public class NotFoundGeneratedAiTests {

    private NotFound exception;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        exception = new NotFound();
    }

    @org.junit.jupiter.api.Test
    void testConstructorWithoutArguments() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A NotFound exception is created without a message.
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @org.junit.jupiter.api.Test
    void testConstructorWithMessage() {
        // GIVEN: A message is passed to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A NotFound exception is created with the provided message.
        exception = new NotFound("Resource not found");
        assertEquals("Resource not found", exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    void testConstructorWithThrowable() {
        // GIVEN: A throwable is passed to the constructor.
        // WHEN: The constructor is called with a throwable.
        // THEN: A NotFound exception is created with the provided throwable.
        exception = new NotFound(new NullPointerException());
        assertEquals(NullPointerException.class, exception.getCause().getClass());
    }

    @org.junit.jupiter.api.Test
    void testConstructorWithMessageAndThrowable() {
        // GIVEN: A message and a throwable are passed to the constructor.
        // WHEN: The constructor is called with a message and a throwable.
        // THEN: A NotFound exception is created with the provided message and throwable.
        exception = new NotFound("Error occurred", new IllegalArgumentException());
        assertEquals("Error occurred", exception.getMessage());
        assertEquals(IllegalArgumentException.class, exception.getCause().getClass());
    }
}
