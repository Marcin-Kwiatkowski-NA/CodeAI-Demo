package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("AuthControllerGeneratedAiTests")
class AuthControllerGeneratedAiTests {

  private AuthController controller;
  private AuthService authService;

  @DisplayName("Setup")
  void setUp() {
    authService = Mockito.mock(AuthService.class);
    controller = new AuthController(authService);
  }

  @Test
  void loginEmailTest() {
    setUp();
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");
    Mockito.when(authService.login("test@example.com", "password123")).thenReturn(new AuthResponse("Bearer", "token", "refresh_token", new Date()));
    AuthResponse response = controller.login(request, null);
    Mockito.verify(authService, Mockito.times(1)).login("test@example.com", "password123");
    Mockito.verify(authService).login("test@example.com", "password123");
  }

  @Test
  void loginRefreshTokenTest() {
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh_token_value");
    Mockito.when(authService.login("refresh_token_value")).thenReturn(new AuthResponse("Bearer", "token", "refresh_token", new Date()));
    AuthResponse response = controller.login(request, null);
    Mockito.verify(authService, Mockito.times(1)).login("refresh_token_value");
    Mockito.verify(authService).login("refresh_token_value");
  }
}
