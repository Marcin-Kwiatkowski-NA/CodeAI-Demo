package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;
  private String tokenType;
  private String token;
  private String refreshToken;
  private Date expiresAt;

  @BeforeEach
  void setUp() {
    tokenType = "Bearer";
    token = "sampleToken";
    refreshToken = "sampleRefreshToken";
    expiresAt = new Date(System.currentTimeMillis() + 10000);
    authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);
  }

  @Test
  void testGetTokenType_ShouldReturnExpectedValue() {
    // GIVEN
    // WHEN
    String result = authResponse.getTokenType();
    // THEN
    assertEquals(tokenType, result);
  }

  @Test
  void testGetToken_ShouldReturnExpectedValue() {
    // GIVEN
    // WHEN
    String result = authResponse.getToken();
    // THEN
    assertEquals(token, result);
  }

  @Test
  void testGetRefreshToken_ShouldReturnExpectedValue() {
    // GIVEN
    // WHEN
    String result = authResponse.getRefreshToken();
    // THEN
    assertEquals(refreshToken, result);
  }

  @Test
  void testGetExpiresAt_ShouldReturnExpectedValue() {
    // GIVEN
    // WHEN
    Date result = authResponse.getExpiresAt();
    // THEN
    assertEquals(expiresAt, result);
  }

  @Test
  void testConstructor_ShouldInitializeAllFieldsCorrectly() {
    // GIVEN
    // WHEN
    AuthResponse response = new AuthResponse(tokenType, token, refreshToken, expiresAt);
    // THEN
    assertEquals(tokenType, response.getTokenType());
    assertEquals(token, response.getToken());
    assertEquals(refreshToken, response.getRefreshToken());
    assertEquals(expiresAt, response.getExpiresAt());
  }

  @Test
  void testConstructor_WithNullValues_ShouldNotThrowException() {
    // GIVEN
    // WHEN
    AuthResponse response = new AuthResponse(null, null, null, null);
    // THEN
    assertEquals(null, response.getTokenType());
    assertEquals(null, response.getToken());
    assertEquals(null, response.getRefreshToken());
    assertEquals(null, response.getExpiresAt());
  }

  @Test
  void testConstructor_ShouldCreateInstanceWithoutExceptions() {
    // GIVEN
    // WHEN
    AuthResponse response = new AuthResponse(tokenType, token, refreshToken, expiresAt);
    // THEN
    assertEquals(tokenType, response.getTokenType());
    assertEquals(token, response.getToken());
    assertEquals(refreshToken, response.getRefreshToken());
    assertEquals(expiresAt, response.getExpiresAt());
  }
}
