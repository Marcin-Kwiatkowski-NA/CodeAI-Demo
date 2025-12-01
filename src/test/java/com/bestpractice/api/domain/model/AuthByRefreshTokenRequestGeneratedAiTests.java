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
    void givenRefreshTokenValue_whenSetRefreshToken_thenGetRefreshTokenReturnsSameValue() {
        // GIVEN: A refresh token value
        String refreshToken = "sampleRefreshToken";

        // WHEN: Setting the refresh token
        authByRefreshTokenRequest.setRefreshToken(refreshToken);

        // THEN: The getter should return the same value
        assertEquals(refreshToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void givenNoRefreshTokenSet_whenGetRefreshToken_thenReturnsNull() {
        // GIVEN: No refresh token is set

        // WHEN: Calling the getter
        String result = authByRefreshTokenRequest.getRefreshToken();

        // THEN: The result should be null
        assertNull(result);
    }
}
