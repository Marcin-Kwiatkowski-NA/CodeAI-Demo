package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnAuthorizedGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor
        // WHEN: The constructor is called without arguments
        // THEN: A RuntimeException is created with no message.
        UnAuthorized exception = new UnAuthorized();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        // GIVEN: A message is passed to the constructor
        // WHEN: The constructor is called with a message
        // THEN: A RuntimeException is created with the provided message.
        UnAuthorized exception = new UnAuthorized("Invalid credentials");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Invalid credentials", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause is passed to the constructor
        // WHEN: The constructor is called with a cause
        // THEN: A RuntimeException is created with the provided cause.
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(new NullPointerException(), exception.getCause());
    }

    @Test
    void constructor_withMsgAndCause() {
        // GIVEN: A message and a cause are passed to the constructor
        // WHEN: The constructor is called with a message and a cause
        // THEN: A RuntimeException is created with the provided message and cause.
        UnAuthorized exception = new UnAuthorized("Authentication failed", new IllegalArgumentException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Authentication failed", exception.getMessage());
        assertSame(new IllegalArgumentException(), exception.getCause());
    }
}
