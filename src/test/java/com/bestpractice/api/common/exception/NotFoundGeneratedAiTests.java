package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundGeneratedAiTests {

    @BeforeEach
    public void setUp() {
        // No shared state to reset before each test
    }

    @Test
    public void testDefaultConstructor() {
        // GIVEN
        // No preconditions needed

        // WHEN
        NotFound exception = new NotFound();

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    public void testMessageConstructor() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        NotFound exception = new NotFound(message);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    public void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    public void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Entity missing";
        Throwable cause = new NullPointerException("Null value");

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
