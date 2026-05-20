package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for {@link CredentialProperty}.
 * This class validates getter/setter behavior and edge cases for convertToIntExpires().
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
    void testConvertToIntExpiresValidNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("12");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(12, result);
    }

    @Test
    void testConvertToIntExpiresInvalidNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresDashValue() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

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
    void testConvertToIntExpiresHandlesLargeIntegerValue() {
        // GIVEN
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MAX_VALUE));

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void testConvertToIntExpiresHandlesNegativeIntegerValue() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-5");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(-5, result);
    }
}
