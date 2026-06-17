package com.bestpractice.api.app.v1;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.reset;

@ExtendWith(MockitoExtension.class)
public class AuthControllerGeneratedAiTests {

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
  void givenValidEmailRequest_whenLogin_thenReturnsAuthResponse() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("user@example.com");
    request.setPassword("password123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", null);
    when(authService.login("user@example.com", "password123")).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("token123");
    verify(authService, times(1)).login("user@example.com", "password123");
  }

  @Test
  void givenInvalidEmailRequest_whenLogin_thenThrowsBadRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    when(bindingResult.hasErrors()).thenReturn(true);

    // WHEN / THEN
    assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    verify(authService, never()).login(anyString(), anyString());
  }

  @Test
  void givenValidRefreshTokenRequest_whenLogin_thenReturnsAuthResponse() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "token456", "refresh123", null);
    when(authService.login("refresh123")).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("token456");
    verify(authService, times(1)).login("refresh123");
  }

  @Test
  void givenInvalidRefreshTokenRequest_whenLogin_thenThrowsBadRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    when(bindingResult.hasErrors()).thenReturn(true);

    // WHEN / THEN
    assertThrows(BadRequest.class, () -> authController.login(request, bindingResult));
    verify(authService, never()).login(anyString());
  }

  @Test
  void givenOptionsRequest_whenOptionsAuth_thenReturnsAllowedHeaders() {
    // GIVEN
    // No setup needed

    // WHEN
    ResponseEntity<Object> response = authController.optionsAuth();

    // THEN
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    HttpHeaders headers = response.getHeaders();
    assertThat(headers.getFirst("Allow")).isEqualTo("POST,OPTIONS");
    assertThat(headers.getFirst("Access-Control-Allow-Origin")).isEqualTo("*");
  }
}
