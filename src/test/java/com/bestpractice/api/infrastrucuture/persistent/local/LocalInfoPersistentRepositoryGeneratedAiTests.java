package com.bestpractice.api.infrastructure.persistent.local;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bestpractice.api.infrastructure.entity.Info;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    public void testNewId() {
        // GIVEN
        // WHEN
        String id = repository.newId();
        // THEN
        assertTrue(id.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}"));
    }

    @Test
    public void testFindAll() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);
        // WHEN
        List<Info> infos = repository.findAll();
        // THEN
        assertEquals(1, infos.size());
    }

    @Test
    public void testFindById() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);
        // WHEN
        Info foundInfo = repository.findById("test-id");
        // THEN
        assertEquals("Test Title", foundInfo.getTitle());
    }

    @Test
    public void testFindByIdNotFound() {
        // GIVEN
        // WHEN
        Info info = repository.findById("non-existent-id");
        // THEN
        assertNull(info);
    }

    @Test
    public void testInsert() {
        // GIVEN
        Info info = new Info();
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        // WHEN
        Info insertedInfo = repository.insert(info);
        // THEN
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void testReplace() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Old Title");
        info.setDescription("Old Description");
        repository.insert(info);
        Info updatedInfo = new Info();
        updatedInfo.setId("test-id");
        updatedInfo.setTitle("New Title");
        updatedInfo.setDescription("New Description");
        // WHEN
        repository.replace("test-id", updatedInfo);
        // THEN
        assertEquals("New Title", repository.findById("test-id").getTitle());
    }

    @Test
    public void testReplaceNotFound() {
        // GIVEN
        Info info = new Info();
        info.setId("new-id");
        info.setTitle("New Title");
        info.setDescription("New Description");
        // WHEN & THEN
        try {
            repository.replace("non-existent-id", info);
        } catch (RuntimeException e) {
            assertEquals("Data does not exist.", e.getMessage());
        }
    }

    @Test
    public void testRemoveById() {
        // GIVEN
        Info info = new Info();
        info.setId("test-id");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        repository.insert(info);
        // WHEN
        boolean result = repository.removeById("test-id");
        // THEN
        assertTrue(result);
    }

    @Test
    public void testRemoveByIdNotFound() {
        // GIVEN
        // WHEN
        boolean result = repository.removeById("non-existent-id");
        // THEN
        assertTrue(result);
    }
}
