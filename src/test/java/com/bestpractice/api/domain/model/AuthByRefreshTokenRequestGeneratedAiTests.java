package com.bestpractice.api.domain.model;

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
import static org.assertj.core.api.Assertions.assertThat;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest authByRefreshTokenRequest;

    @BeforeEach
    void setUp() {
        // Reset the state before each test
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
    }

    @Test
    void givenValidRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: A valid refresh token
        String refreshToken = "valid-refresh-token";

        // WHEN: Setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(refreshToken);

        // THEN: The getter should return the same value
        assertThat(authByRefreshTokenRequest.getRefreshToken()).isEqualTo(refreshToken);
    }

    @Test
    void givenNullRefreshToken_whenSetRefreshToken_thenGetRefreshTokenReturnsNull() {
        // GIVEN: A null refresh token
        String refreshToken = null;

        // WHEN: Setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(refreshToken);

        // THEN: The getter should return null
        assertThat(authByRefreshTokenRequest.getRefreshToken()).isNull();
    }
}
