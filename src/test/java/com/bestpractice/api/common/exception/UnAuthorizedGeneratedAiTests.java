package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset any state needed before each test.  No specific reset needed for this class.
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is thrown with no message.
        UnAuthorized exception = new UnAuthorized();
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is thrown with the provided message.
        UnAuthorized exception = new UnAuthorized("Unauthorized access");
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("Unauthorized access", exception.getMessage());
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is thrown with the provided Throwable.
        Throwable cause = new Exception("Some cause");
        UnAuthorized exception = new UnAuthorized(cause);
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertSame(cause, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a Throwable object are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is thrown with the provided message and Throwable.
        Throwable cause = new Exception("Another cause");
        UnAuthorized exception = new UnAuthorized("Error message", cause);
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("Error message", exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
