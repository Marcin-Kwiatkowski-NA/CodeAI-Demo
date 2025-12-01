package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

class CredentialGeneratedAiTests {

    private Credential credential;
    private static final String TOKEN = "sampleToken";
    private static final String TOKEN_TYPE = "Bearer";
    private static final Date EXPIRATION_DATE = new Date(System.currentTimeMillis() + 10000); // 10 seconds from now
    private static final boolean IS_REFRESH = true;

    @BeforeEach
    void setUp() {
        credential = new Credential(TOKEN, TOKEN_TYPE, EXPIRATION_DATE, IS_REFRESH);
    }

    @Test
    void givenCredentialObject_whenGetToken_thenReturnsCorrectToken() {
        // GIVEN: A Credential object initialized with a token
        // WHEN: getToken is called
        String token = credential.getToken();

        // THEN: The returned token matches the initialized value
        assertThat(token).isEqualTo(TOKEN);
    }

    @Test
    void givenCredentialObject_whenGetTokenType_thenReturnsCorrectTokenType() {
        // GIVEN: A Credential object initialized with a token type
        // WHEN: getTokenType is called
        String tokenType = credential.getTokenType();

        // THEN: The returned token type matches the initialized value
        assertThat(tokenType).isEqualTo(TOKEN_TYPE);
    }

    @Test
    void givenCredentialObject_whenGetExp_thenReturnsCorrectExpirationDate() {
        // GIVEN: A Credential object initialized with an expiration date
        // WHEN: getExp is called
        Date exp = credential.getExp();

        // THEN: The returned expiration date matches the initialized value
        assertThat(exp).isEqualTo(EXPIRATION_DATE);
    }

    @Test
    void givenCredentialObject_whenIsRefresh_thenReturnsCorrectRefreshStatus() {
        // GIVEN: A Credential object initialized with a refresh status
        // WHEN: isRefresh is called
        boolean isRefresh = credential.isRefresh();

        // THEN: The returned refresh status matches the initialized value
        assertThat(isRefresh).isEqualTo(IS_REFRESH);
    }
}
