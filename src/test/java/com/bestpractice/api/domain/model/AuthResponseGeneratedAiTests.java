package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    Date now = new Date();
    Date expiresAt = new Date(now.getTime() + 3600000);
    authResponse = new AuthResponse("Bearer", "abcdef123456", "refresh_token_value", expiresAt);
  }

  @Test
  void testGetTokenType() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType ("Bearer") is returned.
    assertEquals("Bearer", authResponse.getTokenType());
  }

  @Test
  void test getToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getToken() method is called.
    // THEN: The token ("abcdef123456") is returned.
    assertEquals("abcdef123456", authResponse.getToken());
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken ("refresh_token_value") is returned.
    assertEquals("refresh_token_value", authResponse.getRefreshToken());
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: A new AuthResponse object is created with an expiration date in the future.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiration date (expiresAt) is returned.
    assertEquals(new Date(System.currentTimeMillis() + 3600000), authResponse.getExpiresAt());
  }
}
