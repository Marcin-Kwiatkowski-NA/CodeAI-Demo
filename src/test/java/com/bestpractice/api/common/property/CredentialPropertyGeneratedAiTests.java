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

@ExtendWith(DisplayNameGenerator.class)
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
        String hmacSecret = "hmacSecretValue";
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
        Integer actualExpiresHourStr = credentialProperty.convertToIntExpires();
        // THEN the actual expiresHourStr should be equal to the provided expiresHourStr
        assertEquals(123, actualExpiresHourStr);
    }

    @Test
    void convertToIntExpires_null() {
        // GIVEN a CredentialProperty object with expiresHourStr set to "-"
        credentialProperty.setExpiresHourStr("-");
        // WHEN the convertToIntExpires() method is called
        Integer actualExpiresHourStr = credentialProperty.convertToIntExpires();
        // THEN the actual expiresHourStr should be null
        assertNull(actualExpiresHourStr);
    }

    @Test
    void convertToIntExpires_invalidFormat() {
        // GIVEN a CredentialProperty object with expiresHourStr set to "abc"
        credentialProperty.setExpiresHourStr("abc");
        // WHEN the convertToIntExpires() method is called
        Integer actualExpiresHourStr = credentialProperty.convertToIntExpires();
        // THEN the actual expiresHourStr should be null
        assertNull(actualExpiresHourStr);
    }
}
