package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.domain.model.Credential;
import com.bestpractice.api.domain.model.DecodedJWT;

public class AppBeanGeneratedAiTests {

  private AuthComponent authComponent;
  private RequestInfoComponent requestInfo;
  private CredentialProperty credentialProperty;

  @BeforeEach
  void setUp() {
    credentialProperty = new CredentialProperty();
    authComponent = new AuthComponent(credentialProperty);
    requestInfo = new RequestInfoComponent();
  }

  @Test
  void decodeJwt_validToken() {
    // GIVEN
    String token = "Bearer someValidToken";
    // WHEN
    DecodedJWT decodedJWT = authComponent.decodeJwt(token);
    // THEN
    assertNotNull(decodedJWT);
    assertEquals("user_id", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey));
    assertEquals("user_email", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey));
  }

  @Test
  void decodeJwt_expiredToken() {
    // GIVEN
    String token = "Bearer expiredToken";
    // WHEN
    // THEN
    UnAuthorized exception = Assertions.assertThrows(
        (ExecutableStatement) () -> authComponent.decodeJwt(token)
    );
    assertEquals("Token is expired time", exception.getMessage());
  }

  @Test
  void decodeJwt_invalidToken() {
    // GIVEN
    String token = "Bearer invalidToken";
    // WHEN
    // THEN
    UnAuthorized exception = Assertions.assertThrows(
        (ExecutableStatement) () -> authComponent.decodeJwt(token)
    );
    assertEquals("Invalid token", exception.getMessage());
  }

  @Test
  void generateJwt_valid() {
    // GIVEN
    String userId = "user123";
    String email = "user@example.com";
    boolean isRefresh = false;
    // WHEN
    Credential credential = authComponent.generateJwt(userId, email, isRefresh);
    // THEN
    assertNotNull(credential);
    assertEquals(userId, credential.getToken());
    assertEquals(email, credential.getToken());
  }

  @Test
  void generateJwt_refreshToken() {
    // GIVEN
    String userId = "user123";
    String email = "user@example.com";
    boolean isRefresh = true;
    // WHEN
    Credential credential = authComponent.generateJwt(userId, email, isRefresh);
    // THEN
    assertNotNull(credential);
    assertEquals(userId, credential.getToken());
    assertEquals(email, credential.getToken());
    assertTrue(credential.isRefresh());
  }
}