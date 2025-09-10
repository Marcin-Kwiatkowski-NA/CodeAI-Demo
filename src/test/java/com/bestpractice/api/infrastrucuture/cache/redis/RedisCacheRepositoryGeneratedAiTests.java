package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RedisCacheRepositoryGeneratedAiTests {

    private RedisCacheRepository redisCacheRepository;

    public RedisCacheRepositoryGeneratedAiTests() {
        redisCacheRepository = new RedisCacheRepository();
    }

    @Test
    void testGetInstance() {
        // GIVEN: No preconditions needed for getting an instance.
        // WHEN: Get an instance of RedisCacheRepository.
        RedisCacheRepository instance = redisCacheRepository.getInstance();
        // THEN: Verify that the instance is not null.
        assert instance != null;
    }

    @Test
    void testSetCache() {
        // GIVEN: A key and a value to set in the cache.
        String key = "testKey";
        String value = "testValue";
        // WHEN: Attempt to set the cache with the given key and value.
        redisCacheRepository.setCache(key, value);
        // THEN: Verify that the cache has been set successfully.
        assert redisCacheRepository.getCache(key) != null;
    }

    @Test
    void testGetCache() {
        // GIVEN: A key that exists in the cache.
        String key = "testKey";
        String value = "testValue";
        redisCacheRepository.setCache(key, value);
        // WHEN: Retrieve the value from the cache using the given key.
        String retrievedValue = redisCacheRepository.getCache(key);
        // THEN: Verify that the retrieved value matches the original value.
        assert retrievedValue.equals(value);
    }

    @Test
    void testDeleteCache() {
        // GIVEN: A key that exists in the cache.
        String key = "testKey";
        String value = "testValue";
        redisCacheRepository.setCache(key, value);
        // WHEN: Attempt to delete the cache with the given key.
        redisCacheRepository.deleteCache(key);
        // THEN: Verify that the cache no longer contains the key.
        assert redisCacheRepository.getCache(key) == null;
    }

    @Test
    void testSetCacheWithNullValue() {
        // GIVEN: A key.
        String key = "testKey";
        // WHEN: Attempt to set the cache with a null value.
        redisCacheRepository.setCache(key, null);
        // THEN: Verify that the cache has been set successfully with a null value.
        assert redisCacheRepository.getCache(key) == null;
    }

    @Test
    void testDeleteCacheWithNonExistentKey() {
        // GIVEN: A key that does not exist in the cache.
        String key = "testKey";
        // WHEN: Attempt to delete the cache with the given key.
        redisCacheRepository.deleteCache(key);
        // THEN: Verify that the cache no longer contains the key (should not throw an exception).
        assert redisCacheRepository.getCache(key) == null;
    }
}
