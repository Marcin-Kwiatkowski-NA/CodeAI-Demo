package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    // Set up a valid AuthResponse object for testing
    authResponse = new AuthResponse("Bearer", "abcdef123456", "refresh_token_value", Date.from(Instant.now()));
  }

  @Test
  void testGetTokenType() {
    // GIVEN: A valid AuthResponse object is created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType ("Bearer") is returned.
    String tokenType = authResponse.getTokenType();
    Objects.requireNonNull(tokenType, "TokenType should not be null");
    assertEquals("Bearer", tokenType);
  }

  @Test
  void test getToken() {
    // GIVEN: A valid AuthResponse object is created.
    // WHEN: The getToken() method is called.
    // THEN: The token ("abcdef123456") is returned.
    String token = authResponse.getToken();
    Objects.requireNonNull(token, "Token should not be null");
    assertEquals("abcdef123456", token);
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: A valid AuthResponse object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken ("refresh_token_value") is returned.
    String refreshToken = authResponse.getRefreshToken();
    Objects.requireNonNull(refreshToken, "RefreshToken should not be null");
    assertEquals("refresh_token_value", refreshToken);
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: A valid AuthResponse object is created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date (NOW) is returned.
    Date expiresAt = authResponse.getExpiresAt();
    Objects.requireNonNull(expiresAt, "ExpiresAt should not be null");
    assertEquals(expiresAt, Date.from(Instant.now()));
  }
}
