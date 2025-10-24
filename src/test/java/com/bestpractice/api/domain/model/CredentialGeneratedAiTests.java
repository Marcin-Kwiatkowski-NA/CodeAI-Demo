package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialGeneratedAiTests {

    private String token;
    private String tokenType;
    private Date exp;
    private boolean isRefresh;

    @BeforeEach
    void setUp() {
        // Reset state before each test
        token = "sampleToken";
        tokenType = "Bearer";
        exp = new Date(System.currentTimeMillis() + 10000);
        isRefresh = true;
    }

    @Test
    void testGetTokenReturnsCorrectValue() {
        // GIVEN: a Credential object with a specific token
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: getToken is called
        String result = credential.getToken();

        // THEN: the returned token should match the expected value
        assertEquals(token, result);
    }

    @Test
    void testGetTokenTypeReturnsCorrectValue() {
        // GIVEN: a Credential object with a specific token type
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: getTokenType is called
        String result = credential.getTokenType();

        // THEN: the returned token type should match the expected value
        assertEquals(tokenType, result);
    }

    @Test
    void testGetExpReturnsCorrectValue() {
        // GIVEN: a Credential object with a specific expiration date
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: getExp is called
        Date result = credential.getExp();

        // THEN: the returned expiration date should match the expected value
        assertEquals(exp, result);
    }

    @Test
    void testIsRefreshReturnsCorrectValue() {
        // GIVEN: a Credential object with a specific refresh flag
        Credential credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN: isRefresh is called
        boolean result = credential.isRefresh();

        // THEN: the returned refresh flag should match the expected value
        assertTrue(result);
    }

    @Test
    void testCredentialWithDifferentValues() {
        // GIVEN: a Credential object with different values
        String newToken = "anotherToken";
        String newTokenType = "Basic";
        Date newExp = new Date(System.currentTimeMillis() + 20000);
        boolean newIsRefresh = false;
        Credential credential = new Credential(newToken, newTokenType, newExp, newIsRefresh);

        // WHEN & THEN: verify all getters return the correct values
        assertEquals(newToken, credential.getToken());
        assertEquals(newTokenType, credential.getTokenType());
        assertEquals(newExp, credential.getExp());
        assertFalse(credential.isRefresh());
    }
}
