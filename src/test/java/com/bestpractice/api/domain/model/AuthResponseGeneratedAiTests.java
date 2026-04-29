package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Date;

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
  void shouldCreateAuthResponseWithAllFields() {
    // GIVEN
    String expectedTokenType = "Bearer";
    String expectedToken = "tokenValue";
    String expectedRefreshToken = "refreshValue";
    Date expectedExpiresAt = new Date();

    // WHEN
    AuthResponse response = new AuthResponse(expectedTokenType, expectedToken, expectedRefreshToken, expectedExpiresAt);

    // THEN
    assertEquals(expectedTokenType, response.getTokenType());
    assertEquals(expectedToken, response.getToken());
    assertEquals(expectedRefreshToken, response.getRefreshToken());
    assertEquals(expectedExpiresAt, response.getExpiresAt());
  }

  @Test
  void shouldAllowNullValuesForNonAnnotatedFields() {
    // GIVEN
    Date expectedExpiresAt = null;

    // WHEN
    AuthResponse response = new AuthResponse("Bearer", "tokenValue", "refreshValue", expectedExpiresAt);

    // THEN
    assertNull(response.getExpiresAt());
  }

  @Test
  void shouldThrowExceptionWhenRequiredFieldsAreNull() {
    // GIVEN
    String nullTokenType = null;
    String nullToken = null;
    String nullRefreshToken = null;
    Date validDate = new Date();

    // WHEN & THEN
    assertThrows(NullPointerException.class, () -> {
      new AuthResponse(nullTokenType, nullToken, nullRefreshToken, validDate);
    });
  }
}
