package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BadRequestGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        BadRequest exception = new BadRequest();
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the provided message.
        // THEN: A RuntimeException is created with the message.
        BadRequest exception = new BadRequest("Invalid request");
        assertNotNull(exception);
        assertEquals("Invalid request", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The constructor is called with the Throwable object.
        // THEN: A RuntimeException is created with the Throwable object as the cause.
        Throwable cause = new RuntimeException("Something went wrong");
        BadRequest exception = new BadRequest(cause);
        assertNotNull(exception);
        assertSame(cause, exception.getCause());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        // WHEN: The constructor is called with the message and the Throwable object.
        // THEN: A RuntimeException is created with the message and the Throwable object as the cause.
        Throwable cause = new RuntimeException("Detailed error");
        BadRequest exception = new BadRequest("Bad data", cause);
        assertNotNull(exception);
        assertEquals("Bad data", exception.getMessage());
        assertSame(cause, exception.getCause());
        assertEquals(RuntimeException.class, exception.getClass());
    }
}
