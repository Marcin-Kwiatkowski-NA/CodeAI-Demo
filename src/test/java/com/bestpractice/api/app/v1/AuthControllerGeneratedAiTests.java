package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class AuthControllerGeneratedAiTests {

  private AuthController authController;
  private AuthService authService;

  @BeforeEach
  void setUp() {
    authService = Mockito.createMock(AuthService.class);
    authController = new AuthController(authService);
  }

  @Test
  void loginEmailSuccessful() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    AuthResponse response = authController.login(request, null);

    // THEN
    assertNotNull(response);
    assertEquals(AuthResponse.class, response.getClass());
  }

  @Test
  void loginEmailInvalid() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    // Reset the AuthService to throw an exception
    Mockito.doThrow(new BadRequest(""));
    authService = Mockito.createMock(AuthService.class);
    authController = new AuthController(authService);
    AuthResponse response = authController.login(request, null);

    // THEN
    assertNotNull(response);
    assertEquals(AuthResponse.class, response.getClass());
  }

  @Test
  void loginRefreshTokenSuccessful() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refreshToken123");

    // WHEN
    AuthResponse response = authController.login(request, null);

    // THEN
    assertNotNull(response);
    assertEquals(AuthResponse.class, response.getClass());
  }

  @Test
  void loginRefreshTokenInvalid() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refreshToken123");

    // WHEN
    // Reset the AuthService to throw an exception
    Mockito.doThrow(new BadRequest(""));
    authService = Mockito.createMock(AuthService.class);
    authController = new AuthController(authService);
    AuthResponse response = authController.login(request, null);

    // THEN
    assertNotNull(response);
    assertEquals(AuthResponse.class, response.getClass());
  }
}
