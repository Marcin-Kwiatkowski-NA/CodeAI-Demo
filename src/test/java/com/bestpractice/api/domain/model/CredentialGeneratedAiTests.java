package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.Date;

public class CredentialGeneratedAiTests {

    private Credential credential;
    private String token;
    private String tokenType;
    private Date exp;
    private boolean isRefresh;

    @BeforeEach
    void setUp() {
        token = "sampleToken";
        tokenType = "Bearer";
        exp = new Date(System.currentTimeMillis() + 10000);
        isRefresh = true;
        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void shouldReturnCorrectToken() {
        // GIVEN
        String expectedToken = token;

        // WHEN
        String actualToken = credential.getToken();

        // THEN
        assertEquals(expectedToken, actualToken);
    }

    @Test
    void shouldReturnCorrectTokenType() {
        // GIVEN
        String expectedTokenType = tokenType;

        // WHEN
        String actualTokenType = credential.getTokenType();

        // THEN
        assertEquals(expectedTokenType, actualTokenType);
    }

    @Test
    void shouldReturnCorrectExpirationDate() {
        // GIVEN
        Date expectedExp = exp;

        // WHEN
        Date actualExp = credential.getExp();

        // THEN
        assertEquals(expectedExp, actualExp);
    }

    @Test
    void shouldReturnCorrectRefreshFlag() {
        // GIVEN
        boolean expectedIsRefresh = isRefresh;

        // WHEN
        boolean actualIsRefresh = credential.isRefresh();

        // THEN
        assertEquals(expectedIsRefresh, actualIsRefresh);
    }

    @Test
    void shouldHandleNullExpirationDateGracefully() {
        // GIVEN
        Credential nullExpCredential = new Credential(token, tokenType, null, false);

        // WHEN
        Date actualExp = nullExpCredential.getExp();

        // THEN
        assertNull(actualExp);
    }

    @Test
    void shouldHandleEmptyTokenValues() {
        // GIVEN
        Credential emptyTokenCredential = new Credential("", "", exp, false);

        // WHEN
        String actualToken = emptyTokenCredential.getToken();
        String actualTokenType = emptyTokenCredential.getTokenType();

        // THEN
        assertEquals("", actualToken);
        assertEquals("", actualTokenType);
    }

    @Test
    void shouldCreateCredentialWithoutExceptions() {
        // GIVEN
        String localToken = "testToken";
        String localTokenType = "Basic";
        Date localExp = new Date();
        boolean localIsRefresh = false;

        // WHEN
        Credential localCredential = new Credential(localToken, localTokenType, localExp, localIsRefresh);

        // THEN
        assertEquals(localToken, localCredential.getToken());
        assertEquals(localTokenType, localCredential.getTokenType());
        assertEquals(localExp, localCredential.getExp());
        assertEquals(localIsRefresh, localCredential.isRefresh());
    }

    @Test
    void shouldAllowNullTokenAndTokenTypeWithoutExceptions() {
        // GIVEN
        String nullToken = null;
        String nullTokenType = null;
        Date localExp = new Date();
        boolean localIsRefresh = false;

        // WHEN
        Credential nullCredential = new Credential(nullToken, nullTokenType, localExp, localIsRefresh);

        // THEN
        assertNull(nullCredential.getToken());
        assertNull(nullCredential.getTokenType());
        assertEquals(localExp, nullCredential.getExp());
        assertEquals(localIsRefresh, nullCredential.isRefresh());
    }
}
