package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CredentialGeneratedAiTests {

    private String token;
    private String tokenType;
    private Date exp;
    private boolean isRefresh;
    private Credential credential;

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
    void shouldHandleNullExpirationDate() {
        // GIVEN
        Credential nullExpCredential = new Credential(token, tokenType, null, false);

        // WHEN
        Date actualExp = nullExpCredential.getExp();

        // THEN
        assertNull(actualExp);
    }

    @Test
    void shouldAllowNullTokenAndTokenTypeWithoutException() {
        // GIVEN
        String nullToken = null;
        String nullTokenType = null;
        Date expiration = new Date();

        // WHEN
        Credential nullCredential = new Credential(nullToken, nullTokenType, expiration, false);

        // THEN
        assertNull(nullCredential.getToken());
        assertNull(nullCredential.getTokenType());
        assertEquals(expiration, nullCredential.getExp());
        assertEquals(false, nullCredential.isRefresh());
    }

    @Test
    void shouldNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN
        String nullToken = null;
        String nullTokenType = null;
        Date nullExp = null;

        // WHEN & THEN
        Credential nullCredential = new Credential(nullToken, nullTokenType, nullExp, false);
        assertNull(nullCredential.getToken());
        assertNull(nullCredential.getTokenType());
        assertNull(nullCredential.getExp());
        assertEquals(false, nullCredential.isRefresh());
    }

    @Test
    void shouldThrowExceptionWhenAccessingMethodsOnNullCredential() {
        // GIVEN
        Credential nullCredential = null;

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> nullCredential.getToken());
    }
}
