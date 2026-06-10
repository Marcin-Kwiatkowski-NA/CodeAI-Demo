package com.bestpractice.api.infrastrucuture.cache;

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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        String actualValue = cacheRepository.get(key);

        // THEN
        assertEquals(value, actualValue);
    }

    @Test
    void testGetValueWhenKeyDoesNotExistShouldReturnNull() {
        // GIVEN
        String key = "nonExistingKey";

        // WHEN
        String actualValue = cacheRepository.get(key);

        // THEN
        assertNull(actualValue);
    }

    @Test
    void testPutWithNullKeyShouldNotThrowException() {
        // GIVEN
        String key = null;
        String value = "value";

        // WHEN & THEN
        assertThatThrownBy(() -> cacheRepository.put(key, value))
                .doesNotThrowAnyException();
    }

    @Test
    void testPutWithNullValueShouldNotThrowException() {
        // GIVEN
        String key = "key";
        String value = null;

        // WHEN & THEN
        assertThatThrownBy(() -> cacheRepository.put(key, value))
                .doesNotThrowAnyException();
    }

    @Test
    void testRemoveExistingKey() {
        // GIVEN
        String key = "key";
        String value = "value";
        cacheRepository.put(key, value);

        // WHEN
        cacheRepository.remove(key);
        String actualValue = cacheRepository.get(key);

        // THEN
        assertNull(actualValue);
    }

    @Test
    void testRemoveNonExistingKeyShouldNotThrowException() {
        // GIVEN
        String key = "nonExistingKey";

        // WHEN & THEN
        assertThatThrownBy(() -> cacheRepository.remove(key))
                .doesNotThrowAnyException();
    }

    @Test
    void testClearShouldRemoveAllEntries() {
        // GIVEN
        cacheRepository.put("key1", "value1");
        cacheRepository.put("key2", "value2");

        // WHEN
        cacheRepository.clear();

        // THEN
        assertNull(cacheRepository.get("key1"));
        assertNull(cacheRepository.get("key2"));
    }
}
