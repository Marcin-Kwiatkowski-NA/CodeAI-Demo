package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LocalInfoPersistentRepositoryGeneratedAiTests.class)
class LocalInfoPersistentRepositoryGeneratedAiTests {
    private LocalInfoPersistentRepository repository;
    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
        repository.infos.clear();
    }
}
