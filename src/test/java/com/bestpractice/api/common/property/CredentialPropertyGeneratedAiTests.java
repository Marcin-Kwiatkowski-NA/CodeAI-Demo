package com.bestpractice.api.common.property;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        String key = getKey();
        assert key.equals("testKey");
    }

    @Test
    void getProvider() {
        String provider = getProvider();
        assert provider.equals("provider1");
    }

    @Test
    void getSubject() {
        String subject = getSubject();
        assert subject.equals("subject1");
    }

    @Test
    void getAlg() {
        String alg = getAlg();
        assert alg.equals("HS256");
    }

    @Test
    void getHmacSecret() {
        String hmacSecret = getHmacSecret();
        assert hmacSecret.equals("hmacSecret");
    }

    @Test
    void getExpiresHourStr() {
        String expiresHourStr = getExpiresHourStr();
        assert expiresHourStr.equals("1");
    }

    @Test
    void convertToIntExpires() {
        Integer expiresHour = convertToIntExpires();
        assert expiresHour == 1;

        Integer expiresHourNull = convertToIntExpires();
        assert expiresHourNull == null;

        Integer expiresHourException = convertToIntExpires();
        assert expiresHourException == null;
    }
}
