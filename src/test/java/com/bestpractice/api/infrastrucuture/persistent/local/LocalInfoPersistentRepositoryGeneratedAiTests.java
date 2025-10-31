package com.bestpractice.api.infrastrucuture.persistent.local;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LocalInfoPersistentRepositoryGeneratedAiTests {

    private LocalInfoPersistentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new LocalInfoPersistentRepository();
    }

    @Test
    void testNewIdGeneratesUniqueId() {
        // GIVEN - prepare repository

        // WHEN - generate new ID
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN - IDs should be unique and valid UUIDs
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN - insert infos
        Info info1 = new Info();
        info1.setId(repository.newId());
        info1.setTitle("Title1");
        info1.setDescription("Description1");
        repository.insert(info1);

        Info info2 = new Info();
        info2.setId(repository.newId());
        info2.setTitle("Title2");
        info2.setDescription("Description2");
        repository.insert(info2);

        // WHEN - find all
        List<Info> allInfos = repository.findAll();

        // THEN - list should contain both infos
        assertEquals(2, allInfos.size());
        assertTrue(allInfos.contains(info1));
        assertTrue(allInfos.contains(info2));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN - insert info
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN - find by id
        Info found = repository.findById(id);

        // THEN - should return the correct info
        assertNotNull(found);
        assertEquals(id, found.getId());
        assertEquals("Title", found.getTitle());
        assertEquals("Description", found.getDescription());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN - no infos inserted

        // WHEN - find by non-existing id
        Info found = repository.findById("non-existing-id");

        // THEN - should return null
        assertNull(found);
    }

    @Test
    void testInsertAddsInfo() {
        // GIVEN - create info
        Info info = new Info();
        info.setId(repository.newId());
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN - insert info
        Info inserted = repository.insert(info);

        // THEN - inserted info should be returned and present in repository
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN - insert info
        Info info = new Info();
        String id = repository.newId();
        info.setId(id);
        info.setTitle("Old Title");
        info.setDescription("Old Description");
        repository.insert(info);

        // WHEN - replace info
        Info newInfo = new Info();
        newInfo.setId(id);
        newInfo.setTitle("New Title");
        newInfo.setDescription("New Description");
        repository.replace(id, newInfo);

        // THEN - repository should contain updated info
        Info found = repository.findById(id);
        assertEquals("New Title", found.getTitle());
        assertEquals("New Description", found.getDescription());
    }

    @Test
    void testReplaceThrowsExceptionWhenInfoNotFound() {
        // GIVEN - no infos inserted
        Info info = new Info();
        info.setId("some-id");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN & THEN - expect exception
        assertThrows(RuntimeException.class, () -> repository.replace("non-existing-id", info));
    }
}