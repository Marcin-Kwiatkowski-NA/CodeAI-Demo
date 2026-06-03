package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void testSetAndGetKey() {
        String expectedKey = "testKey";
        credentialProperty.setKey(expectedKey);
        assertEquals(expectedKey, credentialProperty.getKey());
    }

    @Test
    void testSetAndGetProvider() {
        String expectedProvider = "provider";
        credentialProperty.setProvider(expectedProvider);
        assertEquals(expectedProvider, credentialProperty.getProvider());
    }

    @Test
    void testSetAndGetSubject() {
        String expectedSubject = "subject";
        credentialProperty.setSubject(expectedSubject);
        assertEquals(expectedSubject, credentialProperty.getSubject());
    }

    @Test
    void testSetAndGetAlg() {
        String expectedAlg = "HS256";
        credentialProperty.setAlg(expectedAlg);
        assertEquals(expectedAlg, credentialProperty.getAlg());
    }

    @Test
    void testSetAndGetHmacSecret() {
        String expectedSecret = "secretValue";
        credentialProperty.setHmacSecret(expectedSecret);
        assertEquals(expectedSecret, credentialProperty.getHmacSecret());
    }

    @Test
    void testSetAndGetExpiresHourStr() {
        String expectedExpires = "24";
        credentialProperty.setExpiresHourStr(expectedExpires);
        assertEquals(expectedExpires, credentialProperty.getExpiresHourStr());
    }

    @Test
    void testConvertToIntExpiresValidNumber() {
        credentialProperty.setExpiresHourStr("12");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(12, result);
    }

    @Test
    void testConvertToIntExpiresZero() {
        credentialProperty.setExpiresHourStr("0");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(0, result);
    }

    @Test
    void testConvertToIntExpiresNegativeNumber() {
        credentialProperty.setExpiresHourStr("-5");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(-5, result);
    }

    @Test
    void testConvertToIntExpiresDashReturnsNull() {
        credentialProperty.setExpiresHourStr("-");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresInvalidNumberReturnsNull() {
        credentialProperty.setExpiresHourStr("invalid");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresNullValueThrowsException() {
        credentialProperty.setExpiresHourStr(null);
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }

    @Test
    void testConvertToIntExpiresMaxIntegerValue() {
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MAX_VALUE));
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void testConvertToIntExpiresMinIntegerValue() {
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MIN_VALUE));
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(Integer.MIN_VALUE, result);
    }
}
