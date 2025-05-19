package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

class LocalCacheRepositoryGeneratedAiTests {

    @Test
    void constructorTest() {
        LocalCacheRepository repository = new LocalCacheRepository();
        assertNotNull(repository);
    }

    @Test
    void getCacheNameTest() {
        LocalCacheRepository repository = new LocalCacheRepository();
        assertEquals("defaultCache", repository.getCacheName());
    }

    @Test
    void setCacheNameTest() {
        LocalCacheRepository repository = new LocalCacheRepository();
        repository.setCacheName("newCacheName");
        assertEquals("newCacheName", repository.getCacheName());
    }
}
