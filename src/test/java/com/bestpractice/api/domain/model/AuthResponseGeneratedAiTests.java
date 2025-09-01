package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;

  @BeforeEach
  void setUp() {
    Date now = new Date();
    authResponse = new AuthResponse("Bearer", "testToken", "refresh", now);
  }

  @Test
  void testGetTokenType() {
    String tokenType = authResponse.getTokenType();
    assert "Bearer".equals(tokenType);
  }

  @Test
  void test getToken() {
    String token = authResponse.getToken();
    assert "testToken".equals(token);
  }

  @Test
  void testGetRefreshToken() {
    String refreshToken = authResponse.getRefreshToken();
    assert "refresh".equals(refreshToken);
  }

  @Test
  void testGetExpiresAt() {
    Date expiresAt = authResponse.getExpiresAt();
    assert new Date(1678886400000L).equals(expiresAt);
  }
}
