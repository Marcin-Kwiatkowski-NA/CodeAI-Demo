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

public class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void testDefaultRefreshTokenIsNull() {
        // GIVEN a new AuthByRefreshTokenRequest instance
        // WHEN retrieving the refreshToken
        String token = request.getRefreshToken();
        // THEN the value should be null
        assertThat(token).isNull();
    }

    @Test
    void testSetAndGetNonNullRefreshToken() {
        // GIVEN a non-null refresh token
        String expectedToken = "valid-refresh-token-123";
        // WHEN setting the refresh token
        request.setRefreshToken(expectedToken);
        // THEN retrieving it should return the same value
        assertThat(request.getRefreshToken()).isEqualTo(expectedToken);
    }

    @Test
    void testSetEmptyStringRefreshToken() {
        // GIVEN an empty string as refresh token
        String expectedToken = "";
        // WHEN setting the refresh token
        request.setRefreshToken(expectedToken);
        // THEN retrieving it should return the empty string
        assertThat(request.getRefreshToken()).isEqualTo(expectedToken);
    }

    @Test
    void testSetLongStringRefreshToken() {
        // GIVEN a long string as refresh token
        String longToken = "a".repeat(1024);
        // WHEN setting the refresh token
        request.setRefreshToken(longToken);
        // THEN retrieving it should return the same long string
        assertThat(request.getRefreshToken()).isEqualTo(longToken);
    }

    @Test
    void testSetNullRefreshToken() {
        // GIVEN a null refresh token
        String nullToken = null;
        // WHEN setting the refresh token
        request.setRefreshToken(nullToken);
        // THEN retrieving it should return null
        assertThat(request.getRefreshToken()).isNull();
    }

    @Test
    void testRepeatedSetUpdatesValue() {
        // GIVEN an initial token
        String firstToken = "first-token";
        // WHEN setting the first token
        request.setRefreshToken(firstToken);
        // AND setting a second token
        String secondToken = "second-token";
        request.setRefreshToken(secondToken);
        // THEN the value should be the second token
        assertThat(request.getRefreshToken()).isEqualTo(secondToken);
    }

    @Test
    void testSetWhitespaceStringRefreshToken() {
        // GIVEN a whitespace string as refresh token
        String whitespaceToken = "   ";
        // WHEN setting the refresh token
        request.setRefreshToken(whitespaceToken);
        // THEN retrieving it should return the whitespace string
        assertThat(request.getRefreshToken()).isEqualTo(whitespaceToken);
    }

    @Test
    void testSetSpecialCharactersRefreshToken() {
        // GIVEN a string with special characters as refresh token
        String specialToken = "!@#$%^&*()_+-=[]{}|;':,.<>/?`~";
        // WHEN setting the refresh token
        request.setRefreshToken(specialToken);
        // THEN retrieving it should return the same special character string
        assertThat(request.getRefreshToken()).isEqualTo(specialToken);
    }
}
