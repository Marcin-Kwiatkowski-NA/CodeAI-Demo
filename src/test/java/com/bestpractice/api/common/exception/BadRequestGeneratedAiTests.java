package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // GIVEN: Reset any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN: No message or cause
        // WHEN: Creating BadRequest using default constructor
        BadRequest exception = new BadRequest();
        // THEN: Exception should have null message and cause
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A specific error message
        String message = "Invalid input data";
        // WHEN: Creating BadRequest with message
        BadRequest exception = new BadRequest(message);
        // THEN: Exception should contain the provided message
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN: A throwable cause
        Throwable cause = new IllegalArgumentException("Invalid argument");
        // WHEN: Creating BadRequest with cause
        BadRequest exception = new BadRequest(cause);
        // THEN: Exception should contain the provided cause
        assertEquals(cause, exception.getCause());
        // Message may contain cause.toString(), verify it contains the cause class name
        assertEquals("java.lang.IllegalArgumentException: Invalid argument", exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN: A message and a cause
        String message = "Bad request occurred";
        Throwable cause = new RuntimeException("Underlying issue");
        // WHEN: Creating BadRequest with message and cause
        BadRequest exception = new BadRequest(message, cause);
        // THEN: Exception should contain both message and cause
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testBadRequestCanBeThrownAndCaught() {
        // GIVEN: A message for the exception
        String message = "Throwing BadRequest";
        // WHEN & THEN: Verify that BadRequest can be thrown and caught properly
        assertThrows(BadRequest.class, () -> {
            throw new BadRequest(message);
        });
    }
}
