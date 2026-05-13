package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthResponseGeneratedAiTests {

    private static final String TOKEN_TYPE = "Bearer";
    private static final String TOKEN = "sampleToken";
    private static final String REFRESH_TOKEN = "sampleRefreshToken";
    private static final Date EXPIRES_AT = new Date();

    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        authResponse = new AuthResponse(TOKEN_TYPE, TOKEN, REFRESH_TOKEN, EXPIRES_AT);
    }

    @Test
    void shouldReturnCorrectTokenType() {
        // GIVEN
        String expectedTokenType = TOKEN_TYPE;

        // WHEN
        String actualTokenType = authResponse.getTokenType();

        // THEN
        assertEquals(expectedTokenType, actualTokenType);
    }

    @Test
    void shouldReturnCorrectToken() {
        // GIVEN
        String expectedToken = TOKEN;

        // WHEN
        String actualToken = authResponse.getToken();

        // THEN
        assertEquals(expectedToken, actualToken);
    }

    @Test
    void shouldReturnCorrectRefreshToken() {
        // GIVEN
        String expectedRefreshToken = REFRESH_TOKEN;

        // WHEN
        String actualRefreshToken = authResponse.getRefreshToken();

        // THEN
        assertEquals(expectedRefreshToken, actualRefreshToken);
    }

    @Test
    void shouldReturnCorrectExpiresAt() {
        // GIVEN
        Date expectedExpiresAt = EXPIRES_AT;

        // WHEN
        Date actualExpiresAt = authResponse.getExpiresAt();

        // THEN
        assertEquals(expectedExpiresAt, actualExpiresAt);
    }

    @Test
    void shouldHandleNullExpiresAtGracefully() {
        // GIVEN
        AuthResponse responseWithNullDate = new AuthResponse(TOKEN_TYPE, TOKEN, REFRESH_TOKEN, null);

        // WHEN
        Date actualExpiresAt = responseWithNullDate.getExpiresAt();

        // THEN
        assertNull(actualExpiresAt);
    }

    @Test
    void shouldCreateAuthResponseWithAllFields() {
        // GIVEN
        String expectedTokenType = TOKEN_TYPE;
        String expectedToken = TOKEN;
        String expectedRefreshToken = REFRESH_TOKEN;
        Date expectedExpiresAt = EXPIRES_AT;

        // WHEN
        AuthResponse response = new AuthResponse(expectedTokenType, expectedToken, expectedRefreshToken, expectedExpiresAt);

        // THEN
        assertEquals(expectedTokenType, response.getTokenType());
        assertEquals(expectedToken, response.getToken());
        assertEquals(expectedRefreshToken, response.getRefreshToken());
        assertEquals(expectedExpiresAt, response.getExpiresAt());
    }

    @Test
    void shouldThrowExceptionWhenTokenTypeIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            new AuthResponse(nullTokenType, TOKEN, REFRESH_TOKEN, EXPIRES_AT);
        });
    }

    @Test
    void shouldThrowExceptionWhenTokenIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            new AuthResponse(TOKEN_TYPE, nullToken, REFRESH_TOKEN, EXPIRES_AT);
        });
    }

    @Test
    void shouldThrowExceptionWhenRefreshTokenIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            new AuthResponse(TOKEN_TYPE, TOKEN, nullRefreshToken, EXPIRES_AT);
        });
    }
}
