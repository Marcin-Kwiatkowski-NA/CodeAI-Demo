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

    @Test
    void testGettersAndSetters() {
        // GIVEN
        String key = "testKey";
        String provider = "testProvider";
        String subject = "testSubject";
        String alg = "HS256";
        String hmacSecret = "secret";
        String expiresHourStr = "12";

        // WHEN
        credentialProperty.setKey(key);
        credentialProperty.setProvider(provider);
        credentialProperty.setSubject(subject);
        credentialProperty.setAlg(alg);
        credentialProperty.setHmacSecret(hmacSecret);
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // THEN
        assertEquals(key, credentialProperty.getKey());
        assertEquals(provider, credentialProperty.getProvider());
        assertEquals(subject, credentialProperty.getSubject());
        assertEquals(alg, credentialProperty.getAlg());
        assertEquals(hmacSecret, credentialProperty.getHmacSecret());
        assertEquals(expiresHourStr, credentialProperty.getExpiresHourStr());
    }

    @Test
    void testConvertToIntExpiresWithValidNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("24");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertEquals(24, result);
    }

    @Test
    void testConvertToIntExpiresWithDashReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresWithInvalidNumberReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresWithEmptyStringReturnsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr("");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertNull(result);
    }

    @Test
    void testConvertToIntExpiresThrowsNullPointerExceptionWhenExpiresHourStrIsNull() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> credentialProperty.convertToIntExpires());
    }
}
