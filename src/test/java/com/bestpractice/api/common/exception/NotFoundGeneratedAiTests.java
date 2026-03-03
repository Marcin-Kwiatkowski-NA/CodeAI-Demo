package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

class NotFoundGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this immutable exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No additional setup needed

        // WHEN
        NotFound exception = new NotFound();

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Resource not found";

        // WHEN
        NotFound exception = new NotFound(message);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        NotFound exception = new NotFound(cause);

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Entity missing";
        Throwable cause = new NullPointerException("Null reference");

        // WHEN
        NotFound exception = new NotFound(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void testInheritance() {
        // GIVEN
        // No additional setup needed

        // WHEN
        NotFound exception = new NotFound();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}
