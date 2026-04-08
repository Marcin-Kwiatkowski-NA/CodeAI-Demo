package com.bestpractice.api.common.exception;

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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        // THEN: Verify instance is created and message is null
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid request data";
        // WHEN: Creating a BadRequest instance with message
        BadRequest exception = new BadRequest(message);
        // THEN: Verify message is correctly set and cause is null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A throwable cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating a BadRequest instance with cause
        BadRequest exception = new BadRequest(cause);
        // THEN: Verify cause is correctly set and message matches cause.toString()
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating a BadRequest instance with message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Verify both message and cause are correctly set
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testBadRequestCanBeThrownAndCaught() {
        // GIVEN: A message for the exception
        String message = "Request failed";
        // WHEN & THEN: Verify exception can be thrown and caught successfully
        assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        assertThatThrownBy(() -> { throw new BadRequest(message); })
            .isInstanceOf(BadRequest.class)
            .hasMessage(message);
    }

    @Test
    void testBadRequestWithNullMessageAndCause() {
        // GIVEN: Null message and cause
        String message = null;
        Throwable cause = null;
        // WHEN: Creating a BadRequest instance with null message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Verify both message and cause are null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }
}
