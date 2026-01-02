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

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CredentialGeneratedAiTests {

    private Credential credential;
    private final String token = "sampleToken";
    private final String tokenType = "Bearer";
    private final Date exp = new Date();
    private final boolean isRefresh = true;

    @BeforeEach
    void setUp() {
        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void testGetTokenReturnsToken() {
        // GIVEN
        // (setup done in setUp)
        // WHEN
        String result = credential.getToken();
        // THEN
        assertThat(result).isEqualTo(token);
    }

    @Test
    void testGetTokenTypeReturnsTokenType() {
        // GIVEN
        // WHEN
        String result = credential.getTokenType();
        // THEN
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void testGetExpReturnsExp() {
        // GIVEN
        // WHEN
        Date result = credential.getExp();
        // THEN
        assertThat(result).isEqualTo(exp);
    }

    @Test
    void testIsRefreshReturnsBoolean() {
        // GIVEN
        // WHEN
        boolean result = credential.isRefresh();
        // THEN
        assertThat(result).isEqualTo(isRefresh);
    }

    @Test
    void testConstructorHandlesNullValues() {
        // GIVEN
        Credential nullCredential = new Credential(null, null, null, false);
        // WHEN
        String tokenResult = nullCredential.getToken();
        String tokenTypeResult = nullCredential.getTokenType();
        Date expResult = nullCredential.getExp();
        boolean isRefreshResult = nullCredential.isRefresh();
        // THEN
        assertThat(tokenResult).isNull();
        assertThat(tokenTypeResult).isNull();
        assertThat(expResult).isNull();
        assertThat(isRefreshResult).isFalse();
    }

    @Test
    void testGetExpReturnsSameInstance() {
        // GIVEN
        Date now = new Date();
        Credential cred = new Credential("t", "Bearer", now, true);
        // WHEN
        Date result = cred.getExp();
        // THEN
        assertThat(result).isSameAs(now);
    }

    @Test
    void testIsRefreshFalseWhenSetToFalse() {
        // GIVEN
        Credential cred = new Credential("t", "Bearer", new Date(), false);
        // WHEN
        boolean result = cred.isRefresh();
        // THEN
        assertThat(result).isFalse();
    }
}
