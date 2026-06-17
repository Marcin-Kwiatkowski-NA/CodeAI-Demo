package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    void testBadRequestReturnsExpectedErrorResponse() {
        BadRequest exception = new BadRequest("Invalid parameter");
        ErrorResponse response = adviceController.badRequest();
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void testUnAuthorizedReturnsExpectedErrorResponse() {
        UnAuthorized exception = new UnAuthorized("Unauthorized access");
        ErrorResponse response = adviceController.unAuthorized();
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void testForbiddenReturnsExpectedErrorResponse() {
        Forbidden exception = new Forbidden("Access denied");
        ErrorResponse response = adviceController.forbidden();
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void testNotFound01ReturnsExpectedErrorResponse() {
        NoHandlerFoundException exception = new NoHandlerFoundException("GET", "/unknown", null);
        ErrorResponse response = adviceController.notFound01();
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testNotFound02ReturnsExpectedErrorResponse() {
        NotFound exception = new NotFound("Resource missing");
        ErrorResponse response = adviceController.notFound02();
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testConflictReturnsExpectedErrorResponse() {
        Conflict exception = new Conflict("Duplicate data");
        ErrorResponse response = adviceController.conflict();
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void testServerErrorReturnsExpectedErrorResponse() {
        Exception exception = new Exception("Unexpected error");
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithNullExceptionThrowsNullPointerException() {
        Exception exception = null;
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    void testServerErrorWithEmptyMessageStillReturnsValidResponse() {
        Exception exception = new Exception("");
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithWhitespaceMessageStillReturnsValidResponse() {
        Exception exception = new Exception("   ");
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithLongMessageStillReturnsValidResponse() {
        String longMessage = "A".repeat(10000);
        Exception exception = new Exception(longMessage);
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithSpecialCharactersMessageStillReturnsValidResponse() {
        Exception exception = new Exception("!@#$%^&*()_+");
        ErrorResponse response = adviceController.serverError(exception);
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testBadRequestConsistencyAcrossMultipleCalls() {
        BadRequest exception = new BadRequest("Invalid parameter");
        ErrorResponse firstResponse = adviceController.badRequest();
        ErrorResponse secondResponse = adviceController.badRequest();
        assertNotNull(firstResponse);
        assertNotNull(secondResponse);
        assertEquals(firstResponse.getStatus(), secondResponse.getStatus());
        assertEquals(firstResponse.getError(), secondResponse.getError());
        assertEquals(firstResponse.getMessage(), secondResponse.getMessage());
    }

    @Test
    void testNotFoundConsistencyAcrossBothHandlers() {
        NotFound notFoundException = new NotFound("Missing resource");
        NoHandlerFoundException noHandlerFoundException = new NoHandlerFoundException("GET", "/missing", null);
        ErrorResponse response1 = adviceController.notFound01();
        ErrorResponse response2 = adviceController.notFound02();
        assertNotNull(response1);
        assertNotNull(response2);
        assertEquals(response1.getStatus(), response2.getStatus());
        assertEquals(response1.getError(), response2.getError());
        assertEquals(response1.getMessage(), response2.getMessage());
    }

    @Test
    void testConflictResponseHasExpectedValues() {
        Conflict conflict = new Conflict("Duplicate entry");
        ErrorResponse response = adviceController.conflict();
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }
}
