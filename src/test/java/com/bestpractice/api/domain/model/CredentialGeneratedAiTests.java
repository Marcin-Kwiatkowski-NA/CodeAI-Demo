package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    void shouldAllowNullTokenAndTokenType() {
        // GIVEN
        Credential nullTokenCredential = new Credential(null, null, exp, false);

        // WHEN
        String actualToken = nullTokenCredential.getToken();
        String actualTokenType = nullTokenCredential.getTokenType();

        // THEN
        assertNull(actualToken);
        assertNull(actualTokenType);
    }
}
