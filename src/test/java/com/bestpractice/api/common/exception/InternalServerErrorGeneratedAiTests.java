package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MockitoRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

@RunWith(MockitoRunner.class)
public class InternalServerErrorGeneratedAiTests {

    private InternalServerError internalServerError;

    @BeforeEach
    void setUp() {
        internalServerError = new InternalServerError();
    }

    @Test
    void testConstructorWithoutArguments() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The InternalServerError constructor is called.
        // THEN: A new InternalServerError object is created with a null message and no cause.
        assertNotNull(internalServerError);
        assertEquals(InternalServerError.class, internalServerError.getClass());
        assertNull(internalServerError.getMessage());
        assertNull(internalServerError.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The InternalServerError constructor is called with a message.
        // THEN: A new InternalServerError object is created with the provided message and no cause.
        String message = "An unexpected error occurred.";
        internalServerError = new InternalServerError(message);
        assertEquals(message, internalServerError.getMessage());
        assertNull(internalServerError.getCause());
    }

    @Test
    void testConstructorWithThrowable() {
        // GIVEN: A Throwable object is provided to the constructor.
        // WHEN: The InternalServerError constructor is called with a Throwable object.
        Throwable cause = new NullPointerException("Something went wrong!");
        internalServerError = new InternalServerError(cause);
        assertEquals(cause, internalServerError.getCause());
        assertNull(internalServerError.getMessage());
    }

    @Test
    void testConstructorWithMessageAndThrowable() {
        // GIVEN: A message and a Throwable object are provided to the constructor.
        String message = "Internal Server Error";
        Throwable cause = new IllegalArgumentException("Invalid input data");
        internalServerError = new InternalServerError(message, cause);
        assertEquals(message, internalServerError.getMessage());
        assertEquals(cause, internalServerError.getCause());
    }

    @Test
    void testEqualsMethods() {
        // GIVEN: Two InternalServerError objects with the same message and cause.
        String message = "Test Message";
        Throwable cause = new NullPointerException("Test Cause");
        InternalServerError obj1 = new InternalServerError(message, cause);
        InternalServerError obj2 = new InternalServerError(message, cause);
        // WHEN: The equals() and hashCode() methods are called on the objects.
        // THEN: The equals() method returns true, and the hashCode() method returns the same value for both objects.
        assertTrue(obj1.equals(obj2));
        assertEquals(obj1.hashCode(), obj2.hashCode());
    }

    @Test
    void testEqualsWithNullMessage() {
        // GIVEN: One InternalServerError object has a null message.
        Throwable cause = new NullPointerException("Test Cause");
        InternalServerError obj1 = new InternalServerError(null, cause);
        InternalServerError obj2 = new InternalServerError(null, cause);
        // WHEN: The equals() method is called on the objects.
        // THEN: The equals() method returns true.
        assertTrue(obj1.equals(obj2));
    }

    @Test
    void testEqualsWithNullCause() {
        // GIVEN: One InternalServerError object has a null cause.
        String message = "Test Message";
        InternalServerError obj1 = new InternalServerError(message, null);
        InternalServerError obj2 = new InternalServerError(message, null);
        // WHEN: The equals() method is called on the objects.
        // THEN: The equals() method returns true.
        assertTrue(obj1.equals(obj2));
    }
}
