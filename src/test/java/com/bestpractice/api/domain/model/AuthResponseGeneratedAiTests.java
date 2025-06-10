package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({})
public class AuthResponseGeneratedAiTests {

    private String tokenType;
    private String token;
    private String refreshToken;
    private Date expiresAt;

    @BeforeEach
    public void setUp() {
        tokenType = "Bearer";
        token = "sample-token";
        refreshToken = "sample-refresh-token";
        expiresAt = new Date(System.currentTimeMillis() + 3600000); // 1 hour from now
    }

    @Test
    public void testGetTokenType() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertEquals("Bearer", result);
    }

    @Test
    public void testGetToken() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertEquals("sample-token", result);
    }

    @Test
    public void testGetRefreshToken() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertEquals("sample-refresh-token", result);
    }

    @Test
    public void testGetExpiresAt() {
        // GIVEN
        AuthResponse authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertEquals(expiresAt, result);
    }
}
