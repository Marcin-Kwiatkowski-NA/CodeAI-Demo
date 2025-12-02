package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void givenKeyValue_whenSetKey_thenGetKeyReturnsSameValue() {
        // GIVEN
        String key = "testKey";

        // WHEN
        credentialProperty.setKey(key);

        // THEN
        assertEquals(key, credentialProperty.getKey());
    }

    // Test for getProvider and setProvider
    @Test
    void givenProviderValue_whenSetProvider_thenGetProviderReturnsSameValue() {
        // GIVEN
        String provider = "testProvider";

        // WHEN
        credentialProperty.setProvider(provider);

        // THEN
        assertEquals(provider, credentialProperty.getProvider());
    }

    // Test for getSubject and setSubject
    @Test
    void givenSubjectValue_whenSetSubject_thenGetSubjectReturnsSameValue() {
        // GIVEN
        String subject = "testSubject";

        // WHEN
        credentialProperty.setSubject(subject);

        // THEN
        assertEquals(subject, credentialProperty.getSubject());
    }

    // Test for getAlg and setAlg
    @Test
    void givenAlgValue_whenSetAlg_thenGetAlgReturnsSameValue() {
        // GIVEN
        String alg = "HS256";

        // WHEN
        credentialProperty.setAlg(alg);

        // THEN
        assertEquals(alg, credentialProperty.getAlg());
    }

    // Test for getHmacSecret and setHmacSecret
    @Test
    void givenHmacSecretValue_whenSetHmacSecret_thenGetHmacSecretReturnsSameValue() {
        // GIVEN
        String hmacSecret = "testSecret";

        // WHEN
        credentialProperty.setHmacSecret(hmacSecret);

        // THEN
        assertEquals(hmacSecret, credentialProperty.getHmacSecret());
    }

    // Test for getExpiresHourStr and setExpiresHourStr
    @Test
    void givenExpiresHourStrValue_whenSetExpiresHourStr_thenGetExpiresHourStrReturnsSameValue() {
        // GIVEN
        String expiresHourStr = "24";

        // WHEN
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // THEN
        assertEquals(expiresHourStr, credentialProperty.getExpiresHourStr());
    }

    // Test for convertToIntExpires with valid integer string
    @Test
    void givenValidIntegerString_whenConvertToIntExpires_thenReturnsParsedInteger() {
        // GIVEN
        credentialProperty.setExpiresHourStr("24");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(24, result);
    }

    // Test for convertToIntExpires with "-" string
    @Test
    void givenDashString_whenConvertToIntExpires_thenReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    // Test for convertToIntExpires with invalid integer string
    @Test
    void givenInvalidIntegerString_whenConvertToIntExpires_thenReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    // Test for convertToIntExpires with exception handling
    @Test
    void givenInvalidIntegerString_whenConvertToIntExpires_thenHandlesNumberFormatExceptionGracefully() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result); // Exception is caught and null is returned
    }

    // Test for convertToIntExpires with null expiresHourStr
    @Test
    void givenNullExpiresHourStr_whenConvertToIntExpires_thenThrowsNullPointerException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }
}
