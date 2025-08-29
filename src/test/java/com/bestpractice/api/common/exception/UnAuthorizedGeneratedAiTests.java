package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.runners.JUnit4;
import static org.junit.jupiter.api.Assertions.*;

package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnAuthorizedGeneratedAiTests {

    private UnAuthorized unAuthorized;

    @BeforeEach
    void setUp() {
        unAuthorized = new UnAuthorized();
    }

    @Test
    void constructor_noArgs() {
        // GIVEN: A new UnAuthorized object is created without arguments.
        // WHEN: The constructor is called.
        // THEN: The UnAuthorized object is created with no message or cause.
        assertNotNull(unAuthorized);
        assertEquals(RuntimeException.class, unAuthorized.getClass());
    }

    @Test
    void constructor_withMessage() {
        // GIVEN: A new UnAuthorized object is created with a message.
        // WHEN: The constructor is called with a message.
        // THEN: The UnAuthorized object is created with the provided message and no cause.
        String message = "Unauthorized access denied";
        unAuthorized = new UnAuthorized(message);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(RuntimeException.class, unAuthorized.getClass());
    }

    @Test
    void constructor_withMessageAndCause() {
        // GIVEN: A new UnAuthorized object is created with a message and a cause.
        // WHEN: The constructor is called with a message and a cause.
        String message = "Unauthorized access denied";
        Throwable cause = new Exception("Something went wrong");
        unAuthorized = new UnAuthorized(message, cause);
        assertEquals(message, unAuthorized.getMessage());
        assertEquals(cause, unAuthorized.getCause());
        assertEquals(RuntimeException.class, unAuthorized.getClass());
    }
}
