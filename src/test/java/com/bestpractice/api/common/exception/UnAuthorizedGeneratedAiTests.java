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

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this exception class
    }

    @Test
    void defaultConstructorShouldCreateExceptionWithNullMessageAndCause() {
        // GIVEN
        // No preconditions needed

        // WHEN
        UnAuthorized exception = new UnAuthorized();

        // THEN
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void messageConstructorShouldSetMessageAndNullCause() {
        // GIVEN
        String expectedMessage = "Access denied";

        // WHEN
        UnAuthorized exception = new UnAuthorized(expectedMessage);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void causeConstructorShouldSetCauseAndNullMessage() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertThat(exception.getCause()).isSameAs(cause);
        assertThat(exception.getMessage()).isNull();
    }

    @Test
    void messageAndCauseConstructorShouldSetBothFields() {
        // GIVEN
        String expectedMessage = "Unauthorized operation";
        Throwable cause = new RuntimeException("Underlying failure");

        // WHEN
        UnAuthorized exception = new UnAuthorized(expectedMessage, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(expectedMessage);
        assertThat(exception.getCause()).isSameAs(cause);
    }

    @Test
    void exceptionShouldBeThrownAndCaughtCorrectly() {
        // GIVEN
        String errorMessage = "Forbidden access";
        Throwable rootCause = new NullPointerException("Null value");

        // WHEN
        try {
            throw new UnAuthorized(errorMessage, rootCause);
        } catch (UnAuthorized ex) {
            // THEN
            assertThat(ex).isInstanceOf(RuntimeException.class);
            assertThat(ex.getMessage()).isEqualTo(errorMessage);
            assertThat(ex.getCause()).isSameAs(rootCause);
        }
    }
}
