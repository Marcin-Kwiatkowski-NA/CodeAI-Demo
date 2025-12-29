package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;
import org.junit.jupiter.api.Test;

class CredentialGeneratedAiTests {

    @Test
    void givenValidTokenAndExpiration_ShouldReturnCorrectTokenAndExpiration() {
        // GIVEN
        String token = "abc123xyz";
        String tokenType = "bearer";
        Date exp = new Date(System.currentTimeMillis() + 3600000); // 1 hour from now
        boolean isRefresh = false;

        // WHEN
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertThat(credential.getToken()).isEqualTo(token);
        assertThat(credential.getTokenType()).isEqualTo(tokenType);
        assertThat(credential.getExp()).isEqualTo(exp);
        assertThat(credential.isRefresh()).isFalse();
    }

    @Test
    void givenRefreshToken_ShouldIndicateRefreshTokenIsTrue() {
        // GIVEN
        String token = "refresh123";
        String tokenType = "refresh";
        Date exp = new Date(System.currentTimeMillis() + 7200000); // 2 hours from now
        boolean isRefresh = true;

        // WHEN
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertThat(credential.getToken()).isEqualTo(token);
        assertThat(credential.getTokenType()).isEqualTo(tokenType);
        assertThat(credential.getExp()).isEqualTo(exp);
        assertThat(credential.isRefresh()).isTrue();
    }

    @Test
    void givenNullToken_ShouldThrowIllegalArgumentException() {
        // GIVEN
        String token = null;
        String tokenType = "bearer";
        Date exp = new Date();
        boolean isRefresh = false;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new Credential(token, tokenType, exp, isRefresh);
        });
    }

    @Test
    void givenEmptyToken_ShouldThrowIllegalArgumentException() {
        // GIVEN
        String token = "";
        String tokenType = "bearer";
        Date exp = new Date();
        boolean isRefresh = false;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new Credential(token, tokenType, exp, isRefresh);
        });
    }

    @Test
    void givenValidToken_ShouldReturnCorrectTokenTypeAndExpiration() {
        // GIVEN
        String token = "validToken";
        String tokenType = "jwt";
        Date exp = new Date(System.currentTimeMillis() + 86400000); // 24 hours
        boolean isRefresh = false;

        // WHEN
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertThat(credential.getTokenType()).isEqualTo(tokenType);
        assertThat(credential.getExp().getTime()).isGreaterThan(System.currentTimeMillis());
    }

    @Test
    void shouldNotAllowNegativeExpirationTime() {
        // GIVEN
        String token = "negative-exp-token";
        String tokenType = "bearer";
        Date exp = new Date(System.currentTimeMillis() - 3600000); // 1 hour ago
        boolean isRefresh = false;

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> {
            new Credential(token, tokenType, exp, isRefresh);
        });
    }
}
