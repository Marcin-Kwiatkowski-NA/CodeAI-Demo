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

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No specific setup required

        // WHEN: Creating an instance using the default constructor
        UnAuthorized exception = new UnAuthorized();

        // THEN: The exception should be created successfully with null message and cause
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Unauthorized access";

        // WHEN: Creating an instance using the message constructor
        UnAuthorized exception = new UnAuthorized(message);

        // THEN: The message should be set correctly and cause should be null
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: Creating an instance using the cause constructor
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN: The cause should be set correctly and message should contain cause.toString()
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Unauthorized with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN: Creating an instance using the message and cause constructor
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN: Both message and cause should be set correctly
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedException() {
        // GIVEN: A message for the exception
        String message = "Access denied";

        // WHEN & THEN: Ensure the exception is thrown correctly
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });

        // THEN: Verify that the thrown exception contains the expected message
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithCause() {
        // GIVEN: A message and a cause for the exception
        String message = "Access denied with cause";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN: Ensure the exception is thrown correctly with cause
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });

        // THEN: Verify that both message and cause are correctly set
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
