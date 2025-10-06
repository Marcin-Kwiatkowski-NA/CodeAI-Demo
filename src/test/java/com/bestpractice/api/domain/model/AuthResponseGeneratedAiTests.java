package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    void setUp() {
        // GIVEN: default valid values for AuthResponse fields
        tokenType = "Bearer";
        token = "sampleToken";
        refreshToken = "sampleRefreshToken";
        expiresAt = new Date();
    }

    @Test
    void testGetTokenType() {
        // GIVEN: an AuthResponse instance with a specific token type
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the token type
        String result = authResponse.getTokenType();

        // THEN: the token type should match the expected value
        assertEquals(tokenType, result);
    }

    @Test
    void testGetToken() {
        // GIVEN: an AuthResponse instance with a specific token
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the token
        String result = authResponse.getToken();

        // THEN: the token should match the expected value
        assertEquals(token, result);
    }

    @Test
    void testGetRefreshToken() {
        // GIVEN: an AuthResponse instance with a specific refresh token
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the refresh token
        String result = authResponse.getRefreshToken();

        // THEN: the refresh token should match the expected value
        assertEquals(refreshToken, result);
    }

    @Test
    void testGetExpiresAt() {
        // GIVEN: an AuthResponse instance with a specific expiration date
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN: retrieving the expiration date
        Date result = authResponse.getExpiresAt();

        // THEN: the expiration date should match the expected value
        assertEquals(expiresAt, result);
    }

    @Test
    void testExpiresAtIsNull() {
        // GIVEN: an AuthResponse instance with null expiration date
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, null);

        // WHEN: retrieving the expiration date
        Date result = authResponse.getExpiresAt();

        // THEN: the expiration date should be null
        assertNull(result);
    }

    @Test
    void testConstructorWithNullValuesDoesNotThrowException() {
        // GIVEN: null values for all parameters
        String nullTokenType = null;
        String nullToken = null;
        String nullRefreshToken = null;
        Date nullExpiresAt = null;

        // WHEN: constructing AuthResponse with null values
        AuthResponse authResponse = new AuthResponse(nullTokenType, nullToken, nullRefreshToken, nullExpiresAt);

        // THEN: all getters should return null
        assertNull(authResponse.getTokenType());
        assertNull(authResponse.getToken());
        assertNull(authResponse.getRefreshToken());
        assertNull(authResponse.getExpiresAt());
    }
}
