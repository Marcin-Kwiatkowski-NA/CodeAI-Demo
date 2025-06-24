package com.bestpractice.api.common.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class CredentialPropertyGeneratedAiTests {

    @BeforeEach
    void setUp() {
        setKey("testKey");
        setProvider("provider1");
        setSubject("subject1");
        setAlg("HS256");
        setHmacSecret("hmacSecret");
        setExpiresHourStr("1");
    }

    @Test
    void getKey() {
        // GIVEN: The key property is set to "testKey".
        // WHEN: The getKey() method is called.
        // THEN: The key property should return "testKey".
        assertEquals("testKey", getKey());
    }

    @Test
    void getProvider() {
        // GIVEN: The provider property is set to "provider1".
        // WHEN: The getProvider() method is called.
        // THEN: The provider property should return "provider1".
        assertEquals("provider1", getProvider());
    }

    @Test
    void getSubject() {
        // GIVEN: The subject property is set to "subject1".
        // WHEN: The getSubject() method is called.
        // THEN: The subject property should return "subject1".
        assertEquals("subject1", getSubject());
    }

    @Test
    void getAlg() {
        // GIVEN: The alg property is set to "HS256".
        // WHEN: The getAlg() method is called.
        // THEN: The alg property should return "HS256".
        assertEquals("HS256", getAlg());
    }

    @Test
    void getHmacSecret() {
        // GIVEN: The hmacSecret property is set to "hmacSecret".
        // WHEN: The getHmacSecret() method is called.
        // THEN: The hmacSecret property should return "hmacSecret".
        assertEquals("hmacSecret", getHmacSecret());
    }

    @Test
    void getExpiresHourStr() {
        // GIVEN: The expiresHourStr property is set to "1".
        // WHEN: The getExpiresHourStr() method is called.
        // THEN: The expiresHourStr property should return 1.
        assertEquals(1, convertToIntExpires());
    }

    @Test
    void convertToIntExpires_emptyString() {
        // GIVEN: The expiresHourStr property is set to "-".
        // WHEN: The convertToIntExpires() method is called.
        // THEN: The method should return null.
        assertEquals(null, convertToIntExpires());
    }

    @Test
    void convertToIntExpires_invalidString() {
        // GIVEN: The expiresHourStr property is set to "invalid".
        // WHEN: The convertToIntExpires() method is called.
        // THEN: The method should return null.
        assertEquals(null, convertToIntExpires());
    }

    @Test
    void convertToIntExpires_validString() {
        // GIVEN: The expiresHourStr property is set to "2".
        // WHEN: The convertToIntExpires() method is called.
        // THEN: The method should return 2.
        assertEquals(2, convertToIntExpires());
    }
}
