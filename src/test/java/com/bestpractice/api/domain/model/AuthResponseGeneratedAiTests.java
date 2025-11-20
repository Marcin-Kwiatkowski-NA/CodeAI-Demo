package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;

class AuthResponseGeneratedAiTests {

    private static final String TOKEN_TYPE = "Bearer";
    private static final String TOKEN = "sampleToken";
    private static final String REFRESH_TOKEN = "sampleRefreshToken";
    private static final Date EXPIRES_AT = new Date();

    private AuthResponse authResponse;

    @BeforeEach
    void setUp() {
        authResponse = new AuthResponse(TOKEN_TYPE, TOKEN, REFRESH_TOKEN, EXPIRES_AT);
    }

    @Test
    void givenAuthResponse_whenGetTokenType_thenReturnsCorrectTokenType() {
        // GIVEN: An AuthResponse object initialized with a token type
        // WHEN: Retrieving the token type
        String tokenType = authResponse.getTokenType();

        // THEN: The token type should match the initialized value
        assertThat(tokenType).isEqualTo(TOKEN_TYPE);
    }

    @Test
    void givenAuthResponse_whenGetToken_thenReturnsCorrectToken() {
        // GIVEN: An AuthResponse object initialized with a token
        // WHEN: Retrieving the token
        String token = authResponse.getToken();

        // THEN: The token should match the initialized value
        assertThat(token).isEqualTo(TOKEN);
    }

    @Test
    void givenAuthResponse_whenGetRefreshToken_thenReturnsCorrectRefreshToken() {
        // GIVEN: An AuthResponse object initialized with a refresh token
        // WHEN: Retrieving the refresh token
        String refreshToken = authResponse.getRefreshToken();

        // THEN: The refresh token should match the initialized value
        assertThat(refreshToken).isEqualTo(REFRESH_TOKEN);
    }

    @Test
    void givenAuthResponse_whenGetExpiresAt_thenReturnsCorrectExpiresAt() {
        // GIVEN: An AuthResponse object initialized with an expiration date
        // WHEN: Retrieving the expiration date
        Date expiresAt = authResponse.getExpiresAt();

        // THEN: The expiration date should match the initialized value
        assertThat(expiresAt).isEqualTo(EXPIRES_AT);
    }
}
