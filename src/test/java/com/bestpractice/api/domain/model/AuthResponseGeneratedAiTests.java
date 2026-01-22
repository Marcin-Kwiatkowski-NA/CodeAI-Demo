package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthResponseGeneratedAiTests {

  private AuthResponse authResponse;
  private final String tokenType = "Bearer";
  private final String token = "abc123";
  private final String refreshToken = "refresh123";
  private final Date expiresAt = new Date();

  @BeforeEach
  void setUp() {
    // GIVEN a new AuthResponse instance with predefined values
    authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);
  }

  @Test
  void testGetTokenType() {
    // GIVEN the AuthResponse instance has been initialized
    // WHEN retrieving the token type
    String result = authResponse.getTokenType();
    // THEN the returned token type should match the initialized value
    assertThat(result).isEqualTo(tokenType);
  }

  @Test
  void testGetToken() {
    // GIVEN the AuthResponse instance has been initialized
    // WHEN retrieving the token
    String result = authResponse.getToken();
    // THEN the returned token should match the initialized value
    assertThat(result).isEqualTo(token);
  }

  @Test
  void testGetRefreshToken() {
    // GIVEN the AuthResponse instance has been initialized
    // WHEN retrieving the refresh token
    String result = authResponse.getRefreshToken();
    // THEN the returned refresh token should match the initialized value
    assertThat(result).isEqualTo(refreshToken);
  }

  @Test
  void testGetExpiresAt() {
    // GIVEN the AuthResponse instance has been initialized
    // WHEN retrieving the expiration date
    Date result = authResponse.getExpiresAt();
    // THEN the returned date should match the initialized value
    assertThat(result).isEqualTo(expiresAt);
  }

  @Test
  void testConstructorAssignsAllFields() {
    // GIVEN a new AuthResponse instance
    // WHEN constructing with specific values
    AuthResponse constructed = new AuthResponse(tokenType, token, refreshToken, expiresAt);
    // THEN all getters should return the corresponding values
    assertThat(constructed.getTokenType()).isEqualTo(tokenType);
    assertThat(constructed.getToken()).isEqualTo(token);
    assertThat(constructed.getRefreshToken()).isEqualTo(refreshToken);
    assertThat(constructed.getExpiresAt()).isEqualTo(expiresAt);
  }

  @Test
  void testExpiresAtIsNotNullWhenProvided() {
    // GIVEN the AuthResponse instance has been initialized with a non-null expiresAt
    // WHEN retrieving the expiration date
    Date result = authResponse.getExpiresAt();
    // THEN the returned date should not be null
    assertThat(result).isNotNull();
  }
}
