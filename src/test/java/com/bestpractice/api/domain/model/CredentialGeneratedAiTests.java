package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class CredentialGeneratedAiTests {

    private Credential credential;
    private final String token = "sampleToken";
    private final String tokenType = "Bearer";
    private final Date exp = new Date(System.currentTimeMillis() + 10000); // 10 seconds from now
    private final boolean isRefresh = true;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void givenCredentialObject_whenGetToken_thenReturnsCorrectToken() {
        // GIVEN: A Credential object initialized with a token
        // WHEN: getToken() is called
        String result = credential.getToken();

        // THEN: The returned token matches the initialized value
        assertThat(result).isEqualTo(token);
    }

    @Test
    void givenCredentialObject_whenGetTokenType_thenReturnsCorrectTokenType() {
        // GIVEN: A Credential object initialized with a token type
        // WHEN: getTokenType() is called
        String result = credential.getTokenType();

        // THEN: The returned token type matches the initialized value
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void givenCredentialObject_whenGetExp_thenReturnsCorrectExpirationDate() {
        // GIVEN: A Credential object initialized with an expiration date
        // WHEN: getExp() is called
        Date result = credential.getExp();

        // THEN: The returned expiration date matches the initialized value
        assertThat(result).isEqualTo(exp);
    }

    @Test
    void givenCredentialObject_whenIsRefresh_thenReturnsCorrectRefreshStatus() {
        // GIVEN: A Credential object initialized with a refresh status
        // WHEN: isRefresh() is called
        boolean result = credential.isRefresh();

        // THEN: The returned refresh status matches the initialized value
        assertThat(result).isEqualTo(isRefresh);
    }
}
