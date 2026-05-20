package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
  void shouldAllowNullValuesForConstructorParametersWithoutThrowingException() {
    // GIVEN
    String nullTokenType = null;
    String nullToken = null;
    String nullRefreshToken = null;
    Date nullExpiresAt = null;

    // WHEN & THEN
    // The constructor should not throw an exception even if parameters are null
    AuthResponse response = new AuthResponse(nullTokenType, nullToken, nullRefreshToken, nullExpiresAt);
    assertNull(response.getTokenType());
    assertNull(response.getToken());
    assertNull(response.getRefreshToken());
    assertNull(response.getExpiresAt());
  }

  @Test
  void shouldNotThrowExceptionWhenAccessingFieldsAfterConstruction() {
    // GIVEN
    AuthResponse response = new AuthResponse("Bearer", "tokenValue", "refreshValue", new Date());

    // WHEN & THEN
    // Ensure getters do not throw exceptions
    assertEquals("Bearer", response.getTokenType());
    assertEquals("tokenValue", response.getToken());
    assertEquals("refreshValue", response.getRefreshToken());
  }
}
