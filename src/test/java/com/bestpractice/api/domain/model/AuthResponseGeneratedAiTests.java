package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthResponseGeneratedAiTests {

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
    // WHEN
    String result = authResponse.getTokenType();
    // THEN
    assertEquals(tokenType, result);
  }

  @Test
  void shouldReturnCorrectToken() {
    // GIVEN
    // WHEN
    String result = authResponse.getToken();
    // THEN
    assertEquals(token, result);
  }

  @Test
  void shouldReturnCorrectRefreshToken() {
    // GIVEN
    // WHEN
    String result = authResponse.getRefreshToken();
    // THEN
    assertEquals(refreshToken, result);
  }

  @Test
  void shouldReturnCorrectExpiresAt() {
    // GIVEN
    // WHEN
    Date result = authResponse.getExpiresAt();
    // THEN
    assertEquals(expiresAt, result);
  }

  @Test
  void shouldHandleNullExpirationDateGracefully() {
    // GIVEN
    AuthResponse responseWithNullDate = new AuthResponse(tokenType, token, refreshToken, null);
    // WHEN
    Date result = responseWithNullDate.getExpiresAt();
    // THEN
    assertEquals(null, result);
  }

  @Test
  void shouldHandleEmptyStringsAsValidInputs() {
    // GIVEN
    String emptyTokenType = "";
    String emptyToken = "";
    String emptyRefreshToken = "";
    Date now = new Date();
    // WHEN
    AuthResponse response = new AuthResponse(emptyTokenType, emptyToken, emptyRefreshToken, now);
    // THEN
    assertEquals("", response.getTokenType());
    assertEquals("", response.getToken());
    assertEquals("", response.getRefreshToken());
    assertEquals(now, response.getExpiresAt());
  }

  @Test
  void shouldHandleWhitespaceOnlyStringsAsValidInputs() {
    // GIVEN
    String whitespaceTokenType = "   ";
    String whitespaceToken = " ";
    String whitespaceRefreshToken = "\t";
    Date now = new Date();
    // WHEN
    AuthResponse response = new AuthResponse(whitespaceTokenType, whitespaceToken, whitespaceRefreshToken, now);
    // THEN
    assertEquals("   ", response.getTokenType());
    assertEquals(" ", response.getToken());
    assertEquals("\t", response.getRefreshToken());
    assertEquals(now, response.getExpiresAt());
  }

  @Test
  void shouldHandleVeryLongStringsAsValidInputs() {
    // GIVEN
    String longString = "a".repeat(10000);
    Date now = new Date();
    // WHEN
    AuthResponse response = new AuthResponse(longString, longString, longString, now);
    // THEN
    assertEquals(longString, response.getTokenType());
    assertEquals(longString, response.getToken());
    assertEquals(longString, response.getRefreshToken());
    assertEquals(now, response.getExpiresAt());
  }

  @Test
  void shouldHandlePastExpirationDate() {
    // GIVEN
    Date pastDate = new Date(System.currentTimeMillis() - 100000);
    // WHEN
    AuthResponse response = new AuthResponse(tokenType, token, refreshToken, pastDate);
    // THEN
    assertEquals(pastDate, response.getExpiresAt());
  }

  @Test
  void shouldHandleFutureExpirationDateFarInFuture() {
    // GIVEN
    Date farFutureDate = new Date(System.currentTimeMillis() + Long.MAX_VALUE / 1000000);
    // WHEN
    AuthResponse response = new AuthResponse(tokenType, token, refreshToken, farFutureDate);
    // THEN
    assertEquals(farFutureDate, response.getExpiresAt());
  }

  @Test
  void shouldHandleSingleCharacterTokens() {
    // GIVEN
    String singleChar = "x";
    Date now = new Date();
    // WHEN
    AuthResponse response = new AuthResponse(singleChar, singleChar, singleChar, now);
    // THEN
    assertEquals("x", response.getTokenType());
    assertEquals("x", response.getToken());
    assertEquals("x", response.getRefreshToken());
    assertEquals(now, response.getExpiresAt());
  }

  @Test
  void shouldHandleMixedCaseTokens() {
    // GIVEN
    String mixedCaseTokenType = "BeArEr";
    String mixedCaseToken = "ToKeN";
    String mixedCaseRefreshToken = "ReFrEsH";
    Date now = new Date();
    // WHEN
    AuthResponse response = new AuthResponse(mixedCaseTokenType, mixedCaseToken, mixedCaseRefreshToken, now);
    // THEN
    assertEquals("BeArEr", response.getTokenType());
    assertEquals("ToKeN", response.getToken());
    assertEquals("ReFrEsH", response.getRefreshToken());
    assertEquals(now, response.getExpiresAt());
  }

  @Test
  void shouldHandleBoundaryDateValues() {
    // GIVEN
    Date minDate = new Date(0);
    Date maxDate = new Date(Long.MAX_VALUE);
    // WHEN
    AuthResponse minResponse = new AuthResponse(tokenType, token, refreshToken, minDate);
    AuthResponse maxResponse = new AuthResponse(tokenType, token, refreshToken, maxDate);
    // THEN
    assertEquals(minDate, minResponse.getExpiresAt());
    assertEquals(maxDate, maxResponse.getExpiresAt());
  }

  @Test
  void shouldHandleTokenTypeWithSpecialCharacters() {
    // GIVEN
    String specialChars = "!@#$%^&*()_+{}|:\"<>?";
    Date now = new Date();
    // WHEN
    AuthResponse response = new AuthResponse(specialChars, specialChars, specialChars, now);
    // THEN
    assertEquals(specialChars, response.getTokenType());
    assertEquals(specialChars, response.getToken());
    assertEquals(specialChars, response.getRefreshToken());
    assertEquals(now, response.getExpiresAt());
  }

  @Test
  void shouldHandleTokenTypeWithUnicodeCharacters() {
    // GIVEN
    String unicodeString = "令狐冲😊🔥";
    Date now = new Date();
    // WHEN
    AuthResponse response = new AuthResponse(unicodeString, unicodeString, unicodeString, now);
    // THEN
    assertEquals(unicodeString, response.getTokenType());
    assertEquals(unicodeString, response.getToken());
    assertEquals(unicodeString, response.getRefreshToken());
    assertEquals(now, response.getExpiresAt());
  }

  @Test
  void shouldMaintainImmutabilityAfterCreation() {
    // GIVEN
    Date originalDate = new Date();
    AuthResponse response = new AuthResponse(tokenType, token, refreshToken, originalDate);
    // WHEN
    Date retrievedDate = response.getExpiresAt();
    retrievedDate.setTime(System.currentTimeMillis() + 999999);
    // THEN
    assertThat(response.getExpiresAt()).isEqualTo(originalDate);
  }

  @Test
  void shouldHandleNullValuesForOptionalFieldsGracefully() {
    // GIVEN
    AuthResponse response = new AuthResponse(tokenType, token, refreshToken, null);
    // WHEN
    Date result = response.getExpiresAt();
    // THEN
    assertEquals(null, result);
  }

  @Test
  void shouldCreateDistinctInstancesWithDifferentValues() {
    // GIVEN
    Date now = new Date();
    AuthResponse response1 = new AuthResponse("Type1", "Token1", "Refresh1", now);
    AuthResponse response2 = new AuthResponse("Type2", "Token2", "Refresh2", now);
    // WHEN
    // THEN
    assertThat(response1.getTokenType()).isNotEqualTo(response2.getTokenType());
    assertThat(response1.getToken()).isNotEqualTo(response2.getToken());
    assertThat(response1.getRefreshToken()).isNotEqualTo(response2.getRefreshToken());
  }

  @Test
  void shouldReturnSameValuesOnMultipleCalls() {
    // GIVEN
    // WHEN
    String firstCall = authResponse.getToken();
    String secondCall = authResponse.getToken();
    // THEN
    assertEquals(firstCall, secondCall);
  }
}
