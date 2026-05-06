package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any modified state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause provided

        // WHEN: Creating instance using default constructor
        ServiceUnavailable exception = new ServiceUnavailable();

        // THEN: Verify exception properties
        assertEquals(ServiceUnavailable.class, exception.getClass());
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific message
        String message = "Service is unavailable";

        // WHEN: Creating instance using message constructor
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN: Verify message is correctly set
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A specific cause
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN: Creating instance using cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN: Verify cause is correctly set
        assertEquals(cause, exception.getCause());
        assertEquals("java.lang.RuntimeException: Underlying cause", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A specific message and cause
        String message = "Service failure";
        Throwable cause = new RuntimeException("Network issue");

        // WHEN: Creating instance using message and cause constructor
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN: Verify both message and cause are correctly set
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableExceptionUsingAssertThrows() {
        // GIVEN: A message for the exception
        String message = "Service temporarily unavailable";

        // WHEN & THEN: Verify that the exception is thrown correctly using assertThrows
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });

        // THEN: Verify message of thrown exception
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableExceptionUsingAssertJ() {
        // GIVEN: A message for the exception
        String message = "Service temporarily unavailable";

        // WHEN & THEN: Verify that the exception is thrown correctly using AssertJ
        assertThatThrownBy(() -> {
            throw new ServiceUnavailable(message);
        }).isInstanceOf(ServiceUnavailable.class)
          .hasMessage(message);
    }
}
