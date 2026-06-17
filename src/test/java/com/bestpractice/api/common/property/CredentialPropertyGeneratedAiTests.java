package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
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
    void testSetAndGetKeyWithEmptyString() {
        String expectedKey = "";
        credentialProperty.setKey(expectedKey);
        assertEquals("", credentialProperty.getKey());
    }

    @Test
    void testSetAndGetKeyWithWhitespaceString() {
        String expectedKey = "   ";
        credentialProperty.setKey(expectedKey);
        assertEquals("   ", credentialProperty.getKey());
    }

    @Test
    void testSetAndGetKeyWithNull() {
        credentialProperty.setKey(null);
        assertEquals(null, credentialProperty.getKey());
    }

    @Test
    void testSetAndGetProvider() {
        String expectedProvider = "testProvider";
        credentialProperty.setProvider(expectedProvider);
        assertEquals(expectedProvider, credentialProperty.getProvider());
    }

    @Test
    void testSetAndGetProviderWithEmptyString() {
        String expectedProvider = "";
        credentialProperty.setProvider(expectedProvider);
        assertEquals("", credentialProperty.getProvider());
    }

    @Test
    void testSetAndGetProviderWithNull() {
        credentialProperty.setProvider(null);
        assertEquals(null, credentialProperty.getProvider());
    }

    @Test
    void testSetAndGetSubject() {
        String expectedSubject = "testSubject";
        credentialProperty.setSubject(expectedSubject);
        assertEquals(expectedSubject, credentialProperty.getSubject());
    }

    @Test
    void testSetAndGetSubjectWithWhitespaceString() {
        String expectedSubject = " ";
        credentialProperty.setSubject(expectedSubject);
        assertEquals(" ", credentialProperty.getSubject());
    }

    @Test
    void testSetAndGetSubjectWithNull() {
        credentialProperty.setSubject(null);
        assertEquals(null, credentialProperty.getSubject());
    }

    @Test
    void testSetAndGetAlg() {
        String expectedAlg = "HS256";
        credentialProperty.setAlg(expectedAlg);
        assertEquals(expectedAlg, credentialProperty.getAlg());
    }

    @Test
    void testSetAndGetAlgWithEmptyString() {
        String expectedAlg = "";
        credentialProperty.setAlg(expectedAlg);
        assertEquals("", credentialProperty.getAlg());
    }

    @Test
    void testSetAndGetAlgWithNull() {
        credentialProperty.setAlg(null);
        assertEquals(null, credentialProperty.getAlg());
    }

    @Test
    void testSetAndGetHmacSecret() {
        String expectedSecret = "secretValue";
        credentialProperty.setHmacSecret(expectedSecret);
        assertEquals(expectedSecret, credentialProperty.getHmacSecret());
    }

    @Test
    void testSetAndGetHmacSecretWithWhitespaceString() {
        String expectedSecret = "   ";
        credentialProperty.setHmacSecret(expectedSecret);
        assertEquals("   ", credentialProperty.getHmacSecret());
    }

    @Test
    void testSetAndGetHmacSecretWithNull() {
        credentialProperty.setHmacSecret(null);
        assertEquals(null, credentialProperty.getHmacSecret());
    }

    @Test
    void testSetAndGetExpiresHourStr() {
        String expectedExpires = "24";
        credentialProperty.setExpiresHourStr(expectedExpires);
        assertEquals(expectedExpires, credentialProperty.getExpiresHourStr());
    }

    @Test
    void testSetAndGetExpiresHourStrWithEmptyString() {
        String expectedExpires = "";
        credentialProperty.setExpiresHourStr(expectedExpires);
        assertEquals("", credentialProperty.getExpiresHourStr());
    }

    @Test
    void testSetAndGetExpiresHourStrWithNull() {
        credentialProperty.setExpiresHourStr(null);
        assertEquals(null, credentialProperty.getExpiresHourStr());
    }

    @Test
    void testConvertToIntExpiresWithValidNumber() {
        credentialProperty.setExpiresHourStr("12");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(12, result);
    }

    @Test
    void testConvertToIntExpiresWithZero() {
        credentialProperty.setExpiresHourStr("0");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(0, result);
    }

    @Test
    void testConvertToIntExpiresWithNegativeNumber() {
        credentialProperty.setExpiresHourStr("-5");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(-5, result);
    }

    @Test
    void testConvertToIntExpiresWithDashReturnsNull() {
        credentialProperty.setExpiresHourStr("-");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresWithInvalidNumberReturnsNull() {
        credentialProperty.setExpiresHourStr("invalid");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresWithWhitespaceStringReturnsNull() {
        credentialProperty.setExpiresHourStr("   ");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresWithEmptyStringReturnsNull() {
        credentialProperty.setExpiresHourStr("");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(null, result);
    }

    @Test
    void testConvertToIntExpiresWithMaxInteger() {
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MAX_VALUE));
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(Integer.MAX_VALUE, result);
    }

    @Test
    void testConvertToIntExpiresWithMinInteger() {
        credentialProperty.setExpiresHourStr(String.valueOf(Integer.MIN_VALUE));
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(Integer.MIN_VALUE, result);
    }

    @Test
    void testConvertToIntExpiresThrowsNullPointerExceptionWhenExpiresHourStrIsNull() {
        credentialProperty.setExpiresHourStr(null);
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }
}
