package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertNotNull(response);
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
        assertNotNull(response);
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
        assertNotNull(response);
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
        assertNotNull(response);
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
        assertNotNull(response);
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
        assertNotNull(response);
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
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void givenBadRequestException_whenThrown_thenHandledCorrectly() {
        // GIVEN
        BadRequest badRequestException = new BadRequest("Bad request occurred");

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void givenConflictException_whenThrown_thenHandledCorrectly() {
        // GIVEN
        Conflict conflictException = new Conflict("Conflict occurred");

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void givenForbiddenException_whenThrown_thenHandledCorrectly() {
        // GIVEN
        Forbidden forbiddenException = new Forbidden("Forbidden occurred");

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void givenUnAuthorizedException_whenThrown_thenHandledCorrectly() {
        // GIVEN
        UnAuthorized unAuthorizedException = new UnAuthorized("Unauthorized occurred");

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void givenNotFoundException_whenThrown_thenHandledCorrectly() {
        // GIVEN
        NotFound notFoundException = new NotFound("Not found occurred");

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void givenNoHandlerFoundException_whenThrown_thenHandledCorrectly() {
        // GIVEN
        NoHandlerFoundException noHandlerFoundException = new NoHandlerFoundException("GET", "/invalid-path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }
}
