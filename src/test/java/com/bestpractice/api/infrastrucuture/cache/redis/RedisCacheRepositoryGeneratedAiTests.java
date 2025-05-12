package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RedisCacheRepositoryTest {

    @Test
    void testCacheInitialization() {
        // Arrange
        RedisCacheRepository repository = new RedisCacheRepository();

        // Act
        Repository.setCache(repository);

        // Assert
        assertEquals(1, repository.getCacheSize());
    }

    @Test
    void testCacheGet() {
        // Arrange
        RedisCacheRepository repository = new RedisCacheRepository();

        // Act
        Repository.getCache("key");

        // Assert
        assertEquals("value")
    }

    @Test
    void testCacheSet() {
        // Arrange
        RedisCacheRepository repository = new RedisCacheRepository();

        // Act
        Repository.setCache("key", "value");

        // Assert
        assertEquals("value")
    }

    @Test
    void testCacheDelete() {
        // Arrange
        RedisCacheRepository repository = new RedisCacheRepository();

        // Act
        Repository.deleteCache("key");

        // Assert
        assertEquals(null);
    }

    @Test
    void testCacheSize() {
        // Arrange
        RedisCacheRepository repository = new RedisCacheRepository();

        // Act
        Repository.setCacheSize(10);

        // Assert
        assertEquals(10);
    }

    @Test
    void testCacheUpdate() {
        // Arrange
        RedisCacheRepository repository = new RedisCacheRepository();

        // Act
        Repository.updateCache("key", "new_value");

        // Assert
        assertEquals("new_value")
    }
}
