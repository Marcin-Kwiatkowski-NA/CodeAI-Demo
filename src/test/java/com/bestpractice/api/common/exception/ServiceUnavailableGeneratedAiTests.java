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
public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: no parameters
        // WHEN: creating the exception using the default constructor
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN: the message and cause should be null
        assertNull(exception.getMessage(), "Message should be null for default constructor");
        assertNull(exception.getCause(), "Cause should be null for default constructor");
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: a message string
        String message = "Service is unavailable";

        // WHEN: creating the exception with a message
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: the message should match and cause should be null
        assertEquals(message, exception.getMessage(), "Message should match the provided string");
        assertNull(exception.getCause(), "Cause should be null when only message is provided");
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: a cause throwable
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: creating the exception with a cause
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN: the cause should match and message should be cause.toString()
        assertEquals(cause, exception.getCause(), "Cause should match the provided throwable");
        assertTrue(exception.getMessage().contains("Underlying cause"), "Message should contain cause description");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: a message and a cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN: creating the exception with both message and cause
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: both message and cause should match
        assertEquals(message, exception.getMessage(), "Message should match the provided string");
        assertEquals(cause, exception.getCause(), "Cause should match the provided throwable");
    }

    @Test
    void testThrowingServiceUnavailableDefaultConstructor() {
        // GIVEN: no parameters
        // WHEN & THEN: assertThrows should catch the exception
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable();
        }, "ServiceUnavailable should be thrown");
        assertNull(thrown.getMessage(), "Message should be null for default constructor");
        assertNull(thrown.getCause(), "Cause should be null for default constructor");
    }

    @Test
    void testThrowingServiceUnavailableWithMessage() {
        // GIVEN: a message string
        String message = "Service is unavailable";

        // WHEN & THEN: assertThrows should catch the exception and verify message
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        }, "ServiceUnavailable should be thrown");
        assertEquals(message, thrown.getMessage(), "Message should match the provided string");
        assertNull(thrown.getCause(), "Cause should be null when only message is provided");
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN: a cause throwable
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN & THEN: assertThrows should catch the exception and verify cause
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        }, "ServiceUnavailable should be thrown");
        assertEquals(cause, thrown.getCause(), "Cause should match the provided throwable");
        assertTrue(thrown.getMessage().contains("Underlying cause"), "Message should contain cause description");
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN: a message and a cause
        String message = "Service failed";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN: assertThrows should catch the exception and verify both
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        }, "ServiceUnavailable should be thrown");
        assertEquals(message, thrown.getMessage(), "Message should match the provided string");
        assertEquals(cause, thrown.getCause(), "Cause should match the provided throwable");
    }
}
