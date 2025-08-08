package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    // Set up the AuthResponse object with sample values.
    this.authResponse = new AuthResponse("Bearer", "abcdef123456", "refresh_token_value", new Date());
  }

  @Test
  void testGetTokenType() {
    // GIVEN: We have an AuthResponse object.
    // WHEN: We call the getTokenType() method.
    // THEN: The method returns the token type ("Bearer").
    assertEquals("Bearer", authResponse.getTokenType());
  }

  @Test
  void test getToken() {
    // GIVEN: We have an AuthResponse object.
    // WHEN: We call the getToken() method.
    // THEN: The method returns the token ("abcdef123456").
    assertEquals("abcdef123456", authResponse.getToken());
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: We have an AuthResponse object.
    // WHEN: We call the getRefreshToken() method.
    // THEN: The method returns the refresh token ("refresh_token_value").
    assertEquals("refresh_token_value", authResponse.getRefreshToken());
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: We have an AuthResponse object.
    // WHEN: We call the getExpiresAt() method.
    // THEN: The method returns the expiration date.
    assertEquals(new Date(), authResponse.getExpiresAt());
  }
}
