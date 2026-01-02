package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class AdviceControllerGeneratedAiTests {

    private AdviceController controller;

    @BeforeEach
    void setUp() {
        controller = new AdviceController();
    }

    @Test
    void testBadRequestHandler() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res = controller.badRequest();

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(400);
        assertThat(res.getError()).isEqualTo("Bad request");
        assertThat(res.getMessage()).isEqualTo("Bad request parameter");
    }

    @Test
    void testUnAuthorizedHandler() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res = controller.unAuthorized();

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(401);
        assertThat(res.getError()).isEqualTo("Unauthorized");
        assertThat(res.getMessage()).isEqualTo("Incorrect authentication info");
    }

    @Test
    void testForbiddenHandler() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res = controller.forbidden();

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(403);
        assertThat(res.getError()).isEqualTo("Forbidden");
        assertThat(res.getMessage()).isEqualTo("Not allowed");
    }

    @Test
    void testConflictHandler() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res = controller.conflict();

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(409);
        assertThat(res.getError()).isEqualTo("Conflict");
        assertThat(res.getMessage()).isEqualTo("Already exist data");
    }

    @Test
    void testNotFoundHandlerWithNoHandlerFoundException() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res = controller.notFound01();

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(404);
        assertThat(res.getError()).isEqualTo("Not found");
        assertThat(res.getMessage()).isEqualTo("Not found path");
    }

    @Test
    void testNotFoundHandlerWithNotFoundException() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res = controller.notFound02();

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(404);
        assertThat(res.getError()).isEqualTo("Not found");
        assertThat(res.getMessage()).isEqualTo("Not found path");
    }

    @Test
    void testServerErrorHandler() {
        // GIVEN
        Exception ex = new Exception("Test exception");

        // WHEN
        ErrorResponse res = controller.serverError(ex);

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(500);
        assertThat(res.getError()).isEqualTo("Internal server error");
        assertThat(res.getMessage()).isEqualTo("Internal server error");
    }

    @Test
    void testBadRequestReturnsNewInstanceEachCall() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse first = controller.badRequest();
        ErrorResponse second = controller.badRequest();

        // THEN
        assertThat(first).isNotSameAs(second);
        assertThat(first.getStatus()).isEqualTo(second.getStatus());
        assertThat(first.getError()).isEqualTo(second.getError());
        assertThat(first.getMessage()).isEqualTo(second.getMessage());
    }

    @Test
    void testServerErrorHandlesNullExceptionGracefully() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res = controller.serverError(null);

        // THEN
        assertThat(res).isNotNull();
        assertThat(res.getStatus()).isEqualTo(500);
        assertThat(res.getError()).isEqualTo("Internal server error");
        assertThat(res.getMessage()).isEqualTo("Internal server error");
    }

    @Test
    void testNotFoundHandlersReturnSameContent() {
        // GIVEN
        // (controller is initialized in setUp)

        // WHEN
        ErrorResponse res1 = controller.notFound01();
        ErrorResponse res2 = controller.notFound02();

        // THEN
        assertThat(res1).isNotSameAs(res2);
        assertThat(res1.getStatus()).isEqualTo(res2.getStatus());
        assertThat(res1.getError()).isEqualTo(res2.getError());
        assertThat(res1.getMessage()).isEqualTo(res2.getMessage());
    }
}
