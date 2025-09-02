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

@RunWith(JUnit4.class)
public class UnAuthorizedGeneratedAiTests {

    private UnAuthorized unAuthorized;

    @BeforeEach
    void setUp() {
        unAuthorized = new UnAuthorized();
    }

    @Test
    void constructor_no_args() {
        // GIVEN: A new UnAuthorized object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The UnAuthorized object is created with no message or cause.
        assertNotNull(unAuthorized);
        assertEquals(RuntimeException.class, unAuthorized.getClass());
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A new UnAuthorized object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The UnAuthorized object is created with the specified message and no cause.
        String message = "Unauthorized access denied";
        unAuthorized = new UnAuthorized(message);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(RuntimeException.class, unAuthorized.getClass());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A new UnAuthorized object is created with a cause.
        // WHEN: The constructor is called with a cause.
        // THEN: The UnAuthorized object is created with the specified message and cause.
        Throwable cause = new RuntimeException("Something went wrong");
        unAuthorized = new UnAuthorized(cause);
        assertEquals(cause, unAuthorized.getCause());
        assertEquals(RuntimeException.class, unAuthorized.getClass());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new UnAuthorized object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Invalid credentials";
        Throwable cause = new RuntimeException("Authentication failed");
        unAuthorized = new UnAuthorized(message, cause);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
        assertEquals(RuntimeException.class, unAuthorized.getClass());
    }
}
