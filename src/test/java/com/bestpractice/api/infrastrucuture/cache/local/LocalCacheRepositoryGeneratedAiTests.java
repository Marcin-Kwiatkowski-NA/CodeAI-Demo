package com.bestpractice.api.infrastrucuture.cache.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

public class LocalCacheRepository {

    private Map<String, String> cache = new HashMap<>();

    public String getValue(String key) {
        return cache.get(key);
    }

    public void putValue(String key, String value) {
        cache.put(key, value);
    }

    public void removeValue(String key) {
        cache.remove(key);
    }
}