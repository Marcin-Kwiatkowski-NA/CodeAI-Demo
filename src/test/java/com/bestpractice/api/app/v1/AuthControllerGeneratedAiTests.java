package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
        reset(authService, bindingResult);
    }

    @Test
    void testOptionsAuthReturnsCorrectHeaders() {
        // GIVEN
        // No preconditions needed for optionsAuth

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        HttpHeaders headers = response.getHeaders();
        assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
        assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void testLoginByEmailSuccess() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("secret");
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", new Date());

        when(bindingResult.hasErrors()).thenReturn(false);
        when(authService.login("user@example.com", "secret")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login("user@example.com", "secret");
        verify(authService, never()).login(anyString());
    }

    @Test
    void testLoginByEmailValidationError() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("secret");

        when(bindingResult.hasErrors()).thenReturn(true);

        // WHEN & THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("AuthByEmailRequest");
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    void testLoginByRefreshTokenSuccess() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token456", "refresh456", new Date());

        when(bindingResult.hasErrors()).thenReturn(false);
        when(authService.login("refreshToken123")).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(authService, times(1)).login("refreshToken123");
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    void testLoginByRefreshTokenValidationError() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");

        when(bindingResult.hasErrors()).thenReturn(true);

        // WHEN & THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("AuthByRefreshTokenRequest");
        verify(authService, never()).login(anyString());
    }
}
