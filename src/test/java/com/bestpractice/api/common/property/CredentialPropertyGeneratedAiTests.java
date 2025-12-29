package com.bestpractice.api.common.property;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(CredentialProperty.class)
public class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void givenValidKey_whenSetThenKeyIsSet() {
        // GIVEN
        String key = "test-key";
        credentialProperty.setKey(key);

        // WHEN
        String actualKey = credentialProperty.getKey();

        // THEN
        assertThat(actualKey).isEqualTo(key);
    }

    @Test
    void givenNullKey_whenSetThenKeyIsNull() {
        // GIVEN
        credentialProperty.setKey(null);

        // WHEN
        String actualKey = credentialProperty.getKey();

        // THEN
        assertThat(actualKey).isNull();
    }

    @Test
    void givenEmptyKey_whenSetThenKeyIsEmpty() {
        // GIVEN
        credentialProperty.setKey("");

        // WHEN
        String actualKey = credentialProperty.getKey();

        // THEN
        assertThat(actualKey).isEmpty();
    }

    @Test
    void givenValidProvider_whenSetThenProviderIsSet() {
        // GIVEN
        String provider = "provider-test";
        credentialProperty.setProvider(provider);

        // WHEN
        String actualProvider = credentialProperty.getProvider();

        // THEN
        assertThat(actualProvider).isEqualTo(provider);
    }

    @Test
    void givenValidSubject_whenSetThenSubjectIsSet() {
        // GIVEN
        String subject = "subject-test";
        credentialProperty.setSubject(subject);

        // WHEN
        String actualSubject = credentialProperty.getSubject();

        // THEN
        assertThat(actualSubject).isEqualTo(subject);
    }

    @Test
    void givenValidAlg_whenSetThenAlgIsSet() {
        // GIVEN
        String alg = "HS256";
        credentialProperty.setAlg(alg);

        // WHEN
        String actualAlg = credentialProperty.getAlg();

        // THEN
        assertThat(actualAlg).isEqualTo(alg);
    }

    @Test
    void givenValidHmacSecret_whenSetThenHmacSecretIsSet() {
        // GIVEN
        String hmacSecret = "secret-123";
        credentialProperty.setHmacSecret(hmacSecret);

        // WHEN
        String actualHmacSecret = credentialProperty.getHmacSecret();

        // THEN
        assertThat(actualHmacSecret).isEqualTo(hmacSecret);
    }

    @Test
    void givenValidExpiresHourStr_whenSetThenConvertToIntExpiresReturnsParsedInteger() {
        // GIVEN
        credentialProperty.setExpiresHourStr("24");

        // WHEN
        Integer actual = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(actual).isEqualTo(24);
    }

    @Test
    void givenMinusExpiresHourStr_whenSetThenConvertToIntExpiresReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

        // WHEN
        Integer actual = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void givenInvalidExpiresHourStr_whenSetThenConvertToIntExpiresReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("abc");

        // WHEN
        Integer actual = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void givenEmptyExpiresHourStr_whenSetThenConvertToIntExpiresReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("");

        // WHEN
        Integer actual = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void givenNullExpiresHourStr_whenSetThenConvertToIntExpiresReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN
        Integer actual = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(actual).isNull();
    }

    @Test
    void givenNonNumericExpiresHourStr_whenSetThenConvertToIntExpiresReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("  3  ");

        // WHEN
        Integer actual = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(actual).isNull();
    }
}