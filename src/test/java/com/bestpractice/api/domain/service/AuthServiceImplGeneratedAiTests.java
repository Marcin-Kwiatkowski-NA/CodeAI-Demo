package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplGeneratedAiTests {

  private AuthServiceImpl authService;

  @BeforeEach
  void setUp() {
    BCryptPasswordEncryptionComponent encryptionComponent = new BCryptPasswordEncryptionComponent();
    AuthComponent authComponent = new AuthComponent();
    UserPersistentRepository userPersistentRepository = new UserPersistentRepository();
    authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
  }

  @Test
  void login_validCredentials() {
    // GIVEN: A user exists in the database with email "test@example.com" and password "password123".
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password123");

    // WHEN: The login method is called with email "test@example.com" and password "password123".
    AuthResponse response = authService.login("test@example.com", "password123");

    // THEN: A valid AuthResponse is returned containing the token, token type, expiration date, and refresh flag.
    assertEquals("Bearer", response.getTokenType());
    assertEquals("token123", response.getToken());
    assertEquals(new Date(1678886400000L), response.getExp());
    assertEquals(true, response.isRefresh());
  }

  @Test
  void login_invalidCredentials() {
    // GIVEN: A user exists in the database with email "test@example.com" and password "password123".
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password123");

    // WHEN: The login method is called with email "test@example.com" and password "wrongpassword".
    UnAuthorized exception = assertThrows(UnAuthorized.class, () -> authService.login("test@example.com", "wrongpassword"));

    // THEN: An UnAuthorized exception is thrown with the message "Email or password is invalid".
    assertEquals("Email or password is invalid", exception.getMessage());
  }

  @Test
  void login_refreshToken() {
    // GIVEN: A user exists in the database with email "test@example.com" and password "password123".
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password123");

    // WHEN: The login method is called with refresh token "refresh_token_value".
    Credential token = new Credential("token123", "Bearer", new Date(1678886400000L), true);
    Credential rToken = new Credential("refresh_token_value", "Bearer", new Date(1678886400000L), true);
    AuthResponse response = authService.login("refresh_token_value");

    // THEN: A valid AuthResponse is returned containing the token, token type, expiration date, and refresh flag.
    assertEquals("Bearer", response.getTokenType());
    assertEquals("token123", response.getToken());
    assertEquals(new Date(1678886400000L), response.getExp());
    assertEquals(true, response.isRefresh());
  }
}