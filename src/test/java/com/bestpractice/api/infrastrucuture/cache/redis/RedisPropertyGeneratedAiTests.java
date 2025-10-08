package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN: a RedisProperty instance and a host value
        String hostValue = "localhost";

        // WHEN: setting the host
        redisProperty.setHost(hostValue);

        // THEN: the retrieved host should match the set value
        assertEquals(hostValue, redisProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN: a RedisProperty instance and a port value
        int portValue = 6379;

        // WHEN: setting the port
        redisProperty.setPort(portValue);

        // THEN: the retrieved port should match the set value
        assertEquals(portValue, redisProperty.getPort());
    }

    @Test
    void testSetAndGetPassword() {
        // SECURITY-SENSITIVE: password handling
        // GIVEN: a RedisProperty instance and a password value
        String passwordValue = "securePassword";

        // WHEN: setting the password
        redisProperty.setPassword(passwordValue);

        // THEN: the retrieved password should match the set value
        assertEquals(passwordValue, redisProperty.getPassword());
    }

    @Test
    void testSetHostWithNullValue() {
        // GIVEN: a RedisProperty instance and a null host value
        String nullHost = null;

        // WHEN: setting the host to null
        redisProperty.setHost(nullHost);

        // THEN: the retrieved host should be null
        assertEquals(null, redisProperty.getHost());
    }

    @Test
    void testSetPasswordWithNullValue() {
        // SECURITY-SENSITIVE: password handling
        // GIVEN: a RedisProperty instance and a null password value
        String nullPassword = null;

        // WHEN: setting the password to null
        redisProperty.setPassword(nullPassword);

        // THEN: the retrieved password should be null
        assertEquals(null, redisProperty.getPassword());
    }

    @Test
    void testSetPortWithNegativeValue() {
        // GIVEN: a RedisProperty instance and a negative port value
        int negativePort = -1;

        // WHEN: setting the port to a negative value
        redisProperty.setPort(negativePort);

        // THEN: the retrieved port should match the set value
        assertEquals(negativePort, redisProperty.getPort());
    }

    @Test
    void testMultipleSettersAndGetters() {
        // GIVEN: a RedisProperty instance and multiple values
        String hostValue = "127.0.0.1";
        int portValue = 8080;
        String passwordValue = "pwd";

        // WHEN: setting all values
        redisProperty.setHost(hostValue);
        redisProperty.setPort(portValue);
        redisProperty.setPassword(passwordValue);

        // THEN: all retrieved values should match the set values
        assertEquals(hostValue, redisProperty.getHost());
        assertEquals(portValue, redisProperty.getPort());
        assertEquals(passwordValue, redisProperty.getPassword());
    }
}
