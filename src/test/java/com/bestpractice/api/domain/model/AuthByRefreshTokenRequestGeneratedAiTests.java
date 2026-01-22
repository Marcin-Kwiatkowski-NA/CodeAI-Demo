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
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void givenNewInstance_whenGetRefreshToken_thenReturnsNull() {
        // GIVEN
        // a new AuthByRefreshTokenRequest instance

        // WHEN
        String token = request.getRefreshToken();

        // THEN
        assertEquals(null, token);
    }

    @Test
    void givenValidRefreshToken_whenSet_thenGetReturnsSameValue() {
        // GIVEN
        String token = "validRefreshToken123";

        // WHEN
        request.setRefreshToken(token);

        // THEN
        assertThat(request.getRefreshToken()).isEqualTo(token);
    }

    @Test
    void givenNullRefreshToken_whenSet_thenGetReturnsNull() {
        // GIVEN
        String token = null;

        // WHEN
        request.setRefreshToken(token);

        // THEN
        assertEquals(null, request.getRefreshToken());
    }

    @Test
    void givenEmptyRefreshToken_whenSet_thenGetReturnsEmptyString() {
        // GIVEN
        String token = "";

        // WHEN
        request.setRefreshToken(token);

        // THEN
        assertThat(request.getRefreshToken()).isEqualTo("");
    }
}
