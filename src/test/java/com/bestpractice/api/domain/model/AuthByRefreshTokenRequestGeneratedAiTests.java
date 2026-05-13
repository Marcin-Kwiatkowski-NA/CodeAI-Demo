package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This test class validates the behavior of AuthByRefreshTokenRequest.
 * It ensures proper getter/setter functionality and checks edge cases.
 */
public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest authByRefreshTokenRequest;

    @BeforeEach
    void setUp() {
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
    }

    @Test
    void shouldSetAndGetRefreshTokenSuccessfully() {
        // GIVEN - a valid refresh token string
        String expectedToken = "sampleRefreshToken";

        // WHEN - setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(expectedToken);

        // THEN - verify that the getter returns the same token
        assertEquals(expectedToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldReturnNullWhenRefreshTokenNotSet() {
        // GIVEN - a new instance without setting refresh token

        // WHEN - getting the refresh token
        String actualToken = authByRefreshTokenRequest.getRefreshToken();

        // THEN - verify that it returns null
        assertNull(actualToken);
    }

    @Test
    void shouldOverrideExistingRefreshTokenValue() {
        // GIVEN - an instance with an initial token
        authByRefreshTokenRequest.setRefreshToken("initialToken");

        // WHEN - setting a new token value
        String newToken = "newTokenValue";
        authByRefreshTokenRequest.setRefreshToken(newToken);

        // THEN - verify that the new value overrides the old one
        assertEquals(newToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldAllowSettingNullRefreshTokenWithoutException() {
        // GIVEN - a valid instance

        // WHEN - setting refresh token to null
        authByRefreshTokenRequest.setRefreshToken(null);

        // THEN - verify that no exception is thrown and value is null
        assertNull(authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void shouldNotThrowExceptionWhenGettingRefreshTokenAfterSettingNull() {
        // GIVEN - refresh token set to null
        authByRefreshTokenRequest.setRefreshToken(null);

        // WHEN - getting refresh token
        String token = authByRefreshTokenRequest.getRefreshToken();

        // THEN - verify that it returns null and does not throw any exception
        assertNull(token);
    }
}
