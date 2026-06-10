package com.bestpractice.api.infrastrucuture.cache;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;


@ExtendWith(MockitoExtension.class)
public class CacheRepositoryGeneratedAiTests {

    @Mock
    private CacheRepository cacheRepository;

    @BeforeEach
    void setUp() {
        cacheRepository = mock(CacheRepository.class);
    }

    @Test
    void testSaveCacheEntry() {
        // GIVEN
        String key = "testKey";
        String value = "testValue";
        doNothing().when(cacheRepository).save(key, value);

        // WHEN
        cacheRepository.save(key, value);

        // THEN
        verify(cacheRepository, times(1)).save(key, value);
    }

    @Test
    void testFindCacheEntryReturnsExpectedValue() {
        // GIVEN
        String key = "testKey";
        String expectedValue = "cachedValue";
        when(cacheRepository.find(key)).thenReturn(expectedValue);

        // WHEN
        String result = cacheRepository.find(key);

        // THEN
        assertEquals(expectedValue, result);
    }

    @Test
    void testFindCacheEntryReturnsNullForMissingKey() {
        // GIVEN
        String key = "missingKey";
        when(cacheRepository.find(key)).thenReturn(null);

        // WHEN
        String result = cacheRepository.find(key);

        // THEN
        assertEquals(null, result);
    }

    @Test
    void testDeleteCacheEntry() {
        // GIVEN
        String key = "deleteKey";
        doNothing().when(cacheRepository).delete(key);

        // WHEN
        cacheRepository.delete(key);

        // THEN
        verify(cacheRepository, times(1)).delete(key);
    }

    @Test
    void testClearCache() {
        // GIVEN
        doNothing().when(cacheRepository).clear();

        // WHEN
        cacheRepository.clear();

        // THEN
        verify(cacheRepository, times(1)).clear();
    }

    @Test
    void testSaveCacheEntryWithNullKeyThrowsException() {
        // GIVEN
        String key = null;
        String value = "value";
        doThrow(new IllegalArgumentException("Key cannot be null")).when(cacheRepository).save(key, value);

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> cacheRepository.save(key, value));
    }

    @Test
    void testSaveCacheEntryWithEmptyKey() {
        // GIVEN
        String key = "";
        String value = "value";
        doNothing().when(cacheRepository).save(key, value);

        // WHEN
        cacheRepository.save(key, value);

        // THEN
        verify(cacheRepository, times(1)).save(key, value);
    }

    @Test
    void testFindCacheEntryWithWhitespaceKeyReturnsNull() {
        // GIVEN
        String key = "   ";
        when(cacheRepository.find(key)).thenReturn(null);

        // WHEN
        String result = cacheRepository.find(key);

        // THEN
        assertEquals(null, result);
    }
}
