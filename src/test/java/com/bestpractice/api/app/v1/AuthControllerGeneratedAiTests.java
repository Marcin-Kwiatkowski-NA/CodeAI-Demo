package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private BindingResult bindingResult;

    private AuthByEmailRequest authByEmailRequest;
    private AuthByRefreshTokenRequest authByRefreshTokenRequest;
    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        authByEmailRequest = new AuthByEmailRequest();
        authByEmailRequest.setEmail("test@example.com");
        authByEmailRequest.setPassword("password");

        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
        authByRefreshTokenRequest.setRefreshToken("refreshToken");

        authResponse = new AuthResponse();
        authResponse.setAccessToken("accessToken");
        authResponse.setRefreshToken("refreshToken");
    }

    @Test
    void login_WithBindingErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(new FieldError("email", "email", "Email is required")));

        AuthResponse response = authController.login(authByEmailRequest, bindingResult);

        assertNull(response);
        verify(authService, times(0)).authenticate(authByEmailRequest);
    }

    @Test
    void loginByRefreshToken_WithBindingErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(new FieldError("refreshToken", "refreshToken", "Refresh token is required")));

        AuthResponse response = authController.loginByRefreshToken(authByRefreshTokenRequest, bindingResult);

        assertNull(response);
        verify(authService, times(0)).refreshToken(authByRefreshTokenRequest);
    }
}
