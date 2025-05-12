package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void getHost_shouldReturnHostValue() {
        // GIVEN: Initialize RedisProperty with a host value
        redisProperty.setHost("localhost");
        // WHEN: Retrieve the host value
        String host = redisProperty.getHost();
        // THEN: Verify that the host value is correctly returned
        assertEquals("localhost", host);
    }

    @Test
    void setHost_shouldSetHostValue() {
        // GIVEN: Initialize RedisProperty
        // WHEN: Set the host value
        redisProperty.setHost("127.0.0.1");
        // THEN: Verify that the host value is set correctly
        assertEquals("127.0.0.1", redisProperty.getHost());
    }

    @Test
    void getPort_shouldReturnPortValue() {
        // GIVEN: Initialize RedisProperty with a port value
        redisProperty.setPort(6379);
        // WHEN: Retrieve the port value
        int port = redisProperty.getPort();
        // THEN: Verify that the port value is correctly returned
        assertEquals(6379, port);
    }

    @Test
    void setPort_shouldSetPortValue() {
        // GIVEN: Initialize RedisProperty
        // WHEN: Set the port value
        redisProperty.setPort(6381);
        // THEN: Verify that the port value is set correctly
        assertEquals(6381, redisProperty.getPort());
    }

    @Test
    void getPassword_shouldReturnPasswordValue() {
        // GIVEN: Initialize RedisProperty with a password value
        redisProperty.setPassword("mysecretpassword");
        // WHEN: Retrieve the password value
        String password = redisProperty.getPassword();
        // THEN: Verify that the password value is correctly returned
        assertEquals("mysecretpassword", password);
    }

    @Test
    void setPassword_shouldSetPasswordValue() {
        // GIVEN: Initialize RedisProperty
        // WHEN: Set the password value
        redisProperty.setPassword("newsecret");
        // THEN: Verify that the password value is set correctly
        assertEquals("newsecret", redisProperty.getPassword());
    }
}
