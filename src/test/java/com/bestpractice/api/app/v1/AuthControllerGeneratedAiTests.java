package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class AuthControllerGeneratedAiTests {

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
    void optionsAuth_ShouldReturnAllowAndCorsHeaders() {
        // GIVEN
        // No preconditions needed for OPTIONS

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HttpHeaders headers = response.getHeaders();
        assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
        assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
        assertThat(response.getBody()).isNull();
    }

    @Test
    void loginWithEmail_ShouldReturnAuthResponse_WhenValid() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("secret");
        when(bindingResult.hasErrors()).thenReturn(false);

        AuthResponse expectedResponse = new AuthResponse(
                "Bearer",
                "token123",
                "refresh123",
                new Date(System.currentTimeMillis() + 3600_000)
        );
        when(authService.login("user@example.com", "secret")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login("user@example.com", "secret");
    }

    @Test
    void loginWithEmail_ShouldThrowBadRequest_WhenBindingResultHasErrors() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("secret");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("AuthByEmailRequest");

        // WHEN
        BadRequest exception = assertThrows(BadRequest.class,
                () -> authController.login(request, bindingResult));

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).contains("AuthByEmailRequest");
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    void loginWithRefreshToken_ShouldReturnAuthResponse_WhenValid() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");
        when(bindingResult.hasErrors()).thenReturn(false);

        AuthResponse expectedResponse = new AuthResponse(
                "Bearer",
                "newToken456",
                "newRefresh456",
                new Date(System.currentTimeMillis() + 7200_000)
        );
        when(authService.login("refreshToken123")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login("refreshToken123");
    }

    @Test
    void loginWithRefreshToken_ShouldThrowBadRequest_WhenBindingResultHasErrors() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("AuthByRefreshTokenRequest");

        // WHEN
        BadRequest exception = assertThrows(BadRequest.class,
                () -> authController.login(request, bindingResult));

        // THEN
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).contains("AuthByRefreshTokenRequest");
        verify(authService, never()).login(anyString());
    }
}
