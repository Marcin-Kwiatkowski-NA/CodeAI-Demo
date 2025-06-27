package com.bestpractice.api.components;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AuthComponentGeneratedAiTests.class)
class AuthComponentGeneratedAiTests {
  private AuthComponent authComponent;

  @BeforeEach
  void setUp() {
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setHmacSecret("secret");
    credentialProperty.setProvider("provider");
    credentialProperty.setSubject("subject");
    credentialProperty.setAlg("alg");
    credentialProperty.setExpiresHourStr("60");
    authComponent = new AuthComponent(credentialProperty);
  }

  @Test
  void decodeJwt_validToken() {
    // GIVEN
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eycB..."
    // WHEN
    DecodedJWT decodedJWT = authComponent.decodeJwt(token);
    // THEN
    assertEquals("user_id", decodedJWT.getClaim(AuthComponent.ClaimUserIdKey));
    assertEquals("user_email", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey));
  }

  @Test
  void generateJwt_valid() {
    // GIVEN
    // WHEN
    Credential credential = authComponent.generateJwt("user_id", "user_email", false);
    // THEN
    assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eycB...", credential.getToken());
    assertEquals("Bearer", credential.getTokenType());
    assertNotNull(credential.getExp());
    assertEquals(false, credential.isRefresh());
  }

  @Test
  void generateJwt_refresh() {
    // GIVEN
    // WHEN
    Credential credential = authComponent.generateJwt("user_id", "user_email", true);
    // THEN
    assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eycB...", credential.getToken());
    assertEquals("Bearer", credential.getTokenType());
    assertEquals(true, credential.isRefresh());
  }

  @Test
  void decodeJwt_expiredToken() {
    // GIVEN
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eycB..."
    // WHEN
    // THEN
    assertThrows(new TokenExpiredException("Token is expired time"), () -> authComponent.decodeJwt(token));
  }

  @Test
  void decodeJwt_invalidSignature() {
    // GIVEN
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eycB..."
    // WHEN
    // THEN
    assertThrows(new SignatureVerificationException("Unknown signature secret key"), () -> authComponent.decodeJwt(token));
  }

  @Test
  void decodeJwt_missingClaim() {
    // GIVEN
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI```java
  @Test
  void generateJwt_invalidClaim() {
    // GIVEN
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eycB..."
    // WHEN
    // THEN
    assertThrows(new IncorrectClaimException("user_id"), () -> authComponent.decodeJwt(token));
  }

  @Test
  void generateJwt_nullExpiresHour() {
    // GIVEN
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setExpiresHourStr("-");
    AuthComponent authComponent = new AuthComponent(credentialProperty);
    // WHEN
    Credential credential = authComponent.generateJwt("user_id", "user_email", false);
    // THEN
    assertNull(credential.getExp());
  }

  @Test
  void generateJwt_invalidHourStr() {
    // GIVEN
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setExpiresHourStr("abc");
    AuthComponent authComponent = new AuthComponent(credentialProperty);
    // WHEN
    Credential credential = authComponent.generateJwt("user_id", "user_email", false);
    // THEN
    assertNull(credential.getExp());
  }

  @Test
  void generateJwt_zeroHour() {
    // GIVEN
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setExpiresHourStr("0");
    AuthComponent authComponent = new AuthComponent(credentialProperty);
    // WHEN
    Credential credential = authComponent.generateJwt("user_id", "user_email", false);
    // THEN
    assertNull(credential.getExp());
  }
}
