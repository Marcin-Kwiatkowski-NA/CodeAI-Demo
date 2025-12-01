package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest authByRefreshTokenRequest;

    @BeforeEach
    void setUp() {
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
    }

    @Test
    void givenValidRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: A valid refresh token
        String validRefreshToken = "sampleRefreshToken";

        // WHEN: Setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(validRefreshToken);

        // THEN: The refresh token should be retrievable and match the set value
        assertEquals(validRefreshToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void givenNullRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsNull() {
        // GIVEN: A null refresh token
        String nullRefreshToken = null;

        // WHEN: Setting the refresh token to null
        authByRefreshTokenRequest.setRefreshToken(nullRefreshToken);

        // THEN: The refresh token should be retrievable and match the set value (null)
        assertEquals(nullRefreshToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void givenEmptyRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsEmptyString() {
        // GIVEN: An empty refresh token
        String emptyRefreshToken = "";

        // WHEN: Setting the refresh token to an empty string
        authByRefreshTokenRequest.setRefreshToken(emptyRefreshToken);

        // THEN: The refresh token should be retrievable and match the set value (empty string)
        assertEquals(emptyRefreshToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void givenNullRefreshToken_whenSetRefreshToken_thenNoExceptionThrown() {
        // GIVEN: A null refresh token
        String nullRefreshToken = null;

        // WHEN: Setting the refresh token to null
        // THEN: No exception should be thrown because the setter does not enforce @NotNull at runtime
        authByRefreshTokenRequest.setRefreshToken(nullRefreshToken);
        assertEquals(nullRefreshToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void givenValidRefreshToken_whenSetRefreshToken_thenStateIsResetBeforeEachTest() {
        // GIVEN: A valid refresh token
        String validRefreshToken = "sampleRefreshToken";

        // WHEN: Setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(validRefreshToken);

        // THEN: The refresh token should be retrievable and match the set value
        assertEquals(validRefreshToken, authByRefreshTokenRequest.getRefreshToken());

        // Reset state and verify
        setUp();
        assertEquals(null, authByRefreshTokenRequest.getRefreshToken());
    }
}
