package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: a default constructor call
        // WHEN: creating an UnAuthorized instance with no arguments
        UnAuthorized exception = new UnAuthorized();
        // THEN: the message and cause should be null
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a specific error message
        String message = "Unauthorized access";
        // WHEN: creating an UnAuthorized instance with a message
        UnAuthorized exception = new UnAuthorized(message);
        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a specific cause exception
        Throwable cause = new RuntimeException("Root cause");
        // WHEN: creating an UnAuthorized instance with a cause
        UnAuthorized exception = new UnAuthorized(cause);
        // THEN: the cause should match and message should contain cause details
        assertEquals(cause, exception.getCause());
        assertTrue(exception.getMessage().contains("Root cause"));
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a specific message and cause
        String message = "Unauthorized due to invalid token";
        Throwable cause = new RuntimeException("Token expired");
        // WHEN: creating an UnAuthorized instance with both message and cause
        UnAuthorized exception = new UnAuthorized(message, cause);
        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithMessage() {
        // GIVEN: a scenario where unauthorized access occurs
        String message = "Access denied";
        // WHEN & THEN: assert that throwing the exception works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithCause() {
        // GIVEN: a scenario with a cause
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN & THEN: assert that throwing the exception with cause works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized("Unauthorized operation", cause);
        });
        assertEquals("Unauthorized operation", thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionOnlyCause() {
        // GIVEN: a scenario with only a cause
        Throwable cause = new RuntimeException("Only cause provided");
        // WHEN & THEN: assert that throwing the exception with only cause works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Only cause provided"));
    }

    @Test
    void testThrowingUnAuthorizedExceptionNoArgs() {
        // GIVEN: a scenario with no arguments
        // WHEN & THEN: assert that throwing the exception with default constructor works as expected
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }
}
