package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided

        // WHEN: Creating instance using default constructor
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN: Verify exception is created and message/cause are null
        assertTrue(exception instanceof ServiceUnavailable);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific message
        String message = "Service is unavailable";

        // WHEN: Creating instance using message constructor
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is set correctly and cause is null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: Creating instance using cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN: Verify cause is set correctly and message matches cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Underlying cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Service failed";
        Throwable cause = new IllegalStateException("Invalid state");

        // WHEN: Creating instance using message and cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: Verify both message and cause are set correctly
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableException() {
        // GIVEN: A message for the exception
        String message = "Service temporarily unavailable";

        // WHEN & THEN: Verify that throwing the exception works as expected
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });

        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new RuntimeException("Network failure");

        // WHEN & THEN: Verify that throwing the exception with cause works as expected
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable("Service down", cause);
        });

        assertEquals("Service down", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
