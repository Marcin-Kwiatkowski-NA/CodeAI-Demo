package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    // Set up the AuthResponse object with sample values for testing
    java.util.Date now = new java.util.Date();
    authResponse = new AuthResponse("Bearer", "testToken", "refresh", now);
  }

  @Test
  void getTokenType_returnsCorrectTokenType() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getTokenType() method is called.
    // THEN: The method returns the expected token type ("Bearer").
    assertEquals("Bearer", authResponse.getTokenType());
  }

  @Test
  void getToken_returnsCorrectToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getToken() method is called.
    // THEN: The method returns the expected token ("testToken").
    assertEquals("testToken", authResponse.getToken());
  }

  @Test
  void getRefreshToken_returnsCorrectRefreshToken() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The method returns the expected refresh token ("refresh").
    assertEquals("refresh", authResponse.getRefreshToken());
  }

  @Test
  void getExpiresAt_returnsCorrectExpirationDate() {
    // GIVEN: A new AuthResponse object is created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The method returns the expected expiration date (the current date).
    assertEquals(new java.util.Date(), authResponse.getExpiresAt());
  }
}
