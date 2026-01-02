package com.bestpractice.api.common.exception;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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

import static org.assertj.core.api.Assertions.*;

public class BadRequestGeneratedAiTests {

    private BadRequest badRequest;

    @BeforeEach
    void setUp() {
        // No shared state to reset for these tests
    }

    @Test
    void defaultConstructorShouldCreateExceptionWithNoMessageAndNoCause() {
        // GIVEN
        // No preconditions needed

        // WHEN
        badRequest = new BadRequest();

        // THEN
        assertThat(badRequest).isInstanceOf(RuntimeException.class);
        assertThat(badRequest.getMessage()).isNull();
        assertThat(badRequest.getCause()).isNull();
    }

    @Test
    void messageConstructorShouldSetMessageAndNoCause() {
        // GIVEN
        String expectedMessage = "Invalid input";

        // WHEN
        badRequest = new BadRequest(expectedMessage);

        // THEN
        assertThat(badRequest.getMessage()).isEqualTo(expectedMessage);
        assertThat(badRequest.getCause()).isNull();
    }

    @Test
    void causeConstructorShouldSetCauseAndNoMessage() {
        // GIVEN
        Throwable cause = new IllegalArgumentException("Illegal argument");

        // WHEN
        badRequest = new BadRequest(cause);

        // THEN
        assertThat(badRequest.getCause()).isSameAs(cause);
        assertThat(badRequest.getMessage()).isNull();
    }

    @Test
    void messageAndCauseConstructorShouldSetBothMessageAndCause() {
        // GIVEN
        String expectedMessage = "Error occurred";
        Throwable cause = new NullPointerException("Null pointer");

        // WHEN
        badRequest = new BadRequest(expectedMessage, cause);

        // THEN
        assertThat(badRequest.getMessage()).isEqualTo(expectedMessage);
        assertThat(badRequest.getCause()).isSameAs(cause);
    }

    @Test
    void exceptionShouldBeThrownAndCaughtCorrectly() {
        // GIVEN
        String errorMsg = "Test exception";

        // WHEN
        RuntimeException thrown = null;
        try {
            throw new BadRequest(errorMsg);
        } catch (RuntimeException e) {
            thrown = e;
        }

        // THEN
        assertThat(thrown).isNotNull();
        assertThat(thrown).isInstanceOf(BadRequest.class);
        assertThat(thrown.getMessage()).isEqualTo(errorMsg);
    }
}
