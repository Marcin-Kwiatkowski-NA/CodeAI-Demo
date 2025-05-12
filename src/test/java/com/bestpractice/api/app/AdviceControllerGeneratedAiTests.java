package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.bestpractice.api.domain.model.ErrorResponse;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;

class AdviceControllerGeneratedAiTests {

    private ErrorResponse errorResponse;

    @BeforeEach
    void setUp() {
        errorResponse = new ErrorResponse();
    }

    @Test
    void badRequest() {
        // GIVEN
        // WHEN
        // THEN
        errorResponse.setStatus(400);
        errorResponse.setError("Bad request");
        errorResponse.setMessage("Bad request parameter");
    }

    @Test
    void unAuthorized() {
        // GIVEN
        // WHEN
        // THEN
        errorResponse.setStatus(401);
        errorResponse.setError("Unauthorized");
        errorResponse.setMessage("Incorrect authentication info");
    }

    @Test
    void forbidden() {
        // GIVEN
        // WHEN
        // THEN
        errorResponse.setStatus(403);
        errorResponse.setError("Forbidden");
        errorResponse.setMessage("Not allowed");
    }

    @Test
    void notFound01() {
        // GIVEN
        // WHEN
        // THEN
        errorResponse.setStatus(404);
        errorResponse.setError("Not found");
        errorResponse.setMessage("Not found path");
    }

    @Test
    void notFound02() {
        // GIVEN
        // WHEN
        // THEN
        errorResponse.setStatus(404);
        errorResponse.setError("Not found");
        errorResponse.setMessage("Not found path");
    }

    @Test
    void conflict() {
        // GIVEN
        // WHEN
        // THEN
        errorResponse.setStatus(409);
        errorResponse.setError("Conflict");
        errorResponse.setMessage("Already exist data");
    }

    @Test
    void serverError() {
        // GIVEN
        // WHEN
        // THEN
        errorResponse.setStatus(500);
        errorResponse.setError("Internal server error");
        errorResponse.setMessage("Internal server error");
    }
}
