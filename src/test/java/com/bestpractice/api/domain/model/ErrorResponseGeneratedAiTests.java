package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class ErrorResponseGeneratedAiTests {

    private ErrorResponse errorResponse;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
        objectMapper = new ObjectMapper();
    }

    @Test
    void givenValidStatusAndError_WhenCreateResponse_ThenStatusAndErrorAreSet() throws JsonProcessingException {
        // GIVEN
        int status = 400;
        String error = "Bad Request";
        String message = "Invalid input provided";

        // WHEN
        errorResponse.setStatus(status);
        errorResponse.setError(error);
        errorResponse.setMessage(message);

        // THEN
        assertThat(errorResponse.getStatus()).isEqualTo(status);
        assertThat(errorResponse.getError()).isEqualTo(error);
        assertThat(errorResponse.getMessage()).isEqualTo(message);
    }

    @Test
    void givenNullStatus_WhenCreateResponse_ThenStatusIsZero() {
        // GIVEN
        errorResponse.setStatus(0);

        // WHEN
        int actualStatus = errorResponse.getStatus();

        // THEN
        assertThat(actualStatus).isEqualTo(0);
    }

    @Test
    void givenEmptyError_WhenCreateResponse_ThenErrorIsEmptyString() {
        // GIVEN
        errorResponse.setError(null);

        // WHEN
        String actualError = errorResponse.getError();

        // THEN
        assertThat(actualError).isEmpty();
    }

    @Test
    void givenInvalidJson_WhenSerialize_ThenJsonProcessingExceptionIsThrown() {
        // GIVEN
        errorResponse.setStatus(500);
        errorResponse.setError("Internal Server Error");
        errorResponse.setMessage("Server is down");

        // WHEN & THEN
        assertThrows(JsonProcessingException.class, () -> {
            objectMapper.writeValueAsString(errorResponse);
        });
    }

    @Test
    void givenEmptyResponse_WhenSerialize_ThenEmptyJsonIsGenerated() throws JsonProcessingException {
        // GIVEN
        errorResponse.setStatus(0);
        errorResponse.setError(null);
        errorResponse.setMessage(null);

        // WHEN
        String json = objectMapper.writeValueAsString(errorResponse);

        // THEN
        assertThat(json).isEqualTo("{\"status\":0,\"error\":null,\"message\":null}");
    }

    @Test
    void givenValidResponse_WhenToString_ThenReturnsFormattedString() {
        // GIVEN
        errorResponse.setStatus(404);
        errorResponse.setError("Not Found");
        errorResponse.setMessage("Resource not found");

        // WHEN
        String result = errorResponse.toString();

        // THEN
        assertThat(result).contains("status=404");
        assertThat(result).contains("error=Not Found");
        assertThat(result).contains("message=Resource not found");
    }
}
