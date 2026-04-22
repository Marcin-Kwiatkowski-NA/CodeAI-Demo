package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructorCreatesInstance() {
        // GIVEN: No specific setup required

        // WHEN: Creating a BadRequest instance using the default constructor
        BadRequest exception = new BadRequest();

        // THEN: Verify that the instance is created and has no message or cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A message to pass to the constructor
        String message = "Invalid request data";

        // WHEN: Creating a BadRequest instance with a message
        BadRequest exception = new BadRequest(message);

        // THEN: Verify that the message is correctly set
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A cause (Throwable) to pass to the constructor
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating a BadRequest instance with a cause
        BadRequest exception = new BadRequest(cause);

        // THEN: Verify that the cause is correctly set
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        // The message should contain the cause's toString() representation
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause to pass to the constructor
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: Creating a BadRequest instance with both message and cause
        BadRequest exception = new BadRequest(message, cause);

        // THEN: Verify that both message and cause are correctly set
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingBadRequestException() {
        // GIVEN: A message to simulate throwing the exception
        String message = "Simulated bad request";

        // WHEN & THEN: Verify that throwing BadRequest results in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });

        // THEN: Verify that the thrown exception contains the expected message
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingBadRequestWithCause() {
        // GIVEN: A cause to simulate throwing the exception
        Throwable cause = new IllegalStateException("State issue");

        // WHEN & THEN: Verify that throwing BadRequest with a cause results in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(cause);
        });

        // THEN: Verify that the thrown exception contains the expected cause
        assertEquals(cause, thrown.getCause());
        assertEquals("java.lang.IllegalStateException: State issue", thrown.getMessage());
    }

    @Test
    void testThrowingBadRequestWithMessageAndCause() {
        // GIVEN: A message and cause to simulate throwing the exception
        String message = "Bad request with cause";
        Throwable cause = new RuntimeException("Runtime issue");

        // WHEN & THEN: Verify that throwing BadRequest with both message and cause results in expected exception
        BadRequest thrown = assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message, cause);
        });

        // THEN: Verify that both message and cause are correctly set in the thrown exception
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
