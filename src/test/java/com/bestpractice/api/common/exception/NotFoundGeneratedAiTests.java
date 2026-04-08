package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any shared state before each test
    }

    @Test
    void shouldCreateNotFoundWithNoArgs() {
        // GIVEN: No arguments provided

        // WHEN: Creating a new NotFound instance
        NotFound exception = new NotFound();

        // THEN: Verify the exception is created successfully
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateNotFoundWithMessage() {
        // GIVEN: A specific message
        String message = "Resource not found";

        // WHEN: Creating a new NotFound instance with message
        NotFound exception = new NotFound(message);

        // THEN: Verify the message is correctly set
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void shouldCreateNotFoundWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating a new NotFound instance with cause
        NotFound exception = new NotFound(cause);

        // THEN: Verify the cause is correctly set
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void shouldCreateNotFoundWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Entity missing";
        Throwable cause = new NullPointerException("Null reference");

        // WHEN: Creating a new NotFound instance with message and cause
        NotFound exception = new NotFound(message, cause);

        // THEN: Verify both message and cause are correctly set
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenExplicitlyThrown() {
        // GIVEN: A message for the exception
        String message = "Explicit throw test";

        // WHEN & THEN: Verify that throwing NotFound triggers the expected exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void shouldThrowNotFoundExceptionWithCauseWhenExplicitlyThrown() {
        // GIVEN: A cause for the exception
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN: Verify that throwing NotFound with cause triggers the expected exception
        NotFound thrown = assertThrows(NotFound.class, () -> {
            throw new NotFound("Error occurred", cause);
        });
        assertEquals("Error occurred", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
