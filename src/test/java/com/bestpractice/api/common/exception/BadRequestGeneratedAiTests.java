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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided
        BadRequest exception;

        // WHEN: Creating BadRequest using default constructor
        exception = new BadRequest();

        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid input";

        // WHEN: Creating BadRequest with message
        BadRequest exception = new BadRequest(message);

        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A cause exception
        Throwable cause = new IllegalArgumentException("Cause of error");

        // WHEN: Creating BadRequest with cause
        BadRequest exception = new BadRequest(cause);

        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        // The message of RuntimeException(cause) is cause.toString()
        assertEquals("java.lang.IllegalArgumentException: Cause of error", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Bad request occurred";
        Throwable cause = new NullPointerException("Null value");

        // WHEN: Creating BadRequest with message and cause
        BadRequest exception = new BadRequest(message, cause);

        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testBadRequestCanBeThrownAndCaught() {
        // GIVEN: A message for the exception
        String message = "Request error";

        // WHEN: Throwing and catching BadRequest
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        // THEN: Verify that the caught exception has the expected message
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testBadRequestWithNullMessageAndCause() {
        // GIVEN: Null message and cause
        String message = null;
        Throwable cause = null;

        // WHEN: Creating BadRequest with null message and cause
        BadRequest exception = new BadRequest(message, cause);

        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testThrowBadRequestWithoutMessage() {
        // GIVEN: No message provided

        // WHEN & THEN: Expect BadRequest to be thrown
        assertThrows(BadRequest.class, () -> {
            throw new BadRequest();
        });
    }
}
