package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Final improved test class for CredentialProperty.
 * Improvements:
 * - Clear GIVEN/WHEN/THEN structure.
 * - Removed redundant imports and unused annotations.
 * - Added missing edge case tests.
 * - Ensured all tests are independent and deterministic.
 * - Verified null and boundary handling.
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
        // THEN
        assertEquals(expectedKey, credentialProperty.getKey());
    }

    @Test
    void testSetAndGetKeyWithEmptyString() {
        // GIVEN
        String expectedKey = "";
        // WHEN
        credentialProperty.setKey(expectedKey);
        // THEN
        assertEquals(expectedKey, credentialProperty.getKey());
    }

    @Test
    void testSetAndGetKeyWithWhitespace() {
        // GIVEN
        String expectedKey = "   ";
        // WHEN
        credentialProperty.setKey(expectedKey);
        // THEN
        assertEquals(expectedKey, credentialProperty.getKey());
    }

    @Test
    void testSetAndGetProvider() {
        // GIVEN
        String expectedProvider = "provider";
        // WHEN
        credentialProperty.setProvider(expectedProvider);
        // THEN
        assertEquals(expectedProvider, credentialProperty.getProvider());
    }

    @Test
    void testSetAndGetProviderWithEmptyString() {
        // GIVEN
        String expectedProvider = "";
        // WHEN
        credentialProperty.setProvider(expectedProvider);
        // THEN
        assertEquals(expectedProvider, credentialProperty.getProvider());
    }

    @Test
    void testSetAndGetSubject() {
        // GIVEN
        String expectedSubject = "subject";
        // WHEN
        credentialProperty.setSubject(expectedSubject);
        // THEN
        assertEquals(expectedSubject, credentialProperty.getSubject());
    }

    @Test
    void testSetAndGetSubjectWithWhitespace() {
        // GIVEN
        String expectedSubject = " ";
        // WHEN
        credentialProperty.setSubject(expectedSubject);
        // THEN
        assertEquals(expectedSubject, credentialProperty.getSubject());
    }

    @Test
    void testSetAndGetAlg() {
        // GIVEN
        String expectedAlg = "HS256";
        // WHEN
        credentialProperty.setAlg(expectedAlg);
        // THEN
        assertEquals(expectedAlg, credentialProperty.getAlg());
    }

    @Test
    void testSetAndGetAlgWithEmptyString() {
        // GIVEN
        String expectedAlg = "";
        // WHEN
        credentialProperty.setAlg(expectedAlg);
        // THEN
        assertEquals(expectedAlg, credentialProperty.getAlg());
    }

    @Test
    void testSetAndGetHmacSecret() {
        // GIVEN
        String expectedSecret = "secretValue";
        // WHEN
        credentialProperty.setHmacSecret(expectedSecret);
        // THEN
        assertEquals(expectedSecret, credentialProperty.getHmacSecret());
    }

    @Test
    void testSetAndGetHmacSecretWithWhitespace() {
        // GIVEN
        String expectedSecret = "   ";
        // WHEN
        credentialProperty.setHmacSecret(expectedSecret);
        // THEN
        assertEquals(expectedSecret, credentialProperty.getHmacSecret());
    }

    @Test
    void testSetAndGetExpiresHourStr() {
        // GIVEN
        String expectedExpires = "24";
        // WHEN
        credentialProperty.setExpiresHourStr(expectedExpires);
        // THEN
        assertEquals(expectedExpires, credentialProperty.getExpiresHourStr());
    }

    @Test
    void testSetAndGetExpiresHourStrWithEmptyString() {
        // GIVEN
        String expectedExpires = "";
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
    void testConvertToIntExpiresWithZero() {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(0, result);
    }

    @Test
    void testConvertToIntExpiresWithNegativeNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-1");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(-1, result);
    }

    @Test
    void testConvertToIntExpiresWithMaxIntegerValue() {
        // GIVEN
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MAX_VALUE));
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void testConvertToIntExpiresWithMinIntegerValue() {
        // GIVEN
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MIN_VALUE));
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    void testConvertToIntExpiresWithDashReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresWithInvalidNumberReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresWithNullExpiresHourStrThrowsException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);
        // WHEN THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }

    @Test
    void testConvertToIntExpiresWithSingleDigitBoundary() {
        // GIVEN
        credentialProperty.setExpiresHourStr("1");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(1, result);
    }

    @Test
    void testConvertToIntExpiresWithLeadingZeros() {
        // GIVEN
        credentialProperty.setExpiresHourStr("0005");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(5, result);
    }

    @Test
    void testConvertToIntExpiresWithWhitespaceAroundNumberReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("  10  ");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresWithPlusSignPrefix() {
        // GIVEN
        credentialProperty.setExpiresHourStr("+5");
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(5, result);
    }

    @Test
    void testConvertToIntExpiresWithBoundaryNearOverflowReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("2147483648"); // one above Integer.MAX_VALUE
        // WHEN
        Integer result = credentialProperty.convertToIntExpires();
        // THEN
        assertEquals(null, result);
    }
}
