package com.bestpractice.api.domain.component;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthComponentGeneratedAiTests {

  @Mock
  private CredentialProperty credentialProperty;

  private AuthComponent authComponent;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(credentialProperty.getHmacSecret()).thenReturn("test-secret");
    when(credentialProperty.getProvider()).thenReturn("test-provider");
    when(credentialProperty.convertToIntExpires()).thenReturn(2);
    authComponent = new AuthComponent(credentialProperty);
  }

  @Test
  void decodeJwt_validToken_returnsDecodedJWT() {
    // GIVEN
    String token = JWT.create()
        .withIssuer("test-provider")
        .sign(Algorithm.HMAC256("test-secret"));

    // WHEN
    DecodedJWT decodedJWT = authComponent.decodeJwt(token);

    // THEN
    assertThat(decodedJWT).isNotNull();
    assertThat(decodedJWT.getIssuer()).isEqualTo("test-provider");
  }

  @Test
  void decodeJwt_invalidSignature_throwsInternalServerError() {
    // GIVEN
    String token = JWT.create()
        .withIssuer("test-provider")
        .sign(Algorithm.HMAC256("wrong-secret"));

    // WHEN & THEN
    assertThatThrownBy(() -> authComponent.decodeJwt(token))
        .isInstanceOf(InternalServerError.class)
        .hasMessage("Unknown signature secret key");
  }

  @Test
  void decodeJwt_expiredToken_throwsUnAuthorized() {
    // GIVEN
    String token = JWT.create()
        .withIssuer("test-provider")
        .withExpiresAt(new Date(System.currentTimeMillis() - 1000))
        .sign(Algorithm.HMAC256("test-secret"));

    // WHEN & THEN
    assertThatThrownBy(() -> authComponent.decodeJwt(token))
        .isInstanceOf(UnAuthorized.class)
        .hasMessage("Token is expired time");
  }

  @Test
  void decodeJwt_invalidToken_throwsUnAuthorized() {
    // GIVEN
    String token = "invalid-token";

    // WHEN & THEN
    assertThatThrownBy(() -> authComponent.decodeJwt(token))
        .isInstanceOf(UnAuthorized.class)
        .hasMessage("Invalid token");
  }

  @Test
  void generateJwt_validInputs_returnsCredential() {
    // GIVEN
    String userId = "12345";
    String email = "test@example.com";
    boolean isRefresh = false;

    // WHEN
    Credential credential = authComponent.generateJwt(userId, email, isRefresh);

    // THEN
    assertThat(credential).isNotNull();
    assertThat(credential.getToken()).isNotEmpty();
    assertThat(credential.getTokenType()).isEqualTo("Bearer");
    assertThat(credential.getExp()).isNotNull();
    assertThat(credential.isRefresh()).isFalse();
  }

  @Test
  void generateJwt_refreshToken_returnsCredentialWithRefreshTrue() {
    // GIVEN
    String userId = "12345";
    String email = "test@example.com";
    boolean isRefresh = true;

    // WHEN
    Credential credential = authComponent.generateJwt(userId, email, isRefresh);

    // THEN
    assertThat(credential).isNotNull();
    assertThat(credential.getToken()).isNotEmpty();
    assertThat(credential.getTokenType()).isEqualTo("Bearer");
    assertThat(credential.getExp()).isNull();
    assertThat(credential.isRefresh()).isTrue();
  }
}