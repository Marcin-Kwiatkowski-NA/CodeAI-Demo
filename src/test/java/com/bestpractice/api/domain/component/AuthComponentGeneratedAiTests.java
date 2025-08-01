package com.bestpractice.api.components;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.components.AuthComponent;
import com.bestpractice.api.common.property.CredentialProperty;

class AuthComponentGeneratedAiTests {

  @Test
  void authComponent_generateJwt_valid() {
    // GIVEN
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setHmacSecret("testSecret");
    credentialProperty.setProvider("testProvider");
    AuthComponent authComponent = new AuthComponent(credentialProperty);
    String userId = "testUser";
    String email = "test@example.com";
    boolean isRefresh = false;

    // WHEN
    Credential credential = authComponent.generateJwt(userId, email, isRefresh);

    // THEN
    assertNotNull(credential);
    assertEquals("Bearer", credential.getTokenType());
    assertEquals(userId, credential.getClaimUserIdKey());
    assertEquals("test@example.com", credential.getClaimUserEmailKey());
    assertNull(credential.getExp());
    assertTrue(credential.isRefresh());
  }

  @Test
  void authComponent_decodeJwt_valid() {
    // GIVEN
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setHmacSecret("testSecret");
    AuthComponent authComponent = new AuthComponent(credentialProperty);
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwicdG9rIjoxMzY4NTg4NTk4LCJleHAiOjE2NzgzMDQzNzgsInVzZXJIZXNDbGl0IjoiU2VjdXJpdGhlcmUifQ.abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // WHEN
    DecodedJWT decodedJwt = authComponent.decodeJwt(token);

    // THEN
    assertNotNull(decodedJwt);
    assertEquals("testUser", decodedJwt.getSubject());
    assertEquals("test@example.com", decodedJwt.getClaimUserEmailKey());
    assertNull(decodedJwt.getExp());
  }

  @Test
  void authComponent_decodeJwt_invalidSignature() {
    // GIVEN
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setHmacSecret("testSecret");
    AuthComponent authComponent = new AuthComponent(credentialProperty);
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwicdG9rIjoxMzY4NTg4NTk4LCJleHAiOjE2NzgzMDQzNzgsInVzZXJIZXNDbGl0IjoiU2VjdXJpdGhlcmUifQ.wrongSignature";

    // WHEN
    try {
      authComponent.decodeJwt(token);
    } catch (Exception e) {
      // THEN
      assertTrue(e instanceof InternalServerError);
      assertEquals("Unknown signature secret key", e.getMessage());
    }
  }

  @Test
  void authComponent_decodeJwt_expiredToken() {
    // GIVEN
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setHmacSecret("testSecret");
    AuthComponent authComponent = new AuthComponent(credentialProperty);
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwicdG9rIjoxMzY4NTg4NTk4LCJleHAiOjE2NzgzMDQzNzgsInVzZXJIZXNDbGl0IjoiU2VjdXJpdGhlcmUifQ.expiredToken";

    // WHEN
    try {
      authComponent.decodeJwt(token);
    } catch (Exception e) {
      // THEN
      assertTrue(e instanceof TokenExpiredException);
      assertEquals("Token is expired time", e.getMessage());
    }
  }
}
