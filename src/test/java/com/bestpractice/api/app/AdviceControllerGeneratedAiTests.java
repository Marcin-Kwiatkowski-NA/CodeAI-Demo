package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
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
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void givenUnAuthorizedException_whenHandled_thenReturnsUnAuthorizedErrorResponse() {
        // GIVEN
        UnAuthorized unAuthorizedException = new UnAuthorized();

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void givenForbiddenException_whenHandled_thenReturnsForbiddenErrorResponse() {
        // GIVEN
        Forbidden forbiddenException = new Forbidden();

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void givenNoHandlerFoundException_whenHandled_thenReturnsNotFoundErrorResponse() {
        // GIVEN
        NoHandlerFoundException noHandlerFoundException = new NoHandlerFoundException("GET", "/invalid-path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void givenNotFoundException_whenHandled_thenReturnsNotFoundErrorResponse() {
        // GIVEN
        NotFound notFoundException = new NotFound();

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void givenConflictException_whenHandled_thenReturnsConflictErrorResponse() {
        // GIVEN
        Conflict conflictException = new Conflict();

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void givenGenericException_whenHandled_thenReturnsInternalServerErrorResponse() {
        // GIVEN
        Exception genericException = new Exception("Generic error");

        // WHEN
        ErrorResponse response = adviceController.serverError(genericException);

        // THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void givenNullException_whenHandled_thenThrowsNullPointerException() {
        // GIVEN
        Exception nullException = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(nullException));
    }

    @Test
    void givenUnhandledException_whenHandled_thenReturnsInternalServerErrorResponse() {
        // GIVEN
        RuntimeException unhandledException = new RuntimeException("Unhandled exception");

        // WHEN
        ErrorResponse response = adviceController.serverError(unhandledException);

        // THEN
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void givenNoHandlerFoundException_whenHandled_thenThrowsException() {
        // GIVEN
        NoHandlerFoundException noHandlerFoundException = new NoHandlerFoundException("GET", "/invalid-path", null);

        //    // WHEN & THEN
        assertThrows(NoHandlerFoundException.class, () -> {
            throw noHandlerFoundException;
        });
    }
}
