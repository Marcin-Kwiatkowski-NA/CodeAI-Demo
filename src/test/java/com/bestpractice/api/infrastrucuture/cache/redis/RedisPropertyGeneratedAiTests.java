package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.ExtensionTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static com.bestpractice.api.infrastrucuture.cache.RedisProperty.RedisProperty;
import static com.bestpractice.api.infrastrucuture.cache.RedisProperty.RedisPropertyTest;

class RedisPropertyGeneratedAiTests {

    @Test
    void testSetHost() {
        RedisProperty redis = new RedisProperty();
        redis.host = "localhost";
        assertEquals("localhost", redis.getHost());
    }

    @Test
    void testSetPort() {
        RedisProperty redis = new RedisProperty();
        redis.port = 8080;
        assertEquals("8080", redis.getPort());
    }

    @Test
    void testSetPassword() {
        RedisProperty redis = new RedisProperty();
        redis.password = "secret";
        assertEquals("secret", redis.getPassword());
    }

    @Test
    void testassertEquals() {
        RedisProperty redis = new RedisProperty();
        redis.host = "localhost";
        redis.port = 8080;
        redis.password = "secret";
        assertEquals("localhost", redis.getHost());
        assertEquals("8080", redis.getPort());
        assertEquals("secret", redis.getPassword());
    }

    @Test
    void testassertEquals() {
        RedisProperty redis = new RedisProperty();
        redis.host = "localhost";
        redis.port = 8080;
        redis.password = "secret";
        redis.getHost() = "localhost";
        redis.getPort() = "8080";
        redis.getPassword() = "secret";
        assertEquals("localhost", redis.getHost());
        assertEquals("8080", redis.getPort());
        assertEquals("secret", redis.getPassword());
    }
}
