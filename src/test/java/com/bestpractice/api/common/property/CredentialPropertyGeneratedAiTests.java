package com.bestpractice.api.common.property;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void testGetAndSetKey() {
        // GIVEN
        String expectedKey = "testKey";
        // WHEN
        credentialProperty.setKey(expectedKey);
        // THEN
        assertEquals(expectedKey, credentialProperty.getKey());
    }

    @Test
    void testGetAndSetProvider() {
        // GIVEN
        String expectedProvider = "testProvider";
        // WHEN
        credentialProperty.setProvider(expectedProvider);
        // THEN
        assertEquals(expectedProvider, credentialProperty.getProvider());
    }

    @Test
    void testGetAndSetSubject() {
        // GIVEN
        String expectedSubject = "testSubject";
        // WHEN
        credentialProperty.setSubject(expectedSubject);
        // THEN
        assertEquals(expectedSubject, credentialProperty.getSubject());
    }

    @Test
    void testGetAndSetAlg() {
        // GIVEN
        String expectedAlg = "HS256";
        // WHEN
        credentialProperty.setAlg(expectedAlg);
        // THEN
        assertEquals(expectedAlg, credentialProperty.getAlg());
    }

    @Test
    void testGetAndSetHmacSecret() {
        // GIVEN
        String expectedSecret = "secretValue";
        // WHEN
        credentialProperty.setHmacSecret(expectedSecret);
        // THEN
        assertEquals(expectedSecret, credentialProperty.getHmacSecret());
    }

    @Test
    void testGetAndSetExpiresHourStr() {
        // GIVEN
        String expectedExpires = "24";
        // WHEN
        credentialProperty.setExpiresHourStr(expectedExpires);
        // THEN
        assertEquals(expectedExpires, credentialProperty.getExpiresHourStr());
    }

    @Test
    void testConvertToIntExpiresWithValidNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("12");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertNotNull(result);
        assertEquals(12, result);
    }

    @Test
    void testConvertToIntExpiresWithDashReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresWithInvalidNumberReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresWithNullValueThrowsNullPointerException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);
        // WHEN / THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }

    @Test
    void testConvertToIntExpiresNumberFormatHandledGracefully() {
        // GIVEN
        credentialProperty.setExpiresHourStr("abc123");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithEmptyStringHandledGracefully() {
        // GIVEN
        credentialProperty.setExpiresHourStr("");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertNull(result);
    }
}
