package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UnAuthorizedGeneratedAiTests {

    @BeforeEach
    void resetState() {
        // No mutable state to reset in UnAuthorized, method kept for compliance with test structure
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // No preconditions needed for default constructor

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
        assertThat(exception).isInstanceOf(RuntimeException.class);
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
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String message = "Unauthorized access with cause";
        Throwable cause = new NullPointerException("Null pointer");

        // WHEN
        UnAuthorized exception = new UnAuthorized(message, cause);

        // THEN
        assertThat(exception).isInstanceOf(RuntimeException.class);
        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
