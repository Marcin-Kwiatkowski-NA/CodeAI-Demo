package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SECURITY-SENSITIVE: This class tests configuration properties that may include credential-related fields.
 * Ensure no real secrets or sensitive data are used in tests.
 */
public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void testSetAndGetKey() {
        // GIVEN
        String expectedKey = "testKey";

        // WHEN
        credentialProperty.setKey(expectedKey);
        String actualKey = credentialProperty.getKey();

        // THEN
        assertEquals(expectedKey, actualKey);
    }

    @Test
    void testSetAndGetProvider() {
        // GIVEN
        String expectedProvider = "testProvider";

        // WHEN
        credentialProperty.setProvider(expectedProvider);
        String actualProvider = credentialProperty.getProvider();

        // THEN
        assertEquals(expectedProvider, actualProvider);
    }

    @Test
    void testSetAndGetSubject() {
        // GIVEN
        String expectedSubject = "testSubject";

        // WHEN
        credentialProperty.setSubject(expectedSubject);
        String actualSubject = credentialProperty.getSubject();

        // THEN
        assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void testSetAndGetAlg() {
        // GIVEN
        String expectedAlg = "HS256";

        // WHEN
        credentialProperty.setAlg(expectedAlg);
        String actualAlg = credentialProperty.getAlg();

        // THEN
        assertEquals(expectedAlg, actualAlg);
    }

    @Test
    void testSetAndGetHmacSecret() {
        // GIVEN
        String expectedSecret = "dummySecret";

        // WHEN
        credentialProperty.setHmacSecret(expectedSecret);
        String actualSecret = credentialProperty.getHmacSecret();

        // THEN
        assertEquals(expectedSecret, actualSecret);
    }

    @Test
    void testSetAndGetExpiresHourStr() {
        // GIVEN
        String expectedExpires = "12";

        // WHEN
        credentialProperty.setExpiresHourStr(expectedExpires);
        String actualExpires = credentialProperty.getExpiresHourStr();

        // THEN
        assertEquals(expectedExpires, actualExpires);
    }

    @Test
    void testConvertToIntExpiresWithValidNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("24");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(24, result);
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
    void testConvertToIntExpiresThrowsNullPointerExceptionWhenExpiresHourStrIsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }

    @Test
    void testConvertToIntExpiresHandlesEmptyStringGracefully() {
        // GIVEN
        credentialProperty.setExpiresHourStr("");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }
}
