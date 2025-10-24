package com.bestpractice.api.common.property;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
        credentialProperty.setKey(null);
        credentialProperty.setProvider(null);
        credentialProperty.setSubject(null);
        credentialProperty.setAlg(null);
        credentialProperty.setHmacSecret(null);
        credentialProperty.setExpiresHourStr(null);
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
    void testSetAndGetProvider() {
        // GIVEN
        String expectedProvider = "testProvider";

        // WHEN
        credentialProperty.setProvider(expectedProvider);

        // THEN
        assertEquals(expectedProvider, credentialProperty.getProvider());
    }

    @Test
    void testSetAndGetSubject() {
        // GIVEN
        String expectedSubject = "testSubject";

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
    void testSetAndGetHmacSecret() {
        // GIVEN
        String expectedSecret = "secretValue";

        // WHEN
        credentialProperty.setHmacSecret(expectedSecret);

        // THEN
        assertEquals(expectedSecret, credentialProperty.getHmacSecret());
    }

    @Test
    void testSetAndGetExpiresHourStr() {
        // GIVEN
        String expectedExpires = "12";

        // WHEN
        credentialProperty.setExpiresHourStr(expectedExpires);

        // THEN
        assertEquals(expectedExpires, credentialProperty.getExpiresHourStr());
    }

    @Test
    void testConvertToIntExpiresWithValidNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("5");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNotNull(result);
        assertEquals(5, result);
    }

    @Test
    void testConvertToIntExpiresWithDash() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresWithInvalidNumber() {
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

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }

    @Test
    void testConvertToIntExpiresHandlesNumberFormatExceptionGracefully() {
        // GIVEN
        credentialProperty.setExpiresHourStr("abc");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresBoundaryCaseZero() {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNotNull(result);
        assertEquals(0, result);
    }

    @Test
    void testConvertToIntExpiresBoundaryCaseNegativeNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-5");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNotNull(result);
        assertEquals(-5, result);
    }

    @Test
    void testConvertToIntExpiresThrowsExceptionWhenExpiresHourStrIsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            credentialProperty.convertToIntExpires();
        });
    }
}
