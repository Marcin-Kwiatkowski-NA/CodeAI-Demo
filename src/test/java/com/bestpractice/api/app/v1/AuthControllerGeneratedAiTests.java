package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyAssertions.class)
class AuthControllerGeneratedAiTests {

  private AuthService authService;
  private AuthController controller;

  @BeforeEach
  void setUp() {
    authService = new AuthService();
    controller = new AuthController(authService);
  }

  @Test
  void loginEmailTest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    // Auto generated method call
    AuthResponse response = controller.login(request, null);

    // THEN
    assertNotNull(response);
    assertEquals("token", response.getTokenType());
    assertEquals("token", response.getToken());
    assertEquals("token", response.getRefreshToken());
    // Removed Date instantiation
  }

  @Test
  void loginRefreshTokenTest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh_token");

    // WHEN
    // Auto generated method call
    AuthResponse response = controller.login(request, null);

    // THEN
    assertNotNull(response);
    assertEquals("token", response.getTokenType());
    assertEquals("token", response.getToken());
    assertEquals("token", response.getRefreshToken());
    // Removed Date instantiation
  }

  @Test
  void loginEmailTestBadRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail(null);

    // WHEN
    // Auto generated method call
    // Expecting exception
    assertThrows(BadRequest.class, () -> controller.login(request, null));
  }

  @Test
  void loginRefreshTokenTestBadRequest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken(null);

    // WHEN
    // Auto generated method call
    // Expecting exception
    assertThrows(BadRequest.class, () -> controller.login(request, null));
  }
}

class MyAssertions {
}