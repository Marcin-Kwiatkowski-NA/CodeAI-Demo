package com.bestpractice.api.app.v1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.anyString;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@ExtendWith(MockitoExtension.class)
class AuthControllerGeneratedAiTests {

    @Mock
    private AuthService authService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void optionsAuth_shouldReturnCorrectHeaders() {
        // GIVEN
        // No specific setup required

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        HttpHeaders headers = response.getHeaders();
        assertThat(headers.get("Allow")).containsExactly("POST,OPTIONS");
        assertThat(headers.get("Access-Control-Allow-Origin")).containsExactly("*");
        assertThat(response.getBody()).isNull();
    }

    @Test
    void login_withEmail_shouldReturnAuthResponse_whenValidRequest() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("accessToken", "refreshToken", "tokenType", new Date());
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login(request.getEmail(), request.getPassword());
    }

    @Test
    void login_withEmail_shouldThrowBadRequest_whenBindingResultHasErrors() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("AuthByEmailRequest");

        // WHEN
        Throwable thrown = catchThrowable(() -> authController.login(request, bindingResult));

        // THEN
        assertThat(thrown).isInstanceOf(BadRequest.class).hasMessage("AuthByEmailRequest");
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    void login_withRefreshToken_shouldReturnAuthResponse_whenValidRequest() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("validRefreshToken");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("accessToken", "refreshToken", "tokenType", new Date());
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login(request.getRefreshToken());
    }

    @Test
    void login_withRefreshToken_shouldThrowBadRequest_whenBindingResultHasErrors() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("AuthByRefreshTokenRequest");

        // WHEN
        Throwable thrown = catchThrowable(() -> authController.login(request, bindingResult));

        // THEN
        assertThat(thrown).isInstanceOf(BadRequest.class).hasMessage("AuthByRefreshTokenRequest");
        verify(authService, never()).login(anyString());
    }
}