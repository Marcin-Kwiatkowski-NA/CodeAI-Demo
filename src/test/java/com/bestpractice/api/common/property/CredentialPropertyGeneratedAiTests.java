package com.bestpractice.api.common.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGenerator;

import static org.junit.jupiter.api.DisplayName.*;

@DisplayNameGenerator(value = DisplayNameGenerator.class)
class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void getKey() {
        // GIVEN a CredentialProperty object
        String key = "testKey";
        // WHEN the getKey() method is called
        String actualKey = credentialProperty.getKey();
        // THEN the actual key should be equal to the provided key
        assertEquals(key, actualKey);
    }

    @Test
    void getProvider() {
        // GIVEN a CredentialProperty object
        String provider = "provider1";
        // WHEN the getProvider() method is called
        String actualProvider = credentialProperty.getProvider();
        // THEN the actual provider should be equal to the provided provider
        assertEquals(provider, actualProvider);
    }

    @Test
    void getSubject() {
        // GIVEN a CredentialProperty object
        String subject = "subject1";
        // WHEN the getSubject() method is called
        String actualSubject = credentialProperty.getSubject();
        // THEN the actual subject should be equal to the provided subject
        assertEquals(subject, actualSubject);
    }

    @Test
    void getAlg() {
        // GIVEN a CredentialProperty object
        String alg = "HS256";
        // WHEN the getAlg() method is called
        String actualAlg = credentialProperty.getAlg();
        // THEN the actual alg should be equal to the provided alg
        assertEquals(alg, actualAlg);
    }

    @Test
    void getHmacSecret() {
        // GIVEN a CredentialProperty object
        String hmacSecret = "secret1";
        // WHEN the getHmacSecret() method is called
        String actualHmacSecret = credentialProperty.getHmacSecret();
        // THEN the actual hmacSecret should be equal to the provided hmacSecret
        assertEquals(hmacSecret, actualHmacSecret);
    }

    @Test
    void getExpiresHourStr() {
        // GIVEN a CredentialProperty object
        String expiresHourStr = "123";
        // WHEN the convertToIntExpires() method is called
        Integer expiresHour = credentialProperty.convertToIntExpires();
        // THEN the converted integer should be equal to the provided integer
        assertEquals(123, expiresHour);
    }

    @Test
    void convertToIntExpires_null() {
        // GIVEN a CredentialProperty object with expiresHourStr as "-"
        String expiresHourStr = "-";
        // WHEN the convertToIntExpires() method is called
        Integer expiresHour = credentialProperty.convertToIntExpires();
        // THEN the converted integer should be null
        assertNull(expiresHour);
    }

    @Test
    void convertToIntExpires_invalidFormat() {
        // GIVEN a CredentialProperty object with expiresHourStr as "abc"
        String expiresHourStr = "abc";
        // WHEN the convertToIntExpires() method is called
        Integer expiresHour = credentialProperty.convertToIntExpires();
        // THEN the converted integer should be null
        assertNull(expiresHour);
    }
}
