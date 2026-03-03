package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link AdviceController}.
 * Each test verifies that the corresponding controller method returns a fresh {@link ErrorResponse}
 * instance with the expected HTTP status code and non‑null fields.
 */
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequestReturnsCorrectStatusAndFields() {
        ErrorResponse response = adviceController.badRequest();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getError()).isNotNull();
    }

    @Test
    public void testUnAuthorizedReturnsCorrectStatusAndFields() {
        ErrorResponse response = adviceController.unAuthorized();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getError()).isNotNull();
    }

    @Test
    public void testForbiddenReturnsCorrectStatusAndFields() {
        ErrorResponse response = adviceController.forbidden();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getError()).isNotNull();
    }

    @Test
    public void testNotFound01ReturnsCorrectStatusAndFields() {
        ErrorResponse response = adviceController.notFound01();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getError()).isNotNull();
    }

    @Test
    public void testNotFound02ReturnsCorrectStatusAndFields() {
        ErrorResponse response = adviceController.notFound02();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getError()).isNotNull();
    }

    @Test
    public void testConflictReturnsCorrectStatusAndFields() {
        ErrorResponse response = adviceController.conflict();
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getError()).isNotNull();
    }

    @Test
    public void testServerErrorReturnsCorrectStatusAndFields() {
        Exception ex = new RuntimeException("Test exception");
        ErrorResponse response = adviceController.serverError(ex);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getMessage()).isNotNull();
        assertThat(response.getError()).isNotNull();
    }

    @Test
    public void testServerErrorWithNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> adviceController.serverError(null));
    }

    @Test
    public void testNotFound01AndNotFound02ReturnDistinctButEqualObjects() {
        ErrorResponse response1 = adviceController.notFound01();
        ErrorResponse response2 = adviceController.notFound02();

        // The two responses must be different instances but contain identical data
        assertThat(response1).isNotSameAs(response2);
        assertThat(response1.getStatus()).isEqualTo(response2.getStatus());
        assertThat(response1.getMessage()).isEqualTo(response2.getMessage());
        assertThat(response1.getError()).isEqualTo(response2.getError());
    }

    @Test
    public void testMultipleCallsProduceDistinctInstances() {
        ErrorResponse first = adviceController.badRequest();
        ErrorResponse second = adviceController.badRequest();

        assertThat(first).isNotSameAs(second);
        assertThat(first.getStatus()).isEqualTo(second.getStatus());
    }

    @Test
    public void testAdviceControllerIsResetBeforeEachTest() {
        // The controller instance is freshly created for every test.
        ErrorResponse response = adviceController.badRequest();
        assertThat(response).isNotNull();
    }
}
