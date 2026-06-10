package com.bestpractice.api.infrastrucuture.cache;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CacheRepositoryGeneratedAiTests {

    private CacheRepository cacheRepository;

    @BeforeEach
    void setUp() {
        cacheRepository = new CacheRepository();
    }

    @Test
    void testPutAndGetValue() {
        // GIVEN
        String key = "testKey";
        String value = "testValue";

        // WHEN
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(value, result);
    }

    @Test
    void testPutNullValue() {
        // GIVEN
        String key = "nullKey";
        Object value = null;

        // WHEN
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testRemoveValue() {
        // GIVEN
        String key = "removeKey";
        String value = "toRemove";
        cacheRepository.put(key, value);

        // WHEN
        cacheRepository.remove(key);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testContainsKeyAfterPut() {
        // GIVEN
        String key = "containsKey";
        String value = "value";
        cacheRepository.put(key, value);

        // WHEN
        boolean contains = cacheRepository.containsKey(key);

        // THEN
        assertTrue(contains);
    }

    @Test
    void testContainsKeyAfterRemove() {
        // GIVEN
        String key = "containsKeyRemoved";
        String value = "value";
        cacheRepository.put(key, value);
        cacheRepository.remove(key);

        // WHEN
        boolean contains = cacheRepository.containsKey(key);

        // THEN
        assertTrue(!contains);
    }

    @Test
    void testClearCache() {
        // GIVEN
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");

        // WHEN
        cacheRepository.clear();

        // THEN
        assertTrue(!cacheRepository.containsKey("key1"));
        assertTrue(!cacheRepository.containsKey("key2"));
    }

    @Test
    void testPutDuplicateKeyOverridesValue() {
        // GIVEN
        String key = "duplicateKey";
        String firstValue = "first";
        String secondValue = "second";
        cacheRepository.put(key, firstValue);

        // WHEN
        cacheRepository.put(key, secondValue);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(secondValue, result);
    }

    @Test
    void testGetNonExistingKeyReturnsNull() {
        // GIVEN
        String key = "nonExistingKey";

        // WHEN
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testCacheSizeAfterOperations() {
        // GIVEN
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");
        cacheRepository.remove("key1");

        // WHEN
        int size = cacheRepository.size();

        // THEN
        assertEquals(1, size);
    }
}
