package com.bestpractice.api.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;

public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    public void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    public void testBadRequest() {
        // GIVEN
        BadRequest exception = new BadRequest("Bad request parameter");

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void testUnAuthorized() {
        // GIVEN
        UnAuthorized exception = new UnAuthorized("Incorrect authentication info");

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void testForbidden() {
        // GIVEN
        Forbidden exception = new Forbidden("Not allowed");

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void testNotFound01() {
        // GIVEN
        NoHandlerFoundException exception = new NoHandlerFoundException(null, null, new HttpHeaders());

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testNotFound02() {
        // GIVEN
        NotFound exception = new NotFound("Not found path");

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void testConflict() {
        // GIVEN
        Conflict exception = new Conflict("Already exist data");

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void testServerError() {
        // GIVEN
        Exception exception = new Exception("Internal server error");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
