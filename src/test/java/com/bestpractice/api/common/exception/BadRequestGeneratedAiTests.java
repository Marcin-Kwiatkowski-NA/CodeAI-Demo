package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructorCreatesInstance() {
        // GIVEN: No input parameters
        // WHEN: Creating a BadRequest instance using default constructor
        BadRequest exception = new BadRequest();
        // THEN: The instance should not be null and message should be null
        assertNotNull(exception);
        assertNull(exception.getMessage());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid request data";
        // WHEN: Creating a BadRequest instance with message
        BadRequest exception = new BadRequest(message);
        // THEN: The message should match the provided one
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A throwable cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a BadRequest instance with cause
        BadRequest exception = new BadRequest(cause);
        // THEN: The cause should match the provided one
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating a BadRequest instance with message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Both message and cause should match the provided ones
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingBadRequestException() {
        // GIVEN: A message for the exception
        String message = "Bad request thrown";
        // WHEN & THEN: Verify that BadRequest can be thrown and caught correctly
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: A cause for the exception
        Throwable cause = new IllegalStateException("Invalid state");
        // WHEN & THEN: Verify that BadRequest with cause can be thrown and caught correctly
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest("Bad request with cause", cause);
        });
        assertEquals("Bad request with cause", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
