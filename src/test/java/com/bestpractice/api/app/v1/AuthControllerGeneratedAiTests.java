package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import java.util.Date;

public class AuthControllerGeneratedAiTests {

  @Mock
  private AuthService authService;

  @Mock
  private BindingResult bindingResult;

  private AuthController authController;

  @BeforeEach
  void setUp() {
    authService = mock(AuthService.class);
    bindingResult = mock(BindingResult.class);
    authController = new AuthController(authService);
  }

  @Test
  void givenValidEmailRequest_whenLogin_thenReturnsAuthResponse() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("accessToken123", "refreshToken123", "userId123", new Date());
    when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isEqualTo(expectedResponse);
    verify(authService, times(1)).login(request.getEmail(), request.getPassword());
  }

  @Test
  void givenInvalidEmailRequest_whenLogin_thenThrowsBadRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    when(bindingResult.hasErrors()).thenReturn(true);

    // WHEN / THEN
    org.junit.jupiter.api.Assertions.assertThrows(BadRequest.class, () -> {
      authController.login(request, bindingResult);
    });
  }

  @Test
  void givenValidRefreshTokenRequest_whenLogin_thenReturnsAuthResponse() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh-token-123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("accessToken456", "refreshToken456", "userId456", new Date());
    when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isEqualTo(expectedResponse);
    verify(authService, times(1)).login(request.getRefreshToken());
  }

  @Test
  void givenInvalidRefreshTokenRequest_whenLogin_thenThrowsBadRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    when(bindingResult.hasErrors()).thenReturn(true);

    // WHEN / THEN
    org.junit.jupiter.api.Assertions.assertThrows(BadRequest.class, () -> {
      authController.login(request, bindingResult);
    });
  }

  @Test
  void givenOptionsRequest_whenOptionsAuth_thenReturnsResponseEntityWithHeaders() {
    // GIVEN
    // No setup needed

    // WHEN
    ResponseEntity<Object> response = authController.optionsAuth();

    // THEN
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    HttpHeaders headers = response.getHeaders();
    assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
    assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
  }
}
