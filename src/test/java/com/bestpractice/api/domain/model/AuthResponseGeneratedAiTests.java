package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
  void shouldReturnCorrectTokenType() {
    // GIVEN
    String expectedTokenType = tokenType;

    // WHEN
    String actualTokenType = authResponse.getTokenType();

    // THEN
    assertEquals(expectedTokenType, actualTokenType);
  }

  @Test
  void shouldReturnCorrectToken() {
    // GIVEN
    String expectedToken = token;

    // WHEN
    String actualToken = authResponse.getToken();

    // THEN
    assertEquals(expectedToken, actualToken);
  }

  @Test
  void shouldReturnCorrectRefreshToken() {
    // GIVEN
    String expectedRefreshToken = refreshToken;

    // WHEN
    String actualRefreshToken = authResponse.getRefreshToken();

    // THEN
    assertEquals(expectedRefreshToken, actualRefreshToken);
  }

  @Test
  void shouldReturnCorrectExpiresAt() {
    // GIVEN
    Date expectedExpiresAt = expiresAt;

    // WHEN
    Date actualExpiresAt = authResponse.getExpiresAt();

    // THEN
    assertEquals(expectedExpiresAt, actualExpiresAt);
  }

  @Test
  void shouldHandleNullValuesGracefully() {
    // GIVEN
    AuthResponse nullAuthResponse = new AuthResponse(null, null, null, null);

    // WHEN
    String actualTokenType = nullAuthResponse.getTokenType();
    String actualToken = nullAuthResponse.getToken();
    String actualRefreshToken = nullAuthResponse.getRefreshToken();
    Date actualExpiresAt = nullAuthResponse.getExpiresAt();

    // THEN
    assertNull(actualTokenType);
    assertNull(actualToken);
    assertNull(actualRefreshToken);
    assertNull(actualExpiresAt);
  }

  @Test
  void shouldCreateObjectWithValidValuesWithoutException() {
    // GIVEN
    String validTokenType = "Bearer";
    String validToken = "tokenValue";
    String validRefreshToken = "refreshValue";
    Date validExpiresAt = new Date();

    // WHEN
    AuthResponse response = new AuthResponse(validTokenType, validToken, validRefreshToken, validExpiresAt);

    // THEN
    assertEquals(validTokenType, response.getTokenType());
    assertEquals(validToken, response.getToken());
    assertEquals(validRefreshToken, response.getRefreshToken());
    assertEquals(validExpiresAt, response.getExpiresAt());
  }

  @Test
  void shouldCreateObjectWithNullValuesWithoutException() {
    // GIVEN
    String nullTokenType = null;
    String nullToken = null;
    String nullRefreshToken = null;
    Date nullExpiresAt = null;

    // WHEN
    AuthResponse response = new AuthResponse(nullTokenType, nullToken, nullRefreshToken, nullExpiresAt);

    // THEN
    assertNull(response.getTokenType());
    assertNull(response.getToken());
    assertNull(response.getRefreshToken());
    assertNull(response.getExpiresAt());
  }
}
