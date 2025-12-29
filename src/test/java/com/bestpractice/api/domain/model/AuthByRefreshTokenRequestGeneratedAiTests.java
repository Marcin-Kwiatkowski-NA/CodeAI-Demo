package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class AuthByRefreshTokenRequestGeneratedAiTests {

    @InjectMocks
    private AuthByRefreshTokenRequest authByRefreshTokenRequest;

    @BeforeEach
    void setUp() {
        authByRefreshTokenRequest = new AuthByRefreshTokenRequest();
    }

    @Test
    void testGetRefreshToken() {
        // GIVEN
        String expectedRefreshToken = "validRefreshToken";

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(expectedRefreshToken);

        // THEN
        assertEquals(expectedRefreshToken, authByRefreshTokenRequest.getRefreshToken());
    }

    @Test
    void testSetRefreshToken() {
        // GIVEN
        String newRefreshToken = "newValidRefreshToken";

        // WHEN
        authByRefreshTokenRequest.setRefreshToken(newRefreshToken);

        // THEN
        assertEquals(newRefreshToken, authByRefreshTokenRequest.getRefreshToken());
    }
}
