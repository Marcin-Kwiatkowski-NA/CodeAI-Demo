package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class AuthControllerGeneratedAiTests {

    @Mock
    private AuthService authService;

    private AuthController authController;

    @BeforeEach
    void setUp() {
        authController = new AuthController(authService);
    }

    @Test
    void optionsAuth_shouldReturnOkWithAllowedHeaders() {
        // GIVEN
        // No preconditions needed

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
    void loginByEmail_shouldReturnAuthResponseWhenValid() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("securePassword");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        AuthResponse expectedResponse = new AuthResponse(
                "Bearer",
                "token123",
                "refresh123",
                new Date(System.currentTimeMillis() + 3600_000)
        );
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isSameAs(expectedResponse);
        verify(authService, times(1)).login("user@example.com", "securePassword");
        verifyNoMoreInteractions(authService);
    }

    @Test
    void loginByEmail_shouldThrowBadRequestWhenValidationFails() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("user@example.com");
        request.setPassword("securePassword");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

        // WHEN & THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("authByEmailRequest");
        verifyNoInteractions(authService);
    }

    @Test
    void loginByRefreshToken_shouldReturnAuthResponseWhenValid() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        AuthResponse expectedResponse = new AuthResponse(
                "Bearer",
                "token456",
                "refresh456",
                new Date(System.currentTimeMillis() + 7200_000)
        );
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse actualResponse = authController.login(request, bindingResult);

        // THEN
        assertThat(actualResponse).isSameAs(expectedResponse);
        verify(authService, times(1)).login("refreshToken123");
        verifyNoMoreInteractions(authService);
    }

    @Test
    void loginByRefreshToken_shouldThrowBadRequestWhenValidationFails() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

        // WHEN & THEN
        assertThatThrownBy(() -> authController.login(request, bindingResult))
                .isInstanceOf(BadRequest.class)
                .hasMessageContaining("authByRefreshTokenRequest");
        verifyNoInteractions(authService);
    }
}
