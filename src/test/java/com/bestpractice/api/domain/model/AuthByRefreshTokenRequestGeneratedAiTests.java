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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthByRefreshTokenRequestGeneratedAiTests {

    private AuthByRefreshTokenRequest request;

    @BeforeEach
    void setUp() {
        request = new AuthByRefreshTokenRequest();
    }

    @Test
    void shouldReturnNullWhenNoRefreshTokenSet() {
        // GIVEN no refresh token set
        // WHEN getRefreshToken is called
        String result = request.getRefreshToken();
        // THEN result should be null
        assertThat(result).isNull();
    }

    @Test
    void shouldSetAndGetRefreshToken() {
        // GIVEN a refresh token value
        String token = "sample-refresh-token";
        // WHEN setRefreshToken is called
        request.setRefreshToken(token);
        // THEN getRefreshToken should return the same value
        assertThat(request.getRefreshToken()).isEqualTo(token);
    }

    @Test
    void shouldAllowEmptyStringAsRefreshToken() {
        // GIVEN an empty string
        String empty = "";
        // WHEN setRefreshToken is called
        request.setRefreshToken(empty);
        // THEN getRefreshToken should return empty string
        assertThat(request.getRefreshToken()).isEqualTo(empty);
    }

    @Test
    void shouldAllowNullRefreshToken() {
        // GIVEN null
        // WHEN setRefreshToken is called
        request.setRefreshToken(null);
        // THEN getRefreshToken should return null
        assertThat(request.getRefreshToken()).isNull();
    }
}
