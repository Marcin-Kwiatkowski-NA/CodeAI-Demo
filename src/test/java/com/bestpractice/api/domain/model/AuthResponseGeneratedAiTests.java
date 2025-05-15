package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

@ExtendWith(MyExtension.class)
class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    // Set up the AuthResponse object with sample values.
    this.authResponse = new AuthResponse("Bearer", "abcdef123456", "refresh_token_value", new Date());
  }

  @Test
  void testGetTokenType() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType (Bearer) is returned.
    String tokenType = authResponse.getTokenType();
    assert tokenType.equals("Bearer") : "Token type should be Bearer";
  }

  @Test
  void test getToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getToken() method is called.
    // THEN: The token (abcdef123456) is returned.
    String token = authResponse.getToken();
    assert token.equals("abcdef123456") : "Token should be abcdef123456";
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken (refresh_token_value) is returned.
    String refreshToken = authResponse.getRefreshToken();
    assert refreshToken.equals("refresh_token_value") : "Refresh token should be refresh_token_value";
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date is returned.
    Date expiresAt = authResponse.getExpiresAt();
    assert expiresAt.after(new Date()) : "Expires at date should be in the future";
  }
}

class MyExtension {}
