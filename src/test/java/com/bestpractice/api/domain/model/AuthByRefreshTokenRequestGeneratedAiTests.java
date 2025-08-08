package com.bestpractice.api.domain.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthByRefreshTokenRequestGeneratedAiTests {

  private AuthByRefreshTokenRequest authByRefreshTokenRequest;

  @BeforeEach
  void setUp() {
    authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
  }

  @Test
  void getRefreshToken() {
    // GIVEN: A new AuthByRefreshTokenRequest object is created.
    // WHEN: The getRefreshToken() method is called.
    // THEN: The returned refreshToken should be null.
    String refreshToken = authByRefreshTokenRequest.getRefreshToken();
    assertEquals(null, refreshToken);
  }

  @Test
  void setRefreshToken() {
    // GIVEN: A new AuthByRefreshTokenRequest object is created.
    // WHEN: The setRefreshToken() method is called with "testRefreshToken".
    // THEN: The returned refreshToken should be "testRefreshToken".
    String refreshToken = "testRefreshToken";
    authByRefreshTokenRequest.setRefreshToken(refreshToken);
    assertEquals(refreshToken, authByRefreshTokenRequest.getRefreshToken());
  }
}