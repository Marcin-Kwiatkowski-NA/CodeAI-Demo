package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest authByRefreshTokenRequest;

    @BeforeEach
    void setUp() {
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
    }

    @Test
    void shouldSetAndGetRefreshTokenSuccessfully() {
        // GIVEN
        String expectedToken = "sampleRefreshToken";

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(expectedToken);
        String actualToken = authByRefreshTokenRequest.getRefreshToken();

        // THEN
        assertEquals(expectedToken, actualToken);
    }

    @Test
    void shouldReturnNullWhenRefreshTokenNotSet() {
        // GIVEN
        // No refresh token set

        // WHEN
        String actualToken = authByRefreshTokenRequest.getRefreshToken();

        // THEN
        assertNull(actualToken);
    }

    @Test
    void shouldAllowSettingRefreshTokenToNull() {
        // GIVEN
        authByRefreshTokenRequest.setRefreshToken("initialToken");

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(null);
        String actualToken = authByRefreshTokenRequest.getRefreshToken();

        // THEN
        assertNull(actualToken);
    }

    @Test
    void shouldNotThrowExceptionWhenSettingNullRefreshToken() {
        // GIVEN
        String nullToken = null;

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(nullToken);
        String actualToken = authByRefreshTokenRequest.getRefreshToken();

        // THEN
        assertNull(actualToken);
    }
}
