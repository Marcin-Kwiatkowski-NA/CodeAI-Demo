package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void givenValidRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: a valid refresh token string
        String token = "sampleRefreshToken";

        // WHEN: setting the refresh token
        request.setRefreshToken(token);

        // THEN: the getter should return the same token
        assertEquals(token, request.getRefreshToken());
        assertThat(request.getRefreshToken()).isEqualTo(token);
    }

    @Test
    void givenNullRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsNull() {
        // GIVEN: a null refresh token
        String token = null;

        // WHEN: setting the refresh token to null
        request.setRefreshToken(token);

        // THEN: the getter should return null
        assertNull(request.getRefreshToken());
        assertThat(request.getRefreshToken()).isNull();
    }

    @Test
    void givenEmptyRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsEmptyString() {
        // GIVEN: an empty refresh token string
        String token = "";

        // WHEN: setting the refresh token to empty string
        request.setRefreshToken(token);

        // THEN: the getter should return empty string
        assertEquals("", request.getRefreshToken());
        assertThat(request.getRefreshToken()).isEmpty();
    }

    @Test
    void givenLongRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: a very long refresh token string
        String token = "a".repeat(500);

        // WHEN: setting the refresh token
        request.setRefreshToken(token);

        // THEN: the getter should return the same token
        assertEquals(token, request.getRefreshToken());
        assertThat(request.getRefreshToken()).hasSize(500);
    }

    @Test
    void givenNullRequestObject_whenCallingGetter_thenThrowsNullPointerException() {
        // GIVEN: a null reference to AuthByRefreshTokenRequest
        AuthByRefreshTokenRequest nullRequest = null;

        // WHEN & THEN: calling getRefreshToken on null should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.getRefreshToken());
    }

    @Test
    void givenNullRequestObject_whenCallingSetter_thenThrowsNullPointerException() {
        // GIVEN: a null reference to AuthByRefreshTokenRequest
        AuthByRefreshTokenRequest nullRequest = null;

        // WHEN & THEN: calling setRefreshToken on null should throw NullPointerException
        assertThrows(NullPointerException.class, () -> nullRequest.setRefreshToken("tokenValue"));
    }

    @Test
    void givenMultipleSetCalls_whenSetRefreshToken_thenGetRefreshTokenReturnsLastValue() {
        // GIVEN: multiple refresh token values
        String firstToken = "firstToken";
        String secondToken = "secondToken";

        // WHEN: setting the refresh token multiple times
        request.setRefreshToken(firstToken);
        request.setRefreshToken(secondToken);

        // THEN: the getter should return the last set token
        assertEquals(secondToken, request.getRefreshToken());
        assertThat(request.getRefreshToken()).isEqualTo(secondToken);
    }

    @Test
    void givenRefreshTokenWithWhitespace_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: a refresh token with leading/trailing whitespace
        String token = "  tokenWithSpaces  ";

        // WHEN: setting the refresh token
        request.setRefreshToken(token);

        // THEN: the getter should return the same token including whitespace
        assertEquals(token, request.getRefreshToken());
        assertThat(request.getRefreshToken()).isEqualTo(token);
    }
}
