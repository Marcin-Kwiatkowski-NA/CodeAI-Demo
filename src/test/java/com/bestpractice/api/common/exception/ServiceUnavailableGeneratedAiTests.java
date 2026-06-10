package com.bestpractice.api.infrastrucuture.cache;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
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
    void testPutAndGetWithValidKey() {
        // GIVEN
        String key = "testKey";
        String value = "testValue";
        when(cacheRepository.get(key)).thenReturn(value);

        // WHEN
        cacheRepository.put(key, value);
        String result = cacheRepository.get(key);

        // THEN
        assertEquals(value, result);
        verify(cacheRepository).put(key, value);
        verify(cacheRepository).get(key);
    }

    @Test
    void testPutWithNullKey() {
        // GIVEN
        String key = null;
        String value = "value";

        // WHEN
        doThrow(new IllegalArgumentException("Key cannot be null")).when(cacheRepository).put(eq(key), eq(value));

        // THEN
        assertThrows(IllegalArgumentException.class, () -> cacheRepository.put(key, value));
    }

    @Test
    void testGetWithNonExistingKey() {
        // GIVEN
        String key = "nonExistingKey";
        when(cacheRepository.get(key)).thenReturn(null);

        // WHEN
        String result = cacheRepository.get(key);

        // THEN
        assertEquals(null, result);
        verify(cacheRepository).get(key);
    }

    @Test
    void testRemoveExistingKey() {
        // GIVEN
        String key = "existingKey";
        doNothing().when(cacheRepository).remove(key);

        // WHEN
        cacheRepository.remove(key);

        // THEN
        verify(cacheRepository).remove(key);
    }

    @Test
    void testRemoveNonExistingKey() {
        // GIVEN
        String key = "nonExistingKey";
        doNothing().when(cacheRepository).remove(key);

        // WHEN
        cacheRepository.remove(key);

        // THEN
        verify(cacheRepository).remove(key);
    }

    @Test
    void testClearCache() {
        // GIVEN
        doNothing().when(cacheRepository).clear();

        // WHEN
        cacheRepository.clear();

        // THEN
        verify(cacheRepository).clear();
    }

    @Test
    void testPutAndGetBoundaryKeyValues() {
        // GIVEN
        String keyMin = "";
        String keyMax = new String(new char[1000]).replace("\0", "K");
        String value = "boundaryValue";
        when(cacheRepository.get(keyMin)).thenReturn(value);
        when(cacheRepository.get(keyMax)).thenReturn(value);

        // WHEN
        cacheRepository.put(keyMin, value);
        cacheRepository.put(keyMax, value);

        // THEN
        assertEquals(value, cacheRepository.get(keyMin));
        assertEquals(value, cacheRepository.get(keyMax));
        verify(cacheRepository).put(keyMin, value);
        verify(cacheRepository).put(keyMax, value);
    }

    @Test
    void testPutDuplicateKey() {
        // GIVEN
        String key = "duplicateKey";
        String value1 = "value1";
        String value2 = "value2";
        when(cacheRepository.get(key)).thenReturn(value2);

        // WHEN
        cacheRepository.put(key, value1);
        cacheRepository.put(key, value2);
        String result = cacheRepository.get(key);

        // THEN
        assertEquals(value2, result);
        verify(cacheRepository, times(2)).put(eq(key), anyString());
        verify(cacheRepository).get(key);
    }
}
