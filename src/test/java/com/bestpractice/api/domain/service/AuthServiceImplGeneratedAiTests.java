package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@Service
public class AuthServiceImplGeneratedAiTests {
  private final BCryptPasswordEncryptionComponent encryptionComponent;
  private final AuthComponent authComponent;
  private final UserPersistentRepository userPersistentRepository;

  @Autowired
  public AuthServiceImplGeneratedAiTests(BCryptPasswordEncryptionComponent encryptionComponent,
      AuthComponent authComponent,
      UserPersistentRepository userPersistentRepository) {

    this.encryptionComponent = encryptionComponent;
    this.authComponent = authComponent;
    this.userPersistentRepository = userPersistentRepository;
  }

  @Test
  void loginTest() {
    // GIVEN
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setId(1L);
    Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
    Mockito.when(encryptionComponent.matchedPassword("password", user.getPassword())).thenReturn(true);
    Credential token = new Credential("token", "token", new Date(), false);
    Credential rToken = new Credential("refresh_token", "refresh_token", new Date(), true);
    Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);
    Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), true)).thenReturn(rToken);

    // WHEN
    AuthResponse authResponse = this.login("test@example.com", "password");

    // THEN
    Assertions.assertNotNull(authResponse);
    Assertions.assertEquals("token", authResponse.getTokenType());
    Assertions.assertEquals("token", authResponse.getToken());
    Assertions.assertEquals("refresh_token", authResponse.getRefreshToken());
    Assertions.assertNotNull(authResponse.getExpiresAt());
  }

  @Test
  void loginRefreshTest() {
    // GIVEN
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setId(1L);
    Mockito.when(userPersistentRepository.findByEmail("test@example.com")).thenReturn(user);
    Credential token = new Credential("token", "token", new Date(), false);
    Mockito.when(authComponent.generateJwt(user.getId(), user.getEmail(), false)).thenReturn(token);

    // WHEN
    AuthResponse authResponse = this.login("token");

    // THEN
    Assertions.assertNotNull(authResponse);
    Assertions.assertEquals("token", authResponse.getTokenType());
    Assertions.assertEquals("token", authResponse.getToken());
    Assertions.assertEquals("refresh_token", authResponse.getRefreshToken());
    Assertions.assertNotNull(authResponse.getExpiresAt());
  }
}
