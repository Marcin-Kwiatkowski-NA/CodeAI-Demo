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

public class ForbiddenGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // No state to reset for this exception class
    }

    @Test
    void testDefaultConstructor() {
        // GIVEN
        // WHEN
        Forbidden exception = new Forbidden();
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isNull();
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testMessageConstructor() {
        // GIVEN
        String msg = "Access denied";
        // WHEN
        Forbidden exception = new Forbidden(msg);
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(msg);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testCauseConstructor() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Bad argument");
        // WHEN
        Forbidden exception = new Forbidden(cause);
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(cause.toString());
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testMessageAndCauseConstructor() {
        // GIVEN
        String msg = "Forbidden";
        Throwable cause = new RuntimeException("Inner");
        // WHEN
        Forbidden exception = new Forbidden(msg, cause);
        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo(msg);
        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    void testGetMessageAfterSetting() {
        // GIVEN
        Forbidden exception = new Forbidden("Initial");
        // WHEN
        // THEN
        assertThat(exception.getMessage()).isEqualTo("Initial");
    }

    @Test
    void testGetCauseAfterSetting() {
        // GIVEN
        Throwable cause = new Exception("cause");
        Forbidden exception = new Forbidden("msg", cause);
        // WHEN
        // THEN
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}
