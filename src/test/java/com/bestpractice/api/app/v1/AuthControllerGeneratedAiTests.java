package com.bestpractice.api.app.v1;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthControllerGeneratedAiTests {

  private AuthController authController;

  @Mock
  private AuthService authService;

  @BeforeEach
  public void setUp() {
    authController = new AuthController(authService);
    // Reset mocks before each test to avoid state leakage between tests
  }

  @Test
  public void optionsAuth_ShouldReturnOptionsResponse() {
    // GIVEN
    // WHEN
    var response = authController.optionsAuth();
    // THEN
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("POST,OPTIONS", response.getHeaders().get("Allow").get(0));
    assertEquals("*", response.getHeaders().get("Access-Control-Allow-Origin").get(0));
  }

  @Test
  public void login_ShouldReturnAuthResponse() {
    // GIVEN
    var emailRequest = new AuthByEmailRequest();
    emailRequest.setEmail("test@example.com");
    emailRequest.setPassword("password");

    var authResponse = new AuthResponse("Bearer", "token", "refreshToken", new Date());

    when(authService.login(emailRequest.getEmail(), emailRequest.getPassword())).thenReturn(authResponse);

    // WHEN
    var response = authController.login(emailRequest, null);

    // THEN
    assertEquals(authResponse, response);
  }

  @Test
  public void loginWithRefreshToken_ShouldReturnAuthResponse() {
    // GIVEN
    var refreshTokenRequest = new AuthByRefreshTokenRequest();
    refreshTokenRequest.setRefreshToken("refreshToken");

    var authResponse = new AuthResponse("Bearer", "token", "refreshToken", new Date());

    when(authService.login(refreshTokenRequest.getRefreshToken())).thenReturn(authResponse);

    // WHEN
    var response = authController.login(refreshTokenRequest, null);

    // THEN
    assertEquals(authResponse, response);
  }
}
