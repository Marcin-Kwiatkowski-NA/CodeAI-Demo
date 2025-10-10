package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAnnotations.class)
class AdviceControllerGeneratedAiTests {

    @BeforeEach
    void setup() {
        // Reset state before each test
    }

    @Test
    void badRequest() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        String result = controller.badRequest();
        // THEN
        assertEquals(400, result.getStatus());
        assertEquals("Bad request", result.getError());
        assertEquals("Bad request parameter", result.getMessage());
    }

    @Test
    void unauthorized() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        String result = controller.unAuthorized();
        // THEN
        assertEquals(401, result.getStatus());
        assertEquals("Unauthorized", result.getError());
        assertEquals("Incorrect authentication info", result.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        String result = controller.forbidden();
        // THEN
        assertEquals(403, result.getStatus());
        assertEquals("Forbidden", result.getError());
        assertEquals("Not allowed", result.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        String result = controller.notFound01();
        // THEN
        assertEquals(404, result.getStatus());
        assertEquals("Not found", result.getError());
        assertEquals("Not found path", result.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        String result = controller.notFound02();
        // THEN
        assertEquals(404, result.getStatus());
        assertEquals("Not found", result.getError());
        assertEquals("Not found path", result.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        String result = controller.conflict();
        // THEN
        assertEquals(409, result.getStatus());
        assertEquals("Conflict", result.getError());
        assertEquals("Already exist data", result.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN
        AdviceController controller = new AdviceController();
        // WHEN
        String result = controller.serverError(new Exception("Test exception"));
        // THEN
        assertEquals(500, result.getStatus());
        assertEquals("Internal server error", result.getError());
        assertEquals("Internal server error", result.getMessage());
    }
}
