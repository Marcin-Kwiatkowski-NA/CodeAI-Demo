package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
public class AuthControllerGeneratedAiTests {

  @Mock
  private AuthService authService;

  @Mock
  private BindingResult bindingResult;

  @InjectMocks
  private AuthController authController;

  @BeforeAll
  static void beforeAll() {
    // GIVEN
    // Initialize global resources if needed
  }

  @AfterAll
  static void afterAll() {
    // THEN
    // Cleanup global resources if needed
  }

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    authController = new AuthController(authService);
  }

  @Test
  void optionsAuth_shouldReturnAllowedHeaders() {
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

  @Test
  void login_withEmail_shouldReturnAuthResponse_whenValidRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("user@example.com");
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
  void login_withEmail_shouldThrowBadRequest_whenValidationFails() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    when(bindingResult.hasErrors()).thenReturn(true);

    // WHEN / THEN
    assertThatThrownBy(() -> authController.login(request, bindingResult))
        .isInstanceOf(BadRequest.class);
    verify(authService, never()).login(anyString(), anyString());
  }

  @Test
  void login_withRefreshToken_shouldReturnAuthResponse_whenValidRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh123");
    when(bindingResult.hasErrors()).thenReturn(false);
    AuthResponse expectedResponse = new AuthResponse("Bearer", "tokenXYZ", "refreshXYZ", new java.util.Date());
    when(authService.login(request.getRefreshToken())).thenReturn(expectedResponse);

    // WHEN
    AuthResponse actualResponse = authController.login(request, bindingResult);

    // THEN
    assertThat(actualResponse).isNotNull();
    assertThat(actualResponse.getRefreshToken()).isEqualTo("refreshXYZ");
    verify(authService, times(1)).login(request.getRefreshToken());
  }

  @Test
  void login_withRefreshToken_shouldThrowBadRequest_whenValidationFails() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    when(bindingResult.hasErrors()).thenReturn(true);

    // WHEN / THEN
    assertThatThrownBy(() -> authController.login(request, bindingResult))
        .isInstanceOf(BadRequest.class);
    verify(authService, never()).login(anyString());
  }
}
