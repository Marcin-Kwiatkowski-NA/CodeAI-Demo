package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CredentialGeneratedAiTests {

    private Credential credential;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        credential = null;
    }

    @Test
    void shouldCreateCredentialWithValidParameters() {
        // GIVEN
        String token = "validToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = true;

        // WHEN
        credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertEquals(token, credential.getToken());
        assertEquals(tokenType, credential.getTokenType());
        assertEquals(exp, credential.getExp());
        assertEquals(isRefresh, credential.isRefresh());
    }

    @Test
    void shouldReturnCorrectToken() {
        // GIVEN
        String token = "validToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = true;

        // WHEN
        credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertEquals(token, credential.getToken());
    }

    @Test
    void shouldReturnCorrectTokenType() {
        // GIVEN
        String token = "validToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = true;

        // WHEN
        credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertEquals(tokenType, credential.getTokenType());
    }

    @Test
    void shouldReturnCorrectExpirationDate() {
        // GIVEN
        String token = "validToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = true;

        // WHEN
        credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertEquals(exp, credential.getExp());
    }

    @Test
    void shouldReturnCorrectRefreshStatus() {
        // GIVEN
        String token = "validToken";
        String tokenType = "Bearer";
        Date exp = new Date();
        boolean isRefresh = true;

        // WHEN
        credential = new Credential(token, tokenType, exp, isRefresh);

        // THEN
        assertEquals(isRefresh, credential.isRefresh());
    }
}
