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

    private CredentialProperty property;

    @BeforeEach
    void setUp() {
        property = new CredentialProperty();
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN
        String key = "myKey";
        String provider = "myProvider";
        String subject = "mySubject";
        String alg = "HS256";
        String hmacSecret = "secret";
        String expiresHourStr = "12";

        // WHEN
        property.setKey(key);
        property.setProvider(provider);
        property.setSubject(subject);
        property.setAlg(alg);
        property.setHmacSecret(hmacSecret);
        property.setExpiresHourStr(expiresHourStr);

        // THEN
        assertThat(property.getKey()).isEqualTo(key);
        assertThat(property.getProvider()).isEqualTo(provider);
        assertThat(property.getSubject()).isEqualTo(subject);
        assertThat(property.getAlg()).isEqualTo(alg);
        assertThat(property.getHmacSecret()).isEqualTo(hmacSecret);
        assertThat(property.getExpiresHourStr()).isEqualTo(expiresHourStr);
    }

    @Test
    void testGettersReturnNullWhenNotSet() {
        // GIVEN
        // No values set

        // WHEN
        String key = property.getKey();
        String provider = property.getProvider();
        String subject = property.getSubject();
        String alg = property.getAlg();
        String hmacSecret = property.getHmacSecret();
        String expiresHourStr = property.getExpiresHourStr();

        // THEN
        assertThat(key).isNull();
        assertThat(provider).isNull();
        assertThat(subject).isNull();
        assertThat(alg).isNull();
        assertThat(hmacSecret).isNull();
        assertThat(expiresHourStr).isNull();
    }

    @Test
    void testConvertToIntExpiresWithValidNumber() {
        // GIVEN
        property.setExpiresHourStr("24");

        // WHEN
        Integer result = property.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(24);
    }

    @Test
    void testConvertToIntExpiresWithZero() {
        // GIVEN
        property.setExpiresHourStr("0");

        // WHEN
        Integer result = property.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testConvertToIntExpiresWithNegativeNumber() {
        // GIVEN
        property.setExpiresHourStr("-5");

        // WHEN
        Integer result = property.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(-5);
    }

    @Test
    void testConvertToIntExpiresWithDash() {
        // GIVEN
        property.setExpiresHourStr("-");

        // WHEN
        Integer result = property.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithInvalidNumber() {
        // GIVEN
        property.setExpiresHourStr("invalid");

        // WHEN
        Integer result = property.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithWhitespace() {
        // GIVEN
        property.setExpiresHourStr(" 12");

        // WHEN
        Integer result = property.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithNullThrowsException() {
        // GIVEN
        property.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> property.convertToIntExpires());
    }
}
