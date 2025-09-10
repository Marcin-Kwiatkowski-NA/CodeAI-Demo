package com.bestpractice.api.app;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import com.bestpractice.api.domain.model.ErrorResponse;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;

import org.junit.jupiter.api.extension.junit.jupiter.api.Extension;

@ExtendWith(MyOpenApiExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    void badRequest() {
        // GIVEN
        // No preconditions set up
        // WHEN
        // The method is called
        // THEN
        ErrorResponse response = adviceController.badRequest();
        // Assert that the status code is 400
        org.junit.jupiter.api.Assertions.assertEquals(400, response.getStatus());
        // Assert that the error message is "Bad request"
        org.junit.jupiter.api.Assertions.assertEquals("Bad request", response.getError());
        // Assert that the message is "Bad request parameter"
        org.junit.jupiter.api.Assertions.assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN
        // No preconditions set up
        // WHEN
        // The method is called
        // THEN
        ErrorResponse response = adviceController.unAuthorized();
        // Assert that the status code is 401
        org.junit.jupiter.api.Assertions.assertEquals(401, response.getStatus());
        // Assert that the error message is "Unauthorized"
        org.junit.jupiter.api.Assertions.assertEquals("Unauthorized", response.getError());
        // Assert that the message is "Incorrect authentication info"
        org.junit.jupiter.api.Assertions.assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN
        // No preconditions set up
        // WHEN
        // The method is called
        // THEN
        ErrorResponse response = adviceController.forbidden();
        // Assert that the status code is 403
        org.junit.jupiter.api.Assertions.assertEquals(403, response.getStatus());
        // Assert that the error message is "Forbidden"
        org.junit.jupiter.api.Assertions.assertEquals("Forbidden", response.getError());
        // Assert that the message is "Not allowed"
        org.junit.jupiter.api.Assertions.assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN
        // No preconditions set up
        // WHEN
        // The method is called
        // THEN
        ErrorResponse response = adviceController.notFound01();
        // Assert that the status code is 404
        org.junit.jupiter.api.Assertions.assertEquals(404, response.getStatus());
        // Assert that the error message is "Not found"
        org.junit.jupiter.api.Assertions.assertEquals("Not found", response.getError());
        // Assert that the message is "Not found path"
        org.junit.jupiter.api.Assertions.assertEquals("Not found path", response.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN
        // No preconditions set up
        // WHEN
        // The method is called
        // THEN
        ErrorResponse response = adviceController.notFound02();
        // Assert that the status code is 404
        org.junit.jupiter.api.Assertions.assertEquals(404, response.getStatus());
        // Assert that the error message is "Not found"
        org.junit.jupiter.api.Assertions.assertEquals("Not found", responsejava
        // Assert that the message is "Not found path"
        org.junit.jupiter.api.Assertions.assertEquals("Not found path", response.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN
        // No preconditions set up
        // WHEN
        // The method is called
        // THEN
        ErrorResponse response = adviceController.conflict();
        // Assert that the status code is 409
        org.junit.jupiter.api.Assertions.assertEquals(409, response.getStatus());
        // Assert that the error message is "Conflict"
        org.junit.jupiter.api.Assertions.assertEquals("Conflict", response.getError());
        // Assert that the message is "Already exist data"
        org.junit.jupiter.api.Assertions.assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN
        // No preconditions set up
        // WHEN
        // The method is called
        // THEN
        ErrorResponse response = adviceController.serverError(new Exception("Test exception"));
        // Assert that the status code is 500
        org.junit.jupiter.api.Assertions.assertEquals(500, response.getStatus());
        // Assert that the error message is "Internal server error"
        org.junit.jupiter.api.Assertions.assertEquals("Internal server error", response.getError());
        // Assert that the message is "Internal server error"
        org.junit.jupiter.api.Assertions.assertEquals("Internal server error", response.getMessage());
    }
}