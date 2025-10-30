package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void givenValidRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN
        String expectedToken = "sampleRefreshToken";

        // WHEN
        request.setRefreshToken(expectedToken);

        // THEN
        assertEquals(expectedToken, request.getRefreshToken());
    }

    @Test
    void givenNullRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsNull() {
        // GIVEN
        String expectedToken = null;

        // WHEN
        request.setRefreshToken(expectedToken);

        // THEN
        assertNull(request.getRefreshToken());
    }

    @Test
    void givenEmptyRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsEmptyString() {
        // GIVEN
        String expectedToken = "";

        // WHEN
        request.setRefreshToken(expectedToken);

        // THEN
        assertEquals(expectedToken, request.getRefreshToken());
    }

    @Test
    void givenRefreshTokenSetTwice_whenGetRefreshToken_thenReturnsLatestValue() {
        // GIVEN
        String firstToken = "firstToken";
        String secondToken = "secondToken";

        // WHEN
        request.setRefreshToken(firstToken);
        request.setRefreshToken(secondToken);

        // THEN
        assertEquals(secondToken, request.getRefreshToken());
    }

    @Test
    void givenRefreshTokenInitiallyNull_whenSetRefreshToken_thenValueIsUpdated() {
        // GIVEN
        assertNull(request.getRefreshToken());
        String newToken = "newToken";

        // WHEN
        request.setRefreshToken(newToken);

        // THEN
        assertEquals(newToken, request.getRefreshToken());
    }

    @Test
    void givenRefreshTokenOverwrittenWithEmptyString_whenGetRefreshToken_thenReturnsEmptyString() {
        // GIVEN
        request.setRefreshToken("initialToken");

        // WHEN
        request.setRefreshToken("");

        // THEN
        assertEquals("", request.getRefreshToken());
    }

    @Test
    void givenRefreshTokenOverwrittenWithNull_whenGetRefreshToken_thenReturnsNull() {
        // GIVEN
        request.setRefreshToken("initialToken");

        // WHEN
        request.setRefreshToken(null);

        // THEN
        assertNull(request.getRefreshToken());
    }

    @Test
    void givenRefreshTokenSameValueSetTwice_whenGetRefreshToken_thenReturnsSameValue() {
        // GIVEN
        String token = "sameToken";

        // WHEN
        request.setRefreshToken(token);
        request.setRefreshToken(token);

        // THEN
        assertEquals(token, request.getRefreshToken());
    }
}
