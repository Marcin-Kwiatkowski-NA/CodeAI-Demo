package com.bestpractice.api.domain.model;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import java.util.Date;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    // Set up the AuthResponse object with default values for testing.
    this.authResponse = new AuthResponse("Bearer", "abcdef123456", "refresh_token_value", new Date());
  }

  @Test
  void testGetTokenType() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType (Bearer) is returned.
    assertEquals("Bearer", authResponse.getTokenType());
  }

  @Test
  void test getToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getToken() method is called.
    // THEN: The token (abcdef123456) is returned.
    assertEquals("abcdef123456", authResponse.getToken());
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken (refresh_token_value) is returned.
    assertEquals("refresh_token_value", authResponse.getRefreshToken());
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date is returned.
    assertEquals(new Date(), authResponse.getExpiresAt());
  }
}