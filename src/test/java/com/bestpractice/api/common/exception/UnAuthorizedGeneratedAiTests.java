package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any state before each test
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
    void testThrowingDefaultConstructorException() {
        // GIVEN: no parameters provided
        // WHEN & THEN: assertThrows should catch the exception
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized();
        });
        assertNull(thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingMessageConstructorException() {
        // GIVEN: a specific error message
        String message = "Unauthorized access";

        // WHEN & THEN: assertThrows should catch the exception and verify message
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void testThrowingCauseConstructorException() {
        // GIVEN: a specific cause
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN: assertThrows should catch the exception and verify cause
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertTrue(thrown.getMessage().contains("Root cause"));
    }

    @Test
    void testThrowingMessageAndCauseConstructorException() {
        // GIVEN: a specific message and cause
        String message = "Unauthorized due to invalid token";
        Throwable cause = new RuntimeException("Token expired");

        // WHEN & THEN: assertThrows should catch the exception and verify both
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
