package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


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
    void testOptionsAuthReturnsCorrectHeaders() {
        // GIVEN
        // No preconditions needed for optionsAuth

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        HttpHeaders headers = response.getHeaders();
        assertThat(headers).containsEntry("Allow", "POST,OPTIONS");
        assertThat(headers).containsEntry("Access-Control-Allow-Origin", "*");
        assertThat(response.getBody()).isNull();
    }

    @Test
    void testLoginWithEmailSuccess() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("securePassword");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", new Date());
        when(authService.login("user@example.com", "securePassword")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getToken()).isEqualTo("token123");
        assertThat(actualResponse.getRefreshToken()).isEqualTo("refresh123");
        verify(authService, times(1)).login("user@example.com", "securePassword");
    }

    @Test
    void testLoginWithEmailValidationError() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("securePassword");
        when(bindingResult.hasErrors()).thenReturn(true);

        // WHEN
        // THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("AuthByEmailRequest");
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    void testLoginWithRefreshTokenSuccess() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshTokenValue");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token456", "refresh456", new Date());
        when(authService.login("refreshTokenValue")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.getToken()).isEqualTo("token456");
        assertThat(actualResponse.getRefreshToken()).isEqualTo("refresh456");
        verify(authService, times(1)).login("refreshTokenValue");
    }

    @Test
    void testLoginWithRefreshTokenValidationError() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshTokenValue");
        when(bindingResult.hasErrors()).thenReturn(true);

        // WHEN
        // THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("AuthByRefreshTokenRequest");
        verify(authService, never()).login(anyString());
    }
}
