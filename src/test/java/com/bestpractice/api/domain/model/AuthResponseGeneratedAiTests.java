package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void givenValidParameters_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void givenValidParameters_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
        assertThat(result).isEqualTo(token);
    }

    @Test
    void givenValidParameters_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
        assertThat(result).isEqualTo(refreshToken);
    }

    @Test
    void givenValidParameters_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNotNull(result);
        assertEquals(expiresAt, result);
        assertThat(result).isEqualTo(expiresAt);
    }

    @Test
    void givenNullParameters_whenCreatingAuthResponse_thenObjectIsCreatedWithoutException() {
        // GIVEN
        String nullTokenType = null;
        String nullToken = null;
        String nullRefreshToken = null;
        Date nullExpiresAt = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, nullToken, nullRefreshToken, nullExpiresAt);

        // THEN
        assertThat(authResponse.getTokenType()).isNull();
        assertThat(authResponse.getToken()).isNull();
        assertThat(authResponse.getRefreshToken()).isNull();
        assertThat(authResponse.getExpiresAt()).isNull();
    }

    @Test
    void givenNullTokenType_whenGetTokenType_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(null, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenNullToken_whenGetToken_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, null, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenNullRefreshToken_whenGetRefreshToken_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, null, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenNullExpiresAt_whenGetExpiresAt_thenReturnsNull() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertThat(result).isNull();
    }
}
