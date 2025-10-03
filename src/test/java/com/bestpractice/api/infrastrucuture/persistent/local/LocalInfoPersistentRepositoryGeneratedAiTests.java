package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
        // GIVEN no specific setup

        // WHEN generating a new ID
        String id1 = repository.newId();
        String id2 = repository.newId();

        // THEN the IDs should be unique and valid UUIDs
        assertNotNull(id1);
        assertNotNull(id2);
        assertNotEquals(id1, id2);
        assertDoesNotThrow(() -> UUID.fromString(id1));
        assertDoesNotThrow(() -> UUID.fromString(id2));
    }

    @Test
    void testFindAllReturnsInsertedInfos() {
        // GIVEN an inserted Info object
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN retrieving all infos
        List<Info> allInfos = repository.findAll();

        // THEN the list should contain the inserted info
        assertNotNull(allInfos);
        assertTrue(allInfos.contains(info));
    }

    @Test
    void testFindByIdReturnsCorrectInfo() {
        // GIVEN an inserted Info object with a known ID
        Info info = new Info();
        info.setId("123");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN finding by the known ID
        Info found = repository.findById("123");

        // THEN the found info should match the inserted one
        assertNotNull(found);
        assertEquals("123", found.getId());
        assertEquals("Title", found.getTitle());
    }

    @Test
    void testFindByIdReturnsNullWhenNotFound() {
        // GIVEN no matching ID in repository

        // WHEN finding by a non-existent ID
        Info found = repository.findById("non-existent");

        // THEN the result should be null
        assertNull(found);
    }

    @Test
    void testInsertAddsInfo() {
        // GIVEN a new Info object
        Info info = new Info();
        info.setId("abc");
        info.setTitle("Title");
        info.setDescription("Description");

        // WHEN inserting the info
        Info inserted = repository.insert(info);

        // THEN the returned info should be the same and present in repository
        assertEquals(info, inserted);
        assertTrue(repository.findAll().contains(info));
    }

    @Test
    void testReplaceUpdatesExistingInfo() {
        // GIVEN an existing Info object in repository
        Info original = new Info();
        original.setId("id1");
        original.setTitle("Old Title");
        original.setDescription("Old Description");
        repository.insert(original);

        String replaceId = "id1";
        Info updated = new Info();
        updated.setId(replaceId);
        updated.setTitle("New Title");
        updated.setDescription("New Description");

        // WHEN replacing the info
        repository.replace(replaceId, updated);

        // THEN the repository should contain the updated info
        Info found = repository.findById(replaceId);
        assertNotNull(found);
        assertEquals("New Title", found.getTitle());
        assertEquals("New Description", found.getDescription());
    }

    @Test
    void testReplaceThrowsWhenIdNotFound() {
        // GIVEN no matching ID in repository
        Info updated = new Info();
        updated.setId("id2");
        updated.setTitle("Title");
        updated.setDescription("Description");

        // WHEN & THEN replacing should throw RuntimeException
        assertThrows(RuntimeException.class, () -> repository.replace("id2", updated));
    }

    @Test
    void testRemoveByIdRemovesInfo() {
        // GIVEN an inserted Info object
        Info info = new Info();
        info.setId("id3");
        info.setTitle("Title");
        info.setDescription("Description");
        repository.insert(info);

        // WHEN removing by ID
        boolean result = repository.removeById("id3");

        // THEN the result should be true and the info should be removed
        assertTrue(result);
        assertNull(repository.findById("id3"));
    }

    @Test
    void testRemoveByIdReturnsTrueWhenNotFound() {
        // GIVEN no matching ID in repository

        // WHEN removing by a non-existent ID
        boolean result = repository.removeById("non-existent");

        // THEN the result should be true
        assertTrue(result);
    }
}
