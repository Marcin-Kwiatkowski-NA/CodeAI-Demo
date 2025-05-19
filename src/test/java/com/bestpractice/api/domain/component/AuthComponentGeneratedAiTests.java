package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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
    credentialProperty.setHmacSecret("testSecret");
    credentialProperty.setProvider("testProvider");
    authComponent = new AuthComponent(credentialProperty);
  }

  @Test
  void generateJwt_validInput_returnsJwt() {
    String userId = "testUser";
    String email = "test@example.com";
    boolean isRefresh = false;

    assertNotNull(authComponent.generateJwt(userId, email, isRefresh));
    assertEquals("Bearer", authComponent.generateJwt(userId, email, isRefresh).getType());
    assertEquals(isRefresh ? "true" : "false", authComponent.generateJwt(userId, email, isRefresh).getClaims().get(AuthComponent.ClaimRefreshKey));
    assertEquals(userId, authComponent.generateJwt(userId, email, isRefresh).getClaims().get(AuthComponent.ClaimUserIdKey));
    assertEquals(email, authComponent.generateJwt(userId, email, isRefresh).getClaims().get(AuthComponent.ClaimUserEmailKey));
  }
}
