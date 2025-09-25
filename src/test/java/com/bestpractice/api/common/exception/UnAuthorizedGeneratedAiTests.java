package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenNoArgsConstructor_whenCreatingInstance_thenInstanceCreated() {
        // GIVEN
        // No specific setup required

        // WHEN
        UnAuthorized exception = new UnAuthorized();

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenMessageConstructor_whenCreatingInstance_thenMessageIsSet() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenCauseConstructor_whenCreatingInstance_thenCauseIsSet() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertNotNull(exception);
        assertEquals(cause, exception.getCause());
        assertEquals(cause.toString(), exception.getMessage());
    }

    @Test
    void givenMessageAndCauseConstructor_whenCreatingInstance_thenMessageAndCauseAreSet() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertNotNull(exception);
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void givenMessage_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Unauthorized throw test";

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
    }

    @Test
    void givenMessageAndCause_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Unauthorized throw with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }

    @Test
    void givenCauseOnly_whenThrowingException_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertEquals(cause.toString(), thrown.getMessage());
    }

    @Test
    void givenNullMessage_whenCreatingInstance_thenMessageIsNull() {
        // GIVEN
        String message = null;

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenNullCause_whenCreatingInstance_thenCauseIsNull() {
        // GIVEN
        Throwable cause = null;

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertNotNull(exception);
        assertNull(exception.getCause());
        assertNull(exception.getMessage());
    }

    @Test
    void givenNullMessageAndCause_whenCreatingInstance_thenBothAreNull() {
        // GIVEN
        String message = null;
        Throwable cause = null;

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertNotNull(exception);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void givenEmptyMessage_whenCreatingInstance_thenMessageIsEmpty() {
        // GIVEN
        String message = "";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertNotNull(exception);
        assertEquals("", exception.getMessage());
        assertNull(exception.getCause());
    }
}
