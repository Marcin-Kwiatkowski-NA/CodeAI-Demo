package com.bestpractice.api.app.v1;

import com.bestpractice.api.domain.model.AuthErrorResponse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import com.bestpractice.api.common.exception.BadRequest;

public class AuthControllerGeneratedAiTests {

  @ExtendWith(MockitoExtension.class)
  public static class MockitoExtension {
  }

  @Test
  public void loginEmailTest() {
    // GIVEN
    AuthService authServiceMock = Mockito.mock(AuthService.class);
    @InjectMocks AuthController controller = new AuthController(authServiceMock);
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password123");

    // WHEN
    AuthResponse response = controller.login(request, null);

    // THEN
    Assertions.assertNotNull(response);
    Assertions.assertEquals("token", response.getTokenType());
    Assertions.assertEquals("token", response.getToken());
    Assertions.assertEquals("token", response.getRefreshToken());
    Assertions.assertNotNull(response.getExpiresAt());
  }

  @Test
  public void loginRefreshTokenTest() {
    // GIVEN
    AuthService authServiceMock = Mockito.mock(AuthService.class);
    @InjectMocks AuthController controller = new AuthController(authServiceMock);
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("refresh_token");

    // WHEN
    AuthResponse response = controller.login(request, null);

    // THEN
    Assertions.assertNotNull(response);
    Assertions.assertEquals("token", response.getTokenType());
    Assertions.assertEquals("token", response.getToken());
    Assertions.assertEquals("token", response.getRefreshToken());
    Assertions.assertNotNull(response.getExpiresAt());
  }

  @Test
  public void loginInvalidEmailTest() {
    // GIVEN
    AuthService authServiceMock = Mockito.mock(AuthService.class);
    @InjectMocks AuthController controller = new AuthController(authServiceMock);
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("invalid-email");
    request.setPassword("password123");

    // WHEN
    BadRequest exception = Assertions.assertThrows(BadRequest.class, () -> controller.login(request, null));

    // THEN
    Assertions.assertEquals("email", exception.getObjectName());
  }

  @Test
  public void loginInvalidRefreshTokenTest() {
    // GIVEN
    AuthService authServiceMock = Mockito.mock(AuthService.class);
    @InjectMocks AuthController controller = new AuthController(authServiceMock);
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("invalid-refresh-token");

    // WHEN
    BadRequest exception = Assertions.assertThrows(BadRequest.class, () -> controller.login(request, null));

    // THEN
    Assertions.assertEquals("refresh_token", exception.getObjectName());
  }
}
