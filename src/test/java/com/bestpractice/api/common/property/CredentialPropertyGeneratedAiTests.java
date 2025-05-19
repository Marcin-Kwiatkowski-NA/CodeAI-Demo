package com.bestpractice.api.common.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void getKey() {
        // GIVEN a new CredentialProperty instance
        // WHEN the getKey() method is called
        // THEN the 'key' property should be returned
        String key = credentialProperty.getKey();
        assertEquals("key", key);
    }

    @Test
    void getProvider() {
        // GIVEN a new CredentialProperty instance
        // WHEN the getProvider() method is called
        // THEN the 'provider' property should be returned
        String provider = credentialProperty.getProvider();
        assertEquals("provider", provider);
    }

    @Test
    void getSubject() {
        // GIVEN a new CredentialProperty instance
        // WHEN the getSubject() method is called
        // THEN the 'subject' property should be returned
        String subject = credentialProperty.getSubject();
        assertEquals("subject", subject);
    }

    @Test
    void getAlg() {
        // GIVEN a new CredentialProperty instance
        // WHEN the getAlg() method is called
        // THEN the 'alg' property should be returned
        String alg = credentialProperty.getAlg();
        assertEquals("alg", alg);
    }

    @Test
    void getHmacSecret() {
        // GIVEN a new CredentialProperty instance
        // WHEN the getHmacSecret() method is called
        // THEN the 'hmacSecret' property should be returned
        String hmacSecret = credentialProperty.getHmacSecret();
        assertEquals("hmacSecret", hmacSecret);
    }

    @Test
    void getExpiresHourStr() {
        // GIVEN a new CredentialProperty instance
        // WHEN the getExpiresHourStr() method is called
        // THEN the 'expiresHourStr' property should be returned
        String expiresHourStr = credentialProperty.getExpiresHourStr();
        assertEquals("expiresHourStr", expiresHourStr);
    }

    @Test
    void convertToIntExpires_ValidHourStr() {
        // GIVEN a new CredentialProperty instance with a valid hour string
        // WHEN the convertToIntExpires() method is called
        // THEN the hour string should be parsed to an integer and returned
        credentialProperty.setExpiresHourStr("1");
        Integer expiresHour = credentialProperty.convertToIntExpires();
        assertEquals(1, expiresHour);
    }

    @Test
    void convertToIntExpires_InvalidHourStr() {
        // GIVEN a new CredentialProperty instance with an invalid hour string
        // WHEN the convertToIntExpires() method is called
        // THEN the method should return null
        credentialProperty.setExpiresHourStr("-");
        Integer expiresHour = credentialProperty.convertToIntExpires();
        assertNull(expiresHour);
    }

    @Test
    void convertToIntExpires_EmptyHourStr() {
        // GIVEN a new CredentialProperty instance with an empty hour string
        // WHEN the convertToIntExpires() method is called
        // THEN the method should return null
        credentialProperty.setExpiresHourStr("");
        Integer expiresHour = credentialProperty.convertToIntExpires();
        assertNull(expiresHour);
    }
}
