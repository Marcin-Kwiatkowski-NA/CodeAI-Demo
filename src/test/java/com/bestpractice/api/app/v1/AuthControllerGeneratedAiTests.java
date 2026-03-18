package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerGeneratedAiTests {

    @Mock
    private AuthService authService;

    @Mock
    private BindingResult bindingResult;

    private AuthController authController;

    @BeforeEach
    void setUp() {
        authController = new AuthController(authService);
    }

    @Test
    void optionsAuth_shouldReturnResponseEntityWithHeaders() {
        // GIVEN
        // No specific setup required

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HttpHeaders headers = response.getHeaders();
        assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
        assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
    }

    @Test
    void login_withEmailRequest_shouldReturnAuthResponse_whenNoValidationErrors() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("accessToken", "refreshToken", "userId", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login(request.getEmail(), request.getPassword());
    }

    @Test
    void login_withEmailRequest_shouldThrowBadRequest_whenValidationErrorsExist() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

        // WHEN / THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("authByEmailRequest");
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    void login_withRefreshTokenRequest_shouldReturnAuthResponse_whenNoValidationErrors() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refresh-token-123");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("accessToken", "refreshToken", "userId", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login(request.getRefreshToken());
    }

    @Test
    void login_withRefreshTokenRequest_shouldThrowBadRequest_whenValidationErrorsExist() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

        // WHEN / THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("authByRefreshTokenRequest");
        verify(authService, never()).login(anyString());
    }
}
