package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest authByRefreshTokenRequest;

    @BeforeEach
    public void setUp() {
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
    }

    @Test
    public void testSetAndGetRefreshToken() {
        // GIVEN
        String expectedRefreshToken = "validRefreshToken";

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(expectedRefreshToken);
        String actualRefreshToken = authByRefreshTokenRequest.getRefreshToken();

        // THEN
        assertEquals(expectedRefreshToken, actualRefreshToken, "The refresh token should be set and retrieved correctly.");
    }

    @Test
    public void testGetRefreshTokenWithNull() {
        // GIVEN
        authByRefreshTokenRequest.setRefreshToken(null);

        // WHEN
        String actualRefreshToken = authByRefreshTokenRequest.getRefreshToken();

        // THEN
        assertNull(actualRefreshToken, "The refresh token should be null.");
    }
}
