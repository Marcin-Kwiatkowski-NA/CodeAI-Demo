package com.bestpractice.api.domain.component;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthComponentGeneratedAiTests {

  private AuthComponent authComponent;

  @BeforeEach
  void setUp() {
    CredentialProperty credentialProperty = new CredentialProperty();
    credentialProperty.setHmacSecret("testSecret");
    credentialProperty.setProvider("testProvider");
    credentialProperty.setExpiresHourStr("1");
    authComponent = new AuthComponent(credentialProperty);
  }

  @Test
  void generateJwt_validInput_returnsCredential() {
    String userId = "testUser";
    String email = "test@example.com";
    boolean isRefresh = false;

    Credential credential = authComponent.generateJwt(userId, email, isRefresh);

    assertNotNull(credential);
    assertEquals("Bearer", credential.getType());
    assertEquals("1", credential.getExpirationHourStr());
    assertEquals(userId, credential.getSubject());
    assertEquals(email, credential.getEmail());
    assertFalse(credential.isRefresh());
  }
}
