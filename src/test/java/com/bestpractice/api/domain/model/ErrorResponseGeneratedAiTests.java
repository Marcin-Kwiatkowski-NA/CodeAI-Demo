package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void shouldSetAndGetStatus() {
        // GIVEN
        int expectedStatus = 404;

        // WHEN
        errorResponse.setStatus(expectedStatus);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(expectedStatus);
    }

    @Test
    void shouldSetAndGetError() {
        // GIVEN
        String expectedError = "Not Found";

        // WHEN
        errorResponse.setError(expectedError);

        // THEN
        assertThat(errorResponse.getError()).isEqualTo(expectedError);
    }

    @Test
    void shouldSetAndGetMessage() {
        // GIVEN
        String expectedMessage = "Resource not found";

        // WHEN
        errorResponse.setMessage(expectedMessage);

        // THEN
        assertThat(errorResponse.getMessage()).isEqualTo(expectedMessage);
    }
}
