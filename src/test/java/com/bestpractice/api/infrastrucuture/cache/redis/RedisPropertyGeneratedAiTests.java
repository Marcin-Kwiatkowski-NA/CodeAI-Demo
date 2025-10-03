package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        // GIVEN: a RedisProperty instance and a password value
        String passwordValue = "securePassword"; // Security-sensitive: do not use real passwords

        // WHEN: setting the password
        redisProperty.setPassword(passwordValue);

        // THEN: the retrieved password should match the set value
        assertEquals(passwordValue, redisProperty.getPassword());
    }

    @Test
    void testDefaultValues() {
        // GIVEN: a new RedisProperty instance

        // WHEN: retrieving default values without setting them

        // THEN: defaults should be null for strings and 0 for int
        assertNull(redisProperty.getHost());
        assertNull(redisProperty.getPassword());
        assertEquals(0, redisProperty.getPort());
    }

    @Test
    void testSetPortWithNegativeValue() {
        // GIVEN: a RedisProperty instance and a negative port value
        int negativePort = -100;

        // WHEN: setting the port to a negative value
        redisProperty.setPort(negativePort);

        // THEN: the retrieved port should match the negative value (no validation in class)
        assertEquals(negativePort, redisProperty.getPort());
    }

    @Test
    void testSetHostWithNullValue() {
        // GIVEN: a RedisProperty instance and a null host value
        String nullHost = null;

        // WHEN: setting the host to null
        redisProperty.setHost(nullHost);

        // THEN: the retrieved host should be null
        assertNull(redisProperty.getHost());
    }

    @Test
    void testSetPasswordWithNullValue() {
        // GIVEN: a RedisProperty instance and a null password value
        String nullPassword = null;

        // WHEN: setting the password to null
        redisProperty.setPassword(nullPassword);

        // THEN: the retrieved password should be null
        assertNull(redisProperty.getPassword());
    }

    @Test
    void testSetPortThrowsExceptionForInvalidTypeUsingReflection() throws Exception {
        // GIVEN: a RedisProperty instance and reflection setup
        java.lang.reflect.Method method = RedisProperty.class.getMethod("setPort", int.class);

        // WHEN & THEN: invoking with wrong type should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            method.invoke(redisProperty, "invalidPort");
        });
    }

    @Test
    void testSetHostWithEmptyString() {
        // GIVEN: a RedisProperty instance and an empty host value
        String emptyHost = "";

        // WHEN: setting the host to empty string
        redisProperty.setHost(emptyHost);

        // THEN: the retrieved host should match the empty string
        assertEquals(emptyHost, redisProperty.getHost());
    }

    @Test
    void testSetPasswordWithEmptyString() {
        // GIVEN: a RedisProperty instance and an empty password value
        String emptyPassword = "";

        // WHEN: setting the password to empty string
        redisProperty.setPassword(emptyPassword);

        // THEN: the retrieved password should match the empty string
        assertEquals(emptyPassword, redisProperty.getPassword());
    }
}
