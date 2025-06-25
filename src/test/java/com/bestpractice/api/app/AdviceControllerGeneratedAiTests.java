package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdviceControllerGeneratedAiTests {

    @Test
    public void badRequest() {
        AdviceController controller = new AdviceController();
        ErrorResponse response = controller.badRequest();
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    public void unAuthorized() {
        AdviceController controller = new AdviceController();
        ErrorResponse response = controller.unAuthorized();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    public void forbidden() {
        AdviceController controller = new AdviceController();
        ErrorResponse response = controller.forbidden();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    public void notFound01() {
        AdviceController controller = new AdviceController();
        ErrorResponse response = controller.notFound01();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void notFound02() {
        AdviceController controller = new AdviceController();
        ErrorResponse response = controller.notFound02();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    public void conflict() {
        AdviceController controller = new AdviceController();
        ErrorResponse response = controller.conflict();
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    public void serverError() {
        AdviceController controller = new AdviceController();
        ErrorResponse response = controller.serverError(new Exception("Test exception"));
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
