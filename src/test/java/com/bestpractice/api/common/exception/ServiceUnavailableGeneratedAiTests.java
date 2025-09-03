package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ServiceUnavailableGeneratedAiTests {

    @Test
    void constructor_noArgs() {
        ServiceUnavailable exception = new ServiceUnavailable();
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void constructor_withMessage() {
        ServiceUnavailable exception = new ServiceUnavailable("Service is temporarily unavailable");
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service is temporarily unavailable", exception.getMessage());
    }

    @Test
    void constructor_withCause() {
        IOException ioException = new IOException("Underlying IO error");
        ServiceUnavailable exception = new ServiceUnavailable(ioException);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Underlying IO error", exception.getMessage());
        assertSame(ioException, exception.getCause());
    }

    @Test
    void constructor_withMessageAndCause() {
        IOException ioException = new IOException("Underlying IO error");
        ServiceUnavailable exception = new ServiceUnavailable("Service unavailable", ioException);
        assertNotNull(exception);
        assertInstanceOf(RuntimeException.class, exception);
        assertEquals("Service unavailable", exception.getMessage());
        assertSame(ioException, exception.getCause());
    }
}
