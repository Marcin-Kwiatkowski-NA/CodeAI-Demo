package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
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
    void testSetAndGetRefreshToken() {
        // GIVEN: a valid refresh token string
        String token = "sampleRefreshToken";

        // WHEN: setting the refresh token
        request.setRefreshToken(token);

        // THEN: the getter should return the same token
        assertEquals(token, request.getRefreshToken());
    }

    @Test
    void testGetRefreshTokenInitiallyNull() {
        // GIVEN: a new AuthByRefreshTokenRequest instance

        // WHEN: retrieving the refresh token without setting it
        String result = request.getRefreshToken();

        // THEN: the refresh token should be null
        assertNull(result);
    }

    @Test
    void testSetRefreshTokenToNull() {
        // GIVEN: a refresh token already set
        request.setRefreshToken("existingToken");

        // WHEN: setting the refresh token to null
        request.setRefreshToken(null);

        // THEN: the getter should return null
        assertNull(request.getRefreshToken());
    }

    @Test
    void testSetRefreshTokenToEmptyString() {
        // GIVEN: a new AuthByRefreshTokenRequest instance

        // WHEN: setting the refresh token to an empty string
        request.setRefreshToken("");

        // THEN: the getter should return an empty string
        assertEquals("", request.getRefreshToken());
    }

    @Test
    void testSetRefreshTokenOverridesPreviousValue() {
        // GIVEN: a refresh token already set
        request.setRefreshToken("oldToken");

        // WHEN: setting a new refresh token
        request.setRefreshToken("newToken");

        // THEN: the getter should return the new token
        assertEquals("newToken", request.getRefreshToken());
    }

    @Test
    void testMultipleSetRefreshTokenCalls() {
        // GIVEN: a new AuthByRefreshTokenRequest instance

        // WHEN: setting refresh token multiple times
        request.setRefreshToken("token1");
        request.setRefreshToken("token2");
        request.setRefreshToken("token3");

        // THEN: the getter should return the last set token
        assertEquals("token3", request.getRefreshToken());
    }

    @Test
    void testSetRefreshTokenWithWhitespace() {
        // GIVEN: a new AuthByRefreshTokenRequest instance

        // WHEN: setting refresh token with leading/trailing whitespace
        request.setRefreshToken("  tokenWithSpaces  ");

        // THEN: the getter should return the token with spaces preserved
        assertEquals("  tokenWithSpaces  ", request.getRefreshToken());
    }
}
