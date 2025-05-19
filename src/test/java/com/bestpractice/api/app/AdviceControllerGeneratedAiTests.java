package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

@Test
public class AdviceControllerGeneratedAiTests {

    private AdviceController controller;

    public AdviceControllerGeneratedAiTests() {
        controller = new AdviceController();
    }

    @org.junit.jupiter.api.Test
    void badRequest() {
        // GIVEN
        // None
        // WHEN
        // The method is called
        // THEN
        // The response status code is 400
        // The response body contains the expected error message
        AdviceController.ErrorResponse res = controller.badRequest();
        // Assertions
        Assertions.assertEquals(400, res.getStatus());
        Assertions.assertEquals("Bad request", res.getError());
        Assertions.assertEquals("Bad request parameter", res.getMessage());
    }

    @org.junit.jupiter.api.Test
    void unAuthorized() {
        // GIVEN
        // None
        // WHEN
        // The method is called
        // THEN
        // The response status code is 401
        // The response body contains the expected error message
        AdviceController.ErrorResponse res = controller.unAuthorized();
        // Assertions
        Assertions.assertEquals(401, res.getStatus());
        Assertions.assertEquals("Unauthorized", res.getError());
        Assertions.assertEquals("Incorrect authentication info", res.getMessage());
    }

    @org.junit.jupiter.api.Test
    void forbidden() {
        // GIVEN
        // None
        // WHEN
        // The method is called
        // THEN
        // The response status code is 403
        // The response body contains the expected error message
        AdviceController.ErrorResponse res = controller.forbidden();
        // Assertions
        Assertions.assertEquals(403, res.getStatus());
        Assertions.assertEquals("Forbidden", res.getError());
        Assertions.assertEquals("Not allowed", res.getMessage());
    }

    @org.junit.jupiter.api.Test
    void notFound01() {
        // GIVEN
        // None
        // WHEN
        // The method is called
        // THEN
        // The response is returned by shareNotFound()
        AdviceController.ErrorResponse res = controller.notFound01();
        // Assertions
        Assertions.assertEquals(404, res.getStatus());
        Assertions.assertEquals("Not found", res.getError());
        Assertions.assertEquals("Not found path", res.getMessage());
    }

    @org.junit.jupiter.api.Test
    void notFound02() {
        // GIVEN
        // None
        // WHEN
        // The method is called
        // THEN
        // The response is returned by shareNotFound()
        AdviceController.ErrorResponse res = controller.notFound02();
        // Assertions
        Assertions.assertEquals(404, res.getStatus());
        Assertions.assertEquals("Not found", res.getError());
        Assertions.assertEquals("Not found path", res.getMessage());
    }

    @org.junit.jupiter.api.Test
    void conflict() {
        // GIVEN
        // None
        // WHEN
        // The method is called
        // THEN
        // The response status code is 409
        // The response body contains the expected error message
        AdviceController.ErrorResponse res = controller.conflict();
        // Assertions
        Assertions.assertEquals(409, res.getStatus());
        Assertions.assertEquals("Conflict", res.getError());
        Assertions.assertEquals("Already exist data", res.getMessage());
    }
}
