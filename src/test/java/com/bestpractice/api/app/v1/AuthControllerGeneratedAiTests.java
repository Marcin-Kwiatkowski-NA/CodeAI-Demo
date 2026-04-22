package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

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
    Mockito.reset(authService, bindingResult);
    authController = new AuthController(authService);
  }

  @Test
  void optionsAuth_shouldReturnAllowHeaders() {
    // GIVEN
    // No preconditions required

    // WHEN
    ResponseEntity<Object> response = authController.optionsAuth();

    // THEN
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    HttpHeaders headers = response.getHeaders();
    assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
    assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
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
    verify(authService, times(1)).login("test@example.com", "password");
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
      assertThat(ex).isInstanceOf(BadRequest.class);
      assertThat(ex.getMessage()).contains("authByEmailRequest");
    }
  }

  @Test
  void refreshTokenLogin_shouldReturnAuthResponse_whenValidRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refreshToken123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "tokenABC", "refreshToken123", null);
    when(authService.login("refreshToken123")).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("tokenABC");
    assertThat(actualResponse.getRefreshToken()).isEqualTo("refreshToken123");
    verify(authService, times(1)).login("refreshToken123");
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
      assertThat(ex).isInstanceOf(BadRequest.class);
      assertThat(ex.getMessage()).contains("authByRefreshTokenRequest");
    }
  }
}
