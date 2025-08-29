package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.runners.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(JUnit4.class)
public class NotFoundGeneratedAiTests {

    private NotFound exception;

    @BeforeEach
    void setUp() {
        exception = new NotFound();
    }

    @Test
    void testConstructorWithoutArguments() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A NotFound exception is created with no message.
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A message is passed to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A NotFound exception is created with the specified message.
        exception = new NotFound("Resource not found");
        assertEquals("Resource not found", exception.getMessage());
        assertEquals("Resource not found", exception.getMessage());
    }

    @Test
    void testConstructorWithThrowable() {
        // GIVEN: A Throwable object is passed to the constructor.
        // WHEN: The constructor is called with a Throwable object.
        // THEN: A NotFound exception is created with the Throwable's message.
        exception = new NotFound(new NullPointerException());
        assertEquals("NullPointerException", exception.getMessage());
        assertEquals("NullPointerException", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndThrowable() {
        // GIVEN: A message and a Throwable object are passed to the constructor.
        // WHEN: The constructor is called with a message and a Throwable object.
        // THEN: A NotFound exception is created with the message and the Throwable's message.
        exception = new NotFound("Resource not found", new IllegalArgumentException());
        assertEquals("Resource not found", exception.getMessage());
        assertEquals("Resource not found", exception.getMessage());
    }
}
