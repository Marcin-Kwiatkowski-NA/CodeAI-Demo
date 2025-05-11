package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotFoundGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        NotFound exception = new NotFound();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMsg() {
        NotFound exception = new NotFound("Resource not found");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Resource not found", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        NotFound exception = new NotFound(new NullPointerException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertSame(NullPointerException.class, exception.getCause());
    }

    @Test
    void constructor_withMsgAndCause() {
        NotFound exception = new NotFound("Error occurred", new IllegalArgumentException());
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Error occurred", exception.getMessage());
        assertSame(IllegalArgumentException.class, exception.getCause());
    }
}
