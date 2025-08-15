package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import com.bestpractice.api.common.exception.BadRequest;

@ExtendWith(AuthControllerGeneratedAiTests.class)
class AuthControllerTest {

  private AuthController authController;
  private AuthService authServiceMock;

  @BeforeEach
  void setUp() {
    authServiceMock = Mockito.mock(AuthService.class);
    authController = new AuthController(authServiceMock);
  }

  @Test
  @DisplayName("Login with Email - Successful")
  void testLoginWithEmailSuccessful() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    AuthResponse response = authController.login(request, null);

    // THEN
    assert response != null;
  }

  @Test
  @DisplayName("Login with Email - Invalid Input")
  void testLoginWithEmailInvalidInput() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail(null);
    request.setPassword(null);

    // WHEN
    when(authServiceMock.login(null, null)).thenReturn(new AuthResponse());

    // THEN
    BadRequest exception = assertThrows(BadRequest.class, () -> {
      authController.login(request, null);
    });

    assert exception.getMessage().contains("email") && exception.getMessage().contains("password");
  }

  @Test
  @DisplayName("Login with Refresh Token - Successful")
  void testLoginWithRefreshTokenSuccessful() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("mockRefreshToken");

    // WHEN
    when(authServiceMock.login(request.getRefreshToken())).thenReturn(new AuthResponse());

    // THEN
    AuthResponse response = authController.login(request, null);

    // Add assertions here
  }
}
