package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no parameters provided
        // WHEN: creating the exception using the default constructor
        UnAuthorized exception = new UnAuthorized();

        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Unauthorized access";

        // WHEN: creating the exception with the message constructor
        UnAuthorized exception = new UnAuthorized(message);

        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: creating the exception with the cause constructor
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Root cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Unauthorized due to invalid token";
        Throwable cause = new RuntimeException("Token expired");

        // WHEN: creating the exception with message and cause constructor
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithMessage() {
        // GIVEN: a specific message
        String message = "Access denied";

        // WHEN & THEN: assert that throwing the exception works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Error occurred";
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN: assert that throwing the exception with message and cause works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionOnlyCause() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Only cause provided");

        // WHEN & THEN: assert that throwing the exception with only cause works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Only cause provided"));
    }

    @Test
    void testThrowingUnAuthorizedExceptionDefaultConstructor() {
        // GIVEN: no parameters
        // WHEN & THEN: assert that throwing the exception with default constructor works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
