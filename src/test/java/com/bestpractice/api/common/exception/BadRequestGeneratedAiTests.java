package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

package com.bestpractice.api.common.exception;

public class BadRequestGeneratedAiTests {

    private BadRequest badRequest;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        badRequest = new BadRequest();
    }

    @Test
    void constructor_no_args() {
        // GIVEN: A new instance of BadRequest is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The constructor should call the constructor of the parent class (RuntimeException) with no arguments.
        assertNotNull(badRequest);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A new instance of BadRequest is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The constructor should call the constructor of the parent class (RuntimeException) with the message.
        String message = "Invalid request";
        badRequest = new BadRequest(message);
        assertEquals(message, badRequest.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A new instance of BadRequest is created with a Throwable cause.
        // WHEN: The constructor is called with a Throwable cause.
        Throwable cause = new Throwable();
        badRequest = new BadRequest(cause);
        assertSame(cause, badRequest.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A new instance of BadRequest is created with a message and a Throwable cause.
        // WHEN: The constructor is called with a message and a Throwable cause.
        Throwable cause = new Throwable();
        String message = "Invalid request with cause";
        badRequest = new BadRequest(message, cause);
        assertEquals(message, badRequest.getMessage());
        assertSame(cause, badRequest.getCause());
    }
}
