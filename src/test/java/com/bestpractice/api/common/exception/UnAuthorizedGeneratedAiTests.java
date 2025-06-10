package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UnAuthorizedGeneratedAiTests {

    private UnAuthorized exception;

    @BeforeEach
    public void setUp() {
        exception = null; // Reset the state before each test
    }

    @Test
    public void testUnAuthorizedDefaultConstructor() {
        // GIVEN: No special setup needed for default constructor
        // WHEN
        exception = new UnAuthorized();
        // THEN
        assertNotNull(exception, "Exception should not be null");
    }

    @Test
    public void testUnAuthorizedStringConstructor() {
        // GIVEN
        String message = "Unauthorized access";
        // WHEN
        exception = new UnAuthorized(message);
        // THEN
        assertEquals(message, exception.getMessage(), "Exception message should match the provided string");
    }

    @Test
    public void testUnAuthorizedThrowableConstructor() {
        // GIVEN
        Throwable cause = new Exception("Cause");
        // WHEN
        exception = new UnAuthorized(cause);
        // THEN
        assertSame(cause, exception.getCause(), "Exception cause should match the provided throwable");
    }

    @Test
    public void testUnAuthorizedStringThrowableConstructor() {
        // GIVEN
        String message = "Unauthorized access";
        Throwable cause = new Exception("Cause");
        // WHEN
        exception = new UnAuthorized(message, cause);
        // THEN
        assertEquals(message, exception.getMessage(), "Exception message should match the provided string");
        assertSame(cause, exception.getCause(), "Exception cause should match the provided throwable");
    }
}
