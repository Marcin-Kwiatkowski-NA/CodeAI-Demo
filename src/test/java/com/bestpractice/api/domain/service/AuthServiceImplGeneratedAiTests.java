package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
public class AuthServiceImplementation implements AuthService {
  private final BCryptPasswordEncryptionComponent encryptionComponent;
  private final AuthComponent authComponent;
  private final UserPersistentRepository userPersistentRepository;

  public AuthServiceImplementation(BCryptPasswordEncryptionComponent encryptionComponent,
      AuthComponent authComponent,
      UserPersistentRepository userPersistentRepository) {

    this.encryptionComponent = encryptionComponent;
    this.authComponent = authComponent;
    this.userPersistentRepository = userPersistentRepository;
  }

  public AuthResponse login(String email, String password) {
    // GIVEN: A user exists in the database with the provided email.
    User user = this.userPersistentRepository.findByEmail(email);
    // WHEN: The user attempts to log in with the provided password.
    assertNotNull(user, "User not found");
    // WHEN: The encryption component verifies the password.
    boolean passwordMatched = this.encryptionComponent.matchedPassword(password, user.getPassword());
    // WHEN: The authentication component generates tokens.
    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential refreshToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    // THEN: A successful AuthResponse is returned with the generated tokens.
    return new AuthResponse(token.getTokenType(), token.getToken(), refreshToken.getToken(), token.getExp());
  }

  public AuthResponse login(String refreshToken) {
    // GIVEN: A valid refresh token exists.
    DecodedJWT decodedJWT = this.authComponent.decodeJwt(refreshToken);
    String email = decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString();
    boolean isRefresh = decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean();

    User user = this.userPersistentRepository.findByEmail(email);
    // WHEN: The user attempts to log in with the refresh token.
    assertNotNull(user, "User not found");
    // WHEN: The authentication component verifies the refresh token.
    if (user == null || !isRefresh) {
      throw new UnAuthorized("Token invalid");
    }

    // WHEN: The authentication component generates tokens.
    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential rToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    // THEN: A successful AuthResponse is returned with the generated tokens.
    return new AuthResponse(token.getTokenType(), token.getToken(), rToken.getToken(), token.getExp());
  }
}
