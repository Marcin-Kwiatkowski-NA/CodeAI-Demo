package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocalInfoPersistentRepositoryTests {

    private LocalInfoPersistentRepository repository;
    private Info info;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
        info = new Info();
    }

    @Test
    void newId() {
        String id = repository.newId();
        assertNotNull(id, "New ID should not be null");
        assertTrue(id.length() > 0, "New ID should not be empty");
    }

    @Test
    void findAll() {
        // GIVEN: Initially, the list is empty
        assertEmpty(repository.findAll());

        // GIVEN: Add an info
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // THEN: The list should contain the added info
        List<Info> allInfos = repository.findAll();
        assertEquals(1, allInfos.size(), "Should contain one info");
        assertTrue(allInfos.contains(info), "Should contain the inserted info");
    }

    @Test
    void findById() {
        // GIVEN: Add an info
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // THEN: Find by ID should return the correct info
        Info foundInfo = repository.findById("testId");
        assertNotNull(foundInfo, "Info should not be null");
        assertEquals("testId", foundInfo.getId(), "ID should match");
        assertEquals("Test Title", foundInfo.getTitle(), "Title should match");
        assertEquals("Test Description", foundInfo.getDescription(), "Description should match");
    }

    @Test
    void insert() {
        // GIVEN: Initially, the list is empty
        assertEmpty(repository.findAll());

        // GIVEN: Add an info
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        Info insertedInfo = repository.insert(info);

        // THEN: The list should contain the inserted info
        List<Info> allInfos = repository.findAll();
        assertEquals(1, allInfos.size(), "Should contain one info");
        assertTrue(allInfos.contains(info), "Should contain the inserted info");
        assertEquals(info.getId(), insertedInfo.getId(), "ID should match");
    }

    @Test
    void replace() {
        // GIVEN: Add an info
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);

        // GIVEN: Find by ID
        Info foundInfo = repository.findById("testId");

        // WHEN: Replace the info with a new one
        Info newInfo = new Info();
        newInfo.setId("newTestId");
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");
        repository.replace("testId", newInfo);

        // THEN: The list should contain the replaced info
        List<Info> allInfos = repository.findAll();
        assertEquals(1, allInfos.size(), "Should contain one info");
        assertTrue(allInfos.contains(newInfo), "Should contain the replaced info");
        assertEquals("newTestId", newInfo.getId(), "ID should match");
        assertEquals("New Title", newInfo.getTitle(), "Title should match");
        assertEquals("New Description", newInfo.getDescription(), "Description should match");
    }
}
