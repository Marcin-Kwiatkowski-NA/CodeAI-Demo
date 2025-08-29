package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import java.util.Date;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // GIVEN: Initialize a new AuthResponse object with default values.
    Date now = new Date();
    authResponse = new AuthResponse("Bearer", "testToken", "refresh", now);
  }

  @org.junit.jupiter.api.Test
  void testGetTokenType() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType ("Bearer") is returned.
    String tokenType = authResponse.getTokenType();
    assert tokenType.equals("Bearer") : "Token type should be Bearer";
  }

  @org.junit.jupiter.api.Test
  void test getToken() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getToken() method is called.
    // THEN: The token ("testToken") is returned.
    String token = authResponse.getToken();
    assert token.equals("testToken") : "Token should be testToken";
  }

  @org.junit.jupiter.api.Test
  void testGetRefreshToken() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken ("refresh") is returned.
    String refreshToken = authResponse.getRefreshToken();
    assert refreshToken.equals("refresh") : "Refresh token should be refresh";
  }

  @org.junit.jupiter.api.Test
  void testGetExpiresAt() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date (current date) is returned.
    Date expiresAt = authResponse.getExpiresAt();
    assert expiresAt.after(new Date()) : "Expires at date should be in the future";
  }
}
