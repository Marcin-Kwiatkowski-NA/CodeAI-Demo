package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    void testBadRequest() {
        BadRequest exception = new BadRequest();
        ErrorResponse response = adviceController.badRequest();
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void testUnAuthorized() {
        UnAuthorized exception = new UnAuthorized();
        ErrorResponse response = adviceController.unAuthorized();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void testForbidden() {
        Forbidden exception = new Forbidden();
        ErrorResponse response = adviceController.forbidden();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void testNotFound01() {
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/path", null);
        ErrorResponse response = adviceController.notFound01();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testNotFound02() {
        NotFound exception = new NotFound();
        ErrorResponse response = adviceController.notFound02();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testConflict() {
        Conflict exception = new Conflict();
        ErrorResponse response = adviceController.conflict();
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void testServerError() {
        Exception exception = new Exception("Test exception");
        ErrorResponse response = adviceController.serverError(exception);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithNullException() {
        Exception exception = null;
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    void testServerErrorWithRuntimeExceptionCause() {
        RuntimeException runtimeException = new RuntimeException("Runtime failure");
        ErrorResponse response = adviceController.serverError(runtimeException);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testBadRequestBoundaryValues() {
        ErrorResponse response = adviceController.badRequest();
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError().trim());
        assertEquals("Bad request parameter", response.getMessage().trim());
    }

    @Test
    void testUnAuthorizedBoundaryValues() {
        ErrorResponse response = adviceController.unAuthorized();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError().strip());
        assertEquals("Incorrect authentication info", response.getMessage().strip());
    }

    @Test
    void testForbiddenBoundaryValues() {
        ErrorResponse response = adviceController.forbidden();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError().strip());
        assertEquals("Not allowed", response.getMessage().strip());
    }

    @Test
    void testNotFoundBoundaryValues() {
        ErrorResponse response = adviceController.notFound02();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError().strip());
        assertEquals("Not found path", response.getMessage().strip());
    }

    @Test
    void testConflictBoundaryValues() {
        ErrorResponse response = adviceController.conflict();
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError().strip());
        assertEquals("Already exist data", response.getMessage().strip());
    }

    @Test
    void testServerErrorBoundaryValues() {
        Exception exception = new Exception("");
        ErrorResponse response = adviceController.serverError(exception);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError().strip());
        assertEquals("Internal server error", response.getMessage().strip());
    }

    @Test
    void testServerErrorWithEmptyMessageException() {
        Exception exception = new Exception("");
        ErrorResponse response = adviceController.serverError(exception);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithWhitespaceMessageException() {
        Exception exception = new Exception("   ");
        ErrorResponse response = adviceController.serverError(exception);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithExtremeNumericValues() {
        Exception exception = new Exception("Extreme numeric test");
        ErrorResponse response = adviceController.serverError(exception);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
        assertEquals(true, Integer.MAX_VALUE > response.getStatus());
        assertEquals(true, Integer.MIN_VALUE < response.getStatus());
    }

    @Test
    void testServerErrorWithNullMessageException() {
        Exception exception = new Exception((String) null);
        ErrorResponse response = adviceController.serverError(exception);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
