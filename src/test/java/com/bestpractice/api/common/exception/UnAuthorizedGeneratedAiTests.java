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
import static org.assertj.core.api.Assertions.assertThat;

class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for these tests
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions

        // WHEN
        UnAuthorized exception = new UnAuthorized();

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String message = "Unauthorized access";

        // WHEN
        UnAuthorized exception = new UnAuthorized(message);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Invalid argument");

        // WHEN
        UnAuthorized exception = new UnAuthorized(cause);

        // THEN
        assertThat(exception.getCause()).isSameAs(cause);
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Unauthorized with cause";
        Throwable cause = new NullPointerException("Null pointer");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isSameAs(cause);
    }
}
