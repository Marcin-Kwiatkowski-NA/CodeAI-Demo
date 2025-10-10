package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;

import java.util.Date;

@ExtendWith(AuthGeneratedAiTests.class)
public class AuthControllerGeneratedAiTests {

  private AuthService authService;
  private AuthController controller;

  @BeforeEach
  void setUp() {
    // Mock the AuthService dependency
    authService = new MockAuthService();
    controller = new AuthController(authService);
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
    assert response != null;
    assert response.getToken() != null;
    assert response.getTokenType() != null;
    assert response.getExpiresAt() != null;
  }

  @Test
  void loginRefreshTokenTest() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh_token_value");

    // WHEN
    AuthResponse response = controller.login(request, null);

    // THEN
    assert response != null;
    assert response.getToken() != null;
    assert response.getTokenType() != null;
    assert response.getExpiresAt() != null;
  }
}
