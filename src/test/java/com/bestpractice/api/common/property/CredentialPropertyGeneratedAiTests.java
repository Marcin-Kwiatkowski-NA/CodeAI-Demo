package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void testGetAndSetKey() {
        // GIVEN
        String expectedKey = "testKey";

        // WHEN
        credentialProperty.setKey(expectedKey);

        // THEN
        assertThat(credentialProperty.getKey()).isEqualTo(expectedKey);
    }

    @Test
    void testGetAndSetProvider() {
        // GIVEN
        String expectedProvider = "testProvider";

        // WHEN
        credentialProperty.setProvider(expectedProvider);

        // THEN
        assertThat(credentialProperty.getProvider()).isEqualTo(expectedProvider);
    }

    @Test
    void testGetAndSetSubject() {
        // GIVEN
        String expectedSubject = "testSubject";

        // WHEN
        credentialProperty.setSubject(expectedSubject);

        // THEN
        assertThat(credentialProperty.getSubject()).isEqualTo(expectedSubject);
    }

    @Test
    void testGetAndSetAlg() {
        // GIVEN
        String expectedAlg = "HS256";

        // WHEN
        credentialProperty.setAlg(expectedAlg);

        // THEN
        assertThat(credentialProperty.getAlg()).isEqualTo(expectedAlg);
    }

    @Test
    void testGetAndSetHmacSecret() {
        // GIVEN
        String expectedHmacSecret = "testSecret";

        // WHEN
        credentialProperty.setHmacSecret(expectedHmacSecret);

        // THEN
        assertThat(credentialProperty.getHmacSecret()).isEqualTo(expectedHmacSecret);
    }

    @Test
    void testGetAndSetExpiresHourStr() {
        // GIVEN
        String expectedExpiresHourStr = "24";

        // WHEN
        credentialProperty.setExpiresHourStr(expectedExpiresHourStr);

        // THEN
        assertThat(credentialProperty.getExpiresHourStr()).isEqualTo(expectedExpiresHourStr);
    }

    @Test
    void testConvertToIntExpiresWithValidValue() {
        // GIVEN
        String validExpiresHourStr = "12";
        credentialProperty.setExpiresHourStr(validExpiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(12);
    }

    @Test
    void testConvertToIntExpiresWithInvalidValue() {
        // GIVEN
        String invalidExpiresHourStr = "invalid";
        credentialProperty.setExpiresHourStr(invalidExpiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithDashValue() {
        // GIVEN
        String dashExpiresHourStr = "-";
        credentialProperty.setExpiresHourStr(dashExpiresHourStr);

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }
}
