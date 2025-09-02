package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    // GIVEN: Initialize a new AuthResponse object with default values.
    Date now = new Date();
    authResponse = new AuthResponse("Bearer", "testToken", "refresh", now);
  }

  @Test
  void testGetTokenType() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getTokenType() method is called.
    // THEN: The tokenType (Bearer) is returned.
    String tokenType = authResponse.getTokenType();
    assert "Bearer".equals(tokenType);
  }

  @Test
  void test getToken() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getToken() method is called.
    // THEN: The token ("testToken") is returned.
    String token = authResponse.getToken();
    assert "testToken".equals(token);
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The refreshToken ("refresh") is returned.
    String refreshToken = authResponse.getRefreshToken();
    assert "refresh".equals(refreshToken);
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN: An AuthResponse object has been created.
    // WHEN: The getExpiresAt() method is called.
    // THEN: The expiresAt date (now) is returned.
    Date expiresAt = authResponse.getExpiresAt();
    assert expiresAt.equals(new Date());
  }
}
