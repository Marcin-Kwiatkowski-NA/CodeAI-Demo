package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class AuthResponseGeneratedAiTests {

    private AuthResponse authResponse;
    private final String tokenType = "Bearer";
    private final String token = "abc123";
    private final String refreshToken = "refresh123";
    private final Date expiresAt = new Date();

    @BeforeEach
    void setUp() {
        authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);
    }

    @Test
    void testGetTokenType() {
        // GIVEN
        // authResponse is initialized in setUp

        // WHEN
        String result = authResponse.getTokenType();

        // THEN
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void testGetToken() {
        // GIVEN
        // authResponse is initialized in setUp

        // WHEN
        String result = authResponse.getToken();

        // THEN
        assertThat(result).isEqualTo(token);
    }

    @Test
    void testGetRefreshToken() {
        // GIVEN
        // authResponse is initialized in setUp

        // WHEN
        String result = authResponse.getRefreshToken();

        // THEN
        assertThat(result).isEqualTo(refreshToken);
    }

    @Test
    void testGetExpiresAtReturnsSameInstance() {
        // GIVEN
        // authResponse is initialized in setUp

        // WHEN
        Date result = authResponse.getExpiresAt();

        // THEN
        assertThat(result).isSameAs(expiresAt);
    }

    @Test
    void testExpiresAtMutability() {
        // GIVEN
        // authResponse is initialized in setUp
        Date returnedDate = authResponse.getExpiresAt();

        // WHEN
        returnedDate.setTime(0L);

        // THEN
        assertThat(authResponse.getExpiresAt().getTime()).isEqualTo(0L);
    }
}
