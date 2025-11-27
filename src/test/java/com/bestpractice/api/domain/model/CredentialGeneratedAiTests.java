package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class CredentialGeneratedAiTests {

    private static final String TEST_TOKEN = "testToken";
    private static final String TEST_TOKEN_TYPE = "Bearer";
    private static final Date TEST_EXPIRATION_DATE = new Date(System.currentTimeMillis() + 100000);
    private static final boolean TEST_IS_REFRESH = true;

    private Credential credential;

    @BeforeEach
    void setUp() {
        credential = new Credential(TEST_TOKEN, TEST_TOKEN_TYPE, TEST_EXPIRATION_DATE, TEST_IS_REFRESH);
    }

    @Test
    void givenValidCredential_whenGetToken_thenReturnsCorrectToken() {
        // GIVEN
        // Credential object is already initialized in setUp()

        // WHEN
        String token = credential.getToken();

        // THEN
        assertThat(token).isEqualTo(TEST_TOKEN);
    }

    @Test
    void givenValidCredential_whenGetTokenType_thenReturnsCorrectTokenType() {
        // GIVEN
        // Credential object is already initialized in setUp()

        // WHEN
        String tokenType = credential.getTokenType();

        // THEN
        assertThat(tokenType).isEqualTo(TEST_TOKEN_TYPE);
    }

    @Test
    void givenValidCredential_whenGetExp_thenReturnsCorrectExpirationDate() {
        // GIVEN
        // Credential object is already initialized in setUp()

        // WHEN
        Date expirationDate = credential.getExp();

        // THEN
        assertThat(expirationDate).isEqualTo(TEST_EXPIRATION_DATE);
    }

    @Test
    void givenValidCredential_whenIsRefresh_thenReturnsCorrectRefreshStatus() {
        // GIVEN
        // Credential object is already initialized in setUp()

        // WHEN
        boolean isRefresh = credential.isRefresh();

        // THEN
        assertThat(isRefresh).isEqualTo(TEST_IS_REFRESH);
    }
}
