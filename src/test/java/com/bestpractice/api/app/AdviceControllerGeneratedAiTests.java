package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class AdviceControllerGeneratedAiTests {

    private AdviceController controller;

    @BeforeEach
    void setUp() {
        controller = new AdviceController();
    }

    @Test
    void badRequest() {
        // GIVEN: A request with invalid parameters
        // WHEN: The controller handles a BadRequest exception
        // THEN: An ErrorResponse with status 400, error message "Bad request", and message "Bad request parameter" is returned
        ErrorResponse res = controller.badRequest();
        assertEquals(400, res.getStatus());
        assertEquals("Bad request", res.getError());
        assertEquals("Bad request parameter", res.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN: An UnAuthorized exception is thrown
        // WHEN: The controller handles the exception
        // THEN: An ErrorResponse with status 401, error message "Unauthorized", and message "Incorrect authentication info" is returned
        ErrorResponse res = controller.unAuthorized();
        assertEquals(401, res.getStatus());
        assertEquals("Unauthorized", res.getError());
        assertEquals("Incorrect authentication info", res.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN: A Forbidden exception is thrown
        // WHEN: The controller handles the exception
        // THEN: An ErrorResponse with status 403, error message "Forbidden", and message "Not allowed" is returned
        ErrorResponse res = controller.forbidden();
        assertEquals(403, res.getStatus());
        assertEquals("Forbidden", res.getError());
        assertEquals("Not allowed", res.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN: NoHandlerFoundException is thrown
        // WHEN: The controller handles the exception
        // THEN: The shareNotFound() method is called and its result is returned
        ErrorResponse res = controller.notFound01();
        assertEquals(404, res.getStatus());
        assertEquals("Not found", res.getError());
        assertEquals("Not found path", res.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN: A NotFound exception is thrown
        // WHEN: The controller handles the exception
        // THEN: The shareNotFound() method is called and its result is returned
        ErrorResponse res = controller.notFound02();
        assertEquals(404, res.getStatus());
        assertEquals("Not found", res.getError());
        assertEquals("Not found path", res.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN: A Conflict exception is thrown
        // WHEN: The controller handles the exception
        // THEN: An ErrorResponse with status 409, error message "Conflict", and message "Already exist data" is returned
        ErrorResponse res = controller.conflict();
        assertEquals(409, res.getStatus());
        assertEquals("Conflict", res.getError());
        assertEquals("Already exist data", res.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN: An Exception is thrown
        // WHEN: The controller handles the exception
        // THEN: An ErrorResponse with status 500, error message "Internal server error", and message "Internal server error" is returned
        ErrorResponse res = controller.serverError(new Exception());
        assertEquals(500, res.getStatus());
        assertEquals("Internal server error", res.getError());
        assertEquals("Internal server error", res.getMessage());
    }
}

// Custom extension to satisfy the naming requirements
class MyExtension implements ExtensionContext.TestableEventDispatcher {
    @Override
    public void beforeTestExecution(TestContext testContext) {
    }
}
