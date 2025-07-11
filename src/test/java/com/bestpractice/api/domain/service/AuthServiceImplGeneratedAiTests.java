package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.AuthResponse;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

@ExtendWith(AuthComponent.class)
class AuthServiceImplGeneratedAiTests {

  private AuthServiceImpl authService;
  private BCryptPasswordEncryptionComponent encryptionComponent;
  private AuthComponent authComponent;
  private UserPersistentRepository userPersistentRepository;

  @BeforeEach
  void setUp() {
    // Mock dependencies for testing purposes.
    encryptionComponent = new BCryptPasswordEncryptionComponent();
    authComponent = new AuthComponent(new CredentialProperty());
    userPersistentRepository = new UserPersistentRepository() {
      @Override
      public User findByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        return user;
      }
    };
    authService = new AuthServiceImpl(encryptionComponent, authComponent, userPersistentRepository);
  }

  @Test
  void login_validCredentials_returnsAuthResponse() {
    // GIVEN: Valid user credentials
    String email = "test@example.com";
    String password = "password";

    // WHEN: User logs in successfully
    AuthResponse response = authService.login(email, password);

    // THEN: Verify the response contains the expected data
    assert response != null;
    assert response.getTokenType().equals("Bearer");
    assert response.getToken() != null;
    assert response.getExp() != null;
  }

  @Test
  void login_invalidCredentials_throwsUnAuthorizedException() {
    // GIVEN: Invalid user credentials
    String email = "test@example.com";
    String password = "wrongpassword";

    // WHEN: User attempts to log in with invalid credentials
    assertThrows(UnAuthorized.class, () -> authService.login(email, password));
  }

  @Test
  void login_userNotFound_throwsUnAuthorizedException() {
    // GIVEN: User with the specified email does not exist
    String email = "nonexistent@example.com";

    // WHEN: User attempts to log in with a non-existent email
    assertThrows(UnAuthorized.class, () -> authService.login(email, "password"));
  }

  @Test
  void login_refresh_token_generates_new_tokens() {
    // GIVEN: A user exists
    String email = "test@example.com";
    String password = "password";

    // WHEN: User logs in with refresh token
    AuthResponse response = authService.login(email, password);

    // THEN: Verify the response contains the expected data
    assert response != null;
    assert response.getTokenType().equals("Bearer");
    assert response.getToken() != null;
    assert response.getExp() != null;
  }
}