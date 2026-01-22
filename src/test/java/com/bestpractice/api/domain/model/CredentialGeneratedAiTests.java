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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CredentialGeneratedAiTests {

    private Credential defaultCredential;
    private final String token = "sampleToken";
    private final String tokenType = "Bearer";
    private final Date exp = new Date(System.currentTimeMillis() + 3600000L); // 1 hour later
    private final boolean isRefresh = true;

    @BeforeEach
    void setUp() {
        // GIVEN a fully populated Credential instance
        defaultCredential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void testGetTokenReturnsExpectedValue() {
        // GIVEN a Credential instance with a non‑null token
        // WHEN getToken() is called
        String result = defaultCredential.getToken();
        // THEN the returned token should match the value passed to the constructor
        assertThat(result).isEqualTo(token);
    }

    @Test
    void testGetTokenTypeReturnsExpectedValue() {
        // GIVEN a Credential instance with a non‑null tokenType
        // WHEN getTokenType() is called
        String result = defaultCredential.getTokenType();
        // THEN the returned tokenType should match the value passed to the constructor
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void testGetExpReturnsSameDateInstance() {
        // GIVEN a Credential instance with a non‑null expiration date
        // WHEN getExp() is called
        Date result = defaultCredential.getExp();
        // THEN the returned Date instance should be the same as the one passed to the constructor
        assertThat(result).isSameAs(exp);
    }

    @Test
    void testIsRefreshReturnsTrueWhenSetToTrue() {
        // GIVEN a Credential instance where isRefresh is true
        // WHEN isRefresh() is called
        boolean result = defaultCredential.isRefresh();
        // THEN the returned boolean should be true
        assertThat(result).isTrue();
    }

    @Test
    void testIsRefreshReturnsFalseWhenSetToFalse() {
        // GIVEN a Credential instance where isRefresh is false
        // WHEN isRefresh() is called
        Credential credential = new Credential(token, tokenType, exp, false);
        boolean result = credential.isRefresh();
        // THEN the returned boolean should be false
        assertThat(result).isFalse();
    }

    @Test
    void testConstructorAllowsNullTokenAndTokenType() {
        // GIVEN a Credential instance created with null token and null tokenType
        // WHEN the getters are called
        Credential credential = new Credential(null, null, exp, isRefresh);
        // THEN the getters should return null for token and tokenType
        assertThat(credential.getToken()).isNull();
        assertThat(credential.getTokenType()).isNull();
    }

    @Test
    void testConstructorAllowsNullExpirationDate() {
        // GIVEN a Credential instance created with null expiration date
        // WHEN getExp() is called
        Credential credential = new Credential(token, tokenType, null, isRefresh);
        // THEN the returned expiration date should be null
        assertThat(credential.getExp()).isNull();
    }

    @Test
    void testModifyingReturnedDateAffectsOriginal() {
        // GIVEN a Credential instance with a mutable Date object
        // WHEN the returned Date is modified
        Date returnedDate = defaultCredential.getExp();
        long originalTime = returnedDate.getTime();
        returnedDate.setTime(originalTime + 1000L);
        // THEN the original Date inside the Credential should reflect the modification
        assertThat(defaultCredential.getExp().getTime()).isEqualTo(originalTime + 1000L);
    }

    @Test
    void testConstructorSharesDateInstance() {
        // GIVEN a Date instance passed to the constructor
        // WHEN the original Date is modified after construction
        Date originalDate = new Date(exp.getTime());
        Credential credential = new Credential(token, tokenType, originalDate, isRefresh);
        long originalTime = originalDate.getTime();
        originalDate.setTime(originalTime + 2000L);
        // THEN the Credential's expiration date should reflect the change
        assertThat(credential.getExp().getTime()).isEqualTo(originalTime + 2000L);
    }

    @Test
    void testConstructorWithPartialNulls() {
        // GIVEN a Credential instance created with null token but non‑null tokenType
        // WHEN getToken() and getTokenType() are called
        Credential credential = new Credential(null, tokenType, exp, isRefresh);
        // THEN getToken() should return null and getTokenType() should return the provided value
        assertThat(credential.getToken()).isNull();
        assertThat(credential.getTokenType()).isEqualTo(tokenType);
    }
}
