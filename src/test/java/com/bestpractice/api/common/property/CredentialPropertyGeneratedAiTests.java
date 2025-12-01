package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(credentialProperty.getKey()).isEqualTo(key);
    }

    // Test for getProvider and setProvider methods
    @Test
    void givenProviderValue_whenSetProvider_thenGetProviderReturnsSameValue() {
        // GIVEN
        String provider = "testProvider";

        // WHEN
        credentialProperty.setProvider(provider);

        // THEN
        assertThat(credentialProperty.getProvider()).isEqualTo(provider);
    }

    // Test for getSubject and setSubject methods
    @Test
    void givenSubjectValue_whenSetSubject_thenGetSubjectReturnsSameValue() {
        // GIVEN
        String subject = "testSubject";

        // WHEN
        credentialProperty.setSubject(subject);

        // THEN
        assertThat(credentialProperty.getSubject()).isEqualTo(subject);
    }

    // Test for getAlg and setAlg methods
    @Test
    void givenAlgValue_whenSetAlg_thenGetAlgReturnsSameValue() {
        // GIVEN
        String alg = "HS256";

        // WHEN
        credentialProperty.setAlg(alg);

        // THEN
        assertThat(credentialProperty.getAlg()).isEqualTo(alg);
    }

    // Test for getHmacSecret and setHmacSecret methods
    @Test
    void givenHmacSecretValue_whenSetHmacSecret_thenGetHmacSecretReturnsSameValue() {
        // GIVEN
        String hmacSecret = "testSecret";

        // WHEN
        credentialProperty.setHmacSecret(hmacSecret);

        // THEN
        assertThat(credentialProperty.getHmacSecret()).isEqualTo(hmacSecret);
    }

    // Test for getExpiresHourStr and setExpiresHourStr methods
    @Test
    void givenExpiresHourStrValue_whenSetExpiresHourStr_thenGetExpiresHourStrReturnsSameValue() {
        // GIVEN
        String expiresHourStr = "24";

        // WHEN
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // THEN
        assertThat(credentialProperty.getExpiresHourStr()).isEqualTo(expiresHourStr);
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
        assertThat(result).isEqualTo(24);
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
        assertThat(result).isNull();
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
        assertThat(result).isNull();
    }
}
