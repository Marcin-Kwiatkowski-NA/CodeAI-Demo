package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    // Set up a default date for testing purposes.
    Date expiresAt = Date.from(Instant.now().plusSeconds(3600));
    authResponse = new AuthResponse("Bearer", "testToken", "refresh", expiresAt);
  }

  @org.junit.jupiter.api.Test
  void testGetTokenType() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType ("Bearer") is returned.
    assertEquals("Bearer", authResponse.getTokenType());
  }

  @org.junit.jupiter.api.Test
  void test getToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getToken() method is called.
    // THEN: The token ("testToken") is returned.
    assertEquals("testToken", authResponse.getToken());
  }

  @org.junit.jupiter.api.Test
  void testGetRefreshToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken ("refresh") is returned.
    assertEquals("refresh", authResponse.getRefreshToken());
  }

  @org.junit.jupiter.api.Test
  void testGetExpiresAt() {
    // GIVEN: A new AuthResponse object is created with an expiration date in the future.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiration date is returned.
    assertEquals(Date.from(Instant.now().plusSeconds(3600)), authResponse.getExpiresAt());
  }
}
