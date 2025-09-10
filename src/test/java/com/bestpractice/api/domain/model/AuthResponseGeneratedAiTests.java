package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import java.time.Instant;
import java.util.Date;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @Before
  void setUp() {
    // Set up a default date for testing purposes.
    Date now = new Date(Instant.now().atZone(java.time.ZoneId.systemDefault()).toInstant().getEpochSecond());
    authResponse = new AuthResponse("Bearer", "someToken", "refreshToken", now);
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
    // THEN: The token ("someToken") is returned.
    assertEquals("someToken", authResponse.getToken());
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken ("refreshToken") is returned.
    assertEquals("refreshToken", authResponse.getRefreshToken());
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date is returned.
    assertEquals(new Date(Instant.now().atZone(java.time.ZoneId.systemDefault()).toInstant().getEpochSecond()), authResponse.getExpiresAt());
  }
}
