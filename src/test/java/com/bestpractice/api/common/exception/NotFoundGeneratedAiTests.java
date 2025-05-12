package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
public class NotFoundGeneratedAiTests {

    @Test
    public void constructor_no_args() {
        NotFound exception = new NotFound();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    public void constructor_with_message() {
        NotFound exception = new NotFound("Resource not found");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Resource not found", exception.getMessage());
    }

    @Test
    public void constructor_with_cause() {
        NotFound exception = new NotFound(new NullPointerException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(new NullPointerException(), exception.getCause());
    }

    @Test
    public void constructor_with_message_and_cause() {
        NotFound exception = new NotFound("Error occurred", new IllegalArgumentException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Error occurred", exception.getMessage());
        assertSame(new IllegalArgumentException(), exception.getCause());
    }
}
