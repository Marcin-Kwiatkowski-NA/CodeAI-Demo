package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThat;
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

import static org.assertj.core.api.Assertions.*;

public class AdviceControllerGeneratedAiTests {

    private AdviceController controller;

    @BeforeEach
    void setUp() {
        controller = new AdviceController();
    }

    @Test
    void badRequest_shouldReturnExpectedErrorResponse() {
        // GIVEN
        // No preconditions required

        // WHEN
        ErrorResponse response = controller.badRequest();

        // THEN
        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.getError()).isEqualTo("Bad request");
        assertThat(response.getMessage()).isEqualTo("Bad request parameter");
    }

    @Test
    void unAuthorized_shouldReturnExpectedErrorResponse() {
        // GIVEN
        // No preconditions required

        // WHEN
        ErrorResponse response = controller.unAuthorized();

        // THEN
        assertThat(response.getStatus()).isEqualTo(401);
        assertThat(response.getError()).isEqualTo("Unauthorized");
        assertThat(response.getMessage()).isEqualTo("Incorrect authentication info");
    }

    @Test
    void forbidden_shouldReturnExpectedErrorResponse() {
        // GIVEN
        // No preconditions required

        // WHEN
        ErrorResponse response = controller.forbidden();

        // THEN
        assertThat(response.getStatus()).isEqualTo(403);
        assertThat(response.getError()).isEqualTo("Forbidden");
        assertThat(response.getMessage()).isEqualTo("Not allowed");
    }

    @Test
    void notFound01_shouldReturnExpectedErrorResponse() {
        // GIVEN
        // No preconditions required

        // WHEN
        ErrorResponse response = controller.notFound01();

        // THEN
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getError()).isEqualTo("Not found");
        assertThat(response.getMessage()).isEqualTo("Not found path");
    }

    @Test
    void notFound02_shouldReturnExpectedErrorResponse() {
        // GIVEN
        // No preconditions required

        // WHEN
        ErrorResponse response = controller.notFound02();

        // THEN
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getError()).isEqualTo("Not found");
        assertThat(response.getMessage()).isEqualTo("Not found path");
    }

    @Test
    void conflict_shouldReturnExpectedErrorResponse() {
        // GIVEN
        // No preconditions required

        // WHEN
        ErrorResponse response = controller.conflict();

        // THEN
        assertThat(response.getStatus()).isEqualTo(409);
        assertThat(response.getError()).isEqualTo("Conflict");
        assertThat(response.getMessage()).isEqualTo("Already exist data");
    }

    @Test
    void serverError_shouldReturnExpectedErrorResponse() {
        // GIVEN
        Exception testException = new Exception("Test exception");

        // WHEN
        ErrorResponse response = controller.serverError(testException);

        // THEN
        assertThat(response.getStatus()).isEqualTo(500);
        assertThat(response.getError()).isEqualTo("Internal server error");
        assertThat(response.getMessage()).isEqualTo("Internal server error");
    }
}
