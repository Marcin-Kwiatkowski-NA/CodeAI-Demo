package com.bestpractice.api.infrastrucuture.cache;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
        // Reset or initialize any shared state before each test
    }

    @Test
    void givenEmptyCache_whenGet_thenReturnsNull() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();

        // WHEN
        Object result = cacheRepository.get("nonexistent");

        // THEN
        assertEquals(null, result);
    }

    @Test
    void givenKeyAndValue_whenPut_thenValueCanBeRetrieved() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();
        String key = "testKey";
        String value = "testValue";

        // WHEN
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(value, result);
    }

    @Test
    void givenNullKey_whenPut_thenThrowsException() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();

        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> cacheRepository.put(null, "value"));
    }

    @Test
    void givenNullValue_whenPut_thenStoresNullValue() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();
        String key = "nullValueKey";

        // WHEN
        cacheRepository.put(key, null);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void givenExistingKey_whenRemove_thenKeyIsDeleted() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();
        String key = "toRemove";
        cacheRepository.put(key, "value");

        // WHEN
        cacheRepository.remove(key);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void givenMultipleKeys_whenClear_thenAllEntriesRemoved() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");

        // WHEN
        cacheRepository.clear();

        // THEN
        assertEquals(null, cacheRepository.get("key1"));
        assertEquals(null, cacheRepository.get("key2"));
    }

    @Test
    void givenDuplicateKey_whenPut_thenValueIsOverwritten() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();
        String key = "duplicateKey";

        // WHEN
        cacheRepository.put(key, "firstValue");
        cacheRepository.put(key, "secondValue");
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals("secondValue", result);
    }

    @Test
    void givenCacheWithEntries_whenSizeCalled_thenReturnsCorrectCount() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");

        // WHEN
        int size = cacheRepository.size();

        // THEN
        assertEquals(2, size);
    }

    @Test
    void givenEmptyCache_whenSizeCalled_thenReturnsZero() {
        // GIVEN
        CacheRepository cacheRepository = new CacheRepository();

        // WHEN
        int size = cacheRepository.size();

        // THEN
        assertEquals(0, size);
    }
}
