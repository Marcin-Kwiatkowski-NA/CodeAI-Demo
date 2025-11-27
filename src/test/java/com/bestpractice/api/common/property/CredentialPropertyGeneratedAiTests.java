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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    // Test for getKey and setKey methods
    @Test
    void givenKeyValue_whenSetKey_thenGetKeyReturnsSameValue() {
        // GIVEN
        String key = "testKey";

        // WHEN
        credentialProperty.setKey(key);

        // THEN
        assertEquals("testKey", credentialProperty.getKey());
    }

    // Test for getProvider and setProvider methods
    @Test
    void givenProviderValue_whenSetProvider_thenGetProviderReturnsSameValue() {
        // GIVEN
        String provider = "testProvider";

        // WHEN
        credentialProperty.setProvider(provider);

        // THEN
        assertEquals("testProvider", credentialProperty.getProvider());
    }

    // Test for getSubject and setSubject methods
    @Test
    void givenSubjectValue_whenSetSubject_thenGetSubjectReturnsSameValue() {
        // GIVEN
        String subject = "testSubject";

        // WHEN
        credentialProperty.setSubject(subject);

        // THEN
        assertEquals("testSubject", credentialProperty.getSubject());
    }

    // Test for getAlg and setAlg methods
    @Test
    void givenAlgValue_whenSetAlg_thenGetAlgReturnsSameValue() {
        // GIVEN
        String alg = "HS256";

        // WHEN
        credentialProperty.setAlg(alg);

        // THEN
        assertEquals("HS256", credentialProperty.getAlg());
    }

    // Test for getHmacSecret and setHmacSecret methods
    @Test
    void givenHmacSecretValue_whenSetHmacSecret_thenGetHmacSecretReturnsSameValue() {
        // GIVEN
        String hmacSecret = "testSecret";

        // WHEN
        credentialProperty.setHmacSecret(hmacSecret);

        // THEN
        assertEquals("testSecret", credentialProperty.getHmacSecret());
    }

    // Test for getExpiresHourStr and setExpiresHourStr methods
    @Test
    void givenExpiresHourStrValue_whenSetExpiresHourStr_thenGetExpiresHourStrReturnsSameValue() {
        // GIVEN
        String expiresHourStr = "24";

        // WHEN
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // THEN
        assertEquals("24", credentialProperty.getExpiresHourStr());
    }

    // Test for convertToIntExpires method with valid integer string
    @Test
    void givenValidExpiresHourStr_whenConvertToIntExpires_thenReturnsIntegerValue() {
        // GIVEN
        String expiresHourStr = "24";
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(24, result);
    }

    // Test for convertToIntExpires method with "-" string
    @Test
    void givenDashExpiresHourStr_whenConvertToIntExpires_thenReturnsNull() {
        // GIVEN
        String expiresHourStr = "-";
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    // Test for convertToIntExpires method with invalid integer string
    @Test
    void givenInvalidExpiresHourStr_whenConvertToIntExpires_thenReturnsNull() {
        // GIVEN
        String expiresHourStr = "invalid";
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    // Test for convertToIntExpires method with null expiresHourStr
    @Test
    void givenNullExpiresHourStr_whenConvertToIntExpires_thenThrowsNullPointerException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }
}
