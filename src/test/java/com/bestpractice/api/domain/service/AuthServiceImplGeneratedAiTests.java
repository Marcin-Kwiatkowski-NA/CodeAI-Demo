package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.mockito.Mockito.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

@ExtendWith(MockitoExtension.class)
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
  public void testLoginSuccessful() {
    // Arrange
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
    when(encryptionComponent.matchedPassword("password", user.getPassword())).thenReturn(true);
    when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(new Credential("token", "token", "refresh_token"));
    when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(new Credential("token", "token", "refresh_token"));

    // Act
    AuthResponse authResponse = this.login("test@example.com", "password");

    // Assert
    assertEquals("token", authResponse.getTokenType());
    assertEquals("token", authResponse.getToken());
    assertEquals("refresh_token", authResponse.getRefreshToken());
    assertEquals(0L, authResponse.getExp());
  }

  @Test
  public void testLoginInvalidCredentials() {
    // Arrange
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
    when(encryptionComponent.matchedPassword("wrong_password", user.getPassword())).thenReturn(false);

    // Act & Assert
    assertThrows(new UnAuthorized("Email or password is invalid"), () -> this.login("test@example.com", "wrong_password"));
  }

  @Test
  public void testLoginUserNotFound() {
    // Arrange
    when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(null);

    // Act & Assert
    assertThrows(new UnAuthorized("Email or password is invalid"), () -> this.login("test@example.com", "password"));
  }
}
