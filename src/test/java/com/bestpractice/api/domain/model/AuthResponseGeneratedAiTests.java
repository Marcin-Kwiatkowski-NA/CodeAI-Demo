package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthResponseGeneratedAiTests {

    private static final String TOKEN_TYPE = "Bearer";
    private static final String ACCESS_TOKEN = "abc123xyz";
    private static final String REFRESH_TOKEN = "def456uvw";
    private static final Date EXPIRES_AT = Date.from(ZonedDateTime.of(2025, 04, 05, 12, 00, 00, 0, ZoneId.of("UTC")).toInstant());

    @InjectMocks
    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authResponse = null;
    }

    @Test
    @Order(1)
    void givenValidInputs_whenConstructAuthResponse_thenAllFieldsAreSet() throws Exception {
        // GIVEN
        String tokenType = TOKEN_TYPE;
        String token = ACCESS_TOKEN;
        String refreshToken = REFRESH_TOKEN;
        Date expiresAt = EXPIRES_AT;

        // WHEN
        authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // THEN
        assertThat(authResponse.getTokenType()).isEqualTo(tokenType);
        assertThat(authResponse.getToken()).isEqualTo(token);
        assertThat(authResponse.getRefreshToken()).isEqualTo(refreshToken);
        assertThat(authResponse.getExpiresAt()).isEqualTo(expiresAt);
    }

    @Test
    @Order(2)
    void givenNullTokenType_whenConstructAuthResponse_thenShouldThrowException() {
        // GIVEN
        String nullTokenType = null;
        String token = ACCESS_TOKEN;
        String refreshToken = REFRESH_TOKEN;
        Date expiresAt = EXPIRES_AT;

        // WHEN & THEN
        assertThatThrownBy(() -> new AuthResponse(nullTokenType, token, refreshToken, expiresAt))
                .hasMessageContaining("token_type");
    }

    @Test
    @Order(3)
    void givenNullToken_whenConstructAuthResponse_thenShouldThrowException() {
        // GIVEN
        String tokenType = TOKEN_TYPE;
        String nullToken = null;
        String refreshToken = REFRESH_TOKEN;
        Date expiresAt = EXPIRES_AT;

        // WHEN & THEN
        assertThatThrownBy(() -> new AuthResponse(tokenType, nullToken, refreshToken, expiresAt))
                .hasMessageContaining("token");
    }

    @Test
    @Order(4)
    void givenNullRefreshToken_whenConstructAuthResponse_thenShouldThrowException() {
        // GIVEN
        String tokenType = TOKEN_TYPE;
        String token = ACCESS_TOKEN;
        String nullRefreshToken = null;
        Date expiresAt = EXPIRES_AT;

        // WHEN & THEN
        assertThatThrownBy(() -> new AuthResponse(tokenType, token, nullRefreshToken, expiresAt))
                .hasMessageContaining("refresh_token");
    }

    @Test
    @Order(5)
    void givenValidAuthResponse_whenToJsonString_thenShouldBeValidJson() throws Exception {
        // GIVEN
        String tokenType = TOKEN_TYPE;
        String token = ACCESS_TOKEN;
        String refreshToken = REFRESH_TOKEN;
        Date expiresAt = EXPIRES_AT;

        // WHEN
        authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);
        String json = new ObjectMapper().writeValueAsString(authResponse);

        // THEN
        assertThat(json).isNotNull();
        assertThat(json).contains("token_type");
        assertThat(json).contains("token");
        assertThat(json).contains("refresh_token");
        assertThat(json).contains("expired_at");
    }
}
