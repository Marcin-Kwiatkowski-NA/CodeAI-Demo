package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided

        // WHEN: Creating a new BadRequest using default constructor
        BadRequest exception = new BadRequest();

        // THEN: Exception should be created with null message and cause
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
        assertThat(exception).isInstanceOf(BadRequest.class);
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid input data";

        // WHEN: Creating a new BadRequest with message
        BadRequest exception = new BadRequest(message);

        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A cause exception
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating a new BadRequest with cause
        BadRequest exception = new BadRequest(cause);

        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Creating a new BadRequest with message and cause
        BadRequest exception = new BadRequest(message, cause);

        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingBadRequestException() {
        // GIVEN: A message for the exception
        String message = "Bad request thrown";

        // WHEN & THEN: Verify that BadRequest can be thrown and caught properly
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        // THEN: Verify message correctness
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalStateException("State issue");

        // WHEN & THEN: Verify that BadRequest can be thrown with a cause
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest("Bad request with cause", cause);
        });

        // THEN: Verify both message and cause correctness
        assertEquals("Bad request with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
