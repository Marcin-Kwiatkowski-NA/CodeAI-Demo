package com.bestpractice.api.common.property;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void givenValidKey_whenSetKey_thenGetKeyReturnsSameValue() {
        // GIVEN
        String key = "myKey";

        // WHEN
        credentialProperty.setKey(key);

        // THEN
        assertThat(credentialProperty.getKey()).isEqualTo(key);
    }

    @Test
    void givenValidProvider_whenSetProvider_thenGetProviderReturnsSameValue() {
        // GIVEN
        String provider = "myProvider";

        // WHEN
        credentialProperty.setProvider(provider);

        // THEN
        assertThat(credentialProperty.getProvider()).isEqualTo(provider);
    }

    @Test
    void givenValidSubject_whenSetSubject_thenGetSubjectReturnsSameValue() {
        // GIVEN
        String subject = "mySubject";

        // WHEN
        credentialProperty.setSubject(subject);

        // THEN
        assertThat(credentialProperty.getSubject()).isEqualTo(subject);
    }

    @Test
    void givenValidAlg_whenSetAlg_thenGetAlgReturnsSameValue() {
        // GIVEN
        String alg = "HS256";

        // WHEN
        credentialProperty.setAlg(alg);

        // THEN
        assertThat(credentialProperty.getAlg()).isEqualTo(alg);
    }

    @Test
    void givenValidHmacSecret_whenSetHmacSecret_thenGetHmacSecretReturnsSameValue() {
        // GIVEN
        String secret = "superSecret";

        // WHEN
        credentialProperty.setHmacSecret(secret);

        // THEN
        assertThat(credentialProperty.getHmacSecret()).isEqualTo(secret);
    }

    @Test
    void givenValidExpiresHourStr_whenSetExpiresHourStr_thenGetExpiresHourStrReturnsSameValue() {
        // GIVEN
        String expires = "24";

        // WHEN
        credentialProperty.setExpiresHourStr(expires);

        // THEN
        assertThat(credentialProperty.getExpiresHourStr()).isEqualTo(expires);
    }

    @Test
    void givenExpiresHourStrIsDash_whenConvertToIntExpires_thenReturnNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenExpiresHourStrIsValidNumber_whenConvertToIntExpires_thenReturnInteger() {
        // GIVEN
        credentialProperty.setExpiresHourStr("48");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(48);
    }

    @Test
    void givenExpiresHourStrIsZero_whenConvertToIntExpires_thenReturnZero() {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void givenExpiresHourStrIsNegativeNumber_whenConvertToIntExpires_thenReturnNegative() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-5");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(-5);
    }

    @Test
    void givenExpiresHourStrIsInvalidNumber_whenConvertToIntExpires_thenReturnNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenExpiresHourStrIsEmptyString_whenConvertToIntExpires_thenReturnNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenExpiresHourStrIsNull_whenConvertToIntExpires_thenThrowNullPointerException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }

    @Test
    void givenExpiresHourStrHasWhitespace_whenConvertToIntExpires_thenReturnNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr(" 10 ");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void givenPropertiesNotSet_whenGettersCalled_thenReturnNull() {
        // GIVEN
        // No properties set

        // WHEN
        String key = credentialProperty.getKey();
        String provider = credentialProperty.getProvider();
        String subject = credentialProperty.getSubject();
        String alg = credentialProperty.getAlg();
        String hmacSecret = credentialProperty.getHmacSecret();
        String expires = credentialProperty.getExpiresHourStr();

        // THEN
        assertThat(key).isNull();
        assertThat(provider).isNull();
        assertThat(subject).isNull();
        assertThat(alg).isNull();
        assertThat(hmacSecret).isNull();
        assertThat(expires).isNull();
    }
}
