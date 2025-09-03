package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called without arguments.
        // THEN: A RuntimeException is created with no message.
        UnAuthorized exception = new UnAuthorized();
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A RuntimeException is created with the provided message.
        UnAuthorized exception = new UnAuthorized("Invalid credentials");
        assertNotNull(exception);
        assertEquals("Invalid credentials", exception.getMessage());
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    void constructor_withThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The constructor is called with a Throwable object.
        // THEN: A RuntimeException is created with the Throwable object as the cause.
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assertNotNull(exception);
        assertEquals(RuntimeException.class, exception.getClass());
        assertSame(NullPointerException.class, exception.getCause());
    }

    @Test
    void constructor_withMsgAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        // WHEN: The constructor is called with a message and a Throwable object.
        // THEN: A RuntimeException is created with the provided message and the Throwable object as the cause.
        UnAuthorized exception = new UnAuthorized("Authentication failed", new IllegalArgumentException());
        assertNotNull(exception);
        assertEquals("Authentication failed", exception.getMessage());
        assertSame(IllegalArgumentException.class, exception.getCause());
        assertEquals(RuntimeException.class, exception.getClass());
    }
}
