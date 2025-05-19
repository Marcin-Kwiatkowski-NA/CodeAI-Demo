package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import com.bestpractice.api.common.exception.BadRequest;
import org.mockito.Mockito;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class AuthControllerGeneratedAiTests {

  private AuthService authService;
  private AuthController controller;

  @BeforeEach
  void setUp() {
    controller = new AuthController(Mockito.mock(AuthService.class));
    authService = Mockito.mock(AuthService.class);
  }

  @Test
  void loginEmailTest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    AuthResponse response = controller.login(request, null);

    // THEN
    Mockito.verify(authService, Mockito.times(1)).login("test@example.com", "password123");
  }

  @Test
  void loginRefreshTokenTest() {
    // GIVEN
    String refreshToken = "someRefreshToken";

    // WHEN
    AuthResponse response = controller.login(new AuthByRefreshTokenRequest(refreshToken), null);

    // THEN
    Mockito.verify(authService, Mockito.times(1)).login(refreshToken);
  }

  @Test
  void loginEmailTestWithValidation() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    Mockito.doThrow(new BadRequest(""));
    AuthResponse response = controller.login(request, null);

    // THEN
    Mockito.verify(authService, Mockito.never()).login("test@example.com", "password123");
  }
}
