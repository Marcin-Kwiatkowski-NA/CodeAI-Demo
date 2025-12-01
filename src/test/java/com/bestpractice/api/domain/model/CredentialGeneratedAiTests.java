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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

class CredentialGeneratedAiTests {

    private Credential credential;
    private final String token = "sampleToken";
    private final String tokenType = "Bearer";
    private final Date exp = new Date(System.currentTimeMillis() + 3600000); // 1 hour from now
    private final boolean isRefresh = true;

    @BeforeEach
    void setUp() {
        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void givenCredentialInstance_whenGetToken_thenReturnsCorrectToken() {
        // GIVEN: A Credential instance is initialized

        // WHEN: getToken is called
        String result = credential.getToken();

        // THEN: The returned token matches the expected value
        assertThat(result).isEqualTo(token);
    }

    @Test
    void givenCredentialInstance_whenGetTokenType_thenReturnsCorrectTokenType() {
        // GIVEN: A Credential instance is initialized

        // WHEN: getTokenType is called
        String result = credential.getTokenType();

        // THEN: The returned tokenType matches the expected value
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void givenCredentialInstance_whenGetExp_thenReturnsCorrectExpirationDate() {
        // GIVEN: A Credential instance is initialized

        // WHEN: getExp is called
        Date result = credential.getExp();

        // THEN: The returned expiration date matches the expected value
        assertThat(result).isEqualTo(exp);
    }

    @Test
    void givenCredentialInstance_whenIsRefresh_thenReturnsCorrectRefreshStatus() {
        // GIVEN: A Credential instance is initialized

        // WHEN: isRefresh is called
        boolean result = credential.isRefresh();

        // THEN: The returned refresh status matches the expected value
        assertThat(result).isEqualTo(isRefresh);
    }
}
