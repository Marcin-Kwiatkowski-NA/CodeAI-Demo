package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
    void shouldReturnCorrectIsRefreshFlag() {
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
    void shouldHandleEmptyTokenAndTokenType() {
        // GIVEN
        Credential emptyCredential = new Credential("", "", exp, false);

        // WHEN
        String actualToken = emptyCredential.getToken();
        String actualTokenType = emptyCredential.getTokenType();

        // THEN
        assertEquals("", actualToken);
        assertEquals("", actualTokenType);
    }

    @Test
    void shouldCreateCredentialWithNullValuesWithoutException() {
        // GIVEN
        String nullToken = null;
        String nullTokenType = null;
        Date nullExp = null;
        boolean refreshFlag = false;

        // WHEN
        Credential nullCredential = new Credential(nullToken, nullTokenType, nullExp, refreshFlag);

        // THEN
        assertNull(nullCredential.getToken());
        assertNull(nullCredential.getTokenType());
        assertNull(nullCredential.getExp());
        assertEquals(refreshFlag, nullCredential.isRefresh());
    }
}
