package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
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

public class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void testSetAndGetStatus() {
        // GIVEN: An ErrorResponse instance and a status value
        int status = 404;

        // WHEN: Setting the status
        errorResponse.setStatus(status);

        // THEN: The status should be correctly retrieved
        assertEquals(status, errorResponse.getStatus());
    }

    @Test
    void testSetAndGetError() {
        // GIVEN: An ErrorResponse instance and an error message
        String error = "Not Found";

        // WHEN: Setting the error message
        errorResponse.setError(error);

        // THEN: The error message should be correctly retrieved
        assertEquals(error, errorResponse.getError());
    }

    @Test
    void testSetAndGetMessage() {
        // GIVEN: An ErrorResponse instance and a message
        String message = "The requested resource was not found.";

        // WHEN: Setting the message
        errorResponse.setMessage(message);

        // THEN: The message should be correctly retrieved
        assertEquals(message, errorResponse.getMessage());
    }
}
