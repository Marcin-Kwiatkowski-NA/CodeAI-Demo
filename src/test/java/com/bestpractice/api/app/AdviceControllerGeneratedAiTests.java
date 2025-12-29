package com.bestpractice.api.app;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.Forbidden;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.model.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class AdviceTest {

    @InjectMocks
    private Advice advice;

    @BeforeEach
    void setUp() {
        advice = new Advice();
    }

    @Test
    void handleBadRequest() {
        BadRequest badRequest = new BadRequest("Bad Request");
        ErrorResponse response = advice.handleBadRequest(badRequest);
        assertThat(response.getMessage()).isEqualTo("Bad Request");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void handleUnauthorized() {
        UnAuthorized unauthorized = new UnAuthorized("Unauthorized");
        ErrorResponse response = advice.handleUnauthorized(unauthorized);
        assertThat(response.getMessage()).isEqualTo("Unauthorized");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void handleForbidden() {
        Forbidden forbidden = new Forbidden("Forbidden");
        ErrorResponse response = advice.handleForbidden(forbidden);
        assertThat(response.getMessage()).isEqualTo("Forbidden");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void handleNotFound() {
        NotFound notFound = new NotFound("Not Found");
        ErrorResponse response = advice.handleNotFound(notFound);
        assertThat(response.getMessage()).isEqualTo("Not Found");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void handleConflict() {
        Conflict conflict = new Conflict("Conflict");
        ErrorResponse response = advice.handleConflict(conflict);
        assertThat(response.getMessage()).isEqualTo("Conflict");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT);
    }
}
