package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.app.v1.AuthController;
import com.bestpractice.api.domain.model.AuthByEmailRequest;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.service.AuthService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AuthGeneratedAiTests {

  @Mock
  private AuthService authService;

  private AuthController controller;

  @BeforeEach
  void setUp() {
    controller = new AuthController(authService);
  }

  @Test
  void emailLogin_validRequest_returnsAuthResponse() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();
    request.setEmail("test@example.com");
    request.setPassword("password");

    // WHEN
    AuthResponse response = controller.login(request, null);

    // THEN
    assertNotNull(response);
    assertNotNull(response.getToken());
    assertNotNull(response.getTokenType());
  }

  @Test
  void emailLogin_invalidRequest_throwsBadRequest() {
    // GIVEN
    AuthByEmailRequest request = new AuthByEmailRequest();

    // WHEN
    // THEN
    assertThrows(BadRequest.class, () -> controller.login(request, null));
  }

  @Test
  void refreshtokenLogin_validRequest_returnsAuthResponse() {
    // GIVEN
    AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
    request.setRefreshToken("valid_refresh_token");

    // WHEN
    AuthResponse response = controller.login(request, null);

    // THEN
    assertNotNull(response);
    assertNotNull(response.getToken());
    assertNotNull(response.getTokenType());
  }
}
