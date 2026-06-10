package com.bestpractice.api.infrastrucuture.cache;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CacheRepositoryGeneratedAiTests {

    private CacheRepository cacheRepository;

    @BeforeEach
    void setUp() {
        cacheRepository = new CacheRepository();
    }

    @Test
    void testPutAndGetCacheValue() {
        // GIVEN
        String key = "testKey";
        String value = "testValue";

        // WHEN
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);

        // THEN
        assertNotNull(result);
        assertEquals(value, result);
    }

    @Test
    void testGetReturnsNullForMissingKey() {
        // GIVEN
        String key = "missingKey";

        // WHEN
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testPutNullKeyThrowsException() {
        // GIVEN
        String key = null;
        String value = "value";

        // WHEN & THEN
        assertThrows(NullPointerException.class, () -> cacheRepository.put(key, value));
    }

    @Test
    void testPutNullValueStoresNullSuccessfully() {
        // GIVEN
        String key = "nullValueKey";
        Object value = null;

        // WHEN
        cacheRepository.put(key, value);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testRemoveExistingKey() {
        // GIVEN
        String key = "removeKey";
        String value = "removeValue";
        cacheRepository.put(key, value);

        // WHEN
        cacheRepository.remove(key);
        Object result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testRemoveNonExistingKeyDoesNothing() {
        // GIVEN
        String key = "nonExistingKey";

        // WHEN
        cacheRepository.remove(key);

        // THEN
        Object result = cacheRepository.get(key);
        assertEquals(null, result);
    }

    @Test
    void testClearRemovesAllEntries() {
        // GIVEN
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");

        // WHEN
        cacheRepository.clear();

        // THEN
        assertEquals(null, cacheRepository.get("key1"));
        assertEquals(null, cacheRepository.get("key2"));
    }
}
