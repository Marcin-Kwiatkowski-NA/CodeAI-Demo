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
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

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
    void testGetToken() {
        // GIVEN a Credential instance with a non-null token
        // WHEN getToken() is called
        String result = credential.getToken();
        // THEN the returned token should match the original value
        assertThat(result).isEqualTo(token);
    }

    @Test
    void testGetTokenWithNull() {
        // GIVEN a Credential instance created with a null token
        Credential credWithNull = new Credential(null, tokenType, exp, isRefresh);
        // WHEN getToken() is called
        String result = credWithNull.getToken();
        // THEN the returned token should be null
        assertThat(result).isNull();
    }

    @Test
    void testGetTokenType() {
        // GIVEN a Credential instance with a specific token type
        // WHEN getTokenType() is called
        String result = credential.getTokenType();
        // THEN the returned token type should match the original value
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void testGetTokenTypeWithNull() {
        // GIVEN a Credential instance created with a null token type
        Credential credWithNull = new Credential(token, null, exp, isRefresh);
        // WHEN getTokenType() is called
        String result = credWithNull.getTokenType();
        // THEN the returned token type should be null
        assertThat(result).isNull();
    }

    @Test
    void testGetExp() {
        // GIVEN a Credential instance with a specific expiration date
        // WHEN getExp() is called
        Date result = credential.getExp();
        // THEN the returned date should be the same instance as provided
        assertThat(result).isSameAs(exp);
    }

    @Test
    void testGetExpWithNull() {
        // GIVEN a Credential instance created with a null expiration date
        Credential credWithNull = new Credential(token, tokenType, null, isRefresh);
        // WHEN getExp() is called
        Date result = credWithNull.getExp();
        // THEN the returned date should be null
        assertThat(result).isNull();
    }

    @Test
    void testIsRefresh() {
        // GIVEN a Credential instance with isRefresh set to true
        // WHEN isRefresh() is called
        boolean result = credential.isRefresh();
        // THEN the returned value should be true
        assertThat(result).isTrue();
    }

    @Test
    void testIsRefreshFalse() {
        // GIVEN a Credential instance with isRefresh set to false
        Credential credFalse = new Credential(token, tokenType, exp, false);
        // WHEN isRefresh() is called
        boolean result = credFalse.isRefresh();
        // THEN the returned value should be false
        assertThat(result).isFalse();
    }
}
