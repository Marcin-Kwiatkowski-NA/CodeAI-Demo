package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

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
    Date now = new Date();
    Date expire = new Date(now.getTime() + 3600000); // Expires in 1 hour
    authResponse = new AuthResponse("Bearer", "abcdef123456", "refresh_token_value", expire);
  }

  @Test
  void getTokenType() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType (Bearer) is returned.
    assertEquals("Bearer", authResponse.getTokenType());
  }

  @Test
  void getToken() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getToken() method is called.
    // THEN: The token (abcdef123456) is returned.
    assertEquals("abcdef123456", authResponse.getToken());
  }

  @Test
  void getRefreshToken() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken (refresh_token_value) is returned.
    assertEquals("refresh_token_value", authResponse.getRefreshToken());
  }

  @Test
  void getExpiresAt() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date (one hour from now) is returned.
    Date now = new Date();
    Date expire = new Date(now.getTime() + 3600000);
    assertEquals(expire, authResponse.getExpiresAt());
  }
}
