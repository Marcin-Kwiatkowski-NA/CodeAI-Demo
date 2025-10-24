package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

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
    void givenValidInputs_whenGetTokenType_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals(tokenType, result);
    }

    @Test
    void givenValidInputs_whenGetToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals(token, result);
    }

    @Test
    void givenValidInputs_whenGetRefreshToken_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals(refreshToken, result);
    }

    @Test
    void givenValidInputs_whenGetExpiresAt_thenReturnsCorrectValue() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertNotNull(result);
        assertEquals(expiresAt, result);
    }

    @Test
    void givenNullTokenType_whenConstructor_thenTokenTypeIsNull() {
        // GIVEN
        String nullTokenType = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, token, refreshToken, expiresAt);

        // THEN
        assertEquals(nullTokenType, authResponse.getTokenType());
    }

    @Test
    void givenNullToken_whenConstructor_thenTokenIsNull() {
        // GIVEN
        String nullToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, nullToken, refreshToken, expiresAt);

        // THEN
        assertEquals(nullToken, authResponse.getToken());
    }

    @Test
    void givenNullRefreshToken_whenConstructor_thenRefreshTokenIsNull() {
        // GIVEN
        String nullRefreshToken = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, nullRefreshToken, expiresAt);

        // THEN
        assertEquals(nullRefreshToken, authResponse.getRefreshToken());
    }

    @Test
    void givenNullExpiresAt_whenConstructor_thenExpiresAtIsNull() {
        // GIVEN
        Date nullExpiresAt = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, nullExpiresAt);

        // THEN
        assertEquals(nullExpiresAt, authResponse.getExpiresAt());
    }

    @Test
    void givenAllNullValues_whenConstructor_thenAllFieldsAreNull() {
        // GIVEN
        String nullTokenType = null;
        String nullToken = null;
        String nullRefreshToken = null;
        Date nullExpiresAt = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, nullToken, nullRefreshToken, nullExpiresAt);

        // THEN
        assertEquals(nullTokenType, authResponse.getTokenType());
        assertEquals(nullToken, authResponse.getToken());
        assertEquals(nullRefreshToken, authResponse.getRefreshToken());
        assertEquals(nullExpiresAt, authResponse.getExpiresAt());
    }

    @Test
    void givenValidInputs_whenConstructor_thenObjectIsCreatedSuccessfully() {
        // GIVEN
        // WHEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // THEN
        assertNotNull(authResponse);
    }

    @Test
    void givenNullValues_whenConstructor_thenObjectIsCreatedSuccessfully() {
        // GIVEN
        String nullTokenType = null;
        String nullToken = null;
        String nullRefreshToken = null;
        Date nullExpiresAt = null;

        // WHEN
        AuthResponse authResponse = new AuthResponse(nullTokenType, nullToken, nullRefreshToken, nullExpiresAt);

        // THEN
        assertNotNull(authResponse);
    }
}
