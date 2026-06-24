package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdviceControllerGeneratedAiTests {

    private AdviceController adviceController;

    @BeforeEach
    void setUp() {
        adviceController = new AdviceController();
    }

    // --- Improvements ---
    // 1. Ensured consistent GIVEN-WHEN-THEN structure.
    // 2. Added missing edge cases for null and unusual inputs.
    // 3. Verified idempotency and consistency of responses.
    // 4. Removed redundant exception instantiations not used in logic.

    @Test
    void testBadRequestReturnsExpectedErrorResponse() {
        // GIVEN
        // No input required

        // WHEN
        ErrorResponse response = adviceController.badRequest();

        // THEN
        assertNotNull(response);
        assertEquals(400, response.getStatus());
        assertEquals("Bad request", response.getError());
        assertEquals("Bad request parameter", response.getMessage());
    }

    @Test
    void testUnAuthorizedReturnsExpectedErrorResponse() {
        // GIVEN WHEN
        ErrorResponse response = adviceController.unAuthorized();

        // THEN
        assertNotNull(response);
        assertEquals(401, response.getStatus());
        assertEquals("Unauthorized", response.getError());
        assertEquals("Incorrect authentication info", response.getMessage());
    }

    @Test
    void testForbiddenReturnsExpectedErrorResponse() {
        // GIVEN WHEN
        ErrorResponse response = adviceController.forbidden();

        // THEN
        assertNotNull(response);
        assertEquals(403, response.getStatus());
        assertEquals("Forbidden", response.getError());
        assertEquals("Not allowed", response.getMessage());
    }

    @Test
    void testNotFound01ReturnsExpectedErrorResponse() {
        // GIVEN WHEN
        ErrorResponse response = adviceController.notFound01();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testNotFound02ReturnsExpectedErrorResponse() {
        // GIVEN WHEN
        ErrorResponse response = adviceController.notFound02();

        // THEN
        assertNotNull(response);
        assertEquals(404, response.getStatus());
        assertEquals("Not found", response.getError());
        assertEquals("Not found path", response.getMessage());
    }

    @Test
    void testConflictReturnsExpectedErrorResponse() {
        // GIVEN WHEN
        ErrorResponse response = adviceController.conflict();

        // THEN
        assertNotNull(response);
        assertEquals(409, response.getStatus());
        assertEquals("Conflict", response.getError());
        assertEquals("Already exist data", response.getMessage());
    }

    @Test
    void testServerErrorReturnsExpectedErrorResponse() {
        // GIVEN
        Exception exception = new Exception("Test exception");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorThrowsNullPointerExceptionWhenExceptionIsNull() {
        // GIVEN
        Exception exception = null;

        // WHEN THEN
        assertThrows(NullPointerException.class, () -> adviceController.serverError(exception));
    }

    @Test
    void testServerErrorHandlesRuntimeExceptionGracefully() {
        // GIVEN
        RuntimeException runtimeException = new RuntimeException("Runtime failure");

        // WHEN
        ErrorResponse response = adviceController.serverError(runtimeException);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    // --- Edge Case Tests ---

    @Test
    void testServerErrorWithEmptyMessage() {
        // GIVEN
        Exception exception = new Exception("");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithLongMessage() {
        // GIVEN
        String longMessage = "A".repeat(10000);
        Exception exception = new Exception(longMessage);

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithSpecialCharactersMessage() {
        // GIVEN
        Exception exception = new Exception("!@#$%^&*()_+{}|:\"<>?~");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithUnicodeMessage() {
        // GIVEN
        Exception exception = new Exception("テスト例外🚀");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithMinIntegerValueMessage() {
        // GIVEN
        Exception exception = new Exception(String.valueOf(Integer.MIN_VALUE));

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    @Test
    void testServerErrorWithMaxIntegerValueMessage() {
        // GIVEN
        Exception exception = new Exception(String.valueOf(Integer.MAX_VALUE));

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }

    // --- Additional Improvements ---
    // These tests ensure that the controller methods are idempotent and consistent.

    @Test
    void testRepeatedBadRequestCallsReturnConsistentResponse() {
        // GIVEN WHEN
        ErrorResponse firstResponse = adviceController.badRequest();
        ErrorResponse secondResponse = adviceController.badRequest();

        // THEN
        assertEquals(firstResponse.getStatus(), secondResponse.getStatus());
        assertEquals(firstResponse.getError(), secondResponse.getError());
        assertEquals(firstResponse.getMessage(), secondResponse.getMessage());
    }

    @Test
    void testRepeatedServerErrorCallsReturnConsistentResponse() {
        // GIVEN
        Exception exception = new Exception("Consistency check");

        // WHEN
        ErrorResponse firstResponse = adviceController.serverError(exception);
        ErrorResponse secondResponse = adviceController.serverError(exception);

        // THEN
        assertEquals(firstResponse.getStatus(), secondResponse.getStatus());
        assertEquals(firstResponse.getError(), secondResponse.getError());
        assertEquals(firstResponse.getMessage(), secondResponse.getMessage());
    }

    @Test
    void testNotFoundMethodsReturnSameResponse() {
        // GIVEN WHEN
        ErrorResponse response1 = adviceController.notFound01();
        ErrorResponse response2 = adviceController.notFound02();

        // THEN
        assertEquals(response1.getStatus(), response2.getStatus());
        assertEquals(response1.getError(), response2.getError());
        assertEquals(response1.getMessage(), response2.getMessage());
    }

    @Test
    void testServerErrorWithWhitespaceMessage() {
        // GIVEN
        Exception exception = new Exception("   ");

        // WHEN
        ErrorResponse response = adviceController.serverError(exception);

        // THEN
        assertNotNull(response);
        assertEquals(500, response.getStatus());
        assertEquals("Internal server error", response.getError());
        assertEquals("Internal server error", response.getMessage());
    }
}
