package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.ExtensionPurpose;
import org.junit.jupiter.api.TestWith;

import java.util.Objects;

public class RedisCacheRepositoryTest {

    @TestWith(RedisCacheRepository)
    void testCacheInitialization() {
        RedisCacheRepository repository = new RedisCacheRepository();
        String result = repository.getCacheData();
        assertEquals("some data", result);
    }

    @TestWith(RedisCacheRepository)
    void testCacheGetData() {
        RedisCacheRepository repository = new RedisCacheRepository();
        String result = repository.getCacheData();
        assertEquals("some data", result);
    }

    @TestWith(RedisCacheRepository)
    void testCacheSetCacheValue() {
        RedisCacheRepository repository = new RedisCacheRepository();
        repository.setCacheValue("some value");
        assertEquals("some value", repository.getCacheData());
    }

    @TestWith(RedisCacheRepository)
    void testCacheDeleteCacheValue() {
        RedisCacheRepository repository = new RedisCacheRepository();
        repository.deleteCacheValue("some value");
        assertEquals("null", repository.getCacheData());
    }

    @TestWith(RedisCacheRepository)
    void testCacheGetCacheValue() {
        RedisCacheRepository repository = new RedisCacheRepository();
        String result = repository.getCacheData();
        assertEquals("null", result);
    }

    @TestWith(RedisCacheRepository)
    void testCacheSetCacheValue() {
        RedisCacheRepository repository = new RedisCacheRepository();
        repository.setCacheValue("some value");
        assertEquals("some value", repository.getCacheData());
    }

    @Test
    void testCacheDeleteCacheValue() {
        RedisCacheRepository repository = new RedisCacheRepository();
        repository.deleteCacheValue("some value");
        assertEquals("null", repository.getCacheData());
    }
}
