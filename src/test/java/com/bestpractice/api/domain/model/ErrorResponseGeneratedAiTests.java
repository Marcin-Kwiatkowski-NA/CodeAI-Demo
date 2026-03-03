package com.bestpractice.api.domain.model;

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
import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testDefaultValues() {
        // GIVEN an ErrorResponse instance
        // WHEN no setters are called
        // THEN all fields should have their default values
        assertEquals(0, errorResponse.getStatus());
        assertThat(errorResponse.getError()).isNull();
        assertThat(errorResponse.getMessage()).isNull();
    }

    @Test
    void testSetAndGetStatus() {
        // GIVEN an ErrorResponse instance
        // WHEN status is set to 404
        // THEN getStatus should return 404
        errorResponse.setStatus(404);
        assertEquals(404, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN an ErrorResponse instance
        // WHEN error is set to "Not Found"
        // THEN getError should return "Not Found"
        errorResponse.setError("Not Found");
        assertThat(errorResponse.getError()).isEqualTo("Not Found");
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN an ErrorResponse instance
        // WHEN message is set to "The requested resource was not found."
        // THEN getMessage should return the set message
        errorResponse.setMessage("The requested resource was not found.");
        assertThat(errorResponse.getMessage()).isEqualTo("The requested resource was not found.");
    }

    @Test
    void testSetMultipleFields() {
        // GIVEN an ErrorResponse instance
        // WHEN multiple fields are set
        // THEN each getter should return the corresponding value
        errorResponse.setStatus(500);
        errorResponse.setError("Internal Server Error");
        errorResponse.setMessage("An unexpected error occurred.");

        assertEquals(500, errorResponse.getStatus());
        assertThat(errorResponse.getError()).isEqualTo("Internal Server Error");
        assertThat(errorResponse.getMessage()).isEqualTo("An unexpected error occurred.");
    }

    @Test
    void testSetNullErrorAndMessage() {
        // GIVEN an ErrorResponse instance
        // WHEN error and message are explicitly set to null
        // THEN getters should return null
        errorResponse.setError(null);
        errorResponse.setMessage(null);

        assertThat(errorResponse.getError()).isNull();
        assertThat(errorResponse.getMessage()).isNull();
    }
}
