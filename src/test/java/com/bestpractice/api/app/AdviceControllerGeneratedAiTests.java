package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
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
        // WHEN: The request is handled by the controller
        // THEN: An error response with status code 400 and appropriate message is returned
        ErrorResponse response = controller.badRequest();
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN: An unauthorized request
        // WHEN: The request is handled by the controller
        // THEN: An error response with status code 401 and appropriate message is returned
        ErrorResponse response = controller.unAuthorized();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN: A forbidden request
        // WHEN: The request is handled by the controller
        // THEN: An error response with status code 403 and appropriate message is returned
        ErrorResponse response = controller.forbidden();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN: A request for a non-existent path
        // WHEN: The request is handled by the controller
        // THEN: An error response with status code 404 and appropriate message is returned
        ErrorResponse response = controller.notFound01();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN: A request for a non-existent resource
        // WHEN: The request is handled by the controller
        // THEN: An error response with status code 404 and appropriate message is returned
        ErrorResponse response = controller.notFound02();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN: A request that would cause a conflict
        // WHEN: The request is handled by the controller
        // THEN: An error response with status code 409 and appropriate message is returned
        ErrorResponse response = controller.conflict();
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN: An unexpected exception occurs
        // WHEN: The exception is handled by the controller
        // THEN: An error response with status code 500 and appropriate message is returned
        ErrorResponse response = controller.serverError(new Exception("Test exception"));
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
