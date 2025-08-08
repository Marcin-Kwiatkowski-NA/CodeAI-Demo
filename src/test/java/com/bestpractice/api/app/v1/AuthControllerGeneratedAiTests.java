package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    // GIVEN: Setup the necessary preconditions
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN: Call the login method with the email and password
    Mockito.when(authService.login("test@example.com", "password123")).thenReturn(new AuthResponse("Bearer", "token", "refresh_token", new Date()));

    // THEN: Assert that the login method returns the expected AuthResponse
    AuthResponse response = controller.login(request, null);

    // Assert that the token type is "Bearer"
    assertEquals("Bearer", response.getTokenType());

    // Assert that the token is "token"
    assertEquals("token", response.getToken());

    // Assert that the refresh token is "refresh_token"
    assertEquals("refresh_token", response.getRefreshToken());
  }

  @Test
  void loginRefreshTokenTest() {
    // GIVEN: Setup the necessary preconditions
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh_token");

    // WHEN: Call the login method with the refresh token
    Mockito.when(authService.login("refresh_token")).thenReturn(new AuthResponse("Bearer", "token", "refresh_token", new Date()));

    // THEN: Assert that the login method returns the expected AuthResponse
    AuthResponse response = controller.login(request, null);

    // Assert that the token type is "Bearer"
    assertEquals("Bearer", response.getTokenType());

    // Assert that the token is "token"
    assertEquals("token", response.getToken());

    // Assert that the refresh token is "refresh_token"
    assertEquals("refresh_token", response.getRefreshToken());
  }

  @Test
  void loginBadRequestTest() {
    // GIVEN: Setup the necessary preconditions
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail(null);
    request.setPassword(null);

    // WHEN: Call the login method with invalid input
    Mockito.doThrow(new BadRequest("email")).when(authService.login(null, null));

    // THEN: Assert that a BadRequest exception is thrown
    assertThrows(BadRequest.class, () -> controller.login(request, null));
  }
}
