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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

class BadRequestGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No mutable state to reset for this exception class
    }

    @Test
    void defaultConstructor_ShouldCreateExceptionWithNoMessageAndNoCause() {
        // GIVEN
        // No preconditions needed for default constructor

        // WHEN
        BadRequest exception = new BadRequest();

        // THEN
        assertThat(exception).isNotNull();
        assertEquals(null, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void messageConstructor_ShouldCreateExceptionWithSpecifiedMessage() {
        // GIVEN
        String expectedMessage = "Invalid request parameters";

        // WHEN
        BadRequest exception = new BadRequest(expectedMessage);

        // THEN
        assertThat(exception).isNotNull();
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(null, exception.getCause());
    }

    @Test
    void causeConstructor_ShouldCreateExceptionWithSpecifiedCause() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Underlying cause");

        // WHEN
        BadRequest exception = new BadRequest(cause);

        // THEN
        assertThat(exception).isNotNull();
        assertEquals(cause.toString(), exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void messageAndCauseConstructor_ShouldCreateExceptionWithBothMessageAndCause() {
        // GIVEN
        String expectedMessage = "Error occurred";
        Throwable cause = new RuntimeException("Root cause");

        // WHEN
        BadRequest exception = new BadRequest(expectedMessage, cause);

        // THEN
        assertThat(exception).isNotNull();
        assertEquals(expectedMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
