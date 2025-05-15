package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void getHost() {
        // GIVEN: A RedisProperty instance is created.
        // WHEN: The getHost() method is called.
        // THEN: The host property is returned.
        String host = redisProperty.getHost();
        assertEquals("", host);
    }

    @Test
    void setHost() {
        // GIVEN: A RedisProperty instance is created.
        // WHEN: The setHost() method is called with a host value.
        // THEN: The host property is set to the provided value.
        redisProperty.setHost("localhost");
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void getPort() {
        // GIVEN: A RedisProperty instance is created.
        // WHEN: The getPort() method is called.
        // THEN: The port property is returned.
        int port = redisProperty.getPort();
        assertEquals(0, port);
    }

    @Test
    void setPort() {
        // GIVEN: A RedisProperty instance is created.
        // WHEN: The setPort() method is called with a port value.
        // THEN: The port property is set to the provided value.
        redisProperty.setPort(6379);
        assertEquals(6379, redisProperty.getPort());
    }

    @Test
    void getPassword() {
        // GIVEN: A RedisProperty instance is created.
        // WHEN: The getPassword() method is called.
        // THEN: The password property is returned.
        String password = redisProperty.getPassword();
        assertEquals("", password);
    }

    @Test
    void setPassword() {
        // GIVEN: A RedisProperty instance is created.
        // WHEN: The setPassword() method is called with a password value.
        // THEN: The password property is set to the provided value.
        redisProperty.setPassword("mysecretpassword");
        assertEquals("mysecretpassword", redisProperty.getPassword());
    }
}
