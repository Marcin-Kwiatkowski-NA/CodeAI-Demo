package com.bestpractice.api.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({})
public class CredentialGeneratedAiTests {

    private String token;
    private String tokenType;
    private Date exp;
    private boolean isRefresh;
    private Credential credential;

    @BeforeEach
    public void setUp() {
        token = "testToken";
        tokenType = "Bearer";
        exp = new Date(System.currentTimeMillis() + 3600000); // 1 hour from now
        isRefresh = true;
        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    public void testGetToken() {
        // GIVEN
        String expectedToken = "testToken";

        // WHEN
        String actualToken = credential.getToken();

        // THEN
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void testGetTokenType() {
        // GIVEN
        String expectedTokenType = "Bearer";

        // WHEN
        String actualTokenType = credential.getTokenType();

        // THEN
        assertEquals(expectedTokenType, actualTokenType);
    }

    @Test
    public void testGetExp() {
        // GIVEN
        long currentTimeMillis = System.currentTimeMillis();
        Date expectedExp = new Date(currentTimeMillis + 3600000); // 1 hour from now

        // WHEN
        Date actualExp = credential.getExp();

        // THEN
        assertEquals(expectedExp.getTime(), actualExp.getTime());
    }

    @Test
    public void testIsRefreshTrue() {
        // GIVEN
        boolean expectedIsRefresh = true;

        // WHEN
        boolean actualIsRefresh = credential.isRefresh();

        // THEN
        assertTrue(actualIsRefresh);
    }

    @Test
    public void testIsRefreshFalse() {
        // GIVEN
        isRefresh = false;
        credential = new Credential(token, tokenType, exp, isRefresh);

        // WHEN
        boolean actualIsRefresh = credential.isRefresh();

        // THEN
        assertFalse(actualIsRefresh);
    }
}
