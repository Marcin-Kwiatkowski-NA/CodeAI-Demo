package com.bestpractice.api.app.v1;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

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
    request.setEmail("test@example.com");
    request.setPassword("password123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "token123", "refresh123", new java.util.Date());
    when(authService.login(request.getEmail(), request.getPassword())).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getToken()).isEqualTo("token123");
    verify(authService, times(1)).login(request.getEmail(), request.getPassword());
  }

  @Test
  void givenInvalidEmailRequest_whenLogin_thenThrowsBadRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail(null);
    request.setPassword(null);
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getObjectName()).thenReturn("authByEmailRequest");

    // WHEN / THEN
    assertThatThrownBy(() -> authController.login(request, bindingResult))
        .isInstanceOf(BadRequest.class)
        .hasMessageContaining("authByEmailRequest");
  }

  @Test
  void givenValidRefreshTokenRequest_whenLogin_thenReturnsAuthResponse() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refreshToken123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "tokenXYZ", "refreshToken123", new java.util.Date());
    when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getRefreshToken()).isEqualTo("refreshToken123");
    verify(authService, times(1)).login(request.getRefreshToken());
  }

  @Test
  void givenInvalidRefreshTokenRequest_whenLogin_thenThrowsBadRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken(null);
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getObjectName()).thenReturn("authByRefreshTokenRequest");

    // WHEN / THEN
    assertThatThrownBy(() -> authController.login(request, bindingResult))
        .isInstanceOf(BadRequest.class)
        .hasMessageContaining("authByRefreshTokenRequest");
  }

  @Test
  void givenOptionsRequest_whenOptionsAuth_thenReturnsAllowedHeaders() {
    // GIVEN
    // No setup required

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
