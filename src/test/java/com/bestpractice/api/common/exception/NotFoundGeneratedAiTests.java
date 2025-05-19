package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotFoundGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        NotFound exception = new NotFound();
        assertNotNull(exception);
    }

    @Test
    public void constructor_with_message() {
        String message = "Resource not found";
        NotFound exception = new NotFound(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        Throwable cause = new NullPointerException("Some other error");
        NotFound exception = new NotFound(cause);
        assertSame(cause, exception.getCause());
    }

    @Test
    public void constructor_with_message_and_cause() {
        String message = "Resource not found";
        Throwable cause = new NullPointerException("Some other error");
        NotFound exception = new NotFound(message, cause);
        assertEquals(message, exception.getMessage());
        assertSame(cause, exception.getCause());
    }
}
