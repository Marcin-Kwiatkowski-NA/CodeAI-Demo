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

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CredentialGeneratedAiTests {

    private Credential credential;
    private String token;
    private String tokenType;
    private Date exp;
    private boolean isRefresh;

    @BeforeEach
    void setUp() {
        token = "sampleToken";
        tokenType = "Bearer";
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        exp = cal.getTime();
        isRefresh = true;
        credential = new Credential(token, tokenType, exp, isRefresh);
    }

    @Test
    void testGetToken() {
        // GIVEN: a Credential instance with a known token
        // WHEN: getToken() is called
        String result = credential.getToken();
        // THEN: the returned token should match the one provided
        assertThat(result).isEqualTo(token);
    }

    @Test
    void testGetTokenType() {
        // GIVEN: a Credential instance with a known token type
        // WHEN: getTokenType() is called
        String result = credential.getTokenType();
        // THEN: the returned token type should match the one provided
        assertThat(result).isEqualTo(tokenType);
    }

    @Test
    void testGetExp() {
        // GIVEN: a Credential instance with a known expiration date
        // WHEN: getExp() is called
        Date result = credential.getExp();
        // THEN: the returned date should be equal to the one provided
        assertThat(result).isEqualToComparingFieldByField(exp);
    }

    @Test
    void testIsRefresh() {
        // GIVEN: a Credential instance with a known refresh flag
        // WHEN: isRefresh() is called
        boolean result = credential.isRefresh();
        // THEN: the returned flag should match the one provided
        assertThat(result).isEqualTo(isRefresh);
    }

    @Test
    void testExpirationDateIsMutableThroughGetter() {
        // GIVEN: a Credential instance with a known expiration date
        // WHEN: the returned Date is modified
        Date returnedDate = credential.getExp();
        returnedDate.setTime(returnedDate.getTime() + 100000);
        // THEN: the internal state of the Credential reflects the change
        assertThat(credential.getExp()).isEqualTo(returnedDate);
    }
}
