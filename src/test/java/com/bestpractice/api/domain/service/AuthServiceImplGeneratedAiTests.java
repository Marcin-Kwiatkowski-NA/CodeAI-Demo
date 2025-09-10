package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    // GIVEN: Setup the environment for login
    User user = this.userPersistentRepository.findByEmail(email);
    // WHEN: User is not found
    if (user == null) {
      // THEN: Throw an UnAuthorized exception
      throw new UnAuthorized("Email or password is invalid");
    }
    // WHEN: Password doesn't match
    if (!this.encryptionComponent.matchedPassword(password, user.getPassword())) {
      // THEN: Throw an UnAuthorized exception
      throw new UnAuthorized("Email or password is invalid");
    }

    // WHEN: Generate JWT and Refresh Token
    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential refreshToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    // THEN: Return AuthResponse with token and refresh token
    return new AuthResponse(token.getTokenType(), token.getToken(), refreshToken.getToken(), token.getExp());
  }

  public AuthResponse login(String refreshToken) {
    // GIVEN: Setup the environment for login with refresh token
    DecodedJWT decodedJWT = this.authComponent.decodeJwt(refreshToken);
    String email = decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString();
    boolean isRefresh = decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean();

    // WHEN: Retrieve user from repository
    User user = this.userPersistentRepository.findByEmail(email);
    // WHEN: User or Refresh Token is invalid
    if (user == null || !isRefresh) {
      // THEN: Throw an UnAuthorized exception
      throw new UnAuthorized("Token invalid");
    }

    // WHEN: Generate new token and refresh token
    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential rToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    // THEN: Return AuthResponse with token and refresh token
    return new AuthResponse(token.getTokenType(), token.getToken(), rToken.getToken(), token.getExp());
  }
}
