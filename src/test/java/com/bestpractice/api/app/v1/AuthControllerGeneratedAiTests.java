package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import com.bestpractice.api.common.exception.BadRequest;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
class AuthControllerGeneratedAiTests {

  private AuthController controller;
  private AuthService authService;

  @BeforeEach
  void setUp() {
    authService = Mockito.mock(AuthService.class);
    controller = new AuthController(authService);
  }

  @Test
  void loginEmailTest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest("test@example.com", "password");
    Mockito.when(authService.login("test@example.com", "password")).thenReturn(new AuthResponse("Bearer", "token", "refresh_token", new Date()));
    // WHEN
    AuthResponse response = controller.login(request, null);
    // THEN
    Mockito.verify(authService, Mockito.times(1)).login("test@example.com", "password");
  }

  @Test
  void loginRefreshTokenTest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest("refresh_token");
    Mockito.when(authService.login("refresh_token")).thenReturn(new AuthResponse("Bearer", "token", "refresh_token", new Date()));
    // WHEN
    AuthResponse response = controller.login(request, null);
    // THEN
    Mockito.verify(authService, Mockito.times(1)).login("refresh_token");
  }

  @Test
  void loginEmailBadRequestTest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest("", "");
    Mockito.when(authService.login("test@example.com", "password")).thenReturn(new AuthResponse("Bearer", "token", "refresh_token", new Date()));
    // WHEN
    BadRequest exception = null;
    try {
      controller.login(request, null);
    } catch (BadRequest e) {
      exception = e;
    }
    // THEN
    Mockito.verify(authService, Mockito.times(1)).login("test@example.com", "password");
    assertNotNull(exception);
  }

  @Test
  void optionsAuthTest() {
    // GIVEN
    // WHEN
    // THEN
  }
}