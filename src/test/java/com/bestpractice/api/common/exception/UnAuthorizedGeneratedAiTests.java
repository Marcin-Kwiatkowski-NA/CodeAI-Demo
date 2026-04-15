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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no input parameters
        // WHEN: creating the exception using the default constructor
        UnAuthorized exception = new UnAuthorized();

        // THEN: verify that the exception is created and message is null
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a message for the exception
        String message = "Unauthorized access";

        // WHEN: creating the exception with a message
        UnAuthorized exception = new UnAuthorized(message);

        // THEN: verify that the message is correctly set and cause is null
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a cause for the exception
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: creating the exception with a cause
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN: verify that the cause is correctly set and message matches cause.toString()
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a message and a cause for the exception
        String message = "Unauthorized with cause";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN: creating the exception with both message and cause
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN: verify that both message and cause are correctly set
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedException() {
        // GIVEN: a message for the exception
        String message = "Unauthorized operation";

        // WHEN & THEN: verify that throwing the exception works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });

        // THEN: verify that the thrown exception contains the expected message
        assertNotNull(thrown);
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithCause() {
        // GIVEN: a cause for the exception
        Throwable cause = new IllegalStateException("Invalid state");

        // WHEN & THEN: verify that throwing the exception with a cause works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized("Unauthorized due to invalid state", cause);
        });

        // THEN: verify that both message and cause are correctly set
        assertNotNull(thrown);
        assertEquals("Unauthorized due to invalid state", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
