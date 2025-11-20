package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    void givenBadRequestException_whenHandled_thenReturnsBadRequestErrorResponse() {
        // GIVEN
        BadRequest badRequestException = new BadRequest();

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getError()).isEqualTo("Bad request");
        assertThat(response.getMessage()).isEqualTo("Bad request parameter");
    }

    @Test
    void givenUnAuthorizedException_whenHandled_thenReturnsUnAuthorizedErrorResponse() {
        // GIVEN
        UnAuthorized unAuthorizedException = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
        assertThat(response.getError()).isEqualTo("Unauthorized");
        assertThat(response.getMessage()).isEqualTo("Incorrect authentication info");
    }

    @Test
    void givenForbiddenException_whenHandled_thenReturnsForbiddenErrorResponse() {
        // GIVEN
        Forbidden forbiddenException = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
        assertThat(response.getError()).isEqualTo("Forbidden");
        assertThat(response.getMessage()).isEqualTo("Not allowed");
    }

    @Test
    void givenNoHandlerFoundException_whenHandled_thenReturnsNotFoundErrorResponse() {
        // GIVEN
        NoHandlerFoundException noHandlerFoundException = new NoHandlerFoundException("GET", "/invalid-path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getError()).isEqualTo("Not found");
        assertThat(response.getMessage()).isEqualTo("Not found path");
    }

    @Test
    void givenNotFoundException_whenHandled_thenReturnsNotFoundErrorResponse() {
        // GIVEN
        NotFound notFoundException = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getError()).isEqualTo("Not found");
        assertThat(response.getMessage()).isEqualTo("Not found path");
    }

    @Test
    void givenConflictException_whenHandled_thenReturnsConflictErrorResponse() {
        // GIVEN
        Conflict conflictException = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(response.getError()).isEqualTo("Conflict");
        assertThat(response.getMessage()).isEqualTo("Already exist data");
    }

    @Test
    void givenGenericException_whenHandled_thenReturnsInternalServerErrorResponse() {
        // GIVEN
        Exception genericException = new Exception("Generic error");

        // WHEN
        ErrorResponse response = adviceController.serverError(genericException);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getError()).isEqualTo("Internal server error");
        assertThat(response.getMessage()).isEqualTo("Internal server error");
    }
}
