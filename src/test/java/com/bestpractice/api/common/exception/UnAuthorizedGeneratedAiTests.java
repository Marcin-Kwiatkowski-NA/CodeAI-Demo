package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset state before each test
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created without a message.
        UnAuthorized exception = new UnAuthorized();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the provided message.
        // THEN: A RuntimeException is created with the provided message.
        UnAuthorized exception = new UnAuthorized("Unauthorized access denied");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withCause() {
        // GIVEN: A cause is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the provided cause.
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(NullPointerException.class, exception.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the provided message and cause.
        UnAuthorized exception = new UnAuthorized("Failed to authenticate", new IllegalArgumentException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Failed to authenticate", exception.getMessage());
        assertSame(IllegalArgumentException.class, exception.getCause());
    }
}
