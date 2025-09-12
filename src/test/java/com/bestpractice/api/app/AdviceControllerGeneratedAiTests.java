package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

public class AdviceControllerGeneratedAiTests extends AdviceController {

    @BeforeEach
    void setUp() {
        // Reset state if needed before each test
    }

    @Test
    void badRequest() {
        // GIVEN
        // Set up the scenario for the bad request exception
        // WHEN
        // Invoke the badRequest method
        // THEN
        // Assert the expected outcome
        ErrorResponse res = this.badRequest();
        assertEquals(400, res.getStatus());
        assertEquals("Bad request", res.getError());
        assertEquals("Bad request parameter", res.getMessage());
    }

    @Test
    void unAuthorized() {
        // GIVEN
        // Set up the scenario for the unauthorized exception
        // WHEN
        // Invoke the unAuthorized method
        // THEN
        // Assert the expected outcome
        ErrorResponse res = this.unAuthorized();
        assertEquals(401, res.getStatus());
        assertEquals("Unauthorized", res.getError());
        assertEquals("Incorrect authentication info", res.getMessage());
    }

    @Test
    void forbidden() {
        // GIVEN
        // Set up the scenario for the forbidden exception
        // WHEN
        // Invoke the forbidden method
        // THEN
        // Assert the expected outcome
        ErrorResponse res = this.forbidden();
        assertEquals(403, res.getStatus());
        assertEquals("Forbidden", res.getError());
        assertEquals("Not allowed", res.getMessage());
    }

    @Test
    void notFound01() {
        // GIVEN
        // Set up the scenario for the not found exception
        // WHEN
        // Invoke the notFound01 method
        // THEN
        // Assert the expected outcome
        ErrorResponse res = this.notFound01();
        assertEquals(404, res.getStatus());
        assertEquals("Not found", res.getError());
        assertEquals("Not found path", res.getMessage());
    }

    @Test
    void notFound02() {
        // GIVEN
        // Set up the scenario for the not found exception
        // WHEN
        // Invoke the notFound02 method
        // THEN
        // Assert the expected outcome
        ErrorResponse res = this.notFound02();
        assertEquals(404, res.getStatus());
        assertEquals("Not found", res.getError());
        assertEquals("Not found path", res.getMessage());
    }

    @Test
    void conflict() {
        // GIVEN
        // Set up the scenario for the conflict exception
        // WHEN
        // Invoke the conflict method
        // THEN
        // Assert the expected outcome
        ErrorResponse res = this.conflict();
        assertEquals(409, res.getStatus());
        assertEquals("Conflict", res.getError());
        assertEquals("Already exist data", res.getMessage());
    }

    @Test
    void serverError() {
        // GIVEN
        // Set up the scenario for the internal server error exception
        // WHEN
        // Invoke the serverError method
        // THEN
        // Assert the expected outcome
        ErrorResponse res = this.serverError(new Exception());
        assertEquals(500, res.getStatus());
        assertEquals("Internal server error", res.getError());
        assertEquals("Internal server error", res.getMessage());
    }
}
