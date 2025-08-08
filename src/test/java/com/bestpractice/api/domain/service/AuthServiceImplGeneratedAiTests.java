package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@Service
public class AuthServiceImplGeneratedAiTests {
  private final BCryptPasswordEncryptionComponent encryptionComponent;
  private final AuthComponent authComponent;
  private final UserPersistentRepository userPersistentRepository;

  public AuthServiceImplGeneratedAiTests(BCryptPasswordEncryptionComponent encryptionComponent,
      AuthComponent authComponent,
      UserPersistentRepository userPersistentRepository) {

    this.encryptionComponent = encryptionComponent;
    this.authComponent = authComponent;
    this.userPersistentRepository = userPersistentRepository;
  }

  @Test
  void loginTestWithValidCredentials() {
    User user = new User();
    user.setId("123");
    user.setUsername("testuser");
    user.setEmail("test@example.com");
    user.setPassword("password");
    userPersistentRepository.findByEmail("test@example.com").thenReturn(user);
    authComponent.generateJwt(user.getId(), user.getEmail(), false).thenReturn(new Credential());
    AuthResponse authResponse = this.authComponent.login("test@example.com", "password");
    assertEquals("token_type", authResponse.getTokenType());
    assertEquals("token", authResponse.getToken());
    assertEquals("refresh_token", authResponse.getRefreshToken());
    assertEquals(0, authResponse.getExpiresAt().getTime());
  }

  @Test
  void loginTestWithInvalidCredentials() {
    User user = new User();
    user.setId("123");
    user.setUsername("testuser");
    user.setEmail("test@example.com");
    user.setPassword("password");
    userPersistentRepository.findByEmail("test@example.com").thenReturn(user);
    AuthResponse authResponse = this.authComponent.login("test@example.com", "wrongpassword");
    assertEquals(UnAuthorized.class, authResponse.getClass());
  }

  @Test
  void loginTestWithRefreshToken() {
    Credential token = new Credential();
    token.setTokenType("token_type");
    token.setToken("token");
    token.setRefreshToken("refresh_token");
    token.setExp(new Date());
    authComponent.generateJwt(1, "test@example.com", false).thenReturn(token);
    AuthResponse authResponse = this.authComponent.login("refresh_token");
    assertEquals(UnAuthorized.class, authResponse.getClass());
  }

  @BeforeEach
  void setUp() {
    userPersistentRepository.clear();
    authComponent.clear();
  }
}