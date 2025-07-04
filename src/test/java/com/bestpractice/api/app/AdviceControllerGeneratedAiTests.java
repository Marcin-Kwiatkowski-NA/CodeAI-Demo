package com.bestpractice.api.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
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
        // THEN: The controller returns a 400 status with an error message
        ErrorResponse response = controller.badRequest();
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN: An unauthorized request
        // WHEN: The controller receives an UnAuthorized exception
        // THEN: The controller returns a 401 status with an error message
        ErrorResponse response = controller.unAuthorized();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN: A forbidden request
        // WHEN: The controller receives a Forbidden exception
        // THEN: The controller returns a 403 status with an error message
        ErrorResponse response = controller.forbidden();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN: A scenario where a resource is not found
        // WHEN: The controller receives a NoHandlerFoundException
        // THEN: The controller returns a 404 status with an error message
        ErrorResponse response = controller.notFound01();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN: A scenario where a resource is not found
        // WHEN: The controller receives a NotFound exception
        // THEN: The controller returns a 404 status with an error message
        ErrorResponse response = controller.notFound02();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN: A conflict scenario (e.g., duplicate data)
        // WHEN: The controller receives a Conflict exception
        // THEN: The controller returns a 409 status with an error message
        ErrorResponse response = controller.conflict();
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN: An unexpected exception occurs
        // WHEN: The controller receives an Exception
        // THEN: The controller returns a 500 status with an error message
        ErrorResponse response = controller.serverError(new Exception("Simulated error"));
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
