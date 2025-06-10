package com.bestpractice.api.common.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    public void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    public void testSetAndGetKey() {
        // GIVEN
        String expectedKey = "testKey";

        // WHEN
        credentialProperty.setKey(expectedKey);

        // THEN
        assertEquals(expectedKey, credentialProperty.getKey());
    }

    @Test
    public void testSetAndGetProvider() {
        // GIVEN
        String expectedProvider = "testProvider";

        // WHEN
        credentialProperty.setProvider(expectedProvider);

        // THEN
        assertEquals(expectedProvider, credentialProperty.getProvider());
    }

    @Test
    public void testSetAndGetSubject() {
        // GIVEN
        String expectedSubject = "testSubject";

        // WHEN
        credentialProperty.setSubject(expectedSubject);

        // THEN
        assertEquals(expectedSubject, credentialProperty.getSubject());
    }

    @Test
    public void testSetAndGetAlg() {
        // GIVEN
        String expectedAlg = "HS256";

        // WHEN
        credentialProperty.setAlg(expectedAlg);

        // THEN
        assertEquals(expectedAlg, credentialProperty.getAlg());
    }

    @Test
    public void testSetAndGetHmacSecret() {
        // GIVEN
        String expectedHmacSecret = "testHmacSecret";

        // WHEN
        credentialProperty.setHmacSecret(expectedHmacSecret);

        // THEN
        assertEquals(expectedHmacSecret, credentialProperty.getHmacSecret());
    }

    @Test
    public void testSetAndGetExpiresHourStr() {
        // GIVEN
        String expectedExpiresHourStr = "12";

        // WHEN
        credentialProperty.setExpiresHourStr(expectedExpiresHourStr);

        // THEN
        assertEquals(expectedExpiresHourStr, credentialProperty.getExpiresHourStr());
    }

    @Test
    public void testConvertToIntExpiresWithValidInput() {
        // GIVEN
        String validExpiresHourStr = "12";
        credentialProperty.setExpiresHourStr(validExpiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(12, result);
    }

    @Test
    public void testConvertToIntExpiresWithInvalidInput() {
        // GIVEN
        String invalidExpiresHourStr = "invalid";
        credentialProperty.setExpiresHourStr(invalidExpiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    @Test
    public void testConvertToIntExpiresWithDashInput() {
        // GIVEN
        String dashExpiresHourStr = "-";
        credentialProperty.setExpiresHourStr(dashExpiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }
}
