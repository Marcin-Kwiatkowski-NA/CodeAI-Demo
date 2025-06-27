package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@MyExtensions
public class AdviceControllerGeneratedAiTests {

    private AdviceController controller;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        controller = new AdviceController();
    }

    @Test
    void badRequest() {
        // GIVEN: A request with invalid parameters
        // WHEN: The controller receives a BadRequest exception
        // THEN: An ErrorResponse with status 400, error "Bad request", and message "Bad request parameter" is returned.
        AdviceController.BadRequest ex = new AdviceController.BadRequest("Bad request parameter");
        AdviceController.ErrorResponse res = controller.badRequest();
        assertEquals(400, res.getStatus());
        assertEquals("Bad request", res.getError());
        assertEquals("Bad request parameter", res.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN: An Unauthorized exception is thrown
        // WHEN: The controller receives the UnAuthorized exception
        // THEN: An ErrorResponse with status 401, error "Unauthorized", and message "Incorrect authentication info" is returned.
        AdviceController.UnAuthorized ex = new AdviceController.UnAuthorized("Incorrect authentication info");
        AdviceController.ErrorResponse res = controller.unAuthorized();
        assertEquals(401, res.getStatus());
        assertEquals("Unauthorized", res.getError());
        assertEquals("Incorrect authentication info", res.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN: A Forbidden exception is thrown
        // WHEN: The controller receives the Forbidden exception
        // THEN: An ErrorResponse with status 403, error "Forbidden", and message "Not allowed" is returned.
        AdviceController.Forbidden ex = new AdviceController.Forbidden("Not allowed");
        AdviceController.ErrorResponse res = controller.forbidden();
        assertEquals(403, res.getStatus());
        assertEquals("Forbidden", res.getError());
        assertEquals("Not allowed", res.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN: NoHandlerFoundException is thrown
        // WHEN: The controller receives the NoHandlerFoundException
        // THEN: The shareNotFound() method is called and its result is returned.
        AdviceController.ErrorResponse res = controller.notFound01();
        assertEquals(404, res.getStatus());
        assertEquals("Not found", res.getError());
        assertEquals("Not found path", res.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN: A NotFound exception is thrown
        // WHEN: The controller receives the NotFound exception
        // THEN: The shareNotFound() method is called and its result is returned.
        AdviceController.ErrorResponse res = controller.notFound02();
        assertEquals(404, res.getStatus());
        assertEquals("Not found", res.getError());
        assertEquals("Not found path", res.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN: A Conflict exception is thrown
        // WHEN: The controller receives the Conflict exception
        // THEN: An ErrorResponse with status 409, error "Conflict", and message "Already exist data" is returned.
        AdviceController.Conflict ex = new AdviceController.Conflict("Already exist data");
        AdviceController.ErrorResponse res = controller.conflict();
        assertEquals(409, res.getStatus());
        assertEquals("Conflict", res.getError());
        assertEquals("Already exist data", res.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN: Any exception is thrown
        // WHEN: The controller receives any exception
        // THEN: An ErrorResponse with status 500, error "Internal server error", and message "Internal server error" is returned.
        AdviceController.ErrorResponse res = controller.serverError(new Exception("Internal server error"));
        assertEquals(500, res.getStatus());
        assertEquals("Internal server error", res.getError());
        assertEquals("Internal server error", res.getMessage());
    }
}
