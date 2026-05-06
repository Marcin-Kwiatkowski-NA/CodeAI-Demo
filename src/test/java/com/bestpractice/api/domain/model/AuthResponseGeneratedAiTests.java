package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.Date;

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
    void shouldHandleNullValuesGracefully() {
        // GIVEN
        AuthResponse responseWithNulls = new AuthResponse(null, null, null, null);

        // WHEN
        String tokenType = responseWithNulls.getTokenType();
        String token = responseWithNulls.getToken();
        String refreshToken = responseWithNulls.getRefreshToken();
        Date expiresAt = responseWithNulls.getExpiresAt();

        // THEN
        assertNull(tokenType);
        assertNull(token);
        assertNull(refreshToken);
        assertNull(expiresAt);
    }
}
