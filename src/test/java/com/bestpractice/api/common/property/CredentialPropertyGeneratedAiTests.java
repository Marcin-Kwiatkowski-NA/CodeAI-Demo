package com.bestpractice.api.common.property;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@MockitoExtension
public class CredentialPropertyGeneratedAiTests {

    @InjectMocks
    private CredentialProperty credentialProperty;

    @Mock
    private CredentialProperty mockCredentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void testGetKey() {
        // GIVEN
        String expectedKey = "testKey";
        credentialProperty.setKey(expectedKey);

        // WHEN
        String actualKey = credentialProperty.getKey();

        // THEN
        assertThat(actualKey).isEqualTo(expectedKey);
    }

    @Test
    void testSetKey() {
        // GIVEN
        String expectedKey = "testKey";

        // WHEN
        credentialProperty.setKey(expectedKey);

        // THEN
        assertThat(credentialProperty.getKey()).isEqualTo(expectedKey);
    }

    @Test
    void testGetProvider() {
        // GIVEN
        String expectedProvider = "testProvider";
        credentialProperty.setProvider(expectedProvider);

        // WHEN
        String actualProvider = credentialProperty.getProvider();

        // THEN
        assertThat(actualProvider).isEqualTo(expectedProvider);
    }

    @Test
    void testSetProvider() {
        // GIVEN
        String expectedProvider = "testProvider";

        // WHEN
        credentialProperty.setProvider(expectedProvider);

        // THEN
        assertThat(credentialProperty.getProvider()).isEqualTo(expectedProvider);
    }

    @Test
    void testGetSubject() {
        // GIVEN
        String expectedSubject = "testSubject";
        credentialProperty.setSubject(expectedSubject);

        // WHEN
        String actualSubject = credentialProperty.getSubject();

        // THEN
        assertThat(actualSubject).isEqualTo(expectedSubject);
    }

    @Test
    void testSetSubject() {
        // GIVEN
        String expectedSubject = "testSubject";

        // WHEN
        credentialProperty.setSubject(expectedSubject);

        // THEN
        assertThat(credentialProperty.getSubject()).isEqualTo(expectedSubject);
    }

    @Test
    void testGetAlg() {
        // GIVEN
        String expectedAlg = "testAlg";
        credentialProperty.setAlg(expectedAlg);

        // WHEN
        String actualAlg = credentialProperty.getAlg();

        // THEN
        assertThat(actualAlg).isEqualTo(expectedAlg);
    }

    @Test
    void testSetAlg() {
        // GIVEN
        String expectedAlg = "testAlg";

        // WHEN
        credentialProperty.setAlg(expectedAlg);

        // THEN
        assertThat(credentialProperty.getAlg()).isEqualTo(expectedAlg);
    }

    @Test
    void testGetHmacSecret() {
        // GIVEN
        String expectedHmacSecret = "testHmacSecret";
        credentialProperty.setHmacSecret(expectedHmacSecret);

        // WHEN
        String actualHmacSecret = credentialProperty.getHmacSecret();

        // THEN
        assertThat(actualHmacSecret).isEqualTo(expectedHmacSecret);
    }

    @Test
    void testSetHmacSecret() {
        // GIVEN
        String expectedHmacSecret = "testHmacSecret";

        // WHEN
        credentialProperty.setHmacSecret(expectedHmacSecret);

        // THEN
        assertThat(credentialProperty.getHmacSecret()).isEqualTo(expectedHmacSecret);
    }

    @Test
    void testGetExpiresHourStr() {
        // GIVEN
        String expectedExpiresHourStr = "testExpiresHourStr";
        credentialProperty.setExpiresHourStr(expectedExpiresHourStr);

        // WHEN
        String actualExpiresHourStr = credentialProperty.getExpiresHourStr();

        // THEN
        assertThat(actualExpiresHourStr).isEqualTo(expectedExpiresHourStr);
    }

    @Test
    void testSetExpiresHourStr() {
        // GIVEN
        String expectedExpiresHourStr = "testExpiresHourStr";

        // WHEN
        credentialProperty.setExpiresHourStr(expectedExpiresHourStr);

        // THEN
        assertThat(credentialProperty.getExpiresHourStr()).isEqualTo(expectedExpiresHourStr);
    }

    @Test
    void testConvertToIntExpires() {
        // GIVEN
        String expiresHourStr = "12";
        when(mockCredentialProperty.getExpiresHourStr()).thenReturn(expiresHourStr);

        // WHEN
        Integer actualExpires = mockCredentialProperty.convertToIntExpires();

        // THEN
        assertThat(actualExpires).isEqualTo(12);
    }

    @Test
    void testConvertToIntExpiresWithNegative() {
        // GIVEN
        String expiresHourStr = "-";
        when(mockCredentialProperty.getExpiresHourStr()).thenReturn(expiresHourStr);

        // WHEN
        Integer actualExpires = mockCredentialProperty.convertToIntExpires();

        // THEN
        assertThat(actualExpires).isNull();
    }
}
