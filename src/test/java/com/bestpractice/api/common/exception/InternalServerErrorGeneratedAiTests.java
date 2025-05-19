package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.extension.ExtendWith(MyExtension.class)
class InternalServerErrorGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test.  No specific reset needed for this simple class.
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        InternalServerError exception = new InternalServerError();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        InternalServerError exception = new InternalServerError("Something went wrong!");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Something went wrong!", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause Throwable object is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the provided cause.
        Throwable cause = new Throwable("Detailed error information");
        InternalServerError exception = new InternalServerError(cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause Throwable object are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        Throwable cause = new Throwable("Detailed error information");
        InternalServerError exception = new InternalServerError("Something went wrong!", cause);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Something went wrong!", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}

class MyExtension implements Extension {}
