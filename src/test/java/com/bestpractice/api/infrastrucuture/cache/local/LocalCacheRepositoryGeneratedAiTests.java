package com.bestpractice.api.infrastrucuture.cache.local;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DisplayName.*;

@ExtendWith(DisplayNameTestExtension.class)
class LocalCacheRepositoryGeneratedAiTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void constructorTest() {
        LocalCacheRepository repository = new LocalCacheRepository();
        assertNotNull(repository);
    }
}
