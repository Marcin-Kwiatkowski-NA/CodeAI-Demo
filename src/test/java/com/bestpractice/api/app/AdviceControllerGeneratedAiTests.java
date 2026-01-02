package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    void setUp() {
        adviceController = new AdviceController();
    }

    @Test
    void testBadRequest() {
        // GIVEN
        // No preconditions required for badRequest

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void testUnAuthorized() {
        // GIVEN
        // No preconditions required for unAuthorized

        // WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void testForbidden() {
        // GIVEN
        // No preconditions required for forbidden

        // WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void testNotFound01() {
        // GIVEN
        // No preconditions required for notFound01

        // WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testNotFound02() {
        // GIVEN
        // No preconditions required for notFound02

        // WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testConflict() {
        // GIVEN
        // No preconditions required for conflict

        // WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void testServerError() {
        // GIVEN
        Exception testException = new Exception("Test exception");

        // WHEN
        ErrorResponse response = adviceController.serverError(testException);

        // THEN
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithNullThrowsNPE() {
        // GIVEN
        Exception nullException = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(nullException));
    }
}
