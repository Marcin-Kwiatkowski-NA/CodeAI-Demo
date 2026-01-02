package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthResponseGeneratedAiTests {

    private AuthResponse authResponse;
    private AuthResponse authResponseNullExpiresAt;
    private final String tokenType = "Bearer";
    private final String token = "abc123";
    private final String refreshToken = "refresh123";
    private final Date expiresAt = new Date();

    @BeforeEach
    void setUp() {
        // GIVEN a valid AuthResponse with all fields set
        authResponse = new AuthResponse(tokenType, token, refreshToken, expiresAt);

        // GIVEN a valid AuthResponse with null expiresAt
        authResponseNullExpiresAt = new AuthResponse(tokenType, token, refreshToken, null);
    }

    @Test
    void testConstructorSetsFields() {
        // GIVEN
        // (setUp already creates authResponse)

        // WHEN
        String actualTokenType = authResponse.getTokenType();
        String actualToken = authResponse.getToken();
        String actualRefreshToken = authResponse.getRefreshToken();
        Date actualExpiresAt = authResponse.getExpiresAt();

        // THEN
        assertThat(actualTokenType).isEqualTo(tokenType);
        assertThat(actualToken).isEqualTo(token);
        assertThat(actualRefreshToken).isEqualTo(refreshToken);
        assertThat(actualExpiresAt).isEqualTo(expiresAt);
    }

    @Test
    void testGetExpiresAtWithNull() {
        // GIVEN
        // (setUp already creates authResponseNullExpiresAt)

        // WHEN
        Date actualExpiresAt = authResponseNullExpiresAt.getExpiresAt();

        // THEN
        assertThat(actualExpiresAt).isNull();
    }

    @Test
    void testGetTokenTypeNotNull() {
        // GIVEN
        // (setUp already creates authResponse)

        // WHEN
        String actualTokenType = authResponse.getTokenType();

        // THEN
        assertThat(actualTokenType).isNotNull();
    }

    @Test
    void testGetTokenNotNull() {
        // GIVEN
        // (setUp already creates authResponse)

        // WHEN
        String actualToken = authResponse.getToken();

        // THEN
        assertThat(actualToken).isNotNull();
    }

    @Test
    void testGetRefreshTokenNotNull() {
        // GIVEN
        // (setUp already creates authResponse)

        // WHEN
        String actualRefreshToken = authResponse.getRefreshToken();

        // THEN
        assertThat(actualRefreshToken).isNotNull();
    }

    @Test
    void testGetExpiresAtNotNull() {
        // GIVEN
        // (setUp already creates authResponse)

        // WHEN
        Date actualExpiresAt = authResponse.getExpiresAt();

        // THEN
        assertThat(actualExpiresAt).isNotNull();
    }
}
