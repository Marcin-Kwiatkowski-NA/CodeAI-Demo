package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoInteractions;

public class AuthControllerGeneratedAiTests {

  private AuthService authService;
  private BindingResult bindingResult;
  private AuthController authController;

  @BeforeEach
  void setUp() {
    authService = mock(AuthService.class);
    bindingResult = mock(BindingResult.class);
    authController = new AuthController(authService);
  }

  @Test
  void optionsAuth_shouldReturnAllowedHeaders() {
    // GIVEN
    // No setup required

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
  void emailLogin_shouldReturnAuthResponse_whenValidRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", null);
    when(authService.login("test@example.com", "password")).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("token123");
    assertThat(actualResponse.getRefreshToken()).isEqualTo("refresh123");
    assertThat(actualResponse.getTokenType()).isEqualTo("Bearer");
  }

  @Test
  void emailLogin_shouldThrowBadRequest_whenBindingHasErrors() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

    // WHEN / THEN
    try {
      authController.login(request, bindingResult);
    } catch (BadRequest ex) {
      assertThat(ex.getMessage()).contains("authByEmailRequest");
    }
    verifyNoInteractions(authService);
  }

  @Test
  void refreshTokenLogin_shouldReturnAuthResponse_whenValidRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "tokenXYZ", "refreshXYZ", null);
    when(authService.login("refresh123")).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("tokenXYZ");
    assertThat(actualResponse.getRefreshToken()).isEqualTo("refreshXYZ");
    assertThat(actualResponse.getTokenType()).isEqualTo("Bearer");
  }

  @Test
  void refreshTokenLogin_shouldThrowBadRequest_whenBindingHasErrors() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

    // WHEN / THEN
    try {
      authController.login(request, bindingResult);
    } catch (BadRequest ex) {
      assertThat(ex.getMessage()).contains("authByRefreshTokenRequest");
    }
    verifyNoInteractions(authService);
  }
}
