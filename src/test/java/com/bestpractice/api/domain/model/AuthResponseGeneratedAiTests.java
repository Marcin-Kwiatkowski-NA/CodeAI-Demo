package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
        // Reset state before each test
        authResponse = new AuthResponse(TOKEN_TYPE, TOKEN, REFRESH_TOKEN, EXPIRES_AT);
    }

    @Test
    void givenAuthResponse_whenGetTokenType_thenReturnsCorrectTokenType() {
        // GIVEN: An AuthResponse object initialized with a token type
        // WHEN: getTokenType is called
        String result = authResponse.getTokenType();

        // THEN: The returned token type matches the initialized value
        assertThat(result).isEqualTo(TOKEN_TYPE);
    }

    @Test
    void givenAuthResponse_whenGetToken_thenReturnsCorrectToken() {
        // GIVEN: An AuthResponse object initialized with a token
        // WHEN: getToken is called
        String result = authResponse.getToken();

        // THEN: The returned token matches the initialized value
        assertThat(result).isEqualTo(TOKEN);
    }

    @Test
    void givenAuthResponse_whenGetRefreshToken_thenReturnsCorrectRefreshToken() {
        // GIVEN: An AuthResponse object initialized with a refresh token
        // WHEN: getRefreshToken is called
        String result = authResponse.getRefreshToken();

        // THEN: The returned refresh token matches the initialized value
        assertThat(result).isEqualTo(REFRESH_TOKEN);
    }

    @Test
    void givenAuthResponse_whenGetExpiresAt_thenReturnsCorrectExpiresAt() {
        // GIVEN: An AuthResponse object initialized with an expiration date
        // WHEN: getExpiresAt is called
        Date result = authResponse.getExpiresAt();

        // THEN: The returned expiration date matches the initialized value
        assertThat(result).isEqualTo(EXPIRES_AT);
    }
}
