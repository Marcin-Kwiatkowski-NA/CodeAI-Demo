package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Test
class AdviceControllerGeneratedAiTests {

    @Test
    void badRequest() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        // Since this is an exception handler, no action is explicitly taken.
        // The exception is caught, and the ErrorResponse is returned.
        // THEN
        ErrorResponse response = controller.badRequest();
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        // Since this is an exception handler, no action is explicitly taken.
        // The exception is caught, and the ErrorResponse is returned.
        // THEN
        ErrorResponse response = controller.unAuthorized();
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        // Since this is an exception handler, no action is explicitly taken.
        // The exception is caught, and the ErrorResponse is returned.
        // THEN
        ErrorResponse response = controller.forbidden();
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        // Since this is an exception handler, no action is explicitly taken.
        // The exception is caught, and the ErrorResponse is returned.
        // THEN
        ErrorResponse response = controller.notFound01();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        // Since this is an exception handler, no action is explicitly taken.
        // The exception is caught, and the ErrorResponse is returned.
        // THEN
        ErrorResponse response = controller.notFound02();
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        // Since this is an exception handler, no action is explicitly taken.
        // The exception is caught, and the ErrorResponse is returned.
        // THEN
        ErrorResponse response = controller.conflict();
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        // Since this is an exception handler, no action is explicitly taken.
        // The exception is caught, and the ErrorResponse is returned.
        // THEN
        ErrorResponse response = controller.serverError(new Exception("Test Exception"));
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
