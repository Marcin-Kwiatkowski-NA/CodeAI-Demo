package com.bestpractice.api.common.property;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.common.property.CredentialProperty;

@ExtendWith(MockitoExtension.class)
class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    // GIVEN: A new CredentialProperty instance is created.
    // WHEN: The getKey() method is called.
    // THEN: The key property is returned.
    @Test
    void testGetKey() {
        String key = "testKey";
        credentialProperty.setKey(key);
        assertEquals(key, credentialProperty.getKey());
    }

    // GIVEN: The expiresHourStr property is set to "1".
    // WHEN: The convertToIntExpires() method is called.
    // THEN: The method returns 1.
    @Test
    void testConvertToIntExpires_ValidHour() {
        credentialProperty.setExpiresHourStr("1");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(1, result);
    }

    // GIVEN: The expiresHourStr property is set to "-".
    // WHEN: The convertToIntExpires() method is called.
    // THEN: The method returns null.
    @Test
    void testConvertToIntExpires_MinusHour() {
        credentialProperty.setExpiresHourStr("-");
        Integer result = credentialProperty.convertToIntExpires();
        assertNull(result);
    }

    // GIVEN: The expiresHourStr property is set to "invalid".
    // WHEN: The convertToIntExpires() method is called.
    // THEN: The method returns null.
    @Test
    void testConvertToIntExpires_InvalidHour() {
        credentialProperty.setExpiresHourStr("invalid");
        Integer result = credentialProperty.convertToIntExpires();
        assertNull(result);
    }

    // GIVEN: The provider property is set to "providerName".
    // WHEN: The getProvider() method is called.
    // THEN: The provider property is returned.
    @Test
    void testGetProvider() {
        String provider = "testProvider";
        credentialProperty.setProvider(provider);
        assertEquals(provider, credentialProperty.getProvider());
    }

    // GIVEN: The subject property is set to "subjectValue".
    // WHEN: The getSubject() method is called.
    // THEN: The subject property is returned.
    @Test
    void testGetSubject() {
        String subject = "testSubject";
        credentialProperty.setSubject(subject);
        assertEquals(subject, credentialProperty.getSubject());
    }

    // GIVEN: The alg property is set to "HMACSHA256".
    // WHEN: The getAlg() method is called.
    // THEN: The alg property is returned.
    @Test
    void testGetAlg() {
        String alg = "HMACSHA256";
        credentialProperty.setAlg(alg);
        assertEquals(alg, credentialProperty.getAlg());
    }

    // GIVEN: The hmacSecret property is set to "secretValue".
    // WHEN: The getHmacSecret() method is called.
    // THEN: The hmacSecret property is returned.
    @Test
    void testGetHmacSecret() {
        String hmacSecret = "testSecret";
        credentialProperty.setHmacSecret(hmacSecret);
        assertEquals(hmacSecret, credentialProperty.getHmacSecret());
    }

    // GIVEN: The expiresHourStr property is set to "23".
    // WHEN: The convertToIntExpires() method is called.
    // THEN: The method returns 23.
    @Test
    void testConvertToIntExpires_Hour23() {
        credentialProperty.setExpiresHourStr("23");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(23, result);
    }
}
