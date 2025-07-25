package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerGeneratedAiTests {

  private AuthService authServiceMock;
  private AuthController controller;

  @BeforeEach
  void setUp() {
    authServiceMock = Mockito.mock(AuthService.class);
    controller = new AuthController(authServiceMock);
  }

  @Test
  void emailLogin_validRequest_returnsAuthResponse() {
    // Arrange
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password");

    // Act
    AuthResponse response = controller.login(request, null);

    // Assert
    assertNotNull(response);
    assertEquals("test@example.com", response.getTokenType());
    assertEquals("token", response.getToken());
    assertEquals("token", response.getRefreshToken());
    assertEquals("expired_at", response.getExpiresAt().toString());
  }

  @Test
  void refreshtokenLogin_validRequest_returnsAuthResponse() {
    // Arrange
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh_token");

    // Act
    AuthResponse response = controller.login(request, null);

    // Assert
    assertNotNull(response);
    assertEquals("token_type", response.getTokenType());
    assertEquals("token", response.getToken());
    assertEquals("token", response.getRefreshToken());
    assertEquals("expired_at", response.getExpiresAt().toString());
  }

  @Test
  void emailLogin_invalidRequest_throwsBadRequest() {
    // Arrange
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail(null);

    // Act & Assert
    assertThrows(BadRequest.class, () -> controller.login(request, null));
  }

  @Test
  void refreshtokenLogin_invalidRequest_throwsBadRequest() {
    // Arrange
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken(null);

    // Act & Assert
    assertThrows(BadRequest.class, () -> controller.login(request, null));
  }
}
