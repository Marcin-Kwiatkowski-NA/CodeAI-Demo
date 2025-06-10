package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NotFoundGeneratedAiTests {

    private NotFound exception;

    @BeforeEach
    public void setUp() {
        exception = null; // Reset before each test
    }

    @Test
    public void givenNoMessageOrCause_whenNotFoundIsInstantiated_thenExceptionShouldBeCreated() {
        // GIVEN: No preconditions

        // WHEN
        exception = new NotFound();

        // THEN
        assertNotNull(exception, "The NotFound exception should be created");
    }

    @Test
    public void givenMessage_whenNotFoundIsInstantiatedWithMessage_thenExceptionShouldHaveCorrectMessage() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        exception = new NotFound(message);

        // THEN
        assertEquals(message, exception.getMessage(), "The NotFound exception should have the correct message");
    }

    @Test
    public void givenThrowable_whenNotFoundIsInstantiatedWithCause_thenExceptionShouldHaveCorrectCause() {
        // GIVEN
        Throwable cause = new Exception("Some cause");

        // WHEN
        exception = new NotFound(cause);

        // THEN
        assertSame(cause, exception.getCause(), "The NotFound exception should have the correct cause");
    }

    @Test
    public void givenMessageAndThrowable_whenNotFoundIsInstantiatedWithMessageAndCause_thenExceptionShouldHaveCorrectMessageAndCause() {
        // GIVEN
        String message = "Resource not found";
        Throwable cause = new Exception("Some cause");

        // WHEN
        exception = new NotFound(message, cause);

        // THEN
        assertEquals(message, exception.getMessage(), "The NotFound exception should have the correct message");
        assertSame(cause, exception.getCause(), "The NotFound exception should have the correct cause");
    }
}
