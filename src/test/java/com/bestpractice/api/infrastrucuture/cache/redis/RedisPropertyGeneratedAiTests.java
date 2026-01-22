package com.bestpractice.api.infrastrucuture.cache.redis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
    void testDefaultValues() {
        // GIVEN a new RedisProperty instance
        // WHEN accessing its properties without setting them
        // THEN the default values should be null for host and password, and 0 for port
        assertEquals(null, redisProperty.getHost());
        assertEquals(null, redisProperty.getPassword());
        assertEquals(0, redisProperty.getPort());
    }

    @Test
    void testSetAndGetHost() {
        // GIVEN a host value
        String host = "localhost";
        // WHEN setting the host
        redisProperty.setHost(host);
        // THEN retrieving the host should return the set value
        assertEquals(host, redisProperty.getHost());
    }

    @Test
    void testSetAndGetPort() {
        // GIVEN a port value
        int port = 6379;
        // WHEN setting the port
        // THEN retrieving the port should return the set value
        redisProperty.setPort(port);
        assertEquals(port, redisProperty.getPort());
    }

    @Test
    void testSetAndGetPassword() {
        // GIVEN a password value
        String password = "secret";
        // WHEN setting the password
        redisProperty.setPassword(password);
        // THEN retrieving the password should return the set value
        assertEquals(password, redisProperty.getPassword());
    }

    @Test
    void testOverwriteProperties() {
        // GIVEN initial property values
        redisProperty.setHost("initialHost");
        redisProperty.setPort(1234);
        redisProperty.setPassword("initialPass");
        // WHEN overwriting the properties with new values
        redisProperty.setHost("newHost");
        redisProperty.setPort(5678);
        redisProperty.setPassword("newPass");
        // THEN the properties should reflect the new values
        assertEquals("newHost", redisProperty.getHost());
        assertEquals(5678, redisProperty.getPort());
        assertEquals("newPass", redisProperty.getPassword());
    }

    @Test
    void testEmptyHostAndPassword() {
        // GIVEN empty strings for host and password
        redisProperty.setHost("");
        redisProperty.setPassword("");
        // WHEN retrieving them
        // THEN they should be empty strings, not null
        assertEquals("", redisProperty.getHost());
        assertEquals("", redisProperty.getPassword());
    }

    @Test
    void testNegativePort() {
        // GIVEN a negative port value
        int negativePort = -1;
        // WHEN setting the negative port
        redisProperty.setPort(negativePort);
        // THEN retrieving the port should return the negative value (no validation in the class)
        assertEquals(negativePort, redisProperty.getPort());
    }
}
