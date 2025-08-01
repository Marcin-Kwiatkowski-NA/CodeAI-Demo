package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

class InfoPersistentRepositoryTest {

    private InfoPersistentRepository repository;
    private Info info;
    private String id;

    @BeforeEach
    void setUp() {
        repository = new InfoPersistentRepository();
        info = new Info("Test Info", "Test Description");
        id = repository.save(info);
    }

    @Test
    void testSaveAndRetrieveInfo() {
        // Act
        Info retrievedInfo = repository.getOne(id);

        // Assert
        assertEquals(info.getName(), retrievedInfo.getName());
        assertEquals(info.getDescription(), retrievedInfo.getDescription());
    }
}
