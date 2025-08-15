package com.bestpractice.api.common.property;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@ExtendWith(DisplayNameGenerator.class)
class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    @DisplayName("getKey - Returns the key value")
    void getKey_ReturnsTheKeyValue() {
        credentialProperty.setKey("testKey");
        assertEquals("testKey", credentialProperty.getKey());
    }

    @Test
    @DisplayName("provider - Returns the provider value")
    void provider_ReturnsTheProviderValue() {
        credentialProperty.setProvider("testProvider");
        assertEquals("testProvider", credentialProperty.getProvider());
    }

    @Test
    @DisplayName("subject - Returns the subject value")
    void subject_ReturnsTheSubjectValue() {
        credentialProperty.setSubject("testSubject");
        assertEquals("testSubject", credentialProperty.getSubject());
    }

    @Test
    @DisplayName("alg - Returns the alg value")
    void alg_ReturnsTheAlgValue() {
        credentialProperty.setAlg("testAlg");
        assertEquals("testAlg", credentialProperty.getAlg());
    }

    @Test
    @DisplayName("hmacSecret - Returns the hmacSecret value")
    void hmacSecret_ReturnsTheHmacSecretValue() {
        credentialProperty.setHmacSecret("testHmacSecret");
        assertEquals("testHmacSecret", credentialProperty.getHmacSecret());
    }

    @Test
    @DisplayName("expiresHourStr - Returns the expiresHourStr value")
    void expiresHourStr_ReturnsTheExpiresHourStrValue() {
        credentialProperty.setExpiresHourStr("123");
        assertEquals("123", credentialProperty.getExpiresHourStr());

        credentialProperty.setExpiresHourStr("-");
        assertEquals(null, credentialProperty.convertToIntExpires());

        credentialProperty.setExpiresHourStr("abc");
        assertEquals(null, credentialProperty.convertToIntExpires());
    }

    @Test
    @DisplayName("convertToIntExpires - Returns the integer value of expiresHourStr")
    void convertToIntExpires_ReturnsTheIntegerValueOfExpiresHourStr() {
        credentialProperty.setExpiresHourStr("123");
        assertEquals(123, credentialProperty.convertToIntExpires());

        credentialProperty.setExpiresHourStr("-");
        assertEquals(null, credentialProperty.convertToIntExpires());

        credentialProperty.setExpiresHourStr("abc");
        assertEquals(null, credentialProperty.convertToIntExpires());
    }
}
