package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
    void optionsAuth_shouldReturnAllowedMethodsAndHeaders() {
        // GIVEN

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().get("Allow")).containsExactly("POST,OPTIONS");
        assertThat(response.getHeaders().get("Access-Control-Allow-Origin")).containsExactly("*");
        assertThat(response.getBody()).isNull();
    }

    @Test
    void login_withEmailLogin_shouldReturnAuthResponse() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refreshToken123", null);
        Mockito.when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse response = authController.login(request, bindingResult);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken123");
        Mockito.verify(authService, Mockito.times(1)).login(request.getEmail(), request.getPassword());
    }

    @Test
    void login_withEmailLogin_shouldThrowBadRequestWhenValidationFails() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getObjectName()).thenReturn("AuthByEmailRequest");

        // WHEN / THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("AuthByEmailRequest");
        Mockito.verify(authService, Mockito.never()).login(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    void login_withRefreshToken_shouldReturnAuthResponse() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refreshToken123", null);
        Mockito.when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse response = authController.login(request, bindingResult);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken123");
        Mockito.verify(authService, Mockito.times(1)).login(request.getRefreshToken());
    }

    @Test
    void login_withRefreshToken_shouldThrowBadRequestWhenValidationFails() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getObjectName()).thenReturn("AuthByRefreshTokenRequest");

        // WHEN / THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessage("AuthByRefreshTokenRequest");
        Mockito.verify(authService, Mockito.never()).login(Mockito.anyString());
    }
}
