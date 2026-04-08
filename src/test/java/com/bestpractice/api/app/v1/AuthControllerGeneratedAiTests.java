package com.bestpractice.api.app.v1;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
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
    Mockito.reset(authService, bindingResult);
    authController = new AuthController(authService);
  }

  @Test
  void optionsAuth_shouldReturnAllowedHeaders() {
    // GIVEN
    // No preconditions required

    // WHEN
    ResponseEntity<Object> response = authController.optionsAuth();

    // THEN
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    HttpHeaders headers = response.getHeaders();
    assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
    assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
    assertThat(response.getBody()).isNull();
  }

  @Test
  void login_withEmail_shouldReturnAuthResponse_whenValidRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("user@example.com");
    request.setPassword("securePassword");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", null);
    when(authService.login("user@example.com", "securePassword")).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("token123");
    assertThat(actualResponse.getRefreshToken()).isEqualTo("refresh123");
  }

  @Test
  void login_withEmail_shouldThrowBadRequest_whenBindingHasErrors() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

    // WHEN / THEN
    org.assertj.core.api.Assertions.assertThatThrownBy(() -> authController.login(request, bindingResult))
        .isInstanceOf(BadRequest.class)
        .hasMessageContaining("authByEmailRequest");
  }

  @Test
  void login_withRefreshToken_shouldReturnAuthResponse_whenValidRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refreshTokenValue");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "tokenXYZ", "refreshTokenValue", null);
    when(authService.login("refreshTokenValue")).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("tokenXYZ");
    assertThat(actualResponse.getRefreshToken()).isEqualTo("refreshTokenValue");
  }

  @Test
  void login_withRefreshToken_shouldThrowBadRequest_whenBindingHasErrors() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

    // WHEN / THEN
    org.assertj.core.api.Assertions.assertThatThrownBy(() -> authController.login(request, bindingResult))
        .isInstanceOf(BadRequest.class)
        .hasMessageContaining("authByRefreshTokenRequest");
  }
}
