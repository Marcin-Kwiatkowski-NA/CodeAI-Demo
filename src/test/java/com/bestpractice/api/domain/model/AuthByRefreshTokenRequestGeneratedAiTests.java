package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import java.util.Objects;

public class AuthByRefreshTokenRequest {

  @NotNull
  private String refreshToken;

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
}

// GeneratedAiTests
import com.bestpractice.api.domain.model.AuthByRefreshTokenRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions.Assertion;

@Test
void testGetRefreshTokenReturnsCorrectValue() {
  AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
  assertNotNull(request.getRefreshToken());
  assertEquals("test_12345", request.getRefreshToken());
}

@Test
void testSetRefreshTokenReturnsCorrectValue() {
  AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
  request.setRefreshToken("test_12345");
  assertNotNull(request.getRefreshToken());
  assertEquals("test_12345", request.getRefreshToken());
}

@Test
void testGetRefreshTokenReturnsNull() {
  AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
  assertNull(request.getRefreshToken());
}

@Test
void testGetRefreshTokenEqualsNull() {
  AuthByRefreshTokenRequest request = new AuthByRefreshTokenRequest();
  assertNull(request.getRefreshToken());
}
