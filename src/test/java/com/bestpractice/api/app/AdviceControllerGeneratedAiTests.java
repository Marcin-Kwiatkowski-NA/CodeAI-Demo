package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception ex = new Exception("Test exception");

        // WHEN
        ErrorResponse response = adviceController.serverError(ex);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    public void testServerErrorWithNullExceptionThrowsNullPointerException() {
        // GIVEN
        Exception ex = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(ex));
    }

    @Test
    public void testBadRequestExceptionHandlerDirectly() {
        // GIVEN
        BadRequest badRequestException = new BadRequest("Bad request occurred");

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
    }

    @Test
    public void testUnAuthorizedExceptionHandlerDirectly() {
        // GIVEN
        UnAuthorized unauthorizedException = new UnAuthorized("Unauthorized access");

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
    }

    @Test
    public void testForbiddenExceptionHandlerDirectly() {
        // GIVEN
        Forbidden forbiddenException = new Forbidden("Forbidden access");

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
    }

    @Test
    public void testNotFound01ExceptionHandlerDirectly() {
        // GIVEN
        NoHandlerFoundException noHandlerFoundException =
                new NoHandlerFoundException("GET", "/invalid-path", null);

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
    }

    @Test
    public void testNotFound02ExceptionHandlerDirectly() {
        // GIVEN
        NotFound notFoundException = new NotFound("Resource not found");

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
    }

    @Test
    public void testConflictExceptionHandlerDirectly() {
        // GIVEN
        Conflict conflictException = new Conflict("Conflict occurred");

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
    }

    @Test
    public void testServerErrorExceptionHandlerDirectly() {
        // GIVEN
        Exception genericException = new Exception("Generic error");

        // WHEN
        ErrorResponse response = adviceController.serverError(genericException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
    }
}
