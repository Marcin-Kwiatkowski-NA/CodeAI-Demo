package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any shared state before each test
    }

    @Test
    void shouldCreateInstanceWithDefaultConstructor() {
        // GIVEN: No input parameters

        // WHEN: Creating instance using default constructor
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN: Verify instance is created and has no message or cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithMessage() {
        // GIVEN: A specific error message
        String message = "Service is temporarily unavailable";

        // WHEN: Creating instance using message constructor
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is set correctly and cause is null
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateInstanceWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: Creating instance using cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN: Verify cause is set correctly and message matches cause.toString()
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void shouldCreateInstanceWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Service failure";
        Throwable cause = new RuntimeException("Network issue");

        // WHEN: Creating instance using message and cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: Verify both message and cause are set correctly
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowServiceUnavailableWhenExplicitlyThrown() {
        // GIVEN: A message for the exception
        String message = "Service unavailable error";

        // WHEN & THEN: Verify that throwing the exception works as expected
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });

        // THEN: Verify message is correctly propagated
        assertEquals(message, thrown.getMessage());
    }
}
