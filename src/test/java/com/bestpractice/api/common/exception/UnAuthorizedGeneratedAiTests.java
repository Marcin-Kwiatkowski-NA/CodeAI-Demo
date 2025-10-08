package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThat(exception).isInstanceOf(RuntimeException.class);
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
        assertThat(exception.getMessage()).isEqualTo(message);
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
        assertThat(exception.getCause()).isInstanceOf(RuntimeException.class);
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
        assertThat(exception.getCause().getMessage()).isEqualTo("Root cause");
    }

    @Test
    void givenExceptionInstance_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Unauthorized throw test";

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message);
        });
        assertEquals(message, thrown.getMessage());
        assertNull(thrown.getCause());
        assertThat(thrown).hasMessage(message);
    }

    @Test
    void givenExceptionWithCause_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        String message = "Unauthorized throw with cause";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(message, cause);
        });
        assertEquals(message, thrown.getMessage());
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getCause()).isInstanceOf(RuntimeException.class);
    }

    @Test
    void givenCauseOnly_whenThrowing_thenAssertThrowsCatchesIt() {
        // GIVEN
        Throwable cause = new RuntimeException("Only cause");

        // WHEN & THEN
        UnAuthorized thrown = assertThrows(UnAuthorized.class, () -> {
            throw new UnAuthorized(cause);
        });
        assertEquals(cause, thrown.getCause());
        assertThat(thrown.getMessage()).isEqualTo(cause.toString());
    }
}
