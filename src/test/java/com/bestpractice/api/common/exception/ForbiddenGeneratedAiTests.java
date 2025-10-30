package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating a Forbidden instance with no arguments
        Forbidden exception = new Forbidden();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Access denied";
        // WHEN: creating a Forbidden instance with a message
        Forbidden exception = new Forbidden(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a Forbidden instance with a cause
        Forbidden exception = new Forbidden(cause);
        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Access denied with cause";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN: creating a Forbidden instance with both message and cause
        Forbidden exception = new Forbidden(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingForbiddenWithMessage() {
        // GIVEN: a specific error message
        String message = "Access denied";
        // WHEN & THEN: assertThrows should catch the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message);
        });
        // THEN: verify message and type
        assertEquals(message, thrown.getMessage());
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testThrowingForbiddenWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: assertThrows should catch the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        // THEN: verify cause and message
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Underlying cause"));
        assertThat(thrown.getCause()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testThrowingForbiddenWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Access denied with cause";
        Throwable cause = new RuntimeException("Underlying cause");
        // WHEN & THEN: assertThrows should catch the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(message, cause);
        });
        // THEN: verify message and cause
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getCause()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testThrowingForbiddenOnlyCauseMessageCheck() {
        // GIVEN: a specific cause with a known message
        Throwable cause = new RuntimeException("Only cause provided");
        // WHEN & THEN: assertThrows should catch the Forbidden exception
        Forbidden thrown = assertThrows(Forbidden.class, () -> {
            throw new Forbidden(cause);
        });
        // THEN: verify cause and that message contains cause message
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Only cause provided"));
    }
}
