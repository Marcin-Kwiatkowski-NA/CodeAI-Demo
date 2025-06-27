package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

@ExtendWith(AuthServiceImplGeneratedAiTests.class)
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
  void login_validCredentials_returnsAuthResponse() {
    // GIVEN: A valid user exists in the database.
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setId(1L);
    userPersistentRepository.save(user);

    // WHEN: The login method is called with valid credentials.
    AuthResponse response = authService.login("test@example.com", "password");

    // THEN: The login method returns an AuthResponse object with the correct token type, token, refresh token, and expiration time.
    assert response.getTokenType().equals("Bearer")
        ::equals
        ::equals
        ::equals
        ::equals
    }

  @Test
  void login_invalidCredentials_throwsUnAuthorizedException() {
    // GIVEN: A valid user exists in the database.
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setId(1L);
    userPersistentRepository.save(user);

    // WHEN: The login method is called with invalid credentials.
    UnAuthorized exception = assertThrows(UnAuthorized.class, () -> {
      authService.login("test@example.com", "wrongpassword");
    });

    // THEN: The login method throws an UnAuthorized exception with the message "Email or password is invalid".
    assert exception.getMessage().equals("Email or password is invalid");
  }

  @Test
  void login_refresh_token_valid_returnsAuthResponse() {
    // GIVEN: A valid user exists in the database.
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setId(1L);
    userPersistentRepository.save(user);

    // WHEN: The login method is called with a valid refresh token.
    Credential token = new Credential("token", "Bearer", new Date(), true);
    Credential rToken = new Credential("refreshToken", "Bearer", new Date(), true);
    AuthResponse response = authService.login("refreshToken");

    // THEN: The login method returns an AuthResponse object with the correct token type, token, refresh token, and expiration time.
  }

  @Test
  void login_refresh_token_invalid_throwsUnAuthorizedException() {
    // GIVEN: A valid user exists in the database.
    User user = new User();
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setId(1L);
    userPersistentRepository.save(user);

    // WHEN: The login method is called with an invalid refresh token.
    Credential token = new Credential("invalidRefreshToken", "Bearer", new Date(), true);
    Credential rToken = new Credential("refreshToken", "Bearer", new Date(), true);
    assertThrows(UnAuthorized.class, () -> {
      authService.login("invalidRefreshToken");
    });

    // THEN: The login method throws an UnAuthorized exception with the message "Token invalid".
  }
}
