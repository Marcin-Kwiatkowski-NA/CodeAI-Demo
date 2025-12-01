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

public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    // Test for getKey and setKey
    @Test
    void givenKey_whenSetKey_thenGetKeyReturnsSameValue() {
        // GIVEN
        String key = "testKey";

        // WHEN
        credentialProperty.setKey(key);

        // THEN
        assertEquals("testKey", credentialProperty.getKey());
    }

    // Test for getProvider and setProvider
    @Test
    void givenProvider_whenSetProvider_thenGetProviderReturnsSameValue() {
        // GIVEN
        String provider = "testProvider";

        // WHEN
        credentialProperty.setProvider(provider);

        // THEN
        assertEquals("testProvider", credentialProperty.getProvider());
    }

    // Test for getSubject and setSubject
    @Test
    void givenSubject_whenSetSubject_thenGetSubjectReturnsSameValue() {
        // GIVEN
        String subject = "testSubject";

        // WHEN
        credentialProperty.setSubject(subject);

        // THEN
        assertEquals("testSubject", credentialProperty.getSubject());
    }

    // Test for getAlg and setAlg
    @Test
    void givenAlg_whenSetAlg_thenGetAlgReturnsSameValue() {
        // GIVEN
        String alg = "testAlg";

        // WHEN
        credentialProperty.setAlg(alg);

        // THEN
        assertEquals("testAlg", credentialProperty.getAlg());
    }

    // Test for getHmacSecret and setHmacSecret
    @Test
    void givenHmacSecret_whenSetHmacSecret_thenGetHmacSecretReturnsSameValue() {
        // GIVEN
        String hmacSecret = "testHmacSecret";

        // WHEN
        credentialProperty.setHmacSecret(hmacSecret);

        // THEN
        assertEquals("testHmacSecret", credentialProperty.getHmacSecret());
    }

    // Test for getExpiresHourStr and setExpiresHourStr
    @Test
    void givenExpiresHourStr_whenSetExpiresHourStr_thenGetExpiresHourStrReturnsSameValue() {
        // GIVEN
        String expiresHourStr = "24";

        // WHEN
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // THEN
        assertEquals("24", credentialProperty.getExpiresHourStr());
    }

    // Test for convertToIntExpires with valid integer string
    @Test
    void givenValidExpiresHourStr_whenConvertToIntExpires_thenReturnsIntegerValue() {
        // GIVEN
        credentialProperty.setExpiresHourStr("24");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(24, result);
    }

    // Test for convertToIntExpires with "-" string
    @Test
    void givenDashExpiresHourStr_whenConvertToIntExpires_thenReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    // Test for convertToIntExpires with invalid integer string
    @Test
    void givenInvalidExpiresHourStr_whenConvertToIntExpires_thenReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    // Test for convertToIntExpires with exception handling
    @Test
    void givenInvalidExpiresHourStr_whenConvertToIntExpires_thenHandlesNumberFormatExceptionGracefully() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result); // The method handles the exception and returns null
    }

    // Test for convertToIntExpires with null expiresHourStr
    @Test
    void givenNullExpiresHourStr_whenConvertToIntExpires_thenThrowsNullPointerException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> {
            credentialProperty.convertToIntExpires();
        });
    }
}
