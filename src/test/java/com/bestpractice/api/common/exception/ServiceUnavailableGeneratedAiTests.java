package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class ServiceUnavailableGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        ServiceUnavailable exception;

        // WHEN
        exception = new ServiceUnavailable();

        // THEN
        assertThat(exception).isInstanceOf(ServiceUnavailable.class);
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithMessage() {
        // GIVEN
        String message = "Service is temporarily unavailable";

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message);

        // THEN
        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void testConstructorWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Underlying cause");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(cause);

        // THEN
        assertEquals(cause, exception.getCause());
        assertThat(exception.getMessage()).contains("Underlying cause");
    }

    @Test
    void testConstructorWithMessageAndCause() {
        // GIVEN
        String message = "Service failure";
        Throwable cause = new RuntimeException("Network issue");

        // WHEN
        ServiceUnavailable exception = new ServiceUnavailable(message, cause);

        // THEN
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testThrowingServiceUnavailableException() {
        // GIVEN
        String message = "Service unavailable error";

        // WHEN & THEN
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    void testThrowingServiceUnavailableWithCause() {
        // GIVEN
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).contains("Root cause");
    }

    @Test
    void testThrowingServiceUnavailableWithMessageAndCause() {
        // GIVEN
        String message = "Critical service failure";
        Throwable cause = new RuntimeException("Timeout");

        // WHEN & THEN
        ServiceUnavailable thrown = assertThrows(ServiceUnavailable.class, () -> {
            throw new ServiceUnavailable(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
    }
}
