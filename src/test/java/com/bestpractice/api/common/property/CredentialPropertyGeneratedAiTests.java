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
 * Unit tests for CredentialProperty class.
 * This class validates getter/setter behavior and conversion logic.
 * Security-sensitive note: This class handles credential-related properties.
 */
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
    void testConvertToIntExpiresWithNullValueThrowsException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN THEN
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
