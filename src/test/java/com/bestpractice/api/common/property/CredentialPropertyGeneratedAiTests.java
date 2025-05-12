package com.bestpractice.api.common.property;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CredentialPropertyGeneratedAiTests {

    @BeforeEach
    void beforeEach() {
        // No changes needed here
    }

    @Test
    void getKey() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setKey("testKey");
        assertEquals("testKey", credentialProperty.getKey());
    }

    @Test
    void getProvider() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setProvider("testProvider");
        assertEquals("testProvider", credentialProperty.getProvider());
    }

    @Test
    void getSubject() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setSubject("testSubject");
        assertEquals("testSubject", credentialProperty.getSubject());
    }

    @Test
    void getAlg() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setAlg("testAlg");
        assertEquals("testAlg", credentialProperty.getAlg());
    }

    @Test
    void getHmacSecret() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setHmacSecret("testHmacSecret");
        assertEquals("testHmacSecret", credentialProperty.getHmacSecret());
    }

    @Test
    void getExpiresHourStr() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setExpiresHourStr("123");
        assertEquals("123", credentialProperty.getExpiresHourStr());
    }

    @Test
    void convertToIntExpires_ValidHourStr() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setExpiresHourStr("123");
        assertEquals(123, credentialProperty.convertToIntExpires());
    }

    @Test
    void convertToIntExpires_NullHourStr() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setExpiresHourStr("-");
        assertEquals(null, credentialProperty.convertToIntExpires());
    }

    @Test
    void convertToIntExpires_InvalidHourStr() {
        CredentialProperty credentialProperty = new CredentialProperty();
        credentialProperty.setExpiresHourStr("abc");
        assertEquals(null, credentialProperty.convertToIntExpires());
    }
}
