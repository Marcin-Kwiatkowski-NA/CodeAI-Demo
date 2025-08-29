package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
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

@Service
public class authServiceImpl implements AuthService {
  private final BCryptPasswordEncryptionComponent encryptionComponent;
  private final AuthComponent authComponent;
  private final UserPersistentRepository userPersistentRepository;

  public authServiceImpl(BCryptPasswordEncryptionComponent encryptionComponent,
      AuthComponent authComponent,
      UserPersistentRepository userPersistentRepository) {

    this.encryptionComponent = encryptionComponent;
    this.authComponent = authComponent;
    this.userPersistentRepository = userPersistentRepository;
  }

  public AuthResponse login(String email, String password) {
    // GIVEN: A user is requested to be logged in with email and password
    User user = this.userPersistentRepository.findByEmail(email);
    // WHEN: The user is retrieved from the database
    if (user == null) {
      // WHEN: The user is not found
      throw new UnAuthorized("Email or password is invalid");
    }
    // WHEN: The password is matched against the stored password
    if (!this.encryptionComponent.matchedPassword(password, user.getPassword())) {
      // WHEN: The password does not match
      throw new UnAuthorized("Email or password is invalid");
    }

    // WHEN: A credential token and a refresh token are generated
    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential refreshToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    // THEN: An AuthResponse object is returned containing the token and refresh token
    return new AuthResponse(token.getTokenType(), token.getToken(), refreshToken.getToken(), token.getExp());
  }

  public AuthResponse login(String refreshToken) {
    // GIVEN: A refresh token is provided to obtain a new access token
    DecodedJWT decodedJWT = this.authComponent.decodeJwt(refreshToken);
    String email = decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey).asString();
    boolean isRefresh = decodedJWT.getClaim(AuthComponent.ClaimRefreshKey).asBoolean();

    // WHEN: The user is retrieved from the database based on the email
    User user = this.userPersistentRepository.findByEmail(email);
    // WHEN: The user is retrieved from the database
    if (user == null || !isRefresh) {
      // WHEN: The user is not found or it's not a refresh token
      throw new UnAuthorized("Token invalid");
    }

    // WHEN: A new token and a refresh token are generated
    Credential token = this.authComponent.generateJwt(user.getId(), user.getEmail(), false);
    Credential rToken = this.authComponent.generateJwt(user.getId(), user.getEmail(), true);
    // THEN: An AuthResponse object is returned containing the token and refresh token
    return new AuthResponse(token.getTokenType(), token.getToken(), rToken.getToken(), token.getExp());
  }
}