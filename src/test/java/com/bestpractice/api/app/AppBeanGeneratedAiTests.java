package com.bestpractice.api.app;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.domain.component.AuthComponent;
import com.bestpractice.api.domain.component.RequestInfoComponent;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.common.property.CredentialProperty;
import com.bestpractice.api.domain.model.Credential;
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

public class AppBeanGeneratedAiTests {

  private AppBean appBean;
  private AuthComponent authComponent;
  private RequestInfoComponent requestInfo;
  private CredentialProperty credentialProperty;

  @BeforeEach
  void setUp() {
    credentialProperty = new CredentialProperty();
    authComponent = new AuthComponent(credentialProperty);
    requestInfo = new RequestInfoComponent();
    appBean = new AppBean(credentialProperty, authComponent, requestInfo);
  }

  @Test
  void testDecodeJwt_validToken() {
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ0.eyJSVlFiOk1WTU1TU1OQU1OQkFOUzEiOiJTYWlsZXJlYWNlOnBhZ2VzIiwicm9sZW1pLXNlc2lvdGlsIjoiQmVkaXJvOnBhZ2VzIiwicGlkcyI6ImRlZmF1bHQifQ.eyJpc3MiOiJTYWlsZXJlYWNlIiwiZXhwIjoxNjc4ODg3ODg0fQ.eyJpc3MiOiJTYWlsZXJlYWNlIiwiZXhwIjoxNjc4ODg3ODg0fQ.eyJpc3MiOiJTYWlsZXJlYWNlIiwiZXhwIjoxNjc4ODg3ODg0fQ.eyJpc3MiOiJTYWlsZXJlYWNlIiwiZXhwIjoxNjc4ODg3ODg0fQ.eyJpc3MiOiJTYWlsZXJlYWNlIiwiZXhwIjoxNjc4ODg3ODg0fQ.";
    DecodedJWT decodedJWT = appBean.interceptorController().interceptorController().decodeJwt(token);
    assertEquals("user", decodedJWT.getSubject());
    assertEquals("user@example.com", decodedJWT.getClaim(AuthComponent.ClaimUserEmailKey));
  }

  @Test
  void testGenerateJwt_validToken() {
    Credential credential = appBean.interceptorController().interceptorController().generateJwt("user", "user@example.com", false);
    assertEquals("user", credential.getToken());
    assertEquals("user@example.com", credential.getToken());
  }

  @Test
  void testDecodeJwt_invalidToken() {
    String invalidToken = "invalid-token";
    assertThrows(new UnAuthorized("Invalid token"), () -> appBean.interceptorController().interceptorController().decodeJwt(invalidToken));
  }
}
