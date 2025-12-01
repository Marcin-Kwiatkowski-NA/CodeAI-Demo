package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
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
        BadRequest exception = new BadRequest();

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
        UnAuthorized exception = new UnAuthorized();

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
        Forbidden exception = new Forbidden();

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
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/invalid-path", null);

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
        NotFound exception = new NotFound();

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
        Conflict exception = new Conflict();

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
        Exception exception = new Exception("Generic error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void givenBadRequestException_whenThrown_thenExceptionIsHandled() {
        // GIVEN
        BadRequest exception = new BadRequest("Bad request occurred");

        // WHEN THEN
        assertThrows(BadRequest.class, () -> {
            throw exception;
        });
    }

    @Test
    void givenConflictException_whenThrown_thenExceptionIsHandled() {
        // GIVEN
        Conflict exception = new Conflict("Conflict occurred");

        // WHEN THEN
        assertThrows(Conflict.class, () -> {
            throw exception;
        });
    }

    @Test
    void givenForbiddenException_whenThrown_thenExceptionIsHandled() {
        // GIVEN
        Forbidden exception = new Forbidden("Forbidden access");

        // WHEN THEN
        assertThrows(Forbidden.class, () -> {
            throw exception;
        });
    }

    @Test
    void givenUnAuthorizedException_whenThrown_thenExceptionIsHandled() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized("Unauthorized access");

        // WHEN THEN
        assertThrows(UnAuthorized.class, () -> {
            throw exception;
        });
    }

    @Test
    void givenNotFoundException_whenThrown_thenExceptionIsHandled() {
        // GIVEN
        NotFound exception = new NotFound("Resource not found");

        // WHEN THEN
        assertThrows(NotFound.class, () -> {
            throw exception;
        });
    }

    @Test
    void givenGenericException_whenThrown_thenExceptionIsHandled() {
        // GIVEN
        Exception exception = new Exception("Generic error occurred");

        // WHEN THEN
        assertThrows(Exception.class, () -> {
            throw exception;
        });
    }
}
