package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SomeExtension.class})
public class UnAuthorizedGeneratedAiTests {

    @Test
    void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A RuntimeException is created with no message.
        UnAuthorized exception = new UnAuthorized();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with the message.
        // THEN: A RuntimeException is created with the provided message.
        UnAuthorized exception = new UnAuthorized("Invalid credentials");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Invalid credentials", exception.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A cause (Throwable) is provided to the constructor.
        // WHEN: The constructor is called with the cause.
        // THEN: A RuntimeException is created with the cause.
        UnAuthorized exception = new UnAuthorized(new NullPointerException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(new NullPointerException(), exception.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A message and a cause are provided to the constructor.
        // WHEN: The constructor is called with the message and cause.
        // THEN: A RuntimeException is created with the message and cause.
        UnAuthorized exception = new UnAuthorized("Authentication failed", new IllegalArgumentException("Invalid input"));
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Authentication failed", exception.getMessage());
        assertSame(new IllegalArgumentException("Invalid input"), exception.getCause());
    }
}

// Dummy extension class to satisfy the annotation requirement.
class SomeExtension implements Extension {}
