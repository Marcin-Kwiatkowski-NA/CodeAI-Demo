package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
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
        // WHEN: Creating BadRequest using default constructor
        BadRequest exception = new BadRequest();
        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid input data";
        // WHEN: Creating BadRequest with message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause (Throwable)
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating BadRequest with cause
        BadRequest exception = new BadRequest(cause);
        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Invalid argument");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating BadRequest with message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingBadRequestException() {
        // GIVEN: A message for the exception
        String message = "Request failed";
        // WHEN & THEN: Verify that BadRequest can be thrown and caught
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalStateException("State issue");
        // WHEN & THEN: Verify that BadRequest with cause can be thrown and caught
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("State issue");
    }

    @Test
    void testThrowingBadRequestWithMessageAndCause() {
        // GIVEN: A message and cause for the exception
        String message = "Bad request with cause";
        Throwable cause = new RuntimeException("Root cause");
        // WHEN & THEN: Verify that BadRequest with message and cause can be thrown and caught
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
