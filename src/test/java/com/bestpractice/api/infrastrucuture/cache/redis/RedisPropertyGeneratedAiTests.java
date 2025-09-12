package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ExtendWith.withFactory;

@ExtendWith(withFactory(() -> new RedisPropertyExtension()))
class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void getSetHost_shouldReturnAndSetHostName() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The host property is set to "localhost"
        redisProperty.setHost("localhost");
        // THEN: The host property should be "localhost"
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void getSetPort_shouldReturnAndSetPortNumber() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The port property is set to 6379
        redisProperty.setPort(6379);
        // THEN: The port property should be 6379
        assertEquals(6379, redisProperty.getPort());
    }

    @Test
    void getPassword_shouldReturnPassword() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The password property is set to "mysecretpassword"
        redisProperty.setPassword("mysecretpassword");
        // THEN: The password property should be "mysecretpassword"
        assertEquals("mysecretpassword", redisProperty.getPassword());
    }

    @Test
    void setPassword_shouldSetPassword() {
        // GIVEN: A new RedisProperty instance
        // WHEN: The password property is set to "newsecret"
        redisProperty.setPassword("newsecret");
        // THEN: The password property should be "newsecret"
        assertEquals("newsecret", redisProperty.getPassword());
    }
}
