package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        UnAuthorized exception;

        // WHEN
        exception = new UnAuthorized();

        // THEN
        assertThat(exception).isInstanceOf(UnAuthorized.class);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Root cause");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithMessage() {
        // GIVEN
        String message = "Access denied";

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });

        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying issue");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });

        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Underlying issue");
    }

    @Test
    void testThrowingUnAuthorizedExceptionWithMessageAndCause() {
        // GIVEN
        String message = "Unauthorized operation";
        Throwable cause = new IllegalStateException("Illegal state");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });

        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
