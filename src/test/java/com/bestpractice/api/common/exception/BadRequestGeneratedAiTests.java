package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyTestFactory.class)
public class BadRequestGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        BadRequest exception = new BadRequest();
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }

    @Test
    public void constructor_with_message() {
        BadRequest exception = new BadRequest("Invalid request");
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("Invalid request", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        String message = "Something went wrong";
        Throwable cause = new Throwable("Detailed error message");
        BadRequest exception = new BadRequest(cause);
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void constructor_with_message_and_cause() {
        String message = "Error occurred";
        Throwable cause = new Throwable("More details");
        BadRequest exception = new BadRequest(message, cause);
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
