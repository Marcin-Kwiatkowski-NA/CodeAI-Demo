package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.infrastrucuture.persistent.local.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class LocalInfoPersistentRepositoryTest {

    private InfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InfoPersistentRepository();
    }

    @Test
    void testAddInfo() {
        // Add an info
        repository.addInfo("Test Info");

        // Verify that the info was added
        List<String> infos = repository.getInfos();
        assert infos.contains("Test Info") : "Info not added";
    }

    @Test
    void testGetInfos() {
        // Add an info
        repository.addInfo("Test Info");

        // Get all infos
        List<String> infos = repository.getInfos();

        // Assert that the list is not empty
        assert !infos.isEmpty() : "List is empty";

        // Assert that the list contains the added info
        assert infos.contains("Test Info") : "Info not in list";
    }
}
