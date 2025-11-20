package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
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
    void testGetToken() {
        // GIVEN: A Credential object initialized with a token
        // WHEN: getToken is called
        String result = credential.getToken();

        // THEN: The returned token should match the initialized value
        assertThat(result).isEqualTo(token);
    }

    @Test
    void testGetTokenType() {
        // GIVEN: A Credential object initialized with a token type
        // WHEN: getTokenType is called
        String result = credential.getTokenType();

        // THEN: The returned token type should match the initialized value
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void testGetExp() {
        // GIVEN: A Credential object initialized with an expiration date
        // WHEN: getExp is called
        Date result = credential.getExp();

        // THEN: The returned expiration date should match the initialized value
        assertThat(result).isEqualTo(exp);
    }

    @Test
    void testIsRefresh() {
        // GIVEN: A Credential object initialized with a refresh flag
        // WHEN: isRefresh is called
        boolean result = credential.isRefresh();

        // THEN: The returned refresh flag should match the initialized value
        assertThat(result).isEqualTo(isRefresh);
    }
}
