package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
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
  void shouldHandleNullExpirationDateGracefully() {
    // GIVEN
    AuthResponse responseWithNullDate = new AuthResponse(tokenType, token, refreshToken, null);

    // WHEN
    Date actualExpiresAt = responseWithNullDate.getExpiresAt();

    // THEN
    assertNull(actualExpiresAt);
  }

  @Test
  void shouldAllowNullTokenType() {
    // GIVEN
    String nullTokenType = null;

    // WHEN
    AuthResponse response = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

    // THEN
    assertNull(response.getTokenType());
  }

  @Test
  void shouldAllowNullToken() {
    // GIVEN
    String nullToken = null;

    // WHEN
    AuthResponse response = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

    // THEN
    assertNull(response.getToken());
  }

  @Test
  void shouldAllowNullRefreshToken() {
    // GIVEN
    String nullRefreshToken = null;

    // WHEN
    AuthResponse response = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

    // THEN
    assertNull(response.getRefreshToken());
  }
}
