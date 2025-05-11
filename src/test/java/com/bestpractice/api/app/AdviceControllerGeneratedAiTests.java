package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdviceControllerGeneratedAiTests {

    private AdviceController controller;

    @BeforeEach
    void setUp() {
        controller = new AdviceController();
    }

    @Test
    void badRequest() {
        // GIVEN: A request with invalid parameters
        // WHEN: The controller receives a BadRequest exception
        // THEN: An ErrorResponse with status 400, error "Bad request", and message "Bad request parameter" is returned.
        ErrorResponse response = controller.badRequest();
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN: An UnAuthorized exception is thrown
        // WHEN: The controller receives the UnAuthorized exception
        // THEN: An ErrorResponse with status 401, error "Unauthorized", and message "Incorrect authentication info" is returned.
        ErrorResponse response = controller.unAuthorized();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN: A Forbidden exception is thrown
        // WHEN: The controller receives the Forbidden exception
        // THEN: An ErrorResponse with status 403, error "Forbidden", and message "Not allowed" is returned.
        ErrorResponse response = controller.forbidden();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN: NoHandlerFoundException is thrown
        // WHEN: The controller receives the NoHandlerFoundException
        // THEN: The shareNotFound() method is called and its result is returned.
        ErrorResponse response = controller.notFound01();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN: A NotFound exception is thrown
        // WHEN: The controller receives the NotFound exception
        // THEN: The shareNotFound() method is called and its result is returned.
        ErrorResponse response = controller.notFound02();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN: A Conflict exception is thrown
        // WHEN: The controller receives the Conflict exception
        // THEN: An ErrorResponse with status 409, error "Conflict", and message "Already exist data" is returned.
        ErrorResponse response = controller.conflict();
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN: An Exception is thrown
        // WHEN: The controller receives the Exception
        // THEN: An ErrorResponse with status 500, error "Internal server error", and message "Internal server error" is returned.
        // The exception stack trace is printed.
        ErrorResponse response = controller.serverError(new Exception("Test Exception"));
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
