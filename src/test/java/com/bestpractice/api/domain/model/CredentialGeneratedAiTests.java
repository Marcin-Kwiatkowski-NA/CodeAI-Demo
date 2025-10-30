package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CredentialGeneratedAiTests {

    private String token;
    private String tokenType;
    private Date exp;
    private boolean isRefresh;
    private Credential credential;

    @BeforeEach
    void setUp() {
        // GIVEN: Initializing test data before each test
        token = "sampleToken";
        tokenType = "Bearer";
        exp = new Date(System.currentTimeMillis() + 10000);
        isRefresh = true;
        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void testGetToken() {
        // GIVEN: A Credential object with a specific token
        // WHEN: Calling getToken()
        String result = credential.getToken();
        // THEN: The returned token should match the initialized value
        assertEquals(token, result);
    }

    @Test
    void testGetTokenType() {
        // GIVEN: A Credential object with a specific token type
        // WHEN: Calling getTokenType()
        String result = credential.getTokenType();
        // THEN: The returned token type should match the initialized value
        assertEquals(tokenType, result);
    }

    @Test
    void testGetExp() {
        // GIVEN: A Credential object with a specific expiration date
        // WHEN: Calling getExp()
        Date result = credential.getExp();
        // THEN: The returned expiration date should match the initialized value
        assertEquals(exp, result);
    }

    @Test
    void testIsRefreshTrue() {
        // GIVEN: A Credential object with isRefresh set to true
        // WHEN: Calling isRefresh()
        boolean result = credential.isRefresh();
        // THEN: The returned value should be true
        assertTrue(result);
    }

    @Test
    void testIsRefreshFalse() {
        // GIVEN: A Credential object with isRefresh set to false
        credential = new Credential(token, tokenType, exp, false);
        // WHEN: Calling isRefresh()
        boolean result = credential.isRefresh();
        // THEN: The returned value should be false
        assertFalse(result);
    }

    @Test
    void testConstructorWithNullValues() {
        // GIVEN: Null values for token, tokenType, and exp
        String nullToken = null;
        String nullTokenType = null;
        Date nullExp = null;
        boolean refreshFlag = false;

        // WHEN: Creating a Credential object with null values
        Credential nullCredential = new Credential(nullToken, nullTokenType, nullExp, refreshFlag);

        // THEN: The getters should return null for strings and date, and correct boolean value
        assertEquals(nullToken, nullCredential.getToken());
        assertEquals(nullTokenType, nullCredential.getTokenType());
        assertEquals(nullExp, nullCredential.getExp());
        assertFalse(nullCredential.isRefresh());
    }
}
