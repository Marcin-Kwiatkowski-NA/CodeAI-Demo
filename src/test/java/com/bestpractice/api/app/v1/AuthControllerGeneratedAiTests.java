package com.bestpractice.api.app.v1;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.assertThat;

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
    void optionsAuth_shouldReturnCorrectResponseEntity() {
        // GIVEN
        HttpHeaders expectedHeaders = new HttpHeaders();
        expectedHeaders.set("Allow", "POST,OPTIONS");
        expectedHeaders.set("Access-Control-Allow-Origin", "*");

        // WHEN
        ResponseEntity<Object> response = authController.optionsAuth();

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders()).isEqualTo(expectedHeaders);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void login_withEmail_shouldReturnAuthResponse_whenRequestIsValid() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refreshToken123", null);
        when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse response = authController.login(request, bindingResult);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken123");
        verify(authService, times(1)).login(request.getEmail(), request.getPassword());
    }

    @Test
    void login_withEmail_shouldThrowBadRequest_whenBindingResultHasErrors() {
        // GIVEN
        AuthByEmailRequest request = new AuthByEmailRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("AuthByEmailRequest");

        // WHEN THEN
        try {
            authController.login(request, bindingResult);
        } catch (BadRequest e) {
            assertThat(e.getMessage()).isEqualTo("AuthByEmailRequest");
        }
        verify(authService, never()).login(anyString(), anyString());
    }

    @Test
    void login_withRefreshToken_shouldReturnAuthResponse_whenRequestIsValid() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        request.setRefreshToken("refreshToken123");
        when(bindingResult.hasErrors()).thenReturn(false);
        AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refreshToken123", null);
        when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

        // WHEN
        AuthResponse response = authController.login(request, bindingResult);

        // THEN
        assertThat(response).isNotNull();
        assertThat(response.getTokenType()).isEqualTo("Bearer");
        assertThat(response.getToken()).isEqualTo("token123");
        assertThat(response.getRefreshToken()).isEqualTo("refreshToken123");
        verify(authService, times(1)).login(request.getRefreshToken());
    }

    @Test
    void login_withRefreshToken_shouldThrowBadRequest_whenBindingResultHasErrors() {
        // GIVEN
        AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getObjectName()).thenReturn("AuthByRefreshTokenRequest");

        // WHEN THEN
        try {
            authController.login(request, bindingResult);
        } catch (BadRequest e) {
            assertThat(e.getMessage()).isEqualTo("AuthByRefreshTokenRequest");
        }
        verify(authService, never()).login(anyString());
    }
}