package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(RedisPropertyExtension.class)
class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void getHost() {
        // GIVEN: RedisProperty instance is created.
        // WHEN: getHost() method is called.
        // THEN: The host property is returned.
        String host = redisProperty.getHost();
        assertEquals("", host);
    }

    @Test
    void setHost() {
        // GIVEN: RedisProperty instance is created.
        // WHEN: setHost("localhost") is called.
        // THEN: The host property is set to "localhost".
        redisProperty.setHost("localhost");
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void getPort() {
        // GIVEN: RedisProperty instance is created.
        // WHEN: getPort() method is called.
        // THEN: The port property is returned.
        int port = redisProperty.getPort();
        assertEquals(0, port);
    }

    @Test
    void setPort() {
        // GIVEN: RedisProperty instance is created.
        // WHEN: setPort(6379) is called.
        // THEN: The port property is set to 6379.
        redisProperty.setPort(6379);
        assertEquals(6379, redisProperty.getPort());
    }

    @Test
    void getPassword() {
        // GIVEN: RedisProperty instance is created.
        // WHEN: getPassword() method is called.
        // THEN: The password property is returned.
        String password = redisProperty.getPassword();
        assertEquals("", password);
    }

    @Test
    void setPassword() {
        // GIVEN: RedisProperty instance is created.
        // WHEN: setPassword("mysecretpassword") is called.
        // THEN: The password property is set to "mysecretpassword".
        redisProperty.setPassword("mysecretpassword");
        assertEquals("mysecretpassword", redisProperty.getPassword());
    }
}
