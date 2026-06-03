package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    void badRequest_ShouldReturnBadRequestStatus() {
        // GIVEN
        AdviceController controller = adviceController;

        // WHEN
        ResponseEntity<String> response = controller.badRequest();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void unAuthorized_ShouldReturnUnauthorizedStatus() {
        // GIVEN
        AdviceController controller = adviceController;

        // WHEN
        ResponseEntity<String> response = controller.unAuthorized();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void forbidden_ShouldReturnForbiddenStatus() {
        // GIVEN
        AdviceController controller = adviceController;

        // WHEN
        ResponseEntity<String> response = controller.forbidden();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void notFound01_ShouldReturnNotFoundStatus() {
        // GIVEN
        AdviceController controller = adviceController;

        // WHEN
        ResponseEntity<String> response = controller.notFound01();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void notFound02_ShouldReturnNotFoundStatus() {
        // GIVEN
        AdviceController controller = adviceController;

        // WHEN
        ResponseEntity<String> response = controller.notFound02();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void conflict_ShouldReturnConflictStatus() {
        // GIVEN
        AdviceController controller = adviceController;

        // WHEN
        ResponseEntity<String> response = controller.conflict();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void serverError_ShouldReturnInternalServerErrorStatus_WhenExceptionProvided() {
        // GIVEN
        AdviceController controller = adviceController;
        Exception exception = new Exception("Test Exception");

        // WHEN
        ResponseEntity<String> response = controller.serverError(exception);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void serverError_ShouldReturnInternalServerErrorStatus_WhenRuntimeExceptionProvided() {
        // GIVEN
        AdviceController controller = adviceController;
        RuntimeException exception = new RuntimeException("Runtime Exception");

        // WHEN
        ResponseEntity<String> response = controller.serverError(exception);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
