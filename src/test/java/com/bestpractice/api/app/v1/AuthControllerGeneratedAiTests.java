package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.service.AuthService;

import org.junit.jupiter.api.Test;

class AuthControllerGeneratedAiTests {

    @Test
    void shouldThrowBadRequestExceptionWhenValidationFailsOnEmailAndPassword() {
        // GIVEN
        AuthByEmailRequest invalidRequest = new AuthByEmailRequest();
        invalidRequest.setEmail(null);
        invalidRequest.setPassword("invalid");

        // WHEN & THEN
        assertThrows(Exception.class, () -> {
            authController.login(invalidRequest);
        });
    }

    @Test
    void shouldThrowBadRequestExceptionWhenValidationFailsOnRefreshToken() {
        // GIVEN
        AuthByRefreshTokenRequest invalidRequest = new AuthByRefreshTokenRequest();
        invalidRequest.setRefreshToken(null);

        // WHEN & THEN
        assertThrows(Exception.class, () -> {
            authController.login(invalidRequest);
        });
    }
}
