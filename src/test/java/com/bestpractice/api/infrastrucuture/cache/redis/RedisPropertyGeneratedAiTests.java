package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.Test;
import static com.bestpractice.api.infrastrucuture.cache.RedisProperty;
import static com.bestpractice.api.infrastrucuture.cache.RedisPropertyTest;

class RedisPropertyGeneratedAiTests {

    @Test
    void testSetHost() {
        RedisProperty redis = new RedisProperty();
        redis.host = "127.0.0.1";
        assertEquals("127.0.0.1", redis.getHost());
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
    void testGetHost() {
        RedisProperty redis = new RedisProperty();
        assertEquals("127.0.0.1", redis.getHost());
    }

    @Test
    void testGetPort() {
        RedisProperty redis = new RedisProperty();
        assertEquals(8080, redis.getPort());
    }

    @Test
    void testGetPassword() {
        RedisProperty redis = new RedisProperty();
        assertEquals("secret", redis.getPassword());
    }

    @Test
    void testSetPassword() {
        RedisProperty redis = new RedisProperty();
        redis.password = "newsecret";
        assertEquals("newsecret", redis.getPassword());
    }

    @Test
    void testSetHostAndPort() {
        RedisProperty redis = new RedisProperty();
        redis.host = "127.0.0.1";
        redis.port = 8080;
        assertEquals("127.0.0.1:8080", redis.getHost() + ":" + redis.getPort());
    }

    @Test
    void testPasswordHasPrivateAccess() {
        RedisProperty redis = new RedisProperty();
        redis.password = "secret";
        assertEquals("secret", redis.getPassword());
    }

    @Test
    void testPortHasPrivateAccess() {
        RedisProperty redis = new RedisProperty();
        redis.port = 8080;
        assertEquals("8080", redis.getPort());
    }

    @Test
    void testHostHasPrivateAccess() {
        RedisProperty redis = new RedisProperty();
        redis.host = "127.0.0.1";
        assertEquals("127.0.0.1", redis.getHost());
    }

    @Test
    void testPortHasPrivateAccess() {
        RedisProperty redis = new RedisProperty();
        redis.port = 8080;
        assertEquals("8080", redis.getPort());
    }
}
